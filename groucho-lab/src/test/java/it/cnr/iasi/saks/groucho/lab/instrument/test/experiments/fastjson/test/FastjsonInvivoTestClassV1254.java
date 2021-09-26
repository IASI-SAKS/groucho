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

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils.*;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254.*;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

public class FastjsonInvivoTestClassV1254 {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Flaky Test(s) - JSONPParseTest2, JSONPParseTest3
	- Context input: byte[] array
	*/
	public boolean invivoParseObject(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		byte[] input =  (byte[]) c.getOtherReferencesInContext().get(0);
		testParseObject(input);
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*Runs some flaky tests that exercise JSON.parseObject() [V. 1.2.54]*/
	private void testParseObject(byte[] input) {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(input);
			JSONPParseTest2 unitTest = new JSONPParseTest2();
			unitTest.configureArray(input);
			unitTest.test_f();
			System.out.println("JSONPParseTest2#test_f passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPParseTest2#test_f failed.");
		}finally {
			shield.applyRollback(input);
		}
		try {
			shield.applyCheckpoint(input);
			JSONPParseTest3 unitTest = new JSONPParseTest3();
			unitTest.configureArray(input);
			unitTest.test_f();
			System.out.println("JSONPParseTest3#test_f passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPParseTest3#test_f failed.");
		}finally {
			shield.applyRollback(input);
		}
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Flaky Test(s) - DateTest5_iso8601#test_date, DateTest4_indian#test_date, Issue1679#test_for_issue
	- Context input: Date
	*/
	public boolean invivoParseObject2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		//Get (TimeZone, Locale) from C
		testParseObject2();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	private void testParseObject2() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		//RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
	 		//shield.applyCheckpoint(input);
			DateTest5_iso8601 unitTest = new DateTest5_iso8601();
			unitTest.configure(InputGenerator.generateDate());
			unitTest.test_date();
			System.out.println("DateTest5_iso8601#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest5_iso8601#test_date failed.");
		}
//		finally {
//			shield.applyRollback(input);
//		}
		try {
			// shield.applyCheckpoint(input);
			DateTest4_indian unitTest = new DateTest4_indian();
			//unitTest.configure(InputGenerator.generateDate());
			unitTest.test_date();
			System.out.println("DateTest4_indian#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest4_indian#test_date failed.");
		}
//		finally {
//			shield.applyRollback(input);
//		}
		try {
			// shield.applyCheckpoint(input);
			Issue1679 unitTest = new Issue1679();
			unitTest.configure(InputGenerator.generateDate());
			unitTest.test_for_issue();
			System.out.println("Issue1679#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1679#test_for_issue failed.");
		}
//		finally {
//			shield.applyRollback(input);
//		}
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Flaky Test(s) - Issue1480#test_for_issue
	- Context input: HashMap<Integer, Integer>
	*/
	public boolean invivoParseObject3(Context c) throws InvocationTargetException, JsonProcessingException {
		this.configure();
		byte[] input =  (byte[]) c.getOtherReferencesInContext().get(0);
		HashMap<String, String> contextMap = InputGenerator.generateMap(input);

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(input);
			Issue1480 unitTest = new Issue1480();
			unitTest.configure(contextMap);
			unitTest.test_for_issue();
			System.out.println("Issue1480#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1480#test_for_issue failed.");
		}finally {
			shield.applyRollback(input);
		}
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Flaky Test(s) - Issue1298#test_for_issue,  Issue1298#test_for_issue_1
	- Context input: TimeZone, Locale
	*/
	public boolean invivoToJsonString(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		//Get timezone info from the context
		testToJsonString();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	private void testToJsonString() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		Date input = InputGenerator.generateDate();

		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(input);
			Issue1298 unitTest = new Issue1298();
			unitTest.configure(input);
			unitTest.test_for_issue();
			System.out.println("Issue1298#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1298#test_for_issue  failed.");
		}
		finally {
			shield.applyRollback(input);
		}
		try {
			shield.applyCheckpoint(input);
			Issue1298 unitTest = new Issue1298();
			unitTest.configure(input);
			unitTest.test_for_issue_1();
			System.out.println("Issue1298#test_for_issue_1 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1298#test_for_issue_1  failed.");
		}
		finally {
			shield.applyRollback(input);
		}
		try {
			shield.applyCheckpoint(input);
			Issue1977 unitTest = new Issue1977();
			unitTest.configure(input);
			unitTest.test_for_issue();
			System.out.println("Issue1977#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1977#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(input);
		}
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Flaky Test(s) - DateTest#test_date
	- Context input: Date
	*/
	public boolean invivoToJsonString2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		//Get timezone info from the context
		testToJsonString2();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*Runs some flaky tests that exercise JSON.toJSONString() [V. 1.2.54]*/
	private void testToJsonString2() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			DateTest unitTest = new DateTest();
			Date date = new Date();
			System.out.println("date" +date);
			unitTest.configure(date);
			unitTest.test_date();
			System.out.println("DateTest#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest#test_date failed.");
		}
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - JSONReader
	- Method Under Test - ReadObject()
	- Flaky Test(s) - DateTest_tz#test_codec
	- Context input: TimeZone, Locale, JSONReader?
	*/
	public boolean invivoReadObject(Context c) throws InvocationTargetException {
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			DateTest_tz unitTest = new DateTest_tz();
			unitTest.configure(InputGenerator.generateDate());
			unitTest.test_codec();
			System.out.println("DateTest_tz#test_codec passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest_tz#test_codec failed.");
		}
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - DefaultJSONParser
	- Method Under Test - parseArray()
	- Context input: TimeZone
	*/
	public boolean invivoParseArray(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();
			unitTest.configure(InputGenerator.generateDate());
			unitTest.test_7();
			System.out.println("DefaultExtJSONParser_parseArray#test_7 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_7 failed.");
		}

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - DefaultJSONParser
	- Method Under Test - parse()
	- Context input: Date
	*/
	public boolean invivoParse(Context c) throws InvocationTargetException {
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		try {
			DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();
			unitTest.configure(InputGenerator.generateDate());
			unitTest.test_8();
			System.out.println("DefaultExtJSONParser_parseArray#test_8 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_8 failed.");
		}

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
	- TODO - Adapt Issue1177_2#test_for_issue
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Fastjson Version - 1.2.54
	- Context input: byte[]
	*/
	public boolean invivoToJsonString3(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testToJsonString3();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	private void testToJsonString3() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			Issue1177_2 unitTest = new Issue1177_2();
			unitTest.mockConfigure();
			unitTest.test_for_issue();
			System.out.println("Issue1177_2#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1177_2#test_for_issue failed.");
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
