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
package it.cnr.iasi.saks.groucho.threadSignalTests;

import it.cnr.iasi.saks.groucho.callback.AbstractGovernanceManager;
import it.cnr.iasi.saks.groucho.callback.GovernanceManagerFactory;
import it.cnr.iasi.saks.groucho.instrument.ThreadHarnessCallbackInvoker;

public class SimpleThread implements Runnable{
	
	private boolean isAlive;
	private SimpleClass c;
	private static int COUNTER;
	private int id;

	public SimpleThread(){
		this.genID();
		System.out.println("Created SimpleThread: " + this.id);
		this.c = new SimpleClass();
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_checkInTheConstructors();
// *************************************************************		
	}
	
	@Override
	public void run() {
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_IncThread();
// *************************************************************		
		System.out.println("Launched SimpleThread: " + this.id);
		this.isAlive = true;
		while (this.isAlive){
			this.doSomething();
			double rnd = Math.random();
			this.isAlive = (rnd >= 0.5f);
		}	
		System.out.println("Closing SimpleThread: " + this.id);
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_decThread();
// *************************************************************		
	}
	
	private synchronized void genID(){
		COUNTER++;
		this.id = COUNTER;
	}
	
	private void doSomething() {
		this.c = new SimpleClass();
		this.c.wasteCPU();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double rnd = Math.random();
		if (rnd <= 0.5f){
			AbstractGovernanceManager gm = GovernanceManagerFactory.getInstance().getGovernanceManager();
			gm.runInvivoTestingSession(id);
		}
	}

	
}

class SimpleClass {
	public SimpleClass (){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_checkInTheConstructors();
// *************************************************************		
	}

	public void wasteCPU(){
		int i = 1000;
		while (i>=0){
			i--;
		}
	}

	public void ingnoreMe (){
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_checkInTheConstructors();
// *************************************************************		
	}
}