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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache.testIssueCache236;

import java.util.Date;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.base.persistence.CachePersistenceException;
import com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener;

import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.OSCacheLRUCacheFactory;

/*
 * This class is an implementation of a Unit Test 
 * proposed in the description of the ISSUE CACHE-236 for
 * the class:
 * <ul>
 * <li> com.opensymphony.oscache.base.algorithm.Cache</li>
 * </ul>
 * distributed with OpenSymphony versions 2.1 and 2.2
 * 
 */

public class Cache_RemoveEntryInternalUnitTest extends Cache{
    
    private static final int CACHE_CAPACITY=4;
    private static final boolean CACHE_UNLIMITED_DISK=true;
    private static final boolean CACHE_PERSISTENCE_OVERFLOW_ONLY=true;
    private static final String CACHE_PERSISTENCE_CLASS="com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener";
	private static final String DEFAULT_CACHE_PATH = System.getProperty("java.io.tmpdir");
	protected String CACHE_PATH = DEFAULT_CACHE_PATH;
    private static final boolean CACHE_BLOCKING=true;
    private static final boolean CACHE_MEMORY=true;

    public Cache_RemoveEntryInternalUnitTest() {
    	super(CACHE_MEMORY, CACHE_UNLIMITED_DISK, CACHE_PERSISTENCE_OVERFLOW_ONLY, CACHE_BLOCKING, null, CACHE_CAPACITY);
    	this.setUpHashDiskPersistenceListener();
    }
	
    protected void setUpHashDiskPersistenceListener(){
		if (this.CACHE_PATH.equals(DEFAULT_CACHE_PATH)) {
//			this.CACHE_PATH = LRUCache_AbstractUnitTest.GENERATE_CACHE_PATH(this.getClass().getName());
			this.CACHE_PATH = OSCacheLRUCacheFactory.GENERATE_CACHE_PATH(this.getClass().getName());
		}
    	
    	HashDiskPersistenceListener listener = new HashDiskPersistenceListener();

        Properties p = new Properties();
        p.setProperty("cache.path", CACHE_PATH);
        p.setProperty("cache.memory", String.valueOf(CACHE_MEMORY));
        p.setProperty("cache.persistence.class", CACHE_PERSISTENCE_CLASS);
        p.setProperty("cache.capacity", String.valueOf(CACHE_CAPACITY));
        p.setProperty("cache.unlimited.disk", String.valueOf(CACHE_UNLIMITED_DISK));
        p.setProperty("cache.persistence.overflow.only", String.valueOf(CACHE_PERSISTENCE_OVERFLOW_ONLY));
        p.setProperty("cache.blocking", String.valueOf(CACHE_BLOCKING));

        listener.configure(new Config(p));
        
        this.setPersistenceListener(listener);
        this.clear();
            	
    }


    @Test
    public void testDescribedWithIssueCACHE_236(){
    	final String KEY_PREFIX = "Key-";
    	final String CONTENT_PREFIX = "Content-";
    	
    	for (int i=0; i < CACHE_CAPACITY+2; i++) {
    		String key = KEY_PREFIX + i;
    		String content = CONTENT_PREFIX + i;
    		this.putInCache(key, content);
    		this.flushEntry(key);		
    	}
    	
		this.flushEntry(KEY_PREFIX+1);		
		
		this.removeEntry(KEY_PREFIX+1);	
		
		String fetchedContent=null;
    	try {
			// Fetching an element that exists but it was cached on the disk
    		fetchedContent = (String) this.getFromCache(KEY_PREFIX + 2);
		} catch (NeedsRefreshException e) {
			try {
				// Fetching the element from the disk
				CacheEntry entry = (CacheEntry) this.getPersistenceListener().retrieve(KEY_PREFIX + 2);
				if (entry != null) {
					fetchedContent = (String) entry.getContent();
				}	
			} catch (CachePersistenceException e1) {
				// Something wrong happened.
				// There are no other threads that may have successfully
				// rebuilt the cache entry, except this one.
		    	Assert.fail();
			}
		}

		String fetchedUnexistingContent=null;
    	try {
			// Fetching an element that does not exist anymore in the cache
    		fetchedUnexistingContent = (String) this.getFromCache(KEY_PREFIX + 1);
		} catch (NeedsRefreshException e) {
			try {
				// Trying to fetch the element also from the disk
				CacheEntry entry = (CacheEntry) this.getPersistenceListener().retrieve(KEY_PREFIX + 1);
				if (entry != null) {
					fetchedContent = (String) entry.getContent();
				}	
			} catch (CachePersistenceException e1) {
				// Something wrong happened.
				// There are no other threads that may have successfully
				// rebuilt the cache entry, except this one.
		    	Assert.fail();
			}
		}
    	
    	Assert.assertEquals(CONTENT_PREFIX+2, fetchedContent);
    	Assert.assertNull(fetchedUnexistingContent);
    }

    @Test
    public void testMoreWhatDescribedWithIssueCACHE_236(){
    	final String KEY_PREFIX = "Key-";
    	final String CONTENT_PREFIX = "Content-";
    	
    	for (int i=0; i < CACHE_CAPACITY+2; i++) {
    		String key = KEY_PREFIX + i;
    		String content = CONTENT_PREFIX + i;
    		this.putInCache(key, content);
    		Date flushTime = new Date(System.currentTimeMillis());
    		this.flushAll(flushTime);
    	}
    	
		this.flushEntry(KEY_PREFIX+1);		
		
		this.removeEntry(KEY_PREFIX+1);	

		this.putInCache(KEY_PREFIX+1, CONTENT_PREFIX + "XXX");

		Date flushTime = new Date(System.currentTimeMillis());
		this.flushAll(flushTime);
		
		String fetchedReplacedContent=null;
    	try {
			// Fetching the element that has been replaced in the cache
    		fetchedReplacedContent = (String) this.getFromCache(KEY_PREFIX+1);
		} catch (NeedsRefreshException e) {
			try {
				// This is odd, however trying to fetch the element also from the disk
				CacheEntry entry = (CacheEntry) this.getPersistenceListener().retrieve(KEY_PREFIX + 1);

				// The content has been replaced and it must be stored in the cache somewhere.
		    	Assert.assertNotNull("The content has been replaced and it must be stored in the cache somewhere.", entry);
		    	
		    	fetchedReplacedContent = (String) entry.getContent();
			} catch (CachePersistenceException e1) {
				// Something wrong happened.
				// There are no other threads that may have successfully
				// rebuilt the cache entry, except this one.
		    	Assert.fail();
			}
		}
    	
    	Assert.assertNotEquals(CONTENT_PREFIX+1, fetchedReplacedContent);
    	Assert.assertEquals(CONTENT_PREFIX+ "XXX", fetchedReplacedContent);
    }

}
