package it.cnr.iasi.saks.groucho.lsh.rest.api.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.rest_client.StateObserverRestClientFactory;


public class SimpleLSHRestAPITest_IT {

	private final String FAKE_LSH_STRING = "9832ndnkjsao8792932jdlaksnlk7aé";
	private final String FAKE_LSH_STRING_BIS = "jkhdkabncyu87a9sncjnksajgdn11";

	private StateObserverLSH obsLSH;
	
    public SimpleLSHRestAPITest_IT() throws LSHException {
    	StateObserverRestClientFactory factory = new StateObserverRestClientFactory();
    	this.obsLSH =  factory.getStateObserverLSH();
    	
		this.obsLSH.resetStateObserver();
     }
    
	@Test
	public void basicTest() throws LSHException{
		this.obsLSH.markState(FAKE_LSH_STRING_BIS);
		boolean condition = this.obsLSH.isStateUnknown(FAKE_LSH_STRING_BIS);		
// ---------------------------------------------------
		Assert.assertEquals(false, condition);
// ---------------------------------------------------
//		Assert.assertFalse(condition);
// ---------------------------------------------------
	}

	@Test
	public void anotherBasicTest() throws LSHException{
		boolean condition = this.obsLSH.isStateUnknown(FAKE_LSH_STRING_BIS);
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
		this.obsLSH.markState(FAKE_LSH_STRING);
		boolean condition = this.obsLSH.isStateUnknown(FAKE_LSH_STRING);
		Assert.assertFalse(condition);
	}

	@Test
	public void unknownStateTest() throws LSHException {
		this.obsLSH.markState(FAKE_LSH_STRING);

		this.obsLSH.resetStateObserver();

		boolean condition = this.obsLSH.isStateUnknown(FAKE_LSH_STRING);

// ---------------------------------------------------
		Assert.assertEquals(true, condition);
// ---------------------------------------------------
//		Assert.assertTrue(condition);
// ---------------------------------------------------
	}

	@Test
	public void unknownStateMultipleChecksTest() throws LSHException {
		this.obsLSH.resetStateObserver();
		
		for (int i=0; i<5; i++){
			boolean condition = this.obsLSH.isStateUnknown(FAKE_LSH_STRING);

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
