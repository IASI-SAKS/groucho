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
import java.lang.reflect.InvocationTargetException;
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
	Class Under Test - JSON
	Method Under Test - parseObject()
	Required Fastjson Version - 1.2.54
	*/
	public boolean invivoTestMethod(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testParseObject();
		setExitStatus();
		return getExitStatus();
	}

	/*
	Class Under Test - JSON
	Method Under Test - toJSONString()
	Required Fastjson Version - 1.2.54
	*/
	public boolean invivoTestMethod2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testToJSONString();
		setExitStatus();
		return getExitStatus();
	}

	/*Runs all the unit tests that exercise JSON.parseObject() [V. 1.2.54]*/
	private void testParseObject() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		JSONPParseTest2 unitTest = new JSONPParseTest2();
		JSONPParseTest3 unitTest2 = new JSONPParseTest3();
		DateTest4_indian unitTest3 = new DateTest4_indian();
		DateTest5_iso8601 unitTest4 = new DateTest5_iso8601();

		try{
			unitTest.test_f();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
			System.out.println(unitTest.getClass() + " failed.");
		}
		try{
			unitTest2.test_f();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
			System.out.println(unitTest2.getClass() + " failed.");
		}
		try{
			unitTest3.test_date();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
			System.out.println(unitTest3.getClass() + " failed.");
		}
		try{
			unitTest4.test_date();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
			System.out.println(unitTest4.getClass() + " failed.");
		}
		System.out.println("... done!");
	}

	/*Runs all the unit tests that exercise JSON.toJSONString() [V. 1.2.54]*/
	private void testToJSONString() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		Issue1298 unitTest = new Issue1298();
		try{
			unitTest.test_for_issue();
		}catch(Throwable t) {
			//resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
			System.out.println(unitTest.getClass() + " failed.");
		}
		try{
			unitTest.test_for_issue_1();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
			System.out.println(unitTest.getClass() + " failed.");
		}

		System.out.println("... done!");
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
