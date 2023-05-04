package it.cnr.iasi.saks.groucho.lsh.tests;

import org.junit.Assert;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.jep.LSHInvivoJep;

public class LSHInvivoResilienceTest {

	private LSHInvivoJep obs; 

	private final String FAKE_LSH_STRING = "9832ndnkjsao'879293\"2jdla,ksnlk7aè";	

	public LSHInvivoResilienceTest() throws LSHException{		
		System.out.println(System.getProperty("java.library.path"));

		this.obs = new LSHInvivoJep();		
		this.obs.resetStateObserver();
	}
	
	private void genericTest (String prefix) throws LSHException {
		String state = prefix + FAKE_LSH_STRING;	
		boolean condition = this.obs.isStateUnknown(state);
		Assert.assertTrue(condition);		
	}
	
	@Test
	public void optionsTest() throws LSHException{
		this.genericTest("-");
	}

	@Test
	public void quoteTest() throws LSHException{
		this.genericTest("\"");
	}

	@Test
	public void singlequoteTest() throws LSHException{
		this.genericTest("'");
	}

	@Test
	public void comaTest() throws LSHException{
		this.genericTest(",");
		this.genericTest(":");
		this.genericTest(";");
		this.genericTest(".");
	}

	@Test
	public void squaresTest() throws LSHException{
		this.genericTest("[");
		this.genericTest("]");
		this.genericTest("(");
		this.genericTest(")");
	}

	@Test
	public void dollarTest() throws LSHException{
		this.genericTest("$");
	}

	@Test
	public void redirectTest() throws LSHException{
		this.genericTest(">");
		this.genericTest("<");
	}

	@Test
	public void pipeTest() throws LSHException{
		this.genericTest("|");
	}
}
