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
package it.cnr.iasi.saks.groucho.carvingStateTests;

import org.junit.Test;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.common.StateCarver;
import junit.framework.Assert;

public class SimpleCarvingStateTest {
	private DummyClass dummyObject;
	
	private String instrumentedClassName;
	private String instrumentedMethodName;
	
	private String invivoTestClass;	
	private String invivoTest;
	
	private final String ORACLE_DEPTH_DEFAULT = "[fieldInt-->-1%fieldBoolean-->true%fieldString-->deafult%fieldChar-->d%fieldObject-->[]%dummy-->[]]";
	private final String ORACLE_DEPTH_5 = "[fieldInt-->-1%fieldBoolean-->true%fieldString-->deafult%fieldChar-->d%fieldObject-->[]%dummy-->[dc-->[fieldInt-->-1%fieldBoolean-->true%fieldString-->deafult%fieldChar-->d%fieldObject-->[]%dummy-->[dc-->[fieldInt-->-1%fieldBoolean-->true%fieldString-->deafult%fieldChar-->d%fieldObject-->[]%dummy-->[]]%v-->[elementData-->[]%elementCount-->null%capacityIncrement-->null%serialVersionUID-->-2767605614048989439%MAX_ARRAY_SIZE-->2147483639]%mySimpleState-->999]]%v-->[elementData-->[]%elementCount-->null%capacityIncrement-->null%serialVersionUID-->-2767605614048989439%MAX_ARRAY_SIZE-->2147483639]%mySimpleState-->999]]";

	public SimpleCarvingStateTest() {
		this.dummyObject = new DummyClass();
		
		this.instrumentedClassName =  this.dummyObject.getClass().getCanonicalName();
		this.instrumentedMethodName = "dummyMethodUnderInvivoSession";
		
		this.invivoTestClass = DummyInvivoTestClass.class.getCanonicalName();		
		this.invivoTest = "dummyInvivoTest";
	}

	@Test
	public void basicTest(){
		String carvedState="";
		Context testingContext = new Context(this.dummyObject, this.instrumentedClassName, this.instrumentedMethodName, this.invivoTestClass, this.invivoTest);

		StateCarver carver = new StateCarver(testingContext);
		try {
			carvedState = carver.carveAllFields();
			System.out.println("Carved State: "+ carvedState);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals(ORACLE_DEPTH_DEFAULT, carvedState);
	}


	@Test
	public void deeperBasicTest(){
		String carvedState="";
		Context testingContext = new Context(this.dummyObject, this.instrumentedClassName, this.instrumentedMethodName, this.invivoTestClass, this.invivoTest, 5);

		StateCarver carver = new StateCarver(testingContext);
		try {
			carvedState = carver.carveAllFields();
			System.out.println("Carved State: "+ carvedState);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals(ORACLE_DEPTH_5, carvedState);
	}

	@Test
	public void otherTest(){
		String carvedState="";
		Context testingContext = new Context(this.dummyObject, this.instrumentedClassName, this.instrumentedMethodName, this.invivoTestClass, this.invivoTest);

		TestGovernanceManager_DenyActivation gm = new TestGovernanceManager_DenyActivation();
		gm.runInvivoTestingSession(testingContext);
		
		carvedState = gm.getCarvedState();
		System.out.println("Carved State: "+ carvedState);

		Assert.assertEquals(ORACLE_DEPTH_DEFAULT, carvedState);
	}
}
