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

import com.alibaba.json.bvt.jsonp.JSONPParseTest2;
import com.alibaba.json.bvt.writeClassName.WriteDuplicateType;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import org.junit.Ignore;
import org.junit.Test;

public class fastjsonInvivoTestClass {
	private static boolean EXIT_STATUS = false;
	
	private static List<String> FAILED_TESTS = new ArrayList<String>();
	
	private String backupDir;
	private Map<String, String> backupDirMap = null;
	private boolean flagCopied = false;

			
	public boolean invivoTestMethod(Context c) throws InvocationTargetException{
		this.configure();
	
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

		this.testSimpleShrinkMutipleInvivo();
		return getExitStatus();
	}

	private void testSimpleShrinkMutipleInvivo() {
		String mName = this.getCurrentMethodName();
		System.out.println("--------------------------------------------");
		System.out.println("["+mName+"] Testing invivo ...");
		System.out.println("--------------------------------------------");

		WriteDuplicateType unitTest = new WriteDuplicateType();
		try{
			unitTest.test_dupType2();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
		}
		System.out.println("... done!");
	}

	private String getCurrentMethodName() {
		 String nameofCurrMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
		 return nameofCurrMethod;
	}

	private void configure() {
		setExitStatus();
		FAILED_TESTS.clear();
		this.backupDir = null;
		this.backupDirMap = new HashMap<String, String>();
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
