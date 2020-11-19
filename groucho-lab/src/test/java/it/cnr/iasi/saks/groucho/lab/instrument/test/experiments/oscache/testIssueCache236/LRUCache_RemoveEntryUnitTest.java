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

import org.junit.Assert;
import org.junit.Test;

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
 * 
 */

public class LRUCache_RemoveEntryUnitTest extends LRUCache_AbstractUnitTest{
    
    private final String KEY_PREFIX = "Key-";
    private final String CONTENT_PREFIX = "Content-";

    public LRUCache_RemoveEntryUnitTest() {
    	super();
    }
	
    @Test
    public void testRemoval(){
    	for (int i=0; i < CACHE_CAPACITY+2; i++) {
    		String key = KEY_PREFIX + i;
    		String content = CONTENT_PREFIX + i;
    		this.cache.put(key, content);
    	}
    	
		this.cache.remove(KEY_PREFIX+1);	

		String fetchedContent=null;
		
		// Fetching an element that exists in the cache    		
    	fetchedContent = (String) this.cache.get(KEY_PREFIX + 2);
    
    	Assert.assertEquals(CONTENT_PREFIX+2, fetchedContent);
    	
		// Fetching an element that never existed in the cache    		
    	fetchedContent = (String) this.cache.get(KEY_PREFIX + "XXX");

    	Assert.assertNull(fetchedContent);    	    	

    	// Fetching an element that does not exists anymore in the cache    		
    	fetchedContent = (String) this.cache.get(KEY_PREFIX + 1);

    	Assert.assertNotEquals(CONTENT_PREFIX+1, fetchedContent);    	    	
    }


    @Test
    public void testFillAndRemoveCheckingEmptiness(){
    	this.fillAndRemove();
    	
    	
    	boolean condition = this.cache.isEmpty();
    	Assert.assertTrue(condition);
    }

    @Test
    public void testFillAndRemoveTryingToRead(){
    	this.fillAndRemove();
    	
    	// Fetching an element that should not exists anymore in the cache    		
    	String fetchedContentCachedOnDisk = (String) this.cache.get(KEY_PREFIX + 1);
    	String fetchedFirstContentCachedInMemory = (String) this.cache.get(KEY_PREFIX + 2);
    	String fetchedOtherContentCachedInMemory = (String) this.cache.get(KEY_PREFIX + 5);

    	Assert.assertNotEquals(CONTENT_PREFIX+5, fetchedOtherContentCachedInMemory);    	    	
    	Assert.assertNotEquals(CONTENT_PREFIX+2, fetchedFirstContentCachedInMemory);    	    	
    	Assert.assertNotEquals(CONTENT_PREFIX+1, fetchedContentCachedOnDisk);    	    	
    	Assert.assertNull(fetchedOtherContentCachedInMemory);    	    	    	
    	Assert.assertNull(fetchedFirstContentCachedInMemory);    	    	    	
    	Assert.assertNull(fetchedContentCachedOnDisk);    	    	    	
    }

	private void fillAndRemove() {

		for (int i=0; i < CACHE_CAPACITY+2; i++) {
    		String key = KEY_PREFIX + i;
    		String content = CONTENT_PREFIX + i;
    		this.cache.put(key, content);
    	}
            
    	for (int i=0; i < CACHE_CAPACITY+2; i++) {
    		String key = KEY_PREFIX + i;
    		this.cache.remove(key);
    	}
	}
}
