package it.cnr.iasi.saks.groucho.lsh.rest.api.test;

import javax.xml.ws.Response;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.cnr.iasi.saks.groucho.lsh.rest.api.impl.IsunknownApiController;

public class SimpleRestAPITest_IT {

	private final String DUMMY_CARVED_STATE = "{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}";
	private final String DUMMY_CARVED_STATE_5 = "{dummy-->{dc-->{dummy-->{dc-->{dummy-->[]%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->[]%fieldString-->deafult%otherDummy-->[]}%myList-->{elementData-->[çççç]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}%myList-->{elementData-->[{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}ç{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}]%size-->5}%mySimpleState-->999%v-->{capacityIncrement-->0%elementCount-->5%elementData-->[10ç20ç30ç40ç50]}%words-->{capacityIncrement-->0%elementCount-->2%elementData-->[FooçBoo]}}%fieldBoolean-->true%fieldChar-->d%fieldInt-->-1%fieldObject-->{}%fieldString-->deafult%otherDummy-->{fieldBooleanOther-->false%fieldCharOther-->c%fieldEnum-->three%fieldIntOther-->88%fieldStringOther-->thisIsFoo}}";

	private final String FAKE_LSH_STRING = "9832ndnkjsao8792932jdlaksnlk7aè";

	private MockMvc mockMvc;
	private Response response;
	 
	@Before
    public void setUp() {
//		IsunknownApiController isUnknownApiController = new IsunknownApiController(new ObjectMapper(), new MockHttpServletRequest());
// DELETE THE DEFAULT CONTRUCTOR IN: it.cnr.iasi.saks.groucho.lsh.rest.api.impl.IsunknownApiController
		IsunknownApiController isUnknownApiController = new IsunknownApiController();
		System.out.println("this is the hash code:" + isUnknownApiController.hashCode());
		System.out.println("..done");
//		isUnknownApiController.hashCode();
//        mockMvc = MockMvcBuilders.standaloneSetup(isUnknownApiController).build();
    }
	
//	 @Test
//	    public void testHome() throws Exception {
//	        mockMvc.perform(MockMvcRequestBuilders.get("/isunknown").param("value", FAKE_LSH_STRING))
//	                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(Boolean.TRUE.toString()));
//	 
//	 }

	@Test
    public void warningMessage() throws Exception {
		System.err.println("****************************************\n********** REMEMBER TO IMPLEMENT THE INTEGRATION TESTS IN THE CLASS: " + SimpleRestAPITest_IT.class.getCanonicalName() + " **********\n****************************************");
	}
}
