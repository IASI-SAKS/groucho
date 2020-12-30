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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test;

import java.io.IOException;

import org.apache.jcs.engine.control.CompositeCache;
import org.apache.jcs.engine.control.CompositeCacheManager;
import org.apache.jcs.engine.memory.lru.LRUMemoryCache;

import org.junit.Assert;
import org.junit.Before;

/*
 * This new implementation improves the implementation in:
 *  - it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test.ShrinkerThreadUnitTest
 * forcing the original test to bind an cache memory that is an instance of
 * the original:
 *  - org.apache.jcs.engine.memory.lru.LRUMemoryCache
 * distributed with Apache JCS.
 */

public class ShrinkerThreadLRUCacheUnitTest extends ShrinkerThreadUnitTest{

	
	@Override
	@Before
	public void configureMemoryCacheWithAMock() {
		LRUMemoryCache lru;
		try {
			lru = this.configureLRUMemoryCache(DEFAULT_ITEMS);
			this.memory = new MemoryCacheWrapper(lru);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
		this.items = DEFAULT_ITEMS;
	}

	private LRUMemoryCache configureLRUMemoryCache(int items) throws IOException {
		CompositeCacheManager cacheMgr = CompositeCacheManager.getUnconfiguredInstance();
//      cacheMgr.configure( "/TestDiskCache.ccf" );
		cacheMgr.configure("/test-conf/TestDiskCache.ccf");

		String region = "indexedRegion1";
		
		CompositeCache cache = cacheMgr.getCache(region);

		LRUMemoryCache lru = new LRUMemoryCache();
		lru.initialize(cache);
		return lru;
	}

	
}
