package it.cnr.iasi.saks.groucho.lsh.service.test;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.StateObserverFactory;
import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.service.IsunknownApiService;
import it.cnr.iasi.saks.groucho.lsh.service.MarkApiService;
import it.cnr.iasi.saks.groucho.lsh.service.ResetApiService;
import it.cnr.iasi.saks.groucho.lsh.service.impl.IsunknownApiServiceImpl;
import it.cnr.iasi.saks.groucho.lsh.service.impl.MarkApiServiceImpl;
import it.cnr.iasi.saks.groucho.lsh.service.impl.ResetApiServiceImpl;
import it.cnr.iasi.saks.groucho.lsh.tests.util.StateObserverFactoryNoSingleton;

public class ApiServiceSingletonTest {

	private final String DUMMY_CARVED_STATE = "{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}";
	private final String DUMMY_CARVED_STATE_5 = "{dummy-->{dc-->{dummy-->{dc-->{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}%myList-->{elementData-->[çççç]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}%myList-->{elementData-->[{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}";

	private final String FAKE_LSH_STRING = "9832ndnkjsao8792932jdlaksnlk7aè";
	private final String ANOTHER_FAKE_LSH_STRING = "kjkdi9879879sahjhnsajsiqqqaasz";

	private IsunknownApiService isunknownService;
	private MarkApiService markService;
	
	private volatile static boolean DUMMY_CARVED_STATE_KNOWN = false;
	private volatile static boolean FAKE_LSH_STRING_KNOWN = false;
	
	public ApiServiceSingletonTest() throws LSHException{
		this.isunknownService = new IsunknownApiServiceImpl();
		this.markService = new MarkApiServiceImpl();
	}
		
	@BeforeClass
	public static void purgeSignleton() throws LSHException{
		StateObserverFactory soFactory = StateObserverFactory.getInstance();
		soFactory.getStateObserver().resetStateObserver();
		soFactory.getStateObserverLSH().resetStateObserver();
	}
	
	@Test
	public void thisIsATest() throws LSHException {
		if (DUMMY_CARVED_STATE_KNOWN) {
			this.dummyCarvedStateKnown();
		}
		else {
			this.dummyCarvedStateBecomesKnown();
			DUMMY_CARVED_STATE_KNOWN = true;
		}
	}

	@Test
	public void thisIsAnotherTest() throws LSHException {
		if (DUMMY_CARVED_STATE_KNOWN) {
			this.dummyCarvedStateKnown();
		}
		else {
			this.dummyCarvedStateBecomesKnown();
			DUMMY_CARVED_STATE_KNOWN = true;
		}
	}
	
	@Test
	public void thisIsAnLSHTest() throws LSHException {
		if (FAKE_LSH_STRING_KNOWN) {
			this.fakeLSHStringKnown();
		}
		else {
			this.fakeLSHStringBecomesKnown();
			FAKE_LSH_STRING_KNOWN = true;
		}
	}

	@Test
	public void thisIsAnotherLSHTest() throws LSHException {
		if (FAKE_LSH_STRING_KNOWN) {
			this.fakeLSHStringKnown();
		}
		else {
			this.fakeLSHStringBecomesKnown();
			FAKE_LSH_STRING_KNOWN = true;
		}
	}
		
	private void dummyCarvedStateKnown() throws LSHException{
		this.markService.markState(DUMMY_CARVED_STATE_5);
		
		ResponseEntity<Boolean> response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE_5);		
		boolean condition = response.getBody();
		Assert.assertFalse(condition);
		
		response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE);		
		condition = response.getBody();
		Assert.assertFalse(condition);
	}
	
	private void dummyCarvedStateBecomesKnown() throws LSHException{		
		this.markService.markState(DUMMY_CARVED_STATE);
		
		ResponseEntity<Boolean> response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE);		
		boolean condition = response.getBody();
		Assert.assertFalse(condition);
		
		response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE_5);		
		condition = response.getBody();
		Assert.assertTrue(condition);
	}
		
	private void fakeLSHStringKnown() throws LSHException{
		this.markService.markState(ANOTHER_FAKE_LSH_STRING);
		
		ResponseEntity<Boolean> response = this.isunknownService.isStateUnknown(ANOTHER_FAKE_LSH_STRING);		
		boolean condition = response.getBody();
		Assert.assertFalse(condition);
		
		response = this.isunknownService.isStateUnknown(FAKE_LSH_STRING);		
		condition = response.getBody();
		Assert.assertFalse(condition);
	}
	
	private void fakeLSHStringBecomesKnown() throws LSHException{		
		this.markService.markState(FAKE_LSH_STRING);
		
		ResponseEntity<Boolean> response = this.isunknownService.isStateUnknown(FAKE_LSH_STRING);		
		boolean condition = response.getBody();
		Assert.assertFalse(condition);
		
		response = this.isunknownService.isStateUnknown(ANOTHER_FAKE_LSH_STRING);		
		condition = response.getBody();
		Assert.assertTrue(condition);
	}

}
