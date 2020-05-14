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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;
import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.LRUMemoryCachePrinterInvivoTestClass;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import it.cnr.iasi.saks.groucho.tests.util.PropertyUtilNoSingleton;

import org.apache.jcs.engine.memory.lru.LRUMemoryCache;
import org.apache.jcs.engine.CacheElement;
import org.apache.jcs.engine.CompositeCacheAttributes;
import org.apache.jcs.engine.ElementAttributes;
import org.apache.jcs.engine.behavior.ICacheElement;
import org.apache.jcs.engine.behavior.ICompositeCacheAttributes;
import org.apache.jcs.engine.behavior.IElementAttributes;
import org.apache.jcs.engine.control.CompositeCache;

public class SimpleJCSTest_IT {
	private String ORACLE_INVIVO_TEST_CLASS = "it.cnr.iasi.saks.groucho.lab.instrument.test.utils.LRUMemoryCachePrinterInvivoTestClass";
	private String ORACLE_INVIVO_TEST = "invivoTestMethod";
	private int ORACLE_INVIVO_CARVING_DEPTH = 1;
	private boolean ORACLE_INVIVO_PAUSE_OTHER_THREADS = false;
		
	private TestableInVivo retrieveInvivoAnnotation(Class<?> c, String methodName, Class<?>... classArray){
		Method reflectedMethod = null;
		try {
			System.out.println("-->"+LRUMemoryCache.class.getCanonicalName());
			reflectedMethod = c.getMethod(methodName,classArray);
		} catch (NoSuchMethodException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		}

		TestableInVivo invivoAnnotation = reflectedMethod.getAnnotation(TestableInVivo.class);
		
		return invivoAnnotation;
	}

	@Test
	public void taggingWithInvivoTestAnnotationMethodUntagged(){
		Class<LRUMemoryCache> reflectiveClass = LRUMemoryCache.class;
		String methodName = "update";
		Class<?>[] paramListClassArray = new Class<?>[1];
		paramListClassArray[0] = ICacheElement.class;
		
		TestableInVivo invivoAnnotation = this.retrieveInvivoAnnotation(reflectiveClass, methodName, paramListClassArray);

		String errMessage = "the method:"+ reflectiveClass.getCanonicalName()+"->"+methodName +" has not been tagged";
		Assert.assertNotNull(errMessage,invivoAnnotation);

		Assert.assertEquals(ORACLE_INVIVO_TEST_CLASS, invivoAnnotation.invivoTestClass());
		Assert.assertEquals(ORACLE_INVIVO_TEST, invivoAnnotation.invivoTest());
		Assert.assertEquals(ORACLE_INVIVO_CARVING_DEPTH, invivoAnnotation.carvingDepth());
		Assert.assertEquals(ORACLE_INVIVO_PAUSE_OTHER_THREADS, invivoAnnotation.pauseOtherThreads());	
	}

	@Test
	public void invokeInvivoTest(){
		// the following statement binds the PropertyUtil to a No Singleton instance.
		// in other words, all the other classes accessing to PropertyUtil.getInstance();
		// will not be affected by singleton
		PropertyUtilNoSingleton prop = PropertyUtilNoSingleton.getInstance();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);
		
		String compositeCacheName = "fooCompositeCache";

		String cacheName = "fooCache";
		String key = "fooKey";
		String val = "fooVal";
		
		ICacheElement ce = new CacheElement(cacheName, key, val);
		LRUMemoryCache cache = new LRUMemoryCache();
		
		ICompositeCacheAttributes cattr = new CompositeCacheAttributes();
		IElementAttributes attr = new ElementAttributes();
		CompositeCache hub = new CompositeCache(compositeCacheName, cattr, attr);		
		cache.initialize(hub);

		int oracle = cache.getCompositeCache().getUpdateCount();
		
		try {
			cache.update(ce);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}		

		int newUpdateCount = cache.getCompositeCache().getUpdateCount();
		
		boolean condition = (oracle == newUpdateCount) && (LRUMemoryCachePrinterInvivoTestClass.getLastObservedCompositeCacheName().equals(compositeCacheName));
		Assert.assertTrue(condition);
	}
}
