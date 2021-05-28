package it.cnr.iasi.saks.groucho.lsh.service.test;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.junit.Assert;
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

public class ApiServiceSimpleTest {

	private final String DUMMY_CARVED_STATE = "{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}";
	private final String DUMMY_CARVED_STATE_5 = "{dummy-->{dc-->{dummy-->{dc-->{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}%myList-->{elementData-->[çççç]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}%myList-->{elementData-->[{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}";

	private final String FAKE_LSH_STRING = "9832ndnkjsao8792932jdlaksnlk7aè";

	private IsunknownApiService isunknownService;
	private MarkApiService markService;
	private ResetApiService resetService;
	
	public ApiServiceSimpleTest() throws LSHException{
		// the following statement binds the StateObserverFactory to a No Singleton instance.
		// in other words, all the other classes accessing to
		// StateObserverFactory.getInstance();
		// will not be affected by singleton		
		StateObserverFactory soFactory = StateObserverFactoryNoSingleton.getInstance();
		
		this.isunknownService = new IsunknownApiServiceImpl();
		this.markService = new MarkApiServiceImpl();
		this.resetService = new ResetApiServiceImpl();
	}
	
	
	@Test
	public void knownStateTest() throws LSHException{		
		this.markService.markState(DUMMY_CARVED_STATE_5);
		
		ResponseEntity<Boolean> response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE_5);		
		boolean condition = response.getBody();
		
		Assert.assertFalse(condition);
	}

	
	@Test
	public void knownStateTestLSH() throws LSHException{		
		this.markService.markStateLSH(FAKE_LSH_STRING);
		
		ResponseEntity<Boolean> response = this.isunknownService.isStateUnknownLSH(FAKE_LSH_STRING);		
		boolean condition = response.getBody();
		
		Assert.assertFalse(condition);
	}

	
	@Test
	public void unknownStateTest() throws LSHException{		
		this.markService.markState(DUMMY_CARVED_STATE_5);
		
		ResponseEntity<Boolean> response = this.resetService.resetStateObserver();		
		boolean condition = response.getBody();
		Assert.assertTrue(condition);
		
		response = this.isunknownService.isStateUnknown(DUMMY_CARVED_STATE_5);		
		condition = response.getBody();
		Assert.assertTrue(condition);
	}

	
	@Test
	public void unknownStateTestLSH() throws LSHException{		
		this.markService.markStateLSH(FAKE_LSH_STRING);
				
		ResponseEntity<Boolean> response = this.resetService.resetStateObserver();		
		boolean condition = response.getBody();
		Assert.assertTrue(condition);
		
		response = this.isunknownService.isStateUnknownLSH(FAKE_LSH_STRING);		
		condition = response.getBody();
		Assert.assertTrue(condition);
	}
}
