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

import com.alibaba.json.bvt.bug.Bug_for_issue_447;
import com.alibaba.json.bvt.bug.Bug_for_xiayucai2012;
import com.alibaba.json.bvt.issue_1400.Issue1493;
import it.cnr.iasi.saks.groucho.common.Context;
import com.alibaba.json.bvt.issue_2400.Issue2428;
import com.alibaba.json.bvt.parser.TypeUtilsTest;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;

import java.util.ArrayList;
import java.util.List;

/*
* This class runs InVivo the original flaky test(s) of FastJson V.1.2.75
* */

public class FastjsonInvivoTestClassV1275_NonParam {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	public boolean invivoIssue2428(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue2428 unitTest = new Issue2428();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue2428#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue2428#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoTypeUtilsTest(Context c) {
		this.configure();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		TypeUtilsTest unitTest = new TypeUtilsTest();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_cast_to_Timestamp_1970_01_01_00_00_00();
			System.out.println("TypeUtilsTest#test_cast_to_Timestamp_1970_01_01_00_00_00 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("TypeUtilsTest#test_cast_to_Timestamp_1970_01_01_00_00_00 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoBug_for_xiayucai2012(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Bug_for_xiayucai2012 unitTest = new Bug_for_xiayucai2012();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.setUp();
			unitTest.test_for_xiayucai2012();
			System.out.println("Bug_for_xiayucai2012#test_for_xiayucai2012 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Bug_for_xiayucai2012#test_for_xiayucai2012 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}

		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1493(Context c) {
		this.configure();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1493 unitTest = new Issue1493();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.setUp();
			unitTest.test_for_issue();
			System.out.println("Issue1493#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1493#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoBug_for_issue_447(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Bug_for_issue_447 unitTest = new Bug_for_issue_447();
		try {
			shield.applyCheckpoint(unitTest);
			unitTest.setUp();
			unitTest.test_for_issue();
			System.out.println("Bug_for_issue_447#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Bug_for_issue_447#test_for_issue failed.");
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
