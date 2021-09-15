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
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import it.cnr.iasi.saks.jvmserializers.MyGenericSerializer;
import org.apache.jcs.engine.memory.lru.LRUMemoryCache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FastjsonInvivoTestClass {
	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	/*Simple inVivo test to try out parametrized unit tests*/
	public boolean invivoTestSimple(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);

		byte[] input =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		JSONPParseTest2 unitTest = new JSONPParseTest2();
		try {
			unitTest.test_f(input);
			System.out.println( "test_f passed.");
		} catch(Throwable t) {
			FAILED_TESTS.add(mName);
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("test_f failed.");
		}
		setExitStatus();
		return getExitStatus();
	}


	/*
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Fastjson Version - 1.2.54
	*/
	public boolean invivoParseObject(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllParseObject();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
	- Class Under Test - JSON
	- Method Under Test - parseObject()
	- Fastjson Version - 1.2.73
	*/
	public boolean invivoParseObject2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllParseObject2();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
	- Class Under Test - JSON
	- Method Under Test - toJSONString()
	- Fastjson Version - 1.2.54
	*/
	public boolean invivoTestToJSONString(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllToJSONString();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		setExitStatus();
		return getExitStatus();
	}

	/*
        - Class Under Test - JSON
        - Method Under Test - toJSONString()
        - Fastjson Version - 1.2.73
    */
	public boolean invivoTestToJSONString2(Context c) throws InvocationTargetException{
		this.configure();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
		testAllToJSONString2();
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
			tests.add(Issue1480.class.getDeclaredMethod("test_for_issue"));
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

	/*Runs all the flaky tests that exercise JSON.parseObject() [V. 1.2.73]*/
	private void testAllParseObject2() {
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
			tests.add(com.alibaba.json.bvt.writeClassName.V1.WriteDuplicateType.class.getDeclaredMethod("test_dupType2"));
			tests.add(Issue1177_2.class.getDeclaredMethod("test_for_issue"));
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

	/* Runs all the flaky tests that exercise JSON.toJSONString() [V. 1.2.73] */
	private void testAllToJSONString2() {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		List<Method> tests = new ArrayList<Method>();
		try {
			//tests.add(Issue1780_JSONObject.class.getDeclaredMethod("test_for_issue"));
			//tests.add(Issue1780_Module.class.getDeclaredMethod("test_for_issue"));
			tests.add(Issue1363.class.getDeclaredMethod("test_for_issue"));
			tests.add(Issue1363.class.getDeclaredMethod("test_for_issue_1"));
			tests.add(SortFieldTest.class.getDeclaredMethod("test_1"));
			tests.add(Bug_for_yangzhou.class.getDeclaredMethod("test_for_issue"));
			tests.add(Issue1177_1.class.getDeclaredMethod("test_for_issue"));
			//tests.add(FeatureCollectionTest.class.getDeclaredMethod("test_geo"));
			tests.add(Issue2447.class.getDeclaredMethod("test_for_issue"));
			tests.add(Issue2447.class.getDeclaredMethod("test_for_issue2"));
			tests.add(Issue2428.class.getDeclaredMethod("test_for_issue"));
			tests.add(com.alibaba.json.bvt.writeClassName.V2.WriteDuplicateType.class.getDeclaredMethod("test_dupType"));
			tests.add(HashMultimapTest.class.getDeclaredMethod("test_for_multimap"));
			tests.add(Issue1972.class.getDeclaredMethod("test_for_issue"));

			/*Build failure*/
			//tests.add(Issue2182.class.getDeclaredMethod("test_for_issue"));
			//tests.add(MultiMapTes.class.getDeclaredMethod("test_multimap"));
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
