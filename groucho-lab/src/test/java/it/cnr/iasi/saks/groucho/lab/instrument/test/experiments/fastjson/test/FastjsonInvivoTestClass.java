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
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import com.alibaba.json.bvt.jsonp.*;
import com.alibaba.json.bvt.serializer.date.*;
import com.alibaba.json.bvt.issue_1200.*;
import com.alibaba.json.bvt.date.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastjsonInvivoTestClass {
	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	private String backupDir;
	private Map<String, String> backupDirMap = null;
	private boolean flagCopied = false;

	/*
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Fastjson Version - 1.2.54
	*/
	public boolean invivoTestMethod(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllParseObject();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Fastjson Version - 1.2.54
	*/
	public boolean invivoTestMethod2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllToJSONString();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}


	/*Runs all the flaky tests that exercise JSON.parseObject() [V. 1.2.54]*/
	private void testAllParseObject() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		List<Method> tests = new ArrayList<Method>();
		try {
			tests.add(JSONPParseTest2.class.getDeclaredMethod("test_f"));
			tests.add(JSONPParseTest3.class.getDeclaredMethod("test_f"));
			tests.add(DateTest4_indian.class.getDeclaredMethod("test_date"));
			tests.add(DateTest5_iso8601.class.getDeclaredMethod("test_date"));
		}catch(Throwable t){
			System.out.println(t.getMessage());
		}
		for (Method m : tests){
			try {
				m.invoke(m.getDeclaringClass().newInstance());
				System.out.println(m.getName() + " passed.");
			} catch(Throwable t) {
				FAILED_TESTS.add(mName);
				resetExitStatus();
				System.out.println(t.getMessage());
				System.out.println(m.getName() + " failed.");
			}
		}
	}

	/* Runs all the flaky tests that exercise JSON.toJSONString() [V. 1.2.54] */
	private void testAllToJSONString() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		List<Method> tests = new ArrayList<Method>();
		try {
			tests.add(Issue1298.class.getDeclaredMethod("test_for_issue"));
			tests.add(Issue1298.class.getDeclaredMethod("test_for_issue_1"));
			tests.add(DateTest.class.getDeclaredMethod("test_date"));
			// ...
		}catch(Throwable t){
			System.out.println(t.getMessage());
		}
		for (Method m : tests){
			try {
				m.invoke(m.getDeclaringClass().newInstance());
				System.out.println(m.getName() + " passed.");
			} catch(Throwable t) {
				FAILED_TESTS.add(mName);
				resetExitStatus();
				System.out.println("message" +t.getMessage());
				System.out.println(m.getName() + " failed.");
			}
		}
	}

	private String getCurrentMethodName() {
		String nameofCurrMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
		return nameofCurrMethod;
	}

	private void configure() {
		setExitStatus();
		FAILED_TESTS.clear();
		this.backupDir = null;
		this.backupDirMap = new HashMap<String, String>();
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
