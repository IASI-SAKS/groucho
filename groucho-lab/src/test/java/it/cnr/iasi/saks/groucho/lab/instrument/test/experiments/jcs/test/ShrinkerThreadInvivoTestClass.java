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

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

import java.io.IOException;

import org.apache.jcs.engine.CacheElement;
import org.apache.jcs.engine.ElementAttributes;
import org.apache.jcs.engine.behavior.ICacheElement;
import org.apache.jcs.engine.control.CompositeCache;
import org.apache.jcs.engine.control.CompositeCacheManager;
import org.apache.jcs.engine.memory.lru.LRUMemoryCache;
import org.junit.Assert;
import org.junit.Test;

public class ShrinkerThreadInvivoTestClass {
	private static boolean EXITUS = false;
	
	public boolean invivoTestMethod(Context c){
		setExitus();
		LRUMemoryCache memCache = ((LRUMemoryCache) c.getInstrumentedObject());

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);			

//		this.testSimpleShrinkInvivo(memCache);

		this.testSimpleShrinkMutipleInvivo(memCache);

//		this.testSimpleShrinkMutipleWithEventHandlerInvivo(memCache);
		
		return getExitus();
	}

	private void testSimpleShrinkInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrink ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrink();
		}catch(Throwable t) {
			resetExitus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}
	
	private void testSimpleShrinkMutipleInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrinkMutiple ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrinkMutiple();
		}catch(Throwable t) {
			resetExitus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}

	private void testSimpleShrinkMutipleWithEventHandlerInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrinkMutipleWithEventHandler ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrinkMutipleWithEventHandler();
		}catch(Throwable t) {
			resetExitus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}

	public synchronized static boolean getExitus(){
		return EXITUS;
	}
	
	public synchronized static void resetExitus(){
		EXITUS = false;
	}

	protected synchronized static void setExitus(){
		EXITUS = true;
	}
	
//	@Test
//	public void testWithPlainJunit() throws IOException {
//		CompositeCacheManager cacheMgr = CompositeCacheManager.getUnconfiguredInstance();
////      cacheMgr.configure( "/TestDiskCache.ccf" );
//		cacheMgr.configure("/test-conf/TestDiskCache.ccf");
//
//		int items = cacheMgr.getDefaultCacheAttributes().getMaxObjects() * 2;
////		items = cacheMgr.getDefaultCacheAttributes().getMaxObjects() + 1;
//		items = 90;
//		String region = "testRegion";
//		
//		CompositeCache cache = cacheMgr.getCache(region);
//
//		LRUMemoryCache lru = new LRUMemoryCache();
//		lru.initialize(cache);
//		
//        this.addElementsInCache(items, lru);
//
//        System.out.println(lru.getCacheName());
//		testSimpleShrinkMutipleInvivo(lru);
//	}
//	
//	private void addElementsInCache(int items, LRUMemoryCache lru)
//			throws IOException {
//		for (int i = 0; i < items; i++) {
//			String key = "key" + i;
//			String value = "value";
//
//			ICacheElement element = new CacheElement("testRegion", key, value);
//
//			ElementAttributes elementAttr = new ElementAttributes();
//			elementAttr.setIsEternal(false);
//			element.setElementAttributes(elementAttr);
////			element.getElementAttributes().setMaxLifeSeconds(1);
//			lru.update(element);
//		}
//	}

}
