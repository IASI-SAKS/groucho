package it.cnr.iasi.saks.groucho.lsh.rest.api.test;

import org.junit.Assert;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.rest_client.StateObserverRestClientFactory;

public class SimpleRestAPITest_IT {

	private final String DUMMY_CARVED_STATE = "{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}";
	private final String DUMMY_CARVED_STATE_5 = "{dummy-->{dc-->{dummy-->{dc-->{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}%myList-->{elementData-->[çççç]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}%myList-->{elementData-->[{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}";

	private StateObserver obs;
	
    public SimpleRestAPITest_IT() throws LSHException {
    	StateObserverRestClientFactory factory = new StateObserverRestClientFactory();
    	this.obs =  factory.getStateObserver();
    	
		this.obs.resetStateObserver();
     }
    
	@Test
	public void basicTest() throws LSHException{
		this.obs.markState(DUMMY_CARVED_STATE_5);
		boolean condition = this.obs.isStateUnknown(DUMMY_CARVED_STATE_5);		
// ---------------------------------------------------
		Assert.assertEquals(false, condition);
// ---------------------------------------------------
//		Assert.assertFalse(condition);
// ---------------------------------------------------
	}

	@Test
	public void anotherBasicTest() throws LSHException{
		boolean condition = this.obs.isStateUnknown(DUMMY_CARVED_STATE);
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
	
////	*****************************************************
//		@Test
//	    public void testHome() throws Exception {
//	        arg0 = MockMvcRequestBuilders.get("/groucho-state-observer/isunknown/"+FAKE_LSH_STRING).accept(MediaType.APPLICATION_JSON);
//			this.mockMvc.perform(arg0).andExpect(MockMvcResultMatchers.status().isOk());
//	        this.mockMvc.perform(MockMvcRequestBuilders.get("/groucho-state-observer/isunknown/"+FAKE_LSH_STRING).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
////	        mockMvc.perform(MockMvcRequestBuilders.get("/groucho-state-observer/isunknown").param("value", FAKE_LSH_STRING))
////            .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(Boolean.TRUE.toString()));
//	 
//	 }

}
