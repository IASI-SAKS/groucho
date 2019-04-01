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
public class CheckAllInterfacesTest {
	
	private String className;
	private String expectedResul;
	
	public CheckAllInterfacesTest(String expectedResult, String className) {
		this.className = className;
		this.expectedResul = expectedResult;
	}
	
	@Parameters
	public static Collection<String[]> getParameters(){
		return Arrays.asList(new String[][]{
				{"true", "it.cnr.iasi.saks.groucho.threadSignalTests.OtherSimpleThread"}, // expected, value
				{"false", "it.cnr.iasi.saks.groucho.devTests.utils.Foo"}, // expected, valueOne, value
				{"false", "java.io.LineNumberReader"}, // expected, value
				{"true", "it.cnr.iasi.saks.groucho.threadSignalTests.SimpleThread"}, // expected, value
				{"false", "java.lang.Object"}, // expected, value
				{"false", "it.cnr.iasi.saks.groucho.instrument.GrouchoClassVisitor"}, // expected, value				
				{"false", "it.cnr.iasi.saks.groucho.instrument.GrouchoClassTransformer"}, // expected, value
				{"true", "it.cnr.iasi.saks.groucho.devTests.utils.FooThread"}, // expected, valueOne, value
				{"true", "it.cnr.iasi.saks.groucho.devTests.utils.OtherFooThread"}, // expected, valueOne, value
		});
	}

	@Test
	public void isImplementingRunnableSomehowTest(){
		System.out.println("Is Implementing Runnable Somehow: "+this.className);
		GrouchoClassVisitor_TestVersion cv = new GrouchoClassVisitor_TestVersion(null, this.className);
		boolean result = cv.isImplementingRunnableSomehow();

		System.out.println(" \t " + this.expectedResul + " --> " + result);
		Assert.assertEquals(Boolean.parseBoolean(this.expectedResul), result);
	}

}
