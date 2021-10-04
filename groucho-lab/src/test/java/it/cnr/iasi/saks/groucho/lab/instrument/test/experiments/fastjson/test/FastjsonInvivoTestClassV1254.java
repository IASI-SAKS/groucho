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
	- Context input: byte[] array
	- Flaky Test(s) - JSONPParseTest2, JSONPParseTest3
	*/
	public boolean invivoJSONParse(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		byte[] input =  (byte[]) c.getOtherReferencesInContext().get(0);
		testJSONParse(input);
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	private void testJSONParse(byte[] input) {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(input);
			JSONPParseTest2 unitTest = new JSONPParseTest2();
			unitTest.configure(input);
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
	- Flaky Test(s) - Issue1480#test_for_issue
	- Context input: HashMap<Integer, Integer>
	*/
	public boolean invivoParseObject3(Context c) throws InvocationTargetException, JsonProcessingException {
		this.configure();
		byte[] input =  (byte[]) c.getOtherReferencesInContext().get(0);
		HashMap<String, String> contextMap = InputGenerator.generateHashMap(input);

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
	- Context input: Date
	- Flaky Test(s) - Issue1298#test_for_issue,  Issue1298#test_for_issue_1,
					  Issue1977#test_for_issue, DateTest#test_date,
					  DateTest5_iso8601#test_date, DateTest4_indian#test_date,
					  Issue1679#test_for_issue, DateTest_tz#test_codec,
					  DefaultExtJSONParser_parseArray#test_8
	*/
	public boolean invivoTestDate(Context c) {
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testDate();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	private void testDate() {
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
			System.out.println("Issue1298#test_for_issue failed.");
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
		try {
			shield.applyCheckpoint(input);
			DateTest unitTest = new DateTest();
			unitTest.configure(input);
			unitTest.test_date();
			System.out.println("DateTest#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest#test_date failed.");
		}
		finally {
			shield.applyRollback(input);
		}
		try {
			shield.applyCheckpoint(input);
			DateTest5_iso8601 unitTest = new DateTest5_iso8601();
			unitTest.configure(input);
			unitTest.test_date();
			System.out.println("DateTest5_iso8601#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest5_iso8601#test_date failed.");
		}
		finally {
			shield.applyRollback(input);
		}
		try {
			shield.applyCheckpoint(input);
			DateTest4_indian unitTest = new DateTest4_indian();
			unitTest.configure(input);
			unitTest.test_date();
			System.out.println("DateTest4_indian#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest4_indian#test_date failed.");
		}
		finally {
			shield.applyRollback(input);
		}
		try {
			shield.applyCheckpoint(input);
			Issue1679 unitTest = new Issue1679();
			unitTest.configure(input);
			unitTest.test_for_issue();
			System.out.println("Issue1679#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1679#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(input);
		}
		try {
			shield.applyCheckpoint(input);
			DateTest_tz unitTest = new DateTest_tz();
			unitTest.configure(input);
			unitTest.test_codec();
			System.out.println("DateTest_tz#test_codec passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest_tz#test_codec failed.");
		}
		finally {
			shield.applyRollback(input);
		}
		try {
			shield.applyCheckpoint(input);
			DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();
			unitTest.configureDate(input);
			unitTest.test_8();
			System.out.println("DefaultExtJSONParser_parseArray#test_8 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_8 failed.");
		}
		finally {
			shield.applyRollback(input);
		}
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Flaky Test(s) - WriteDuplicateType
	- Context input: LinkedHashMap<String, HashMap<String, Object>>
	*/
	public boolean invivoCartMap(Context c) throws InvocationTargetException{
		this.configure();
		byte[] contextData = (byte[]) c.getOtherReferencesInContext().get(0);

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		String mName = this.getCurrentMethodName();

		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		LinkedHashMap<String, HashMap<String, Object>> cartMap = InputGenerator.generateCartMap(0, contextData);

		try {
			shield.applyCheckpoint(cartMap);
			WriteDuplicateType unitTest = new WriteDuplicateType();
			unitTest.configure(cartMap);
			unitTest.test_dupType2();
			System.out.println("WriteDuplicateType#test_dupType2 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("WriteDuplicateType#test_dupType2 failed.");
		}
		finally {
			shield.applyRollback(cartMap);
		}
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}



	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - DefaultJSONParser
	- Method Under Test - parseArray()
	- Context input: String representation of an array
	*/
	public boolean invivoParseArray(Context c) throws JsonProcessingException {
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		System.out.println("["+mName+"] Testing invivo ...");
		try {
			shield.applyCheckpoint(contextData);
			DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();
			unitTest.configureArray(InputGenerator.generateStringArray(contextData));
			unitTest.test_7();
			System.out.println("DefaultExtJSONParser_parseArray#test_7 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_7 failed.");
		}finally {
			shield.applyRollback(contextData);
		}

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
	- Fastjson Version - 1.2.54
	- Class Under Test - JSONPath
	- Method Under Test - set() (?)
	- Context input: HashMap<String, String>
	*/
	public boolean invivoJSONPath(Context c) throws InvocationTargetException, JsonProcessingException {
		this.configure();

		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		HashMap<String, String> objectMap = InputGenerator.generateSimpleHashMap(contextData);

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(objectMap);
			Issue1177_2 unitTest = new Issue1177_2();
			unitTest.configure(objectMap);
			unitTest.test_for_issue();
			System.out.println("Issue1177_2#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1177_2#test_for_issue failed.");
		}finally {
			shield.applyRollback(objectMap);
		}

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
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
