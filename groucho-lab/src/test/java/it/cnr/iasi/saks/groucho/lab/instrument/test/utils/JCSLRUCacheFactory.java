package it.cnr.iasi.saks.groucho.lab.instrument.test.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.jcs.engine.control.CompositeCache;
import org.apache.jcs.engine.control.CompositeCacheManager;
import org.apache.jcs.engine.memory.lru.LRUMemoryCache;

import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;

public class JCSLRUCacheFactory {
	
	private static final String CONF_FILE = "/test-conf/TestDiskCache.ccf"; 
	private static String LAST_CONF_FILE = CONF_FILE; 
	public static final String DEFAULT_REGION_FILE = "indexedRegion1"; 

	private static final String DEFAULT_CACHE_PATH = System.getProperty("java.io.tmpdir");
	protected String CACHE_PATH = DEFAULT_CACHE_PATH;
	
	public static final String LABEL_INDEXED_DISK_CACHE_PATH = "jcs.auxiliary.indexedDiskCache.attributes.DiskPath";
	public static final String LABEL_INDEXED_DISK_CACHE2_PATH = "jcs.auxiliary.indexedDiskCache2.attributes.DiskPath";
	
	public static LRUMemoryCache configureLRUMemoryCache() throws IOException {
		return JCSLRUCacheFactory.configureLRUMemoryCache(null);
	}
		
	public static LRUMemoryCache configureLRUMemoryCache(String confFile) throws IOException {
		return JCSLRUCacheFactory.configureLRUMemoryCache(DEFAULT_REGION_FILE, confFile);
	}
	
	public static LRUMemoryCache configureLRUMemoryCache(String region, String confFile) throws IOException {
		CompositeCacheManager cacheMgr = CompositeCacheManager.getUnconfiguredInstance();
		if ((confFile == null) || (confFile.isEmpty())) {
			//      cacheMgr.configure( "/TestDiskCache.ccf" );
			cacheMgr.configure(CONF_FILE);
			LAST_CONF_FILE = CONF_FILE;
		} else {
			cacheMgr.configure(confFile);			
			LAST_CONF_FILE = confFile;
		}
			
		CompositeCache cache = cacheMgr.getCache(region);
        cache.removeAll();

		LRUMemoryCache lru = new LRUMemoryCache();
		lru.initialize(cache);
		
		return lru;
	}
	
	public static String getConfFile() {
		String confFile = LAST_CONF_FILE;
	
		return confFile;
	}
	
	public static String generateTmpDir(String prefix) {
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

	public static File generateRandomSubDir(File dir) {
		String rndString = RandomGenerator.getInstance().nextString();
		
		File subdir = new File(dir, rndString); 
		subdir.mkdir();
		
		return subdir;
	}
	
	/*
	 * This method forces the clean-up of the cache by removing the
	 * files that store the entries on the disk.
     * This is a brute-force approach for removing items and it has
     * sense only wrt a demo/testing scenario.
	 *
	 * This feature is not supposed to be used in production.
	 */	
	public static void bruteForceCacheDisposal() throws IOException {
		Properties p = new Properties();
		p.load(JCSLRUCacheFactory.class.getResourceAsStream(JCSLRUCacheFactory.getConfFile()));

		File dirToBeDisposed;
		
		String diskCachePath = p.getProperty(LABEL_INDEXED_DISK_CACHE_PATH);
		dirToBeDisposed = new File(diskCachePath);
		FileUtils.deleteQuietly(dirToBeDisposed);

		String diskCache2Path = p.getProperty(LABEL_INDEXED_DISK_CACHE2_PATH);
		dirToBeDisposed = new File(diskCache2Path);
		FileUtils.deleteQuietly(dirToBeDisposed);
	}
	
}
