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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache.test;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.algorithm.LRUCache;
import com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener;

/*
 * This class is an implementation of a Unit Test 
 * proposed in the description of the ISSUE CACHE-236 for
 * the class:
 * <ul>
 * <li> com.opensymphony.oscache.base.algorithm.Cache</li>
 * <li> com.opensymphony.oscache.base.algorithm.AbstractConcurrentReadCache</li>
 * </ul>
 * distributed with OpenSymphony versions 2.1 and 2.2
 * 
 * Actually this code is an inspired re-implementation of the
 * test: 
 * <ul>
 * <li> com.opensymphony.oscache.base.algorithm.TestLRUCache.java
 * </ul>
 * 
 */

public class LRUCache_RemoveEntryInternalUnitTest extends LRUCache{
    
    private static final int CACHE_CAPACITY=4;
    private static final boolean CACHE_UNLIMITED_DISK=true;
    private static final boolean CACHE_PERSISTENCE_OVERFLOW_ONLY=true;
    private static final String CACHE_PERSISTENCE_CLASS="com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener";
    private static final String CACHE_PATH=System.getProperty("java.io.tmpdir");
    private static final boolean CACHE_BLOCKING=true;
    private static final boolean CACHE_MEMORY=true;

    private final String KEY_PREFIX = "Key-";
    private final String CONTENT_PREFIX = "Content-";
	
    public LRUCache_RemoveEntryInternalUnitTest() {
    	super(CACHE_CAPACITY);

    	this.setMemoryCaching(CACHE_MEMORY);
    	this.setUnlimitedDiskCache(CACHE_UNLIMITED_DISK);
    	this.setOverflowPersistence(CACHE_PERSISTENCE_OVERFLOW_ONLY);

    	this.setUpHashDiskPersistenceListener();
    }
	
    public void setUpHashDiskPersistenceListener(){
        HashDiskPersistenceListener listener = new HashDiskPersistenceListener();

        Properties p = new Properties();
        p.setProperty("cache.path", String.valueOf(CACHE_PATH));
        p.setProperty("cache.memory", String.valueOf(CACHE_MEMORY));
        p.setProperty("cache.persistence.class", CACHE_PERSISTENCE_CLASS);
        p.setProperty("cache.capacity", String.valueOf(CACHE_CAPACITY));
        p.setProperty("cache.unlimited.disk", String.valueOf(CACHE_UNLIMITED_DISK));
        p.setProperty("cache.persistence.overflow.only", String.valueOf(CACHE_PERSISTENCE_OVERFLOW_ONLY));
        p.setProperty("cache.blocking", String.valueOf(CACHE_BLOCKING));

        listener.configure(new Config(p));
        
        this.setPersistenceListener(listener);            	
    }


    @Test
    public void testRemoval(){
    	for (int i=0; i < CACHE_CAPACITY+2; i++) {
    		String key = KEY_PREFIX + i;
    		this.itemPut(key);
    	}
   	
        // Get the last element
        this.itemRetrieved(KEY_PREFIX+0);

        // The least recently used item is key + 1
        Assert.assertTrue((KEY_PREFIX + 1).equals(this.removeItem()));
    }

}
