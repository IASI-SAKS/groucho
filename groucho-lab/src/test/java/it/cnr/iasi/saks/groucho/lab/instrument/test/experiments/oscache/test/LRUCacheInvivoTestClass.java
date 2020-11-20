package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache.test;

import java.util.List;

import com.opensymphony.oscache.base.algorithm.LRUCache;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

public class LRUCacheInvivoTestClass {
	private static boolean EXIT_STATUS = false;
	
	public boolean invivoTestMethod(Context c){
		setExitStatus();
		
		LRUCache memCache = ((LRUCache) c.getInstrumentedObject());

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);			

		this.testDummyInvivo(memCache);

//		this.testClear_oneInvivo(memCache);
//
//		this.testAllInvivo(memCache);
		
		return getExitStatus();
	}

	private void testAllInvivo(LRUCache memCache) {
		// TODO Auto-generated method stub		
	}

	private void testClear_oneInvivo(LRUCache memCache) {
		System.out.println("Testing invivo: testClear_oneInvivo ... ");

		ConfigurableLRUCacheUnitTest unitTest = new ConfigurableLRUCacheUnitTest();
		unitTest.updateLRUCache(memCache);
		
		try{
			unitTest.testClear_one();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}

	private void testDummyInvivo(LRUCache memCache) {
		System.out.println("Testing invivo: testDummyInvivo ... ");

		ConfigurableLRUCacheUnitTest unitTest = new ConfigurableLRUCacheUnitTest();
		unitTest.updateLRUCache(memCache);
		
		try{
			unitTest.testDummy();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}

	public synchronized static boolean getExitStatus(){
		return EXIT_STATUS;
	}
	
	public synchronized static void resetExitStatus(){
		EXIT_STATUS = false;
	}

	protected synchronized static void setExitStatus(){
		EXIT_STATUS = true;
	}

	public static List<String> getFailedTests() {
		// TODO Auto-generated method stub
		return null;
	}

}
