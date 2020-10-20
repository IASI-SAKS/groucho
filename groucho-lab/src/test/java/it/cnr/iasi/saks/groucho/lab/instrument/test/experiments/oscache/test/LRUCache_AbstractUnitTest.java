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

import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.algorithm.LRUCache;
import com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener;

/*
 * This class declares a common configuration for those Unit Tests 
 * that would comply with the environmental set-up and described in ISSUE CACHE-236
 * for the classes:
 * <ul>
 * <li> com.opensymphony.oscache.base.algorithm.Cache</li>
 * <li> com.opensymphony.oscache.base.algorithm.AbstractConcurrentReadCache</li>
 * </ul>
 * and distributed with OpenSymphony versions 2.1 and 2.2  
 * 
 */

public abstract class LRUCache_AbstractUnitTest {
    
	protected static final int CACHE_CAPACITY=4;
	protected static final boolean CACHE_UNLIMITED_DISK=true;
	protected static final boolean CACHE_PERSISTENCE_OVERFLOW_ONLY=true;
	protected static final String CACHE_PERSISTENCE_CLASS="com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener";
	protected static final String CACHE_PATH=System.getProperty("java.io.tmpdir");
	protected static final boolean CACHE_BLOCKING=true;
	protected static final boolean CACHE_MEMORY=true;

	protected LRUCache cache = null;

    public LRUCache_AbstractUnitTest() {
    	this.cache = new LRUCache(CACHE_CAPACITY);

    	this.cache.setMemoryCaching(CACHE_MEMORY);
    	this.cache.setUnlimitedDiskCache(CACHE_UNLIMITED_DISK);
    	this.cache.setOverflowPersistence(CACHE_PERSISTENCE_OVERFLOW_ONLY);

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
        
        this.cache.setPersistenceListener(listener);            	
    }

}
