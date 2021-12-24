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

import com.alibaba.json.bvt.date.DateTest;
import com.alibaba.json.bvt.date.DateTest_tz;
import com.alibaba.json.bvt.issue_1100.Issue1177_2;
import com.alibaba.json.bvt.issue_1200.Issue1298;
import com.alibaba.json.bvt.issue_1400.Issue1480;
import com.alibaba.json.bvt.issue_1600.Issue1679;
import com.alibaba.json.bvt.issue_1900.Issue1977;
import com.alibaba.json.bvt.jsonp.JSONPParseTest2;
import com.alibaba.json.bvt.jsonp.JSONPParseTest3;
import com.alibaba.json.bvt.parser.DefaultExtJSONParser_parseArray;
import com.alibaba.json.bvt.serializer.date.DateTest4_indian;
import com.alibaba.json.bvt.serializer.date.DateTest5_iso8601;
import com.alibaba.json.bvt.writeClassName.V1254.WriteDuplicateType;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;

import java.util.*;

/*
 * This class runs InVivo the original flaky test(s) of FastJson V.1.2.54
 * */

public class FastjsonInvivoTestClassV1254_NonParam {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	public boolean invivoJSONPParseTest2(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		JSONPParseTest2 unitTest = new JSONPParseTest2();
		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_f();
			System.out.println("JSONPParseTest2#test_f passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPParseTest2#test_f failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoJSONPParseTest3(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		JSONPParseTest3 unitTest = new JSONPParseTest3();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_f();
			System.out.println("JSONPParseTest3#test_f passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPParseTest3#test_f failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDateTest5_iso8601(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		DateTest5_iso8601 unitTest = new DateTest5_iso8601();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_date();
			System.out.println("DateTest5_iso8601#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest5_iso8601#test_date failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1480(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1480 unitTest = new Issue1480();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1480#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1480#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1298(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1298 unitTest = new Issue1298();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1298#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1298#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1298_1(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1298 unitTest = new Issue1298();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue_1();
			System.out.println("Issue1298#test_for_issue_1 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1298#test_for_issue_1 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1977(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1977 unitTest = new Issue1977();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1977#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1977#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDateTest(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		DateTest unitTest = new DateTest();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_date();
			System.out.println("DateTest#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest#test_date failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDateTest4_indian(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		DateTest4_indian unitTest = new DateTest4_indian();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_date();
			System.out.println("DateTest4_indian#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest4_indian#test_date failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1679(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1679 unitTest = new Issue1679();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_for_issue();
			System.out.println("Issue1679#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1679#test_for_issue failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDateTest_tz(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		DateTest_tz unitTest = new DateTest_tz();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_codec();
			System.out.println("DateTest_tz#test_codec passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest_tz#test_codec failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDefaultExtJSONParser_parseArray(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_8();
			System.out.println("DefaultExtJSONParser_parseArray#test_8 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_8 failed.");
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
			unitTest.test_dupType2();
			System.out.println("WriteDuplicateType#test_dupType2 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("WriteDuplicateType#test_dupType2 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDefaultExtJSONParser_parseArray_8(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_8();
			System.out.println("DefaultExtJSONParser_parseArray#test_8 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_8 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoDefaultExtJSONParser_parseArray_7(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();

		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_7();
			System.out.println("DefaultExtJSONParser_parseArray#test_7 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_7 failed.");
		}finally {
			shield.applyRollback(unitTest);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1177_2(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		Issue1177_2 unitTest = new Issue1177_2();

		try {
			unitTest.test_for_issue();
			System.out.println("Issue1177_2#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1177_2#test_for_issue failed.");
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
