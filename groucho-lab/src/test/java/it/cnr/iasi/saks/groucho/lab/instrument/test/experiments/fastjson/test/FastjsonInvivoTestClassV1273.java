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

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273.*;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils.InputGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class FastjsonInvivoTestClassV1273 {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	public boolean invivoIssue2447(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Issue2447 unitTest = new Issue2447();
			unitTest.configure(InputGenerator.generateVO(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue2447#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue2447#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue2447_2(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Issue2447 unitTest = new Issue2447();
			unitTest.configure(InputGenerator.generateVO(contextData));
			unitTest.test_for_issue2();
			System.out.println("Issue2447#test_for_issue2 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue2447#test_for_issue2 failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoJSONPath_reverse_test(Context c) throws JsonProcessingException {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			JSONPath_reverse_test unitTest = new JSONPath_reverse_test();
			unitTest.configureSimple(InputGenerator.generateJSONObject(0, contextData));
			unitTest.test_reserve();
			System.out.println("JSONPath_reverse_test#test_reserve passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPath_reverse_test#test_reserve failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoJSONPath_reverse_test_3(Context c) throws JsonProcessingException {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(contextData);
			JSONPath_reverse_test unitTest = new JSONPath_reverse_test();
			unitTest.configureNested(InputGenerator.generateJSONObject(1, contextData));
			unitTest.test_reserve3();
			System.out.println("JSONPath_reverse_test#test_reserve3 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPath_reverse_test#test_reserve3 failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivo1363(Context c) throws JsonProcessingException {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(contextData);
			Issue1363 unitTest = new Issue1363();
			com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO a = InputGenerator.generateDataSimpleVO(contextData);
			com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO b = InputGenerator.generateDataSimpleVO(contextData);
			unitTest.configure(a,b);
			unitTest.test_for_issue();
			System.out.println("Issue1363#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1363#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivo1363_1(Context c) throws JsonProcessingException {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(contextData);
			Issue1363 unitTest =  new Issue1363();
			com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO a = InputGenerator.generateDataSimpleVO(contextData);
			com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO b = InputGenerator.generateDataSimpleVO(contextData);
			unitTest.configure(a,b);
			unitTest.test_for_issue_1();
			System.out.println("Issue1363#test_for_issue_1 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1363#test_for_issue_1 failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1780_JSONObject(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Issue1780_JSONObject unitTest = new Issue1780_JSONObject();
			unitTest.configure(InputGenerator.generateJSONObject(0, contextData));
			unitTest.test_for_issue();
			System.out.println("Issue1780_JSONObject#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1780_JSONObject#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1780_Module(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(contextData);
			Issue1780_Module unitTest = new Issue1780_Module();
			unitTest.configure(InputGenerator.generateJSONObject(0, contextData));
			unitTest.test_for_issue();
			System.out.println("Issue1780_Module#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1780_Module#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoArrayListMultimapTest(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			ArrayListMultimapTest unitTest = new ArrayListMultimapTest();
			unitTest.configure(InputGenerator.generateArrayListMultimap(contextData));
			unitTest.test_for_multimap();
			System.out.println("ArrayListMultimapTest#test_for_multimap passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("ArrayListMultimapTest#test_for_multimap failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoSqlDateDeserializerTest2(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			SqlDateDeserializerTest2 unitTest = new SqlDateDeserializerTest2();
			unitTest.configure(InputGenerator.generateSqlDate());
			unitTest.test_sqlDate();
			System.out.println("SqlDateDeserializerTest2#test_sqlDate passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("SqlDateDeserializerTest2#test_sqlDate failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}


	public boolean invivoIssue1177_1(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Issue1177_1 unitTest = new Issue1177_1();
			unitTest.configure(InputGenerator.generateAlibabaJSONObject(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue1177_1#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1177_1#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1972(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(contextData);
			Issue1972 unitTest = new Issue1972();
			unitTest.configure(InputGenerator.generateSimpleAlibabaJSONObject(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue1972#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1972#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoJSONObjectTest_readObject(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		try {
			shield.applyCheckpoint(contextData);
			JSONObjectTest_readObject unitTest = new JSONObjectTest_readObject();
			unitTest.configure(InputGenerator.generateAlibabaJSONObject(contextData));
			unitTest.test_6();
			System.out.println("JSONObjectTest_readObject#test_6 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONObjectTest_readObject#test_6 failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1492(Context c) throws InvocationTargetException{
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			shield.applyCheckpoint(contextData);
			Issue1492 unitTest = new Issue1492();
			unitTest.configure(InputGenerator.generateRandomJSONObject(contextData), InputGenerator.generateJSONArray(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue1492#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1492#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoWriteDuplicateType(Context c) throws InvocationTargetException{
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			shield.applyCheckpoint(contextData);
			WriteDuplicateType unitTest = new WriteDuplicateType();
			unitTest.configure(InputGenerator.generateLinkedHashMapOfJSONObject(contextData));
			unitTest.test_dupType();
			System.out.println("WriteDuplicateType#test_dupType passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("WriteDuplicateType#test_dupType failed.");
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
