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
package it.cnr.iasi.saks.grouchoTestingStuff;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;

public class SimpleThread implements Runnable {
	
	private boolean isAlive;
	private SimpleClass c;
	private static int COUNTER;
	private int id;

	public SimpleThread(){
		this.genID();
		System.out.println("Created SimpleThread: " + this.id);
	}

	@Override	
	public void run() {
		System.out.println("Launched SimpleThread: " + this.id);
		this.isAlive = true;
		this.c = new SimpleClass();
		while (this.isAlive){
			this.doSomething();
			double rnd = Math.random();
			this.isAlive = (rnd >= 0.5f);
		}	
		System.out.println("Closing SimpleThread: " + this.id);
	}
	
	private synchronized void genID(){
		COUNTER++;
		this.id = COUNTER;
	}
	
	@TestableInVivo(invivoTestClass = "it.cnr.iasi.saks.groucho.performanceOverheadTest.DummyInvivoTest", invivoTest = "fooTest")
	private void doSomething() {
		this.c = new SimpleClass();
		this.c.wasteCPU();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}

