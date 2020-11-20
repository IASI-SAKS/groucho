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

		for (int run = 0; run < 10; run++) {
			int items = 30 + RandomGenerator.getInstance().nextInt(99);
			LRUCache lru = this.configureLRUCache(items);
			
	        System.out.println("Waiting for a while ...");
	        Thread.sleep( 5000 );
	        System.out.println("... ok");

	        TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);

	        int memSize = lru.size();

	        TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);

			boolean condition = LRUCacheInvivoTestClass.getExitStatus();
			
			String failedTests = "";
			for (String item : LRUCacheInvivoTestClass.getFailedTests()) {
				failedTests += item + ",";
			}
	        System.out.println("[Run "+ run +"]\tItems: " + items + "\tSize: " + memSize + "\tCondition:"+condition);			

	        Assert.assertEquals("Something wrong happened, the cache has been corrupted!!", memSize, items);			
			Assert.assertEquals(failedTests, condition);
		}
	}
	
	private LRUCache configureLRUCache(int items) throws IOException {
		
		LRUCache cache = OSCacheLRUCacheFactory.crateLRUCache(items);
		
		this.addElementsInCache(items, cache);
		
		return cache;
	}
	
	private void addElementsInCache(int items, LRUCache lru) {
		for (int i = 0; i < items; i++) {
			String key = "key" + i;
			String value = "value";

			lru.put(key, value);

	    	String returnedElement = (String) lru.get(key);
	    	Assert.assertNotNull("We should have received an element", returnedElement);
		}
	}


}
