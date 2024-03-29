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
package it.cnr.iasi.saks.groucho.lsh.tests;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;

import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.jep.LSHInvivoJep;

public class SimpleLSHInvivoJepTest {

	private LSHInvivoJep obs; 
	private final String DUMMY_CARVED_STATE = "{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}";
	private final String DUMMY_CARVED_STATE_5 = "{dummy-->{dc-->{dummy-->{dc-->{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}%myList-->{elementData-->[çççç]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}%myList-->{elementData-->[{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}";

	private final String FAKE_LSH_STRING = "9832ndnkjsao8792932jdlaksnlk7aè";	
	
	public SimpleLSHInvivoJepTest() throws LSHException{		
		System.out.println(System.getProperty("java.library.path"));

		this.obs = new LSHInvivoJep();		
		this.obs.resetStateObserver();
	}
	
	
	@Test
	public void basicTest() throws LSHException{
		this.obs.markState(DUMMY_CARVED_STATE_5);
		boolean condition = this.obs.isStateUnknown(DUMMY_CARVED_STATE_5);
//		this.obs.detachJEP();
		
// ---------------------------------------------------
		Assert.assertEquals(false, condition);
// ---------------------------------------------------
//		Assert.assertFalse(condition);
// ---------------------------------------------------
	}

	@Test
	public void anotherBasicTest() throws LSHException{
		boolean condition = this.obs.isStateUnknown(FAKE_LSH_STRING);
//		this.obs.detachJEP();

// ---------------------------------------------------
		Assert.assertEquals(true, condition);
// ---------------------------------------------------
//		Assert.assertTrue(condition);
// ---------------------------------------------------
	}
	
//	***********************************************************************************
//	***********************************************************************************
	
	@Test
	public void knownStateTest() throws LSHException {
		this.obs.markState(DUMMY_CARVED_STATE_5);
		boolean condition = this.obs.isStateUnknown(DUMMY_CARVED_STATE_5);
		Assert.assertFalse(condition);
	}

	@Test
	public void unknownStateTest() throws LSHException {
		this.obs.markState(DUMMY_CARVED_STATE_5);

		this.obs.resetStateObserver();

		boolean condition = this.obs.isStateUnknown(DUMMY_CARVED_STATE_5);

// ---------------------------------------------------
		Assert.assertEquals(true, condition);
// ---------------------------------------------------
//		Assert.assertTrue(condition);
// ---------------------------------------------------
	}

	@Test
	public void unknownStateMultipleChecksTest() throws LSHException {
		this.obs.resetStateObserver();
		
		for (int i=0; i<5; i++){
			boolean condition = this.obs.isStateUnknown(DUMMY_CARVED_STATE_5);

// ---------------------------------------------------
			Assert.assertEquals(true, condition);
// ---------------------------------------------------
//			Assert.assertTrue(condition);
// ---------------------------------------------------
		}
	}
		
}
