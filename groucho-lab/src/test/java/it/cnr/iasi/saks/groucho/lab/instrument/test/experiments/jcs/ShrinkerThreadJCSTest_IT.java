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
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test.ShrinkerThreadInvivoTestClass;
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

public class ShrinkerThreadJCSTest_IT {

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

		try {
			cache.update(ce);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}		

		boolean condition = ShrinkerThreadInvivoTestClass.getExitus();
		Assert.assertTrue(condition);
	}
}
