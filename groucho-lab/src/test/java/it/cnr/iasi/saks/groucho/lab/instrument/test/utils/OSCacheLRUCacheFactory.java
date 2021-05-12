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
package it.cnr.iasi.saks.groucho.lab.instrument.test.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.algorithm.LRUCache;
import com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener;

import it.cnr.iasi.saks.foo.MyDummyLRUCache;
import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;

/**
 * Note that property "cache.persistence.class" will be always ignored
 * @author gulyx
 *
 */
public class OSCacheLRUCacheFactory {

	public final static String CACHE_PATH_LABEL = "cache.path";
	public final static String CACHE_MEMORY_LABEL = "cache.memory";
	public final static String CACHE_CAPACITY_LABEL = "cache.capacity";
	public final static String CACHE_UNLIMITED_DISK_LABEL = "cache.unlimited.disk";
	public final static String CACHE_PERSISTENCE_OVERFLOW_ONLY_LABEL = "cache.persistence.overflow.only";
	public final static String CACHE_BLOCKING_LABEL = "cache.blocking";
	public final static String CACHE_PERSISTENCE_CLASS_LABEL = "cache.persistence.class";
	
	private static final int DEFAULT_CACHE_CAPACITY=4;
    private static final boolean DEFAULT_CACHE_UNLIMITED_DISK=true;
    private static final boolean DEFAULT_CACHE_PERSISTENCE_OVERFLOW_ONLY=true;
    private static final String DEFAULT_CACHE_PERSISTENCE_CLASS="com.opensymphony.oscache.plugins.diskpersistence.HashDiskPersistenceListener";
	private static final String DEFAULT_CACHE_PATH = System.getProperty("java.io.tmpdir");
	protected String CACHE_PATH = DEFAULT_CACHE_PATH;
    private static final boolean DEFAULT_CACHE_BLOCKING=true;
    private static final boolean DEFAULT_CACHE_MEMORY=true;

    public static LRUCache crateLRUCache(int capacity) {
		Properties p = new Properties();
		p.setProperty(CACHE_CAPACITY_LABEL, String.valueOf(capacity));
		
		return crateLRUCache(p);
	}
	
	public static LRUCache crateLRUCache(Properties p) {	
		p.putIfAbsent(CACHE_PATH_LABEL, GENERATE_CACHE_PATH(OSCacheLRUCacheFactory.class.getName()));

		p.putIfAbsent(CACHE_MEMORY_LABEL, String.valueOf(DEFAULT_CACHE_MEMORY));
		p.putIfAbsent(CACHE_CAPACITY_LABEL, String.valueOf(DEFAULT_CACHE_CAPACITY));
		p.putIfAbsent(CACHE_UNLIMITED_DISK_LABEL, String.valueOf(DEFAULT_CACHE_UNLIMITED_DISK));
		p.putIfAbsent(CACHE_PERSISTENCE_OVERFLOW_ONLY_LABEL, String.valueOf(DEFAULT_CACHE_PERSISTENCE_OVERFLOW_ONLY));
		p.putIfAbsent(CACHE_BLOCKING_LABEL, String.valueOf(DEFAULT_CACHE_BLOCKING));
		
// Note: cache.persistence.class will be always ignored
		String oldCachePersistenceClass = (String) p.setProperty(CACHE_PERSISTENCE_CLASS_LABEL, DEFAULT_CACHE_PERSISTENCE_CLASS);
		
		int capacity = Integer.valueOf(p.getProperty(CACHE_CAPACITY_LABEL));
//		LRUCache cache = new LRUCache(capacity);
		LRUCache cache = new MyDummyLRUCache(capacity);

		boolean b = Boolean.valueOf((String) p.get(CACHE_MEMORY_LABEL));
		cache.setMemoryCaching(b);
		b = Boolean.valueOf((String)p.get(CACHE_UNLIMITED_DISK_LABEL));
		cache.setUnlimitedDiskCache(b);
		b = Boolean.valueOf((String)p.get(CACHE_PERSISTENCE_OVERFLOW_ONLY_LABEL));
		cache.setOverflowPersistence(b);
				
		HashDiskPersistenceListener listener = new HashDiskPersistenceListener();
		listener.configure(new Config(p));
		cache.setPersistenceListener(listener);
		
		if (oldCachePersistenceClass != null) {
			p.setProperty("cache.persistence.class", oldCachePersistenceClass);			
		}
		
		return cache;
	}
	
	public static String GENERATE_CACHE_PATH(String prefix) {
		String cachePath = DEFAULT_CACHE_PATH;
		try {
			String rndString = RandomGenerator.getInstance().nextString();
			rndString = prefix + "-" + rndString;
			cachePath = Files.createTempDirectory(rndString).toString();
		} catch (IOException e) {
			e.printStackTrace();
			cachePath = DEFAULT_CACHE_PATH;
		}
		
		return cachePath;
	}

}
