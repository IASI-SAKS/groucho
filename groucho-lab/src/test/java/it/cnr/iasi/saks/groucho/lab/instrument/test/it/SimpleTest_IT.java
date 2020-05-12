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
package it.cnr.iasi.saks.groucho.lab.instrument.test.it;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import it.cnr.iasi.saks.foo.SimpleClass;
import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;

public class SimpleTest_IT {
	
	private String ORACLE_INVIVO_TEST_CLASS = "it.cnr.iasi.saks.groucho.lab.instrument.test.utils.FooInvivoTestClass";
	private String ORACLE_INVIVO_TEST = "invivoTestMethod";
	private int ORACLE_INVIVO_CARVING_DEPTH = 1;
	private boolean ORACLE_INVIVO_PAUSE_OTHER_THREADS = false;
	
	
	@Test
	public void taggingWithInvivoTestAnnotationMethodUntagged(){
		SimpleClass s = new SimpleClass();

		Method reflectedMethod = null;
		try {
			System.out.println("-->"+SimpleClass.class.getCanonicalName());
			reflectedMethod = SimpleClass.class.getMethod("otherDummyMethod",boolean.class, int.class);
		} catch (NoSuchMethodException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		}
		
		TestableInVivo invivoAnnotation = reflectedMethod.getAnnotation(TestableInVivo.class);
		String errMessage = "the method:"+ SimpleClass.class.getCanonicalName()+":"+reflectedMethod.getName() +" has not been tagged";
		Assert.assertNotNull(errMessage,invivoAnnotation);
		
		Assert.assertEquals(ORACLE_INVIVO_TEST_CLASS, invivoAnnotation.invivoTestClass());
		Assert.assertEquals(ORACLE_INVIVO_TEST, invivoAnnotation.invivoTest());
		Assert.assertEquals(ORACLE_INVIVO_CARVING_DEPTH, invivoAnnotation.carvingDepth());
		Assert.assertEquals(ORACLE_INVIVO_PAUSE_OTHER_THREADS, invivoAnnotation.pauseOtherThreads());	
	}

	@Test
	public void taggingWithInvivoTestAnnotationMethodAlreadyTagged(){
		SimpleClass s = new SimpleClass();

		Method reflectedMethod = null;
		try {
			System.out.println("-->"+SimpleClass.class.getCanonicalName());
			reflectedMethod = SimpleClass.class.getMethod("printMessage",String.class);
		} catch (NoSuchMethodException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		}
		
		TestableInVivo invivoAnnotation = reflectedMethod.getAnnotation(TestableInVivo.class);
		String errMessage = "the method:"+ SimpleClass.class.getCanonicalName()+":"+reflectedMethod.getName() +" has not been tagged";
		Assert.assertNotNull(errMessage,invivoAnnotation);
		
		Assert.assertEquals(ORACLE_INVIVO_TEST_CLASS, invivoAnnotation.invivoTestClass());
		Assert.assertFalse(invivoAnnotation.invivoTest().equals(ORACLE_INVIVO_TEST));
		Assert.assertEquals(ORACLE_INVIVO_CARVING_DEPTH, invivoAnnotation.carvingDepth());
		Assert.assertEquals(ORACLE_INVIVO_PAUSE_OTHER_THREADS, invivoAnnotation.pauseOtherThreads());	
	}

	@Test
	public void taggingWithInvivoTestAnnotationMethodNotListedInTheModel(){
		SimpleClass s = new SimpleClass();

		Method reflectedMethod = null;
		try {
			System.out.println("-->"+SimpleClass.class.getCanonicalName());
			reflectedMethod = SimpleClass.class.getMethod("dummyMethod",int.class,Object.class);
		} catch (NoSuchMethodException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		}
		
		TestableInVivo invivoAnnotation = reflectedMethod.getAnnotation(TestableInVivo.class);
		Assert.assertNull(invivoAnnotation);		
	}

}
