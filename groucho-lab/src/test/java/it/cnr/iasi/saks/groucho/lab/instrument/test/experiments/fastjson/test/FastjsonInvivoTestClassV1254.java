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

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils.*;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254.*;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

public class FastjsonInvivoTestClassV1254 {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	public boolean invivoJSONPParseTest2(Context c) {

		this.configure();
		byte[] input =  (byte[]) c.getOtherReferencesInContext().get(0);

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

		setExitStatus();
		return getExitStatus();
	}


	public boolean invivoJSONPParseTest3(Context c) {
		this.configure();
		byte[] input =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
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

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1480(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		JSONObject contextObj = InputGenerator.generateAlibabaJSONObject(contextData);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextObj);
			Issue1480 unitTest = new Issue1480();
			unitTest.configure(contextObj);
			unitTest.test_for_issue();
			System.out.println("Issue1480#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1480#test_for_issue failed.");
		}finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1977(Context c) {
		this.configure();
		Date input = InputGenerator.generateDate();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(input);
			Issue1977 unitTest = new Issue1977();
			unitTest.configure(input);
			unitTest.test_for_issue();
			System.out.println("Issue1977#test_for_issue passed.");
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println("Issue1977#test_for_issue failed.");
		} finally {
			shield.applyRollback(input);
		}

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDateTest(Context c) {
		this.configure();
		Date input = InputGenerator.generateDate();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

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

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDateTest5_iso8601(Context c) {
		this.configure();
		Date input = InputGenerator.generateDate();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

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

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDateTest4_indian(Context c) {
		this.configure();
		Date input = InputGenerator.generateDate();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

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

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1679(Context c) {
		this.configure();
		Date input = InputGenerator.generateDate();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

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

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDateTest_tz(Context c) {
		this.configure();
		Date input = InputGenerator.generateDate();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

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
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDefaultExtJSONParser_parseArray_8(Context c) {
		this.configure();
		Date input = InputGenerator.generateDate();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

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
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivo1298(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		System.out.println("["+mName+"] Testing invivo ...");
		try {
			shield.applyCheckpoint(contextData);
			Issue1298 unitTest = new Issue1298();
			unitTest.configure(InputGenerator.generateAlibabaJSONObject(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue1298#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1298#test_for_issue failed.");
		}finally {
			shield.applyRollback(contextData);
		}

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivo1298_1(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		System.out.println("["+mName+"] Testing invivo ...");

		try {
			shield.applyCheckpoint(contextData);
			Issue1298 unitTest = new Issue1298();
			unitTest.configure(InputGenerator.generateAlibabaJSONObject(contextData));
			unitTest.test_for_issue_1();
			System.out.println("Issue1298#test_for_issue_1 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1298#test_for_issue_1  failed.");
		}finally {
			shield.applyRollback(contextData);
		}

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoWriteDuplicateType(Context c) throws JsonProcessingException {
		this.configure();
		byte[] contextData = (byte[]) c.getOtherReferencesInContext().get(0);
		LinkedHashMap<String, HashMap<String, Object>> cartMap = InputGenerator.generateLinkedHashMapOfHashMap(contextData);

		String mName = this.getCurrentMethodName();

		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

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
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDefaultExtJSONParser_parseArray_7(Context c) {
		this.configure();
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

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoJSONPath(Context c) {
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
