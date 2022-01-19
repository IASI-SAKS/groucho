package it.cnr.iasi.saks.groucho.lsh.tests.threads;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.jep.threads.LSHInvivoJepWorker;

public class LSHInvivoJepWorkerBasicParallelTest {
	
	private final static int NUMBER_OF_CLENTS = 30;		

	@Test
	public void simpleParallelTest() {
		List<JepRequesterMock> poolOfRequesters = new ArrayList<JepRequesterMock>(NUMBER_OF_CLENTS);
		
		LSHInvivoJepWorker jepWorker = new LSHInvivoJepWorker();
		Thread workerThread = new Thread(jepWorker);

		for (int i=0; i < NUMBER_OF_CLENTS; i++) {
			JepRequesterMock requester = new JepRequesterMock(jepWorker);
			
			poolOfRequesters.add(requester);
		}
		
		workerThread.start();
		for (JepRequesterMock requester : poolOfRequesters) {
			Thread requesterThread = new Thread(requester);
			requesterThread.start();
		}
		
		int numberOfRequestersCompleted = 0;
		int counterToForceExit = 30;
		while ((numberOfRequestersCompleted != NUMBER_OF_CLENTS) && (counterToForceExit > 0)) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean exitus = true;
			for (int i=0; (i < NUMBER_OF_CLENTS) && (exitus); i++) {
				boolean completed = poolOfRequesters.get(i).isRequestCompleted();				
				exitus = (exitus) && (completed);
			}	
			
			if (!exitus) {
				numberOfRequestersCompleted = 0;				
			} else {
				numberOfRequestersCompleted = NUMBER_OF_CLENTS;
			}
			
			counterToForceExit--;
		}
		
		assertEquals(NUMBER_OF_CLENTS,numberOfRequestersCompleted);
	}

}
