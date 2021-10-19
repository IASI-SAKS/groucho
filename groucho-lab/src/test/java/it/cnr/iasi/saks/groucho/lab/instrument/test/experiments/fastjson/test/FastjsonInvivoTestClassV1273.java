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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273.*;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils.InputGenerator;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class FastjsonInvivoTestClassV1273 {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	/*
	- Fastjson Version - 1.2.73
	- Flaky Test(s) - Issue2447#test_for_issue, Issue2447#test_for_issue2
	- Context input: VO, VO2;
	*/
	public boolean invivoTestVO(Context c) {
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

    /*
- Fastjson Version - 1.2.73
- Flaky Test(s) - Issue2447JSONPath_reverse_test#ttest_reserve,
                  Issue2447JSONPath_reverse_test#ttest_reserve3
- Context input: String text;
*/
    public boolean invivoPathReverse(Context c) throws JsonProcessingException {
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

	/*
	- Fastjson Version - 1.2.73
	- Flaky Test(s) - Issue1492#test_for_issue
	- Context input: JSONObject, JSONArray;
	*/
		public boolean invivoJsonObjArr(Context c) throws InvocationTargetException{
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

	/*
	- Fastjson Version - 1.2.73
	- Flaky Test(s) - WriteDuplicateType#test_dupType
	- Context input: LinkedHashMap<String, JSONObject>
	*/
	public boolean invivoDupType(Context c) throws InvocationTargetException{
		this.configure();
		byte[] contextData =  (byte[]) c.getOtherReferencesInContext().get(0);
		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");
		try {
			shield.applyCheckpoint(contextData);
			WriteDuplicateType unitTest = new WriteDuplicateType();
			unitTest.configure(InputGenerator.generateSimpleCartMap(contextData));
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
