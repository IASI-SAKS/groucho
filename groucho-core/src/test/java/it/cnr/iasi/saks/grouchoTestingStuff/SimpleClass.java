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

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class SimpleClass {
	private Random rnd;

	public SimpleClass (){
		this.rnd = new Random();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void simpleCPUwaste(){
		int i = 1000;
		while (i>=0){
			i--;
		}
	}

	public void wasteCPU(){
		int size = 1000;		
		Vector<Integer> v = new Vector<Integer>();
		for (int i = 0; i < size; i++) {
			int element = this.rnd.nextInt();
			v.add(element);			
		}
		Collections.sort(v);
	}

	public void ingnoreMe (){
		System.out.println("You should not have read this!!!");
	}
}