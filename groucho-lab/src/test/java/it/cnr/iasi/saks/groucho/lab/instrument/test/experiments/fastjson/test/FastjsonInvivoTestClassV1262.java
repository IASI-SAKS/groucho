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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1263.Bug_for_smoothrat6;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils.InputGenerator;

import java.util.ArrayList;
import java.util.List;

public class FastjsonInvivoTestClassV1262 {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	public boolean invivoBug_for_smoothrat6(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Bug_for_smoothrat6 unitTest = new Bug_for_smoothrat6();
			unitTest.configure(InputGenerator.generateSimpleHashMap(contextData));
			unitTest.test_set();
			System.out.println("Bug_for_smoothrat6#test_set passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Bug_for_smoothrat6#test_set failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	private String getCurrentMethodName() {
		String nameofCurrMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
		return nameofCurrMethod;
	}

	private void configure() {
		setExitStatus();
		FAILED_TESTS.clear();
	}


	public synchronized static boolean getExitStatus(){
		return EXIT_STATUS;
	}

	public synchronized static void resetExitStatus(){
		EXIT_STATUS = false;
		FAILED_TESTS.clear();
	}

	protected synchronized static void setExitStatus(){
		EXIT_STATUS = true;
	}

	public synchronized static List<String> getFailedTests() {
		List<String> list = new ArrayList<String>();
		for (String failedTest : FAILED_TESTS) {
			list.add(failedTest);
		}
		return list;
	}

}
