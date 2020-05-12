package it.cnr.iasi.saks.groucho.lab.instrument.test.utils;

import java.io.IOException;

import org.apache.jcs.engine.CacheElement;
import org.apache.jcs.engine.CompositeCacheAttributes;
import org.apache.jcs.engine.ElementAttributes;
import org.apache.jcs.engine.behavior.ICacheElement;
import org.apache.jcs.engine.behavior.ICompositeCacheAttributes;
import org.apache.jcs.engine.behavior.IElementAttributes;
import org.apache.jcs.engine.control.CompositeCache;
import org.apache.jcs.engine.memory.lru.LRUMemoryCache;
import org.junit.Assert;
import org.junit.Test;

public class LRUMemoryCacheLocalTest{
	
	@Test
	public void updateLRUMemoryCacheTest(){
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
	}

}
