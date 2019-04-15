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
package it.cnr.iasi.saks.groucho.performanceOverheadTest;

import java.util.Vector;

import org.junit.Ignore;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.callback.GovernanceManagerFactory;
import it.cnr.iasi.saks.groucho.config.PropertyUtil;
import it.cnr.iasi.saks.groucho.tests.util.PropertyUtilNoSingleton;
import it.cnr.iasi.saks.grouchoTestingStuff.SimpleThread;

public class PerformanceOverheadTest_IT {

	private static final String GM_ACTIVATION_WITH_PROBABILITY_CLASSNAME = "it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability";

//	private static final int REPETITIONS = 4;
	private static final int REPETITIONS = 2;
	private static final int INIT_THERADS = 10;
	private static final int MAX_THERADS = 30;
//	private static final int MAX_THERADS = 10;
	private static final int THERADS_INC_STEP = 10;
	
	private Vector<Vector<Float>> elapsedTimesMatrix;
	
	public PerformanceOverheadTest_IT(){
		elapsedTimesMatrix = new Vector<Vector<Float>>();
	}
	
	@Ignore
	@Test
	public void firstSimpleTestLong(){
		this.firstSimpleTest(50);
	}
	
	@Ignore
	@Test
	public void firstSimpleTest(){
		this.firstSimpleTest(10);
	}
	
//	@Ignore
	@Test
	public void maxNumberOfTheradTest(){		
		PropertyUtilNoSingleton prop = PropertyUtilNoSingleton.getInstance();
		
//		String classesToExcludeCSV = "org.eclipse.jdt.internal.junit, org.eclipse.jdt.internal.junit4";
//		prop.setProperty(PropertyUtil.TRANFORMER_DISABLED_ON_CLASSES_LABEL, classesToExcludeCSV);
		prop.setProperty(PropertyUtil.GOVERNANCE_MANAGER_CLASS_LABEL, GM_ACTIVATION_WITH_PROBABILITY_CLASSNAME);
		
		GovernanceManagerFactory.getInstance().getThreadHarness().decThreads();

		for (int maxThreads = INIT_THERADS; maxThreads <= MAX_THERADS; maxThreads+=THERADS_INC_STEP) {
			Vector<Float> elapsedTimes = new Vector<Float>();
			for (int i = 0; i < REPETITIONS; i++) {
				float et = this.firstSimpleTest(maxThreads);
				elapsedTimes.add(et);
			}
			this.elapsedTimesMatrix.add(elapsedTimes);
		}
		this.printAverages();
	}
	
	private float firstSimpleTest(int size){		
		long startTS = System.currentTimeMillis();
		double rnd;
		Thread t = null;
		for (int i = 0; i < size; i++) {
			rnd = Math.random();
			if (rnd <= 0.9f){
				SimpleThread s = new SimpleThread();
				t = new Thread(s);
				t.start();
			}			
		}
		try {
			System.out.println("------------------------- Join Invoked");
			if (t!=null){
				t.join();
			}
			System.out.println("------------------------- done");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long finishTS = System.currentTimeMillis();
		float elapsedSec = (finishTS - startTS)/1000f;

		System.out.println("Completed in almost: " + elapsedSec + " sec.");
		return elapsedSec;		
	}
	
	private void printAverages() {
		int count = 0;
		for (Vector<Float> vector : this.elapsedTimesMatrix) {
			count += THERADS_INC_STEP;
			float averageET = 0f;
			String etCSV = "";
			if (vector.size() != 0) {
				for (Float et : vector) {
					averageET += et.floatValue();
					etCSV = etCSV + et.toString() + ", ";
				}
				averageET = averageET / vector.size();
			}
			System.out.println("Average When Max Therads: " + count +" ---> "+averageET + "["+etCSV+"] ("+vector.size()+")");
		}
	}
}
