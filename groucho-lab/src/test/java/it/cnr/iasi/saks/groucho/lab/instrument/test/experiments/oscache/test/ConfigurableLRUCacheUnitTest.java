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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.opensymphony.oscache.base.algorithm.LRUCache;

import ch.usi.precrime.lrucache.LRUCacheTest;

/*
 * This class is a re-implementation of the original Unit Test:
 * ch.usi.precrime.lrucache.LRUCacheTest
 * developed at Università della Svizzera Italiana (USI) 
 * independently from the GROUCHO project, and by only consulting
 * the documentation available about LRUCache in OSCache project. 
 * 
 * This new implementation has only 2 minor purposes:
 * 1. separate the configuration part of the tests from their actual execution.
 *    this way it is easier to reuse them during invivo testing sessions.
 * 2. the tests assume the memory may not be empty, thus the for statements
 *    that locally populate the memory starts from the item at position `memory.getSize()`    
 * For all the rest the test is completely equivalent
 * to the one originally conceived by the tester at USI.
 */

public class ConfigurableLRUCacheUnitTest extends LRUCacheTest {
		
	public ConfigurableLRUCacheUnitTest() {
		System.out.println("... DEAFULT CONFIGURATION DONE!!!");
	}

	public void updateLRUCache(LRUCache mem) {
		this.cache = mem;
		System.out.println("... CONFIGURATION DONE!!!");
	}

	
    @Test
    public void testDummy()
    {
    	System.out.println("Invoked Dummy Test ... Passed");
    	assertTrue(true);
    }

}
