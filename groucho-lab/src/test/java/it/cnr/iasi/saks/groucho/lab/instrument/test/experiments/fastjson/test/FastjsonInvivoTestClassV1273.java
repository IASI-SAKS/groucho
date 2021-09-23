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
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273.*;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class FastjsonInvivoTestClassV1273 {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	/*
	- Fastjson Version - 1.2.73
	- Class Under Test - JSON
	- Method Under Test - ToJSONString()
	- Flaky Test(s) - Issue1363#test_for_issue, Issue1363#test_for_issue_1
	- Context input: DataSimpleVO(String, Object), DataSimpleVO(String, Object);
	*/
	public boolean invivoToJsonString(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		//Get Context info
		testToJsonString();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	private void testToJsonString() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		//RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			//shield.applyCheckpoint(input);
			Issue1363 unitTest = new Issue1363();
			unitTest.mockConfigure();
			//unitTest.configure(input);
			unitTest.test_for_issue();
			System.out.println("Issue1363#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1363#test_for_issue failed.");
		}
//		finally {
//			shield.applyRollback(input);
//		}
		try {
			//shield.applyCheckpoint(input);
			Issue1363 unitTest = new Issue1363();
			unitTest.mockConfigure();
			//unitTest.configure(input);
			unitTest.test_for_issue_1();
			System.out.println("Issue1363#test_for_issue_1 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1363#test_for_issue_1 failed.");
		}
//		finally {
//			shield.applyRollback(input);
//		}
	}

	/*
	- Fastjson Version - 1.2.73
	- Class Under Test - JSON
	- Method Under Test - ToJSONString()
	- Flaky Test(s) - Issue1363#test_for_issue, Issue1363#test_for_issue_1
	- Context input: JSONObject(String, String), JSONArray[String];
	*/
		public boolean invivoToJsonString2(Context c) throws InvocationTargetException{
			this.configure();
			TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
			//Get Context info
			testToJsonString2();
			TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
			setExitStatus();
			return getExitStatus();
		}

	private void testToJsonString2() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			Issue1492 unitTest = new Issue1492();
			unitTest.mockConfigure();
			//unitTest.configure(input);
			unitTest.test_for_issue();
			System.out.println("Issue1492#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1492#test_for_issue failed.");
		}
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
