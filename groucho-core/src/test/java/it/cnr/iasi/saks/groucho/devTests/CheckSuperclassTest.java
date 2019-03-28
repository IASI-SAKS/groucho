package it.cnr.iasi.saks.groucho.devTests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import it.cnr.iasi.saks.groucho.devTests.utils.GrouchoClassVisitor_TestVersion;
import junit.framework.Assert;

@RunWith(value=Parameterized.class)
public class CheckSuperclassTest {
	
	private String className;
	private String expectedResul;
	
	public CheckSuperclassTest(String expectedResult, String className) {
		this.className = className;
		this.expectedResul = expectedResult;
	}
	
	@Parameters
	public static Collection<String[]> getParameters(){
		return Arrays.asList(new String[][]{
				{"true", "it.cnr.iasi.saks.groucho.devTests.utils.GrouchoClassVisitor_TestVersion"}, // expected, value
				{"false", "it.cnr.iasi.saks.groucho.devTests.utils.Foo"}, // expected, valueOne, value
				{"true", "java.io.LineNumberReader"}, // expected, value
				{"false", "it.cnr.iasi.saks.groucho.threadSignalTests.SimpleThread"}, // expected, value
				{"false", "java.lang.Object"}, // expected, value
		});
	}

	@Test
	public void hasExplicitSuperclassTest(){
		GrouchoClassVisitor_TestVersion cv = new GrouchoClassVisitor_TestVersion(null, this.className);
		boolean result = cv.hasExplicitSuperclass(className);

		System.out.println(this.expectedResul + " --> " + result);
		Assert.assertEquals(Boolean.parseBoolean(this.expectedResul), result);
	}

}
