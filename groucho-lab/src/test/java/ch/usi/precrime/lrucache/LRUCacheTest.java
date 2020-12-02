/** 
 * This file IS NOT part of the GROUCHO project.
 * 
 * This is a reference test program developed by
 * a PhD student at Università della Svizzera Italiana 
 * by reading the documentation available about LRUCache.
 * The developer had long industrial experience in software 
 * testing. Specifically, before starting the PhD Program
 * worked both as software tester, and test manager in 
 * several companies.
 * 
 */

package ch.usi.precrime.lrucache;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;

import java.util.Collection;

import java.util.Set;

import org.junit.Test;

import com.opensymphony.oscache.base.algorithm.LRUCache;

import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache.testIssueCache236.LRUCache_AbstractUnitTest;

/** 
 * This file IS NOT part of the GROUCHO project.
 * 
 * This is a reference test program developed by
 * a PhD student at Università della Svizzera Italiana 
 * by reading the documentation available about LRUCache.
 * The developer had long industrial experience in software 
 * testing. Specifically, before starting the PhD Program
 * worked both as software tester, and test manager in 
 * several companies.
 * 
 */

/**
 * Unit test for  LRUCache.
 */
public class LRUCacheTest extends LRUCache_AbstractUnitTest {

	protected final String K1 = "key-6fd3fc6e3105127bdc968175d1f108ec"; 
	protected final String V1 = "val-6fd3fc6e3105127bdc968175d1f108ec"; 

	protected final String K2 = "key-f4173c82908877d21cb616ae758a38a6"; 
	protected final String V2 = "val-f4173c82908877d21cb616ae758a38a6"; 
	
	
    @Test
    public void testClear_one()
    {
    	this.cache.put(K1, V1);
    	this.cache.clear();
        assertTrue(this.cache.size() == 0);
    }
    
    @Test
    public void testClear_more()
    {
    	for(int i = 0; i < 3; i++) {
    		this.cache.put(K1 + "-" + Integer.toString(i), V1 + "-" + Integer.toString(i));
    	}
    	this.cache.clear();
    	assertTrue(this.cache.size() == 0);
    	
    }
    
    @Test
    public void testClone()
    {
    	for(int i = 0; i < 3; i++) {
    		this.cache.put(K1 + "-" + Integer.toString(i), V1 + "-" + Integer.toString(i));
    	}
    	LRUCache clone_cache = (LRUCache) this.cache.clone();
    	assertTrue(clone_cache.size() == this.cache.size());
    	for(int i = 0; i < 3; i++) {
    		assertTrue(clone_cache.get(K1 + "-" + Integer.toString(i)).equals(V1 + "-" + Integer.toString(i)));
    	}  	
    }
    
    @Test
    public void testContains()
    {
    	this.cache.put(K1, V1);
    	assertTrue(this.cache.contains(V1));
    }
    
    @Test
    public void testNotContains()
    {
    	this.cache.put(K1, V1);
        assertTrue(!this.cache.contains(V2));
    }
    
    @Test
    public void testContainsKey()
    {
    	this.cache.put(K1, V1);
        assertTrue(this.cache.containsKey(K1));
    }
    
    @Test
    public void testNotContainsKey()
    {
    	this.cache.clear();
    	this.cache.put(K1, V1);
        assertFalse(this.cache.containsKey(K2));
    }
    
    @Test
    public void testContainsValue()
    {
    	this.cache.clear();
    	this.cache.put(K1, V1);
        assertTrue(this.cache.containsValue(V1));
    }
    
    @Test
    public void testNotContainsValue()
    {
    	this.cache.put(K1, V1);
        assertFalse(this.cache.containsValue(V2));
    }
    
    @Test 
    public void testGet()
    {
    	this.cache.put(K1, V1);
    	assertTrue(this.cache.get(K1) == V1);
    	assertTrue(this.cache.get(K2) == null);
    }
    
    @Test
    public void testContainsValue_morethanOnce()
    {
    	this.cache.put(K1, V1);
    	this.cache.put(K2, V1);
        assertTrue(this.cache.containsValue(V1));
    }
        
    @Test
    public void testGetMaxEntries()
    {
    	assertTrue(this.cache.getMaxEntries() == CACHE_CAPACITY);
    }
    
    @Test
    public void testIsEmpty_whenEmpty()
    {
    	assertTrue(this.cache.isEmpty());
    }
    
    @Test
    public void testIsEmpty_whenNotEmpty()
    {
    	this.cache.put(K1, V1);
    	assertFalse(this.cache.isEmpty());
    }
    
    @Test
    public void testKeySet()
    {
    	for(int i = 0; i < 3; i++) {
    		this.cache.put(K1 + "-" + Integer.toString(i), V1 + "-" + Integer.toString(i));
    	}
    	Set keySet = this.cache.keySet();
    	for(int i = 0; i < 3; i++) {
    		assertTrue(keySet.contains(K1 + "-" +Integer.toString(i)));
    	}
    }
    
    @Test
    public void testPut()
    {
    	this.cache.put(K1, V1);
    	assertTrue(this.cache.get(K1) == V1);    	 
    }
    
    @Test
    public void testPutMore()
    {
    	for(int i = 0; i < 3; i++) {
    		this.cache.put(K1 + "-" + Integer.toString(i), V1 + "-" + Integer.toString(i));
    		assertTrue(this.cache.get(K1 + "-" + Integer.toString(i)).equals(V1 + "-" + Integer.toString(i)));
    	}
    	    	 
    }
    
    @Test
    public void testRemove()
    {
    	this.cache.put(K1, V1);
    	this.cache.remove(K1);
    	assertFalse(this.cache.containsKey(K1));
    }
    
    @Test
    public void testSetMaxEntries()
    {
    	this.cache.setMaxEntries(5);
    	assertTrue(this.cache.getMaxEntries() == 5);
    }
    
    @Test
    public void testSize_zero()
    {
    	assertTrue(this.cache.size() == 0);
    }
    
    @Test
    public void testSize_one()
    {
    	this.cache.put(K1, V1);
    	assertTrue(this.cache.size() == 1);
    }
    
    @Test
    public void testSize_more()
    {
    	for(int i = 0; i < 3; i++) {
    		this.cache.put(K1 + "-" + Integer.toString(i), V1 + "-" + Integer.toString(i));
    	}
    	assertTrue(this.cache.size() == 3);
    }
    
    @Test
    public void testValues()
    {
    	for(int i = 0; i < 3; i++) {
    		this.cache.put(K1 + "-" + Integer.toString(i), V1 + "-" + Integer.toString(i));
    	}
    	Collection values = this.cache.values();
    	for(int i = 0; i < 3; i++) {
    		assertTrue(values.contains(V1 + "-" + Integer.toString(i)));
    	}
    }
    
}
