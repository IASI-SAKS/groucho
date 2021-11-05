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
import com.alibaba.json.bvt.issue_2400.Issue2428;
import java.util.ArrayList;
import java.util.List;

/*
* This class runs InVivo the original flaky test(s) of FastJson V.1.2.75
* */

public class FastjsonInvivoTestClassV1275_Default {

	private static boolean EXIT_STATUS = false;

	private static List<String> FAILED_TESTS = new ArrayList<String>();

	/*
	- Fastjson Version - 1.2.75
	- Flaky Test(s) - Issue2428#test_for_issue
	*/
	public boolean invivoTest2428(Context c) {
		this.configure();

		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		try {
			Issue2428 unitTest = new Issue2428();
			unitTest.test_for_issue();
			System.out.println("Issue2428#test_for_issue passed.");
		}catch(Throwable t){
			System.out.println(t.getMessage());
			System.out.println("Issue2428#test_for_issue failed.");
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
