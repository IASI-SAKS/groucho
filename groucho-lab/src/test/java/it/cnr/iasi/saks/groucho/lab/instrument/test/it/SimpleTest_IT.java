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
