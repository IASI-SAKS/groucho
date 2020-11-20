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

import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.persistence.CachePersistenceException;
import com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener;

/*
 * This class is an implementation of a Unit Test 
 * proposed in the description of the ISSUE CACHE-236 which
 * it impact the classes:
 * <ul>
 * <li> com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener</li>
 * <li> com.opensymphony.oscache.base.algorithm.Cache</li>
 * <li> com.opensymphony.oscache.base.algorithm.AbstractConcurrentReadCache</li>
 * </ul>
 * distributed with OpenSymphony versions 2.1 and 2.2
 * 
 * Actually this code is an inspired re-implementation of the
 * test: 
 * <ul>
 * <li> com.opensymphony.oscache.plugins.diskpersistence.TestHashDiskPersistenceListener.java
 * </ul>
 *
 * However HashDiskPersistenceListener does not look to be affected by ISSUE CACAHE-236
 */

public class HashDiskPersistenceListener_RemoveEntryUnitTest{
    
    private static final int CACHE_CAPACITY=4;
    private static final boolean CACHE_UNLIMITED_DISK=true;
    private static final boolean CACHE_PERSISTENCE_OVERFLOW_ONLY=true;
    private static final String CACHE_PERSISTENCE_CLASS="com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener";
    private static final String CACHE_PATH=System.getProperty("java.io.tmpdir");
    private static final boolean CACHE_BLOCKING=true;
    private static final boolean CACHE_MEMORY=true;

    private HashDiskPersistenceListener listener;
    
    @Before
    public void setUp(){
        listener = new HashDiskPersistenceListener();

        Properties p = new Properties();
        p.setProperty("cache.path", String.valueOf(CACHE_PATH));
        p.setProperty("cache.memory", String.valueOf(CACHE_MEMORY));
        p.setProperty("cache.persistence.class", CACHE_PERSISTENCE_CLASS);
        p.setProperty("cache.capacity", String.valueOf(CACHE_CAPACITY));
        p.setProperty("cache.unlimited.disk", String.valueOf(CACHE_UNLIMITED_DISK));
        p.setProperty("cache.persistence.overflow.only", String.valueOf(CACHE_PERSISTENCE_OVERFLOW_ONLY));
        p.setProperty("cache.blocking", String.valueOf(CACHE_BLOCKING));

        listener.configure(new Config(p));
    	
    }
    
    @Test
    public void testDescribedWithIssueCACHE_236() throws CachePersistenceException{
    	final String KEY_PREFIX = "Key-";
    	final String CONTENT_PREFIX = "Content-";
    	
    	CacheEntry[] entries = new CacheEntry[CACHE_CAPACITY+2];
    	
    	for (int i=0; i < CACHE_CAPACITY+2; i++) {
    		String key = KEY_PREFIX + i;
    		String content = CONTENT_PREFIX + i;
    		entries[i] = new CacheEntry(key);
    		entries[i].setContent(content);
        	this.listener.store(key, entries[i]);
    	}
    	
    	entries[1].flush();
    	this.listener.remove(KEY_PREFIX+1);
    	entries[1].needsRefresh(CacheEntry.INDEFINITE_EXPIRY);

		String fetchedContent=null;
		fetchedContent = (String) this.listener.retrieve(KEY_PREFIX+1);
    	
    	Assert.assertNotEquals(CONTENT_PREFIX+1, fetchedContent);
    }
}
