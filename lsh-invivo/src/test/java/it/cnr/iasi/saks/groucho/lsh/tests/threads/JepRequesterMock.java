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
