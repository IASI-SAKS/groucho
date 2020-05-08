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

import it.cnr.iasi.saks.groucho.config.PropertyUtil;
import it.cnr.iasi.saks.groucho.tests.util.PropertyUtilNoSingleton;
import it.cnr.iasi.saks.grouchoTestingStuff.SimpleThread;

public class PerformanceOverheadTest_IT {

	private static final String GM_ACTIVATION_WITH_PROBABILITY_CLASSNAME = "it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability";

	private static final int REPETITIONS = 10;
//	private static final int REPETITIONS = 100;
//	private static final int REPETITIONS = 4;
//	private static final int REPETITIONS = 2;

	private static final int INIT_THERADS = 5;
//	private static final int INIT_THERADS = 10;
//	private static final int MAX_THERADS = 30;
//	private static final int MAX_THERADS = 50;
	private static final int MAX_THERADS = 100;
//	private static final int MAX_THERADS = 200;
//	private static final int MAX_THERADS = 10;
//	private static final int THERADS_INC_STEP = 10;
	private static final int THERADS_INC_STEP = 5;
        private static final float ACTIVATION_PROBABILITY_WHEN_VARYING_NUMER_OF_THERADS = 0.0001f;
//        private static final float ACTIVATION_PROBABILITY_WHEN_VARYING_NUMER_OF_THERADS = 0.001f;
//        private static final float ACTIVATION_PROBABILITY_WHEN_VARYING_NUMER_OF_THERADS = 0.01f;
//        private static final float ACTIVATION_PROBABILITY_WHEN_VARYING_NUMER_OF_THERADS = 0.1f;
	
	private static final float INIT_ACTIVATION_PROBABILITY = 0.00001f;
//    	private static final float INIT_ACTIVATION_PROBABILITY = 0.0001f;
//	private static final float INIT_ACTIVATION_PROBABILITY = 0.001f;
//	private static final float INIT_ACTIVATION_PROBABILITY = 0.025f;
//	private static final float INIT_ACTIVATION_PROBABILITY = 0.05f;
	private static final float MAX_ACTIVATION_PROBABILITY = 0.0001f;
//	private static final float MAX_ACTIVATION_PROBABILITY = 0.1f;
//	private static final float MAX_ACTIVATION_PROBABILITY = 0.1f;
//	private static final float MAX_ACTIVATION_PROBABILITY = 0.35f;
//	private static final float MAX_ACTIVATION_PROBABILITY = 0.5f;
	private static final float ACTIVATION_PROBABILITY_INC_STEP = 0.00005f;
//	private static final float ACTIVATION_PROBABILITY_INC_STEP = 0.0005f;
//	private static final float ACTIVATION_PROBABILITY_INC_STEP = 0.005f;
//	private static final float ACTIVATION_PROBABILITY_INC_STEP = 0.025f;
//	private static final float ACTIVATION_PROBABILITY_INC_STEP = 0.05f;
//	private static final float ACTIVATION_PROBABILITY_INC_STEP = 0.1f;
	private static final int NUMER_OF_THERADS_WHEN_VARYING_ACTIVATION_PROBABILITY = 30;
//	private static final int NUMER_OF_THERADS_WHEN_VARYING_ACTIVATION_PROBABILITY = 50;

	private Vector<Vector<Float>> elapsedTimesMatrix;
	
	public PerformanceOverheadTest_IT(){
		elapsedTimesMatrix = new Vector<Vector<Float>>();
	}
	
	@Ignore
	@Test
	public void maxNumberOfTheradTest(){		
		PropertyUtilNoSingleton prop = PropertyUtilNoSingleton.getInstance();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(ACTIVATION_PROBABILITY_WHEN_VARYING_NUMER_OF_THERADS);
		
//		String classesToExcludeCSV = "org.eclipse.jdt.internal.junit, org.eclipse.jdt.internal.junit4";
//		prop.setProperty(PropertyUtil.TRANFORMER_DISABLED_ON_CLASSES_LABEL, classesToExcludeCSV);
		prop.setProperty(PropertyUtil.GOVERNANCE_MANAGER_CLASS_LABEL, GM_ACTIVATION_WITH_PROBABILITY_CLASSNAME);
		
//		GovernanceManagerFactory.getInstance().getThreadHarness().decThreads();
		
		for (int maxThreads = INIT_THERADS; maxThreads <= MAX_THERADS; maxThreads+=THERADS_INC_STEP) {
			Vector<Float> elapsedTimes = new Vector<Float>();
			for (int i = 0; i < REPETITIONS; i++) {
				float et = this.firstSimpleTest(maxThreads);
				elapsedTimes.add(et);
			}
			this.elapsedTimesMatrix.add(elapsedTimes);
		}
		this.printAveragesForTherads();
	}
	
	@Ignore
	@Test
	public void maxActivationFrequencyTest(){		
		PropertyUtilNoSingleton prop = PropertyUtilNoSingleton.getInstance();
		
		prop.setProperty(PropertyUtil.GOVERNANCE_MANAGER_CLASS_LABEL, GM_ACTIVATION_WITH_PROBABILITY_CLASSNAME);
		
//		GovernanceManagerFactory.getInstance().getThreadHarness().decThreads();
		
		for (float activationProbability = INIT_ACTIVATION_PROBABILITY; activationProbability <= MAX_ACTIVATION_PROBABILITY; activationProbability+=ACTIVATION_PROBABILITY_INC_STEP) {
			TestGovernanceManager_ActivationWithProbability.setActivationProbability(activationProbability);

			Vector<Float> elapsedTimes = new Vector<Float>();
			for (int i = 0; i < REPETITIONS; i++) {
				float et = this.firstSimpleTest(NUMER_OF_THERADS_WHEN_VARYING_ACTIVATION_PROBABILITY);
				elapsedTimes.add(et);
			}
			this.elapsedTimesMatrix.add(elapsedTimes);
		}
		this.printAveragesActivationProability();
	}

	private float firstSimpleTest(int size){		
		System.out.println("Started Processing, size: " + size + "; Activation Probability: " +	TestGovernanceManager_ActivationWithProbability.getActivationProbability());

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
	
	private void printAveragesForTherads() {
		int count = INIT_THERADS;
		for (Vector<Float> vector : this.elapsedTimesMatrix) {
			float averageET = 0f;
			String etCSV = "";
			if (vector.size() != 0) {
				for (Float et : vector) {
					averageET += et.floatValue();
					etCSV = etCSV + ", " + et.toString();
				}
				averageET = averageET / vector.size();
			}
			etCSV = etCSV.replaceFirst(", ", "");
			
			System.out.println("Average When Max Therads: " + count +" ---> "+averageET + "["+etCSV+"] ("+vector.size()+")");

			count += THERADS_INC_STEP;
		}
	}

	private void printAveragesActivationProability() {
		float apCount = INIT_ACTIVATION_PROBABILITY;
		for (Vector<Float> vector : this.elapsedTimesMatrix) {
			float averageET = 0f;
			String etCSV = "";
			if (vector.size() != 0) {
				for (Float et : vector) {
					averageET += et.floatValue();
					etCSV = etCSV + ", " + et.toString();
				}
				averageET = averageET / vector.size();
			}
			etCSV = etCSV.replaceFirst(", ", "");

			System.out.println("Average When AP: " + apCount +" ---> "+averageET + "["+etCSV+"] ("+vector.size()+")");

			apCount += ACTIVATION_PROBABILITY_INC_STEP;
		}
	}
}
