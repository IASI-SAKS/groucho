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
package it.cnr.iasi.saks.groucho.lab.instrument.test.utils;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;

public class SimpleClass {

	
	public Object dummyMethod(int i, Object foo) {
		this.printMessage("dummyMethod");
		Object obj = foo;
		if (i%2 == 0) {
			obj = new Object();
		}
		return obj;	
	}
	
	public int otherDummyMethod(boolean b, int i) {
		this.printMessage("otherDummyMethod");
		int j = i;
		if (b) {
			j = i+1;
		}
		return j;	
	}
	
	@TestableInVivo(invivoTest = "invivoTestMethod", invivoTestClass = "it.cnr.iasi.saks.groucho.lab.instrument.test.utils.FooInvivoTestClass")
	private void printMessage(String method){
		System.out.println("Invoked: "+SimpleClass.class.getCanonicalName()+":"+ method);
	}
}
