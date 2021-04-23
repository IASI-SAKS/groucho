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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.opensymphony.oscache.base.algorithm.LRUCache;

import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache.test.LRUCacheInvivoTestClass;
import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.OSCacheLRUCacheFactory;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import it.cnr.iasi.saks.groucho.tests.util.PropertyUtilNoSingleton;

public class LRUCacheOSCacheTest_IT {

	
	@Test
	public void invokeInvivoTest() throws InterruptedException, IOException {
		// the following statement binds the PropertyUtil to a No Singleton instance.
		// in other words, all the other classes accessing to
		// PropertyUtil.getInstance();
		// will not be affected by singleton
		PropertyUtilNoSingleton prop = PropertyUtilNoSingleton.getInstance();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);
//****************************

		for (int run = 0; run < 15; run++) {
			int capacity = 30 + RandomGenerator.getInstance().nextInt(99);
			int items = this.getNumberOfItems(capacity);
			
			LRUCache lru = this.configureLRUCache(capacity, items);
			
	        System.out.println("Waiting for a while ...");
	        Thread.sleep( 5000 );
	        System.out.println("... ok");

	        TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);

	        int memSize = lru.size();
	        System.out.println("And what about here ...");
	        
	        TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

			boolean condition = LRUCacheInvivoTestClass.getExitStatus();
			
			String failedTests = "";
			try {
				for (String item : LRUCacheInvivoTestClass.getFailedTests()) {
					failedTests += item + ", ";
				}
				
				System.out.println("[Run "+ run +"]\tItems: " + items + "\tSize: " + memSize + "\tCapacity: " + capacity + "\tCondition: "+condition + "\tFailed Tests: "+failedTests);			

				Assert.assertTrue("Some Tests Failed: "+failedTests, condition);
			}catch (AssertionError aErr) {
				/** Actually there is nothing to do here.
				 *  The experiment simply has to keep going trying 
				 *  invivo testing sessions over other configurations
				 */
			}	
			Assert.assertEquals("Something wrong happened, the cache has been corrupted!!", memSize, items);			
//			Assert.assertEquals("Something wrong happened, the cache has been corrupted!!", memSize, capacity);			
		}
	}
	
	private LRUCache configureLRUCache(int capacity, int items) throws IOException {
		
		LRUCache cache = OSCacheLRUCacheFactory.crateLRUCache(capacity);
		
		this.addElementsInCache(items, cache);
		
		return cache;
	}
	
	
	private int getNumberOfItems (int capacity) {	
		int items;
		switch (RandomGenerator.getInstance().nextInt(7)) {
		case 0:
			items = 0;
			break;
		case 1:
			items = 1;
			break;
		case 2:
			items = (capacity / 2) - 1;						
			break;
		case 3:
			items = capacity / 2;
			break;
		case 4:
			items = (capacity / 2) + 1;			
			break;
		case 5:
			items = capacity - 1;
			break;
		default:
			items = capacity;
			break;
		}
		return items;
	}
	
	private void addElementsInCache(int items, LRUCache lru) {
		for (int i = 0; i < items; i++) {
			String key = "key" + i;
			String value = "value" + i;

			lru.put(key, value);

	    	String returnedElement = (String) lru.get(key);
	    	Assert.assertNotNull("We should have received an element", returnedElement);
		}
	}


}
