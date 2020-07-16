package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test;

import java.io.IOException;

import org.apache.jcs.engine.CacheElement;
import org.apache.jcs.engine.CompositeCacheAttributes;
import org.apache.jcs.engine.ElementAttributes;
import org.apache.jcs.engine.behavior.ICacheElement;
import org.apache.jcs.engine.control.CompositeCache;
import org.apache.jcs.engine.control.event.ElementEventHandlerMockImpl;
import org.apache.jcs.engine.memory.MemoryCache;
import org.apache.jcs.engine.memory.MemoryCacheMockImpl;
import org.apache.jcs.engine.memory.shrinking.ShrinkerThread;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * This class is a re-implementation of the original Unit Test:
 * org.apache.jcs.engine.memory.shrinking.ShrinkerThreadUnitTest
 * distributed with Apache JCS 1.3
 * 
 * This new implementation has only 2 minor purposes:
 * 1. migrate the test to JUnit 4
 * 2. separate the configuration part of the test from its actual execution,
 *    it would be easier to reuse it during invivo testing sessions.
 * 3. the tests assume the memory may not be empty, thus the for statements
 *    that locally populate the memory starts from the item at position `memory.getSize()`    
 * For all the rest the test is completely equivalent
 * to the one originally conceived for Apache JCS.
 */

public class ShrinkerThreadUnitTest {

	private MemoryCacheWrapper memory;

	private final static String DEFAULT_CACHE_REGION = "testRegion";
	
	public final static int DEFAULT_ITEMS = 10;
	private int items;

	public ShrinkerThreadUnitTest() {
	}

	public void configureMemoryCache(MemoryCache m) {
		this.configureMemoryCache(m, DEFAULT_ITEMS);
		System.out.println("... CONFIGURATION DONE!!!");
	}

	public void configureMemoryCache(MemoryCache m, int items) {
		this.memory = new MemoryCacheWrapper(m);
		this.items = items;
	}

	@Before
	public void configureMemoryCacheWithAMock() {
		MemoryCacheMockImpl m = new MemoryCacheMockImpl();
		this.memory = new MemoryCacheWrapper(m);
		this.items = 10;
	}

	/**
	 * Setup cache attributes in mock. Create the shrinker with the mock. Add some
	 * elements into the mock memory cache see that they get spooled.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testSimpleShrink() throws IOException, InterruptedException {

		CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
		cacheAttr.setMaxMemoryIdleTimeSeconds(1);
		cacheAttr.setMaxSpoolPerRun(10);

		memory.setCacheAttributes(cacheAttr);

		String key = "key";
		String value = "value";

		ICacheElement element = new CacheElement("testRegion", key, value);

		ElementAttributes elementAttr = new ElementAttributes();
		elementAttr.setIsEternal(false);
		element.setElementAttributes(elementAttr);
		element.getElementAttributes().setMaxLifeSeconds(1);
		memory.update(element);

		ICacheElement returnedElement1 = memory.get(key);
		Assert.assertNotNull("We should have received an element", returnedElement1);

		// set this to 2 seconds ago.
		elementAttr.lastAccessTime = System.currentTimeMillis() - 2000;

		ShrinkerThread shrinker = new ShrinkerThread(memory);
		Thread runner = new Thread(shrinker);
		runner.run();

		Thread.sleep(500);

		ICacheElement returnedElement2 = memory.get(key);
		Assert.assertTrue("Waterfall should have been called.", memory.getWaterfallCallCounter() > 0);
		Assert.assertNull("We not should have received an element.  It should have been spooled.", returnedElement2);
	}

	/**
	 * Add 10 to the memory cache. Set the spool per run limit to 3.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 *
	 * @throws Exception
	 */
	@Test
	public void testSimpleShrinkMutiple() throws IOException, InterruptedException {
		CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
		cacheAttr.setMaxMemoryIdleTimeSeconds(1);
		cacheAttr.setMaxSpoolPerRun(3);

		memory.setCacheAttributes(cacheAttr);

		int sizeBeforeUpdates = memory.getSize();

		String regionName = DEFAULT_CACHE_REGION;
		CompositeCache compositeCache = memory.getCompositeCache();
		if (compositeCache != null) {
			regionName = compositeCache.getCacheName();
		}
		System.out.println(regionName);
		
//		for (int i = 0; i < items; i++) {
		for (int i = sizeBeforeUpdates; i < sizeBeforeUpdates + items; i++) {
			String key = "key" + i;
			String value = "value";

			ICacheElement element = new CacheElement(regionName, key, value);

			ElementAttributes elementAttr = new ElementAttributes();
			elementAttr.setIsEternal(false);
			element.setElementAttributes(elementAttr);
			element.getElementAttributes().setMaxLifeSeconds(1);
			memory.update(element);
			System.out.println("++current size++" + memory.getSize());

			ICacheElement returnedElement1 = memory.get(key);
			Assert.assertNotNull("We should have received an element", returnedElement1);

			// set this to 2 seconds ago.
			elementAttr.lastAccessTime = System.currentTimeMillis() - 2000;
		}

		ShrinkerThread shrinker = new ShrinkerThread(memory);
		Thread runner = new Thread(shrinker);
		runner.run();

		Thread.sleep(500);

		Assert.assertEquals("Waterfall called the wrong number of times.", 3, memory.getWaterfallCallCounter());
		int size = memory.getSize();
		int expectedSize = ((sizeBeforeUpdates + items)>= memory.getCacheAttributes().getMaxObjects())?memory.getCacheAttributes().getMaxObjects()-3:sizeBeforeUpdates+items-3;
		System.out.println("++sizeBeforeUpdates++" + sizeBeforeUpdates + " " + items);
//		System.out.println("++size in testSimpleShrinkMutiple++" + size + "++expected++" + (sizeBeforeUpdates + items - 3));
//		Assert.assertEquals("Wrong number of elements remain.", (sizeBeforeUpdates + items - 3), memory.getSize());
		System.out.println("++size in testSimpleShrinkMutiple++" + size + "++expected++" + expectedSize);
		Assert.assertEquals("Wrong number of elements remain.", expectedSize, memory.getSize());
	}

	/**
	 * Add a mock event handler to the items. Verify that it gets called.
	 * <p>
	 * This is only testing the spooled background event
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 *
	 * @throws Exception
	 */
	@Test
	public void testSimpleShrinkMutipleWithEventHandler() throws IOException, InterruptedException {
		CompositeCacheAttributes cacheAttr = new CompositeCacheAttributes();
		cacheAttr.setMaxMemoryIdleTimeSeconds(1);
		cacheAttr.setMaxSpoolPerRun(3);

		memory.setCacheAttributes(cacheAttr);

		int sizeBeforeUpdates = memory.getSize();

		String regionName = DEFAULT_CACHE_REGION;
		CompositeCache compositeCache = memory.getCompositeCache();
		if (compositeCache != null) {
			regionName = compositeCache.getCacheName();
		}
		System.out.println(regionName);

		ElementEventHandlerMockImpl handler = new ElementEventHandlerMockImpl();

		for (int i = sizeBeforeUpdates; i < sizeBeforeUpdates + items; i++) {
			String key = "key" + i;
			String value = "value";

			ICacheElement element = new CacheElement("testRegion", key, value);

			ElementAttributes elementAttr = new ElementAttributes();
			elementAttr.addElementEventHandler(handler);
			elementAttr.setIsEternal(false);
			element.setElementAttributes(elementAttr);
			element.getElementAttributes().setMaxLifeSeconds(1);
			memory.update(element);

			ICacheElement returnedElement1 = memory.get(key);
			Assert.assertNotNull("We should have received an element", returnedElement1);

			// set this to 2 seconds ago.
			elementAttr.lastAccessTime = System.currentTimeMillis() - 2000;
		}

		ShrinkerThread shrinker = new ShrinkerThread(memory);
		Thread runner = new Thread(shrinker);
		runner.run();

		Thread.sleep(500);

		Assert.assertEquals("Waterfall called the wrong number of times.", 3, memory.getWaterfallCallCounter());

		// the shrinker delegates the the composite cache on the memory cache to put the
		// event on the queue. This make it hard to test. TODO we need to change this to
		// make it easier to verify.
		// assertEquals( "Event handler ExceededIdleTimeBackground called the wrong
		// number of times.", 3, handler.getExceededIdleTimeBackgroundCount() );

		int size = memory.getSize();
		System.out.println("++size in testSimpleShrinkMutipleWithEventHandler++" + size + "++expected++" + (items - 3));
		Assert.assertEquals("Wrong number of elements remain.", items - 3, memory.getSize());
	}

}
