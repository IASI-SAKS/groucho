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

import org.junit.Ignore;
import org.junit.Test;

public class SimpleThreadTest {

	@Ignore
	@Test
	public void firstSimpleTestLong(){
		this.firstSimpleTest(50);
	}
	
	@Test
	public void firstSimpleTest(){
		this.firstSimpleTest(10);
	}

	private void firstSimpleTest(int size){
		long startTS = System.currentTimeMillis();
		double rnd;
		Thread t = null;
		for (int i = 0; i < size; i++) {
			rnd = Math.random();
			if (rnd <= 0.9f){
				SimpleThread s = new SimpleThread();
				t = new Thread(s);
				t.start();
			}			
		}
		try {
			System.out.println("------------------------- Join Invoked");
			if (t!=null){
				t.join();
			}
			System.out.println("------------------------- done");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long finishTS = System.currentTimeMillis();
		float elapsedSec = (finishTS - startTS)/1000;

		System.out.println("Completed in almost: " + elapsedSec + " sec.");		
		
	}
}
