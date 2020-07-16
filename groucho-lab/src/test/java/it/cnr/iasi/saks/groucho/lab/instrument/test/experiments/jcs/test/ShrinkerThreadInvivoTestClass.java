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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

import org.apache.jcs.engine.memory.lru.LRUMemoryCache;

public class ShrinkerThreadInvivoTestClass {
	private static boolean EXIT_STATUS = false;
	
	public boolean invivoTestMethod(Context c){
		setExitStatus();
		LRUMemoryCache memCache = ((LRUMemoryCache) c.getInstrumentedObject());

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);			

//		this.testSimpleShrinkInvivo(memCache);

		this.testSimpleShrinkMutipleInvivo(memCache);

//		this.testSimpleShrinkMutipleWithEventHandlerInvivo(memCache);
		
		return getExitStatus();
	}

	private void testSimpleShrinkInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrink ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrink();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}
	
	private void testSimpleShrinkMutipleInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrinkMutiple ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrinkMutiple();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}

	private void testSimpleShrinkMutipleWithEventHandlerInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrinkMutipleWithEventHandler ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrinkMutipleWithEventHandler();
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

}
