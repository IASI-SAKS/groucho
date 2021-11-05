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

import java.util.*;

/*
 * This class runs InVivo the original flaky test(s) of FastJson V.1.2.54
 * */

public class FastjsonInvivoTestClassV1254_Default {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	private boolean inVivoTestDefault(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			JSONPParseTest2 unitTest = new JSONPParseTest2();
			unitTest.test_f();
			System.out.println("JSONPParseTest2#test_f passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPParseTest2#test_f failed.");
		}
		try {
			JSONPParseTest3 unitTest = new JSONPParseTest3();
			unitTest.test_f();
			System.out.println("JSONPParseTest3#test_f passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONPParseTest3#test_f failed.");
		}
		try {
			Issue1480 unitTest = new Issue1480();
			unitTest.test_for_issue();
			System.out.println("Issue1480#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1480#test_for_issue failed.");
		}
		try {
			Issue1298 unitTest = new Issue1298();
			unitTest.test_for_issue();
			System.out.println("Issue1298#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1298#test_for_issue failed.");
		}
		try {
			Issue1298 unitTest = new Issue1298();
			unitTest.test_for_issue_1();
			System.out.println("Issue1298#test_for_issue_1 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1298#test_for_issue_1 failed.");
		}
		try {
			Issue1977 unitTest = new Issue1977();
			unitTest.test_for_issue();
			System.out.println("Issue1977#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1977#test_for_issue failed.");
		}
		try {
			DateTest unitTest = new DateTest();
			unitTest.test_date();
			System.out.println("DateTest#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest#test_date failed.");
		}
		try {
			DateTest5_iso8601 unitTest = new DateTest5_iso8601();
			unitTest.test_date();
			System.out.println("DateTest5_iso8601#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest5_iso8601#test_date failed.");
		}
		try {
			DateTest4_indian unitTest = new DateTest4_indian();
			unitTest.test_date();
			System.out.println("DateTest4_indian#test_date passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest4_indian#test_date failed.");
		}
		try {
			Issue1679 unitTest = new Issue1679();
			unitTest.test_for_issue();
			System.out.println("Issue1679#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1679#test_for_issue failed.");
		}
		try {
			DateTest_tz unitTest = new DateTest_tz();
			unitTest.test_codec();
			System.out.println("DateTest_tz#test_codec passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DateTest_tz#test_codec failed.");
		}
		try {
			DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();
			unitTest.test_8();
			System.out.println("DefaultExtJSONParser_parseArray#test_8 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_8 failed.");
		}
		try {
			WriteDuplicateType unitTest = new WriteDuplicateType();
			unitTest.test_dupType2();
			System.out.println("WriteDuplicateType#test_dupType2 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("WriteDuplicateType#test_dupType2 failed.");
		}
		try {
			DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();
			unitTest.test_7();
			System.out.println("DefaultExtJSONParser_parseArray#test_7 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("DefaultExtJSONParser_parseArray#test_7 failed.");
		}
		try {
			Issue1177_2 unitTest = new Issue1177_2();
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
