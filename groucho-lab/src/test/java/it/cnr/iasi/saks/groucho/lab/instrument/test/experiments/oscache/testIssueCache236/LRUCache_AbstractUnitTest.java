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

import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.algorithm.LRUCache;
import com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener;

import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;
import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.OSCacheLRUCacheFactory;

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

	protected static final int DEFAULT_CACHE_CAPACITY = 4;
	protected static int CACHE_CAPACITY = DEFAULT_CACHE_CAPACITY;
	protected static final boolean CACHE_UNLIMITED_DISK = true;
	protected static final boolean CACHE_PERSISTENCE_OVERFLOW_ONLY = true;
	protected static final String CACHE_PERSISTENCE_CLASS = "com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener";
//	private static final String DEFAULT_CACHE_PATH = System.getProperty("java.io.tmpdir");
//	protected String CACHE_PATH = DEFAULT_CACHE_PATH;
	protected static final boolean CACHE_BLOCKING = true;
	protected static final boolean CACHE_MEMORY = true;

	protected LRUCache cache = null;

	public LRUCache_AbstractUnitTest() {
		Properties p = new Properties();
		p.setProperty(OSCacheLRUCacheFactory.CACHE_CAPACITY_LABEL, String.valueOf(CACHE_CAPACITY));

		p.setProperty(OSCacheLRUCacheFactory.CACHE_PATH_LABEL, OSCacheLRUCacheFactory.GENERATE_CACHE_PATH(this.getClass().getName()));

		p.setProperty(OSCacheLRUCacheFactory.CACHE_MEMORY_LABEL, String.valueOf(CACHE_MEMORY));
		p.setProperty(OSCacheLRUCacheFactory.CACHE_CAPACITY_LABEL, String.valueOf(CACHE_CAPACITY));
		p.setProperty(OSCacheLRUCacheFactory.CACHE_UNLIMITED_DISK_LABEL, String.valueOf(CACHE_UNLIMITED_DISK));
		p.setProperty(OSCacheLRUCacheFactory.CACHE_PERSISTENCE_OVERFLOW_ONLY_LABEL, String.valueOf(CACHE_PERSISTENCE_OVERFLOW_ONLY));
		p.setProperty(OSCacheLRUCacheFactory.CACHE_BLOCKING_LABEL, String.valueOf(CACHE_BLOCKING));
// Note that likely this value will be ignored. See the class: OSCacheLRUCacheFactory 
		p.setProperty(OSCacheLRUCacheFactory.CACHE_PERSISTENCE_CLASS_LABEL, CACHE_PERSISTENCE_CLASS);

		this.cache = OSCacheLRUCacheFactory.crateLRUCache(p);
	}

//	public LRUCache_AbstractUnitTest() {
//		this.cache = new LRUCache(CACHE_CAPACITY);
//
//		this.cache.setMemoryCaching(CACHE_MEMORY);
//		this.cache.setUnlimitedDiskCache(CACHE_UNLIMITED_DISK);
//		this.cache.setOverflowPersistence(CACHE_PERSISTENCE_OVERFLOW_ONLY);
//
//		this.setUpHashDiskPersistenceListener();
//	}
//
//	protected void setUpHashDiskPersistenceListener() {
//		HashDiskPersistenceListener listener = new HashDiskPersistenceListener();
//
//		if (this.CACHE_PATH.equals(DEFAULT_CACHE_PATH)) {
//			this.CACHE_PATH = GENERATE_CACHE_PATH(this.getClass().getName());
//		}
//
//		Properties p = new Properties();
//		p.setProperty("cache.path", String.valueOf(CACHE_PATH));
//		p.setProperty("cache.memory", String.valueOf(CACHE_MEMORY));
//		p.setProperty("cache.persistence.class", CACHE_PERSISTENCE_CLASS);
//		p.setProperty("cache.capacity", String.valueOf(CACHE_CAPACITY));
//		p.setProperty("cache.unlimited.disk", String.valueOf(CACHE_UNLIMITED_DISK));
//		p.setProperty("cache.persistence.overflow.only", String.valueOf(CACHE_PERSISTENCE_OVERFLOW_ONLY));
//		p.setProperty("cache.blocking", String.valueOf(CACHE_BLOCKING));
//
//		listener.configure(new Config(p));
//
//		this.cache.setPersistenceListener(listener);
//	}
//
//	public static String GENERATE_CACHE_PATH(String prefix) {
//		String cachePath = DEFAULT_CACHE_PATH;
//		try {
//			String rndString = RandomGenerator.getInstance().nextString();
//			rndString = prefix + "-" + rndString;
//			cachePath = Files.createTempDirectory(rndString).toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//			cachePath = DEFAULT_CACHE_PATH;
//		}
//		
//		return cachePath;
//	}

}
