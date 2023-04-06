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
package it.cnr.iasi.saks.groucho.lsh.service.test;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;

import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.service.IsunknownApiService;
import it.cnr.iasi.saks.groucho.lsh.service.MarkApiService;
import it.cnr.iasi.saks.groucho.lsh.service.ResetApiService;
import it.cnr.iasi.saks.groucho.lsh.service.impl.IsunknownApiServiceImpl;
import it.cnr.iasi.saks.groucho.lsh.service.impl.MarkApiServiceImpl;
import it.cnr.iasi.saks.groucho.lsh.service.impl.ResetApiServiceImpl;
import it.cnr.iasi.saks.groucho.lsh.tests.util.ConcurrentStateObserverFactoryNoSingleton;

public class ApiServiceSimpleTest {

	private final String DUMMY_CARVED_STATE = "{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}";
	private final String DUMMY_CARVED_STATE_5 = "{dummy-->{dc-->{dummy-->{dc-->{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}%myList-->{elementData-->[çççç]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}%myList-->{elementData-->[{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}";

	private final String FAKE_LSH_STRING = "9832ndnkjsao8792932jdlaksnlk7aè";

	private IsunknownApiService isunknownService;
	private MarkApiService markService;
	private ResetApiService resetService;

	public ApiServiceSimpleTest() {
		this.isunknownService = new IsunknownApiServiceImpl();
		this.markService = new MarkApiServiceImpl();
		this.resetService = new ResetApiServiceImpl();
	}

	@Before
	@After
	public void cleanUp() throws LSHException {
		ConcurrentStateObserverFactoryNoSingleton.resetFactory();
	}

	@Test
	public void knownStateTest() {
		this.markService.markState(DUMMY_CARVED_STATE_5);

		ResponseEntity<Boolean> response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE_5);
		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		
		boolean condition = response.getBody();
		Assert.assertFalse(condition);
	}

	@Test
	public void knownStateTestLSH() {
		this.markService.markStateLSH(FAKE_LSH_STRING);

		ResponseEntity<Boolean> response = this.isunknownService.isStateUnknownLSH(FAKE_LSH_STRING);
		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		
		boolean condition = response.getBody();
		Assert.assertFalse(condition);
	}

	@Test
	public void unknownStateTest() {
		this.markService.markState(DUMMY_CARVED_STATE_5);

		ResponseEntity<Boolean> response = this.resetService.resetStateObserver();
		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		
		boolean condition = response.getBody();
		Assert.assertTrue(condition);

		response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE_5);
		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		
		condition = response.getBody();
		Assert.assertTrue(condition);
	}

	@Test
	public void unknownStateMultipleChecksTest() {
		ResponseEntity<Boolean> response = this.resetService.resetStateObserver();
		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		
		boolean condition = response.getBody();
		Assert.assertTrue(condition);
		
		for (int i=0; i<5; i++){
			response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE_5);
			Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
			
			condition = response.getBody();
			Assert.assertTrue(condition);
		}
	}

	@Test
	public void unknownStateTestLSH() {
		this.markService.markStateLSH(FAKE_LSH_STRING);

		ResponseEntity<Boolean> response = this.resetService.resetStateObserver();
		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		
		boolean condition = response.getBody();
		Assert.assertTrue(condition);

		response = this.isunknownService.isStateUnknownLSH(FAKE_LSH_STRING);
		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		
		condition = response.getBody();
		Assert.assertTrue(condition);
	}

	@Test
	public void unknownStateMultipleChecksTestLSH() {
		ResponseEntity<Boolean> response = this.resetService.resetStateObserver();
		Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
		
		boolean condition = response.getBody();
		Assert.assertTrue(condition);
		
		for (int i=0; i<5; i++) {
			response = this.isunknownService.isStateUnknownLSH(FAKE_LSH_STRING);
			Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
			
			condition = response.getBody();
			Assert.assertTrue(condition);
		}
	}
}
	