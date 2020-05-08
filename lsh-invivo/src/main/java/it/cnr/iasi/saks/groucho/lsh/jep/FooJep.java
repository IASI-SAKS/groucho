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
package it.cnr.iasi.saks.groucho.lsh.jep;

import java.io.FileNotFoundException;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import jep.Interpreter;
import jep.JepException;
import jep.SubInterpreter;

public class FooJep {

	private final String DUMMY_CARVED_STATE = "{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}";

	private void myFirstTest() throws JepException, FileNotFoundException {
		Interpreter interp = new SubInterpreter();

		String pythonArgvAsString = "['foo-thisInRequiredBy-py/lshinvivo.py','staterepresentation']"; 
		interp.eval("import sys");
		interp.eval("sys.argv = " + pythonArgvAsString);
		interp.eval("argv = "  + pythonArgvAsString);
          
		interp.runScript("py/lshinvivo.py");
		
		boolean runInvivoFlag = interp.getValue("run_invivo", Integer.class) != 0;
		System.out.println(runInvivoFlag);
//		interp.exec("import somePyModule");
//		// any of the following work, these are just pseudo-examples
//
//		// using exec(String) to invoke methods
//		interp.set("arg", obj);
//		interp.exec("x = somePyModule.foo1(arg)");
//		Object result1 = interp.getValue("x");
//
//		// using getValue(String) to invoke methods
//		Object result2 = interp.getValue("somePyModule.foo2()");
//
//		// using invoke to invoke methods
//		interp.exec("foo3 = somePyModule.foo3");
//		Object result3 = interp.invoke("foo3", obj);
		interp.close();
	}

	private void simpleLSHInvivoJepTest() throws LSHException{
		StateObserver obs = new LSHInvivoJep();
		obs.resetStateObserver();
		obs.isStateUnknown(DUMMY_CARVED_STATE);
	}
	
	
	public static void main(String args[]) {
		FooJep f = new FooJep();
		try {
//			f.myFirstTest();
			f.simpleLSHInvivoJepTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
