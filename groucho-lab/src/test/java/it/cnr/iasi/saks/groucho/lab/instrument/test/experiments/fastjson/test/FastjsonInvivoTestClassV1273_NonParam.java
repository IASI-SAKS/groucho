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

import com.alibaba.json.bvt.issue_1300.Issue1363;
import com.alibaba.json.bvt.issue_1400.Issue1492;
import com.alibaba.json.bvt.issue_2400.Issue2447;
import com.alibaba.json.bvt.guava.ArrayListMultimapTest;
import com.alibaba.json.bvt.path.JSONPath_reverse_test;
import com.alibaba.json.bvt.issue_1700.Issue1780_JSONObject;
import com.alibaba.json.bvt.issue_1700.Issue1780_Module;
import com.alibaba.json.bvt.writeClassName.V1273.WriteDuplicateType;
import it.cnr.iasi.saks.groucho.common.Context;
import com.alibaba.json.bvt.parser.deser.SqlDateDeserializerTest2;
import com.alibaba.json.bvt.issue_1900.Issue1972;
import com.alibaba.json.bvt.JSONObjectTest_readObject;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;

import java.util.ArrayList;
import java.util.List;

/*
 * This class runs InVivo the original flaky test(s) of FastJson V.1.2.73
 * */
public class FastjsonInvivoTestClassV1273_NonParam {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	public boolean invivoIssue2447(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue2447 unitTest = new Issue2447();
		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue2447#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue2447#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue2447_2(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue2447 unitTest = new Issue2447();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue2();
			System.out.println("Issue2447#test_for_issue2 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue2447#test_for_issue2 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoJSONPath_reverse_test(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		JSONPath_reverse_test unitTest = new JSONPath_reverse_test();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_reserve();
			System.out.println("JSONPath_reverse_test#test_reserve passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPath_reverse_test#test_reserve failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}


	public boolean invivoJSONPath_reverse_test_3(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		JSONPath_reverse_test unitTest = new JSONPath_reverse_test();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_reserve3();
			System.out.println("JSONPath_reverse_test#test_reserve3 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPath_reverse_test#test_reserve3 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1492(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1492 unitTest = new Issue1492();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1492#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1492#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoWriteDuplicateType(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		WriteDuplicateType unitTest = new WriteDuplicateType();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_dupType();
			System.out.println("WriteDuplicateType#test_dupType passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("WriteDuplicateType#test_dupType failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1780_JSONObject(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1780_JSONObject unitTest = new Issue1780_JSONObject();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1780_JSONObject#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1780_JSONObject#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1780_Module(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1780_Module unitTest = new Issue1780_Module();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1780_Module#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1780_Module#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1363(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1363 unitTest = new Issue1363();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1363#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1363#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}
	public boolean invivoIssue1363_1(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1363 unitTest =  new Issue1363();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue_1();
			System.out.println("Issue1363#test_for_issue_1 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1363#test_for_issue_1 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}
	public boolean invivoArrayListMultimapTest(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		ArrayListMultimapTest unitTest = new ArrayListMultimapTest();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_multimap();
			System.out.println("ArrayListMultimapTest#test_for_multimap passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("ArrayListMultimapTest#test_for_multimap failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoSqlDateDeserializerTest2(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		SqlDateDeserializerTest2 unitTest = new SqlDateDeserializerTest2();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.setUp();
			unitTest.test_sqlDate();
			System.out.println("SqlDateDeserializerTest2#test_sqlDate passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("SqlDateDeserializerTest2#test_sqlDate failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1972(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1972 unitTest = new Issue1972();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1972#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1972#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoJSONObjectTest_readObject(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		JSONObjectTest_readObject unitTest = new JSONObjectTest_readObject();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_6();
			System.out.println("JSONObjectTest_readObject#test_6 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONObjectTest_readObject#test_6 failed.");
		}finally {
			shield.applyRollback(unitTest);
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
