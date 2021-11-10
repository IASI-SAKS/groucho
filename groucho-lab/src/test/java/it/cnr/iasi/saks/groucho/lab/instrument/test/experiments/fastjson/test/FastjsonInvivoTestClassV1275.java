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
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1275.Issue2428;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1275.TypeUtilsTest;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils.InputGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FastjsonInvivoTestClassV1275 {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	/*
	- Fastjson Version - 1.2.75
	- Flaky Test(s) - Issue2428#test_for_issue
	*/
	public boolean invivoTest2428(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Issue2428 unitTest = new Issue2428();
			unitTest.configure(InputGenerator.generateSimpleHashMap(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue2428#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue2428#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoTypeUtils(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			TypeUtilsTest unitTest = new TypeUtilsTest();
			unitTest.configure(InputGenerator.generateDate());
			unitTest.test_cast_to_Timestamp_1970_01_01_00_00_00();
			System.out.println("TypeUtilsTest#test_cast_to_Timestamp_1970_01_01_00_00_00 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("TypeUtilsTest#test_cast_to_Timestamp_1970_01_01_00_00_00 failed.");
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
