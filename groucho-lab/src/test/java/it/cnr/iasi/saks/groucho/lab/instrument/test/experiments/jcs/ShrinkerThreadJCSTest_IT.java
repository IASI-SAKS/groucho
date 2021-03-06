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

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test.ShrinkerThreadInvivoTestClass;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test.ShrinkerThreadUnitTest;
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
import org.apache.jcs.engine.control.CompositeCacheManager;

public class ShrinkerThreadJCSTest_IT {

	/*
	 * This is an old version about the test 
	 * checking if the Invivo Test were actually invoked.
	 * 
	 * It is structured in order to have the invivo testing session
	 * always activable, ant it just checks if the invocation
	 * to the invivo test is actually invoked.
	 * 
	 * This test is useless wrt the experimentations within JCS,
	 * thus possibly it could be removed. I am leaving it here
	 * only for potential future debugging activities
	 */
	@Ignore
	@Test
	public void invokeInvivoTestSimple() {
		// the following statement binds the PropertyUtil to a No Singleton instance.
		// in other words, all the other classes accessing to
		// PropertyUtil.getInstance();
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

		try {
			cache.update(ce);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}

		boolean condition = ShrinkerThreadInvivoTestClass.getExitStatus();
		Assert.assertTrue(condition);
	}

	@Test
	public void invokeInvivoTest() throws IOException, InterruptedException {
		// the following statement binds the PropertyUtil to a No Singleton instance.
		// in other words, all the other classes accessing to
		// PropertyUtil.getInstance();
		// will not be affected by singleton
		PropertyUtilNoSingleton prop = PropertyUtilNoSingleton.getInstance();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
//****************************

		for (int run = 0; run < 10; run++) {
			// In the conf file "/test-conf/TestDiskCache.ccf" (used below) the maxCapacity before disk caching is set to 100
			// Sometimes the number of items added in the cache will exceed such limit enabling caching on the disk.
			int items = 40 + RandomGenerator.getInstance().nextInt(99);
			LRUMemoryCache lru = this.configureLRUMemoryCache(items);
			
	        System.out.println("Waiting for a while ...");
	        Thread.sleep( 5000 );
	        System.out.println("... ok");

	        TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);

	        this.addElementsInCache(1, lru);

	        TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

	        int memSize = lru.getSize();
			boolean condition = ShrinkerThreadInvivoTestClass.getExitStatus();
			// This expected results come from the description of the ISSUE JCS-16
			boolean expected = (items + ShrinkerThreadUnitTest.DEFAULT_ITEMS < lru.getCacheAttributes().getMaxObjects());
	        System.out.println("[Run "+ run +"]\tItems: " + items + "\tSize: " + memSize + "\tCondition:"+condition + "\tExpected:"+expected);			
			Assert.assertEquals(expected, condition);
		}

//		Assert.assertTrue(condition);
	}

	private LRUMemoryCache configureLRUMemoryCache() throws IOException {
		return this.configureLRUMemoryCache(0);
	}
		
	private LRUMemoryCache configureLRUMemoryCache(int items) throws IOException {
		CompositeCacheManager cacheMgr = CompositeCacheManager.getUnconfiguredInstance();
//      cacheMgr.configure( "/TestDiskCache.ccf" );
		cacheMgr.configure("/test-conf/TestDiskCache.ccf");

		String region = "indexedRegion1";
		
		CompositeCache cache = cacheMgr.getCache(region);

		LRUMemoryCache lru = new LRUMemoryCache();
		lru.initialize(cache);
		
		this.addElementsInCache(items, lru);
		
		return lru;
	}

	private void addElementsInCache(int items, LRUMemoryCache lru)
			throws IOException {
		for (int i = 0; i < items; i++) {
			String key = "key" + i;
			String value = "value";

			ICacheElement element = new CacheElement("testRegion", key, value);

			ElementAttributes elementAttr = new ElementAttributes();
//			elementAttr.setIsEternal(false);
			element.setElementAttributes(elementAttr);
			lru.update(element);

			ICacheElement returnedElement1 = lru.get(key);
			Assert.assertNotNull("We should have received an element", returnedElement1);
		}
	}

}
