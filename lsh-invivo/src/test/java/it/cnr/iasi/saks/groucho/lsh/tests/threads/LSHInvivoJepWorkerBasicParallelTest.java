/* 
 * This file is part of the GROUCHO project.
 * 
 * GROUCHO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GROUCHO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GROUCHO.  If not, see <https://www.gnu.org/licenses/>
 *
 */
package it.cnr.iasi.saks.groucho.lsh.tests.threads;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		jepWorker.stopWorker();
	}

}
