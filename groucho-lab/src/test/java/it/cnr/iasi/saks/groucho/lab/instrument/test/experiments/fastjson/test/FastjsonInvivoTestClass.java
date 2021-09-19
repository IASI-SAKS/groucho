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
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import it.cnr.iasi.saks.jvmserializers.MyGenericSerializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.json.bvt.asm.*;
import com.alibaba.json.bvt.bug.*;
import com.alibaba.json.bvt.date.*;
//import com.alibaba.json.bvt.geo.*;
import com.alibaba.json.bvt.guava.*;
import com.alibaba.json.bvt.issue_1100.*;
import com.alibaba.json.bvt.issue_1200.*;
import com.alibaba.json.bvt.issue_1300.*;
import com.alibaba.json.bvt.issue_1400.*;
import com.alibaba.json.bvt.issue_1500.*;
//import com.alibaba.json.bvt.issue_1700.*;
import com.alibaba.json.bvt.issue_1900.*;
import com.alibaba.json.bvt.issue_2100.*;
import com.alibaba.json.bvt.issue_2400.*;
import com.alibaba.json.bvt.issue_3000.*;
import com.alibaba.json.bvt.jsonp.*;
import com.alibaba.json.bvt.parser.deser.*;
import com.alibaba.json.bvt.serializer.date.*;
import com.alibaba.json.bvt.writeClassName.V2.*;

public class FastjsonInvivoTestClass {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	/*
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Fastjson Version - 1.2.54
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
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Fastjson Version - 1.2.54
	- Context input: timezone, locale
	*/
	public boolean invivoParseObject2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		//Get timezone info from the context
		testParseObject2();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*Runs some flaky tests that exercise JSON.parseObject() [V. 1.2.54]*/
	private void testParseObject2() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			//Apply checkpoint
			// shield.applyCheckpoint(input);
			DateTest5_iso8601 unitTest = new DateTest5_iso8601();
			//Configure test with Context info
			unitTest.configure(TimeZone.getDefault(), Locale.getDefault());
			unitTest.test_date();
			System.out.println("DateTest5_iso8601#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest5_iso8601#test_date failed.");
		}
		//finally {
		//	shield.applyRollback(input);
		//}
		try {
			//Apply checkpoint
			// shield.applyCheckpoint(input);
			DateTest4_indian unitTest = new DateTest4_indian();
			//Configure test with Context info
			unitTest.configure(TimeZone.getDefault(), Locale.getDefault());
			unitTest.test_date();
			System.out.println("DateTest4_indian#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest4_indian#test_date failed.");
		}
		//finally {
		//	shield.applyRollback(input);
		//}
	}

	/*
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Fastjson Version - 1.2.54
	- Context input: HashMap<Integer, Integer>
	*/
	public boolean invivoParseObject3(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		//Configure test with Context info
		testParseObject3();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*Runs some flaky tests that exercise JSON.parseObject() [V. 1.2.54]*/
	private void testParseObject3() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			Issue1480 unitTest = new Issue1480();
			//Configure with HashMap from C
			//unitTest.configure();
			unitTest.test_for_issue();
			System.out.println("Issue1480#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1480#test_for_issue failed.");
		}
	}

	/*
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Fastjson Version - 1.2.54
	- Context input: date, timezone, locale
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

	/*Runs some flaky tests that exercise JSON.toJSONString() [V. 1.2.54]*/
	private void testToJsonString() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			DateTest unitTest = new DateTest();
			//It should be configured with input from the Context
			//unitTest.configure(...);
			long millis = 1324138987429L;
			Date date = new Date(millis);
			//unitTest.configure(TimeZone.getTimeZone("Asia/Shangai"), Locale.CHINA, date);
			unitTest.configure(TimeZone.getDefault(), Locale.getDefault(), date);
			unitTest.test_date();
			System.out.println("DateTest#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest#test_date failed.");
		}
	}


	/*
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Fastjson Version - 1.2.54
	- Context input: timezone, locale, JSONReader?
	*/
	public boolean invivoReadObject(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		//Get info from the context
		testReadObject();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*Runs some flaky tests that exercise JSONReader.readObject() [V. 1.2.54]*/
	private void testReadObject() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			DateTest_tz unitTest = new DateTest_tz();
			//It should be configured with input from the Context
			unitTest.configure(TimeZone.getTimeZone("Asia/Shangai"), Locale.CHINA);
			System.out.println("DateTest_tz#test_codec passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest_tz#test_codec passed.");
		}
	}

	/*
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Fastjson Version - 1.2.73
	*/
	/*public boolean myInvivoParseObject2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllParseObject2();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}*/

	/*
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Fastjson Version - 1.2.54
	*/
	/*public boolean invivoTestToJSONString(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllToJSONString();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}*/

	/*
        - Class Under Test - JSON
        - Method Under Test - toJSONString()
        - Fastjson Version - 1.2.73
    */
	/*public boolean invivoTestToJSONString2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllToJSONString2();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}*/



	/*Runs all the flaky tests that exercise JSON.parseObject() [V. 1.2.73]*/
	/*private void testAllParseObject2() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		List<Method> tests = new ArrayList<Method>();
		try {
			tests.add(Issue1584.class.getDeclaredMethod("test_for_issue"));
			tests.add(Issue3082.class.getDeclaredMethod("test_for_issue"));
			tests.add(SqlDateDeserializerTest2.class.getDeclaredMethod("test_sqlDate"));
			tests.add(WriteClassNameTest_Map.class.getDeclaredMethod("test_list"));
			tests.add(Issue1492.class.getDeclaredMethod("test_for_issue"));
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
	}*/

	/* Runs all the flaky tests that exercise JSON.toJSONString() [V. 1.2.54] */
//	private void testAllToJSONString() {
//		String mName = this.getCurrentMethodName();
//		System.out.println("["+mName+"] Testing invivo ...");
//		List<Method> tests = new ArrayList<Method>();
//		try {
//			tests.add(Issue1298.class.getDeclaredMethod("test_for_issue"));
//			tests.add(Issue1298.class.getDeclaredMethod("test_for_issue_1"));
//			tests.add(DateTest.class.getDeclaredMethod("test_date"));
//			tests.add(com.alibaba.json.bvt.writeClassName.V1.WriteDuplicateType.class.getDeclaredMethod("test_dupType2"));
//			tests.add(Issue1177_2.class.getDeclaredMethod("test_for_issue"));
//		}catch(Throwable t){
//			System.out.println(t.getMessage());
//		}
//		for (Method m : tests){
//			try {
//				m.invoke(m.getDeclaringClass().newInstance());
//				System.out.println(m.getName() + " passed.");
//			} catch(Throwable t) {
//				FAILED_TESTS.add(mName);
//				resetExitStatus();
//				System.out.println("message" +t.getMessage());
//				System.out.println(m.getName() + " failed.");
//			}
//		}
//	}

	/* Runs all the flaky tests that exercise JSON.toJSONString() [V. 1.2.73] */
//	private void testAllToJSONString2() {
//		String mName = this.getCurrentMethodName();
//		System.out.println("["+mName+"] Testing invivo ...");
//		List<Method> tests = new ArrayList<Method>();
//		try {
//			//tests.add(Issue1780_JSONObject.class.getDeclaredMethod("test_for_issue"));
//			//tests.add(Issue1780_Module.class.getDeclaredMethod("test_for_issue"));
//			tests.add(Issue1363.class.getDeclaredMethod("test_for_issue"));
//			tests.add(Issue1363.class.getDeclaredMethod("test_for_issue_1"));
//			tests.add(SortFieldTest.class.getDeclaredMethod("test_1"));
//			tests.add(Bug_for_yangzhou.class.getDeclaredMethod("test_for_issue"));
//			tests.add(Issue1177_1.class.getDeclaredMethod("test_for_issue"));
//			//tests.add(FeatureCollectionTest.class.getDeclaredMethod("test_geo"));
//			tests.add(Issue2447.class.getDeclaredMethod("test_for_issue"));
//			tests.add(Issue2447.class.getDeclaredMethod("test_for_issue2"));
//			tests.add(Issue2428.class.getDeclaredMethod("test_for_issue"));
//			tests.add(com.alibaba.json.bvt.writeClassName.V2.WriteDuplicateType.class.getDeclaredMethod("test_dupType"));
//			tests.add(HashMultimapTest.class.getDeclaredMethod("test_for_multimap"));
//			tests.add(Issue1972.class.getDeclaredMethod("test_for_issue"));
//
//			/*Build failure*/
//			//tests.add(Issue2182.class.getDeclaredMethod("test_for_issue"));
//			//tests.add(MultiMapTes.class.getDeclaredMethod("test_multimap"));
//			// ...
//		}catch(Throwable t){
//			System.out.println(t.getMessage());
//		}
//		for (Method m : tests){
//			try {
//				m.invoke(m.getDeclaringClass().newInstance());
//				System.out.println(m.getName() + " passed.");
//			} catch(Throwable t) {
//				FAILED_TESTS.add(mName);
//				resetExitStatus();
//				System.out.println("message" +t.getMessage());
//				System.out.println(m.getName() + " failed.");
//			}
//		}
//	}

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
