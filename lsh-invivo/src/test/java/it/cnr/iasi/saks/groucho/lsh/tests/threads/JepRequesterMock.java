package it.cnr.iasi.saks.groucho.lsh.tests.threads;

import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.jep.threads.LSHInvivoJepWorker;

public class JepRequesterMock implements Runnable {
	private LSHInvivoJepWorker worker;
	private volatile boolean completed; 
	
	public JepRequesterMock(LSHInvivoJepWorker worker) {
		this.worker = worker;
		
		this.completed = false;
	}
	
	@Override
	public void run() {
		long millis = ((long)(Math.random()*10))*1000;
//		System.out.println("Sleeping for:"+millis);

		int typeOfRequest = ((int)(Math.random()*3))%3;
//		System.out.println("Request:"+typeOfRequest);
		
		try {
			Thread.sleep(millis);
			switch (typeOfRequest) {
			case 0:
				this.worker.isStateUnknown("carvedState");				
				break;
			case 1:
				this.worker.markState("carvedState");
				break;
			case 2:
				this.worker.resetStateObserver();				
				break;
			default:
				this.worker.isStateUnknown("carvedState");				
				break;
			}
			this.completed = true;
		} catch (InterruptedException e) {
			this.completed = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LSHException e) {
			this.completed = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized boolean isRequestCompleted() {
		return this.completed;
	}

}
