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
package it.cnr.iasi.saks.groucho.carvingStateTests.dummyValues;

import org.junit.Ignore;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.carvingStateTests.DummyInvivoTestClass;
import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;
import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.common.StateCarver;
import junit.framework.Assert;

public class DummyValuesGeneratorDriver {
	private DummyClassWithDummyValues dummyObject;
	
	private String instrumentedClassName;
	private String instrumentedMethodName;
	
	private String invivoTestClass;	
	private String invivoTest;
		
	private static final int MAX_SAMPLES = 100;
	private static final int MAX_DEPTH = 5;
	
	public DummyValuesGeneratorDriver() {
		this.dummyObject = new DummyClassWithDummyValues();
		
		this.instrumentedClassName =  this.dummyObject.getClass().getCanonicalName();
		this.instrumentedMethodName = "dummyMethodWithNoSoCommonPath";
		
		this.invivoTestClass = DummyInvivoTestClass.class.getCanonicalName();		
		this.invivoTest = "dummyInvivoTest";
	}

	@Ignore
	@Test
	public void basicGeneration(){
		this.basicGeneration(MAX_SAMPLES);
	}
	
	public void basicGeneration(int maxSamples){
		String carvedState="";
		for (int i = 0; i < maxSamples; i++){
			this.dummyObject = new DummyClassWithDummyValues();
			Context testingContext = new Context(this.dummyObject, this.instrumentedClassName, this.instrumentedMethodName, this.invivoTestClass, this.invivoTest);

			StateCarver carver = new StateCarver(testingContext);
			try {
				carvedState = carver.carveAllFields();
				System.out.println("Carved State: "+ carvedState);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
		}	
	}


	@Ignore
	@Test
	public void deeperBasicGeneration(){
		this.deeperBasicGeneration(MAX_SAMPLES);
	}
	
	public void deeperBasicGeneration(int maxSamples) {
		String carvedState = "";
		for (int i = 0; i < maxSamples; i++) {
			this.dummyObject = new DummyClassWithDummyValues();			
			int depth = RandomGenerator.getInstance().nextInt(MAX_DEPTH)+1;
			
			Context testingContext = new Context(this.dummyObject, this.instrumentedClassName, this.instrumentedMethodName, this.invivoTestClass, this.invivoTest, depth);

			StateCarver carver = new StateCarver(testingContext);
			try {
				carvedState = carver.carveAllFields();
				System.out.println("Carved State: " + carvedState);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
		}
	}
}
