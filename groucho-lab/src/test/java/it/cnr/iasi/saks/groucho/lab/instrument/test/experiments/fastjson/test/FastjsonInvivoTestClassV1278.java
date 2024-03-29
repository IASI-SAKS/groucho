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

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1278.*;

import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils.InputGenerator;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.util.ArrayList;
import java.util.List;

public class FastjsonInvivoTestClassV1278 {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	public boolean invivoHashMultimapTest(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			HashMultimapTest unitTest = new HashMultimapTest();
			unitTest.configure(InputGenerator.generateHashMultimap(contextData));
			unitTest.test_for_multimap();
			System.out.println("HashMultimapTest#test_for_multimap passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("HashMultimapTest#test_for_multimap failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoMaxBufSizeTest(Context c) {
		this.configure();
		SerializeWriter sw = (SerializeWriter) c.getInstrumentedObject();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(sw);
			MaxBufSizeTest unitTest = new MaxBufSizeTest();

			unitTest.configure(sw);
			unitTest.test_max_buf();
			System.out.println("MaxBufSizeTest#test_max_buf passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("MaxBufSizeTest#test_max_buf failed.");
		}
		finally {
			shield.applyRollback(sw);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1368(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Issue1368 unitTest = new Issue1368();
			//unitTest.configure(InputGenerator.generateAlibabaJSONObject(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue1368#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1368#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue1584(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Issue1584 unitTest = new Issue1584();
			unitTest.configure(InputGenerator.generateString(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue1584#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue1584#test_for_issue failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoSortFieldTest(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			SortFieldTest unitTest = new SortFieldTest();
			unitTest.configure(InputGenerator.generateAlibabaJSONObject(contextData));
			unitTest.test_1();
			System.out.println("invivoSortField#test_1 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("invivoSortField#test_1 failed.");
		}
		finally {
			shield.applyRollback(contextData);
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoIssue3082(Context c) {
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		try {
			shield.applyCheckpoint(contextData);
			Issue3082 unitTest = new Issue3082();
			unitTest.configure(InputGenerator.generateNestedSet(contextData));
			unitTest.test_for_issue();
			System.out.println("Issue3082#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue3082#test_for_issue failed.");
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
