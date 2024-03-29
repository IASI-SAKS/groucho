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

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.json.bvt.serializer.JSONFieldTest5;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1251.JSONSerializerTest2;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;

import java.util.ArrayList;
import java.util.List;

/*
 * This class runs InVivo the original flaky test(s) of FastJson V.1.2.51
 * */

public class FastjsonInvivoTestClassV1251_NonParam {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	public boolean invivoJsonSerializerTest2(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		JSONSerializerTest2 unitTest = new JSONSerializerTest2();
		try {
			shield.applyCheckpoint(unitTest.getSerializer());
			unitTest.test_0();
			System.out.println("JSONSerializerTest2#test_0 passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONSerializerTest2#test_0 failed.");
		}
		finally {
			shield.applyRollback(unitTest.getSerializer());
		}
		setExitStatus();
		return getExitStatus();
	}

	public boolean invivoJSONFieldTest5(Context c) {
		this.configure();
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		JSONFieldTest5 unitTest = new JSONFieldTest5();
		try {
			shield.applyCheckpoint(unitTest);
			unitTest.test_jsonField();
			System.out.println("JSONFieldTest5#test_jsonField passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("JSONFieldTest5#test_jsonField failed.");
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
