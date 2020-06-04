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
import org.apache.jcs.engine.memory.lru.LRUMemoryCache;

public class ShrinkerThreadInvivoTestClass {
	private static boolean EXITUS = false;
	
	public boolean invivoTestMethod(Context c){
		setExitus();
		LRUMemoryCache memCache = ((LRUMemoryCache) c.getInstrumentedObject());

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrink();
//			unitTest.testSimpleShrinkMutiple();
//			unitTest.testSimpleShrinkMutipleWithEventHandler();
		}catch(Exception t) {
			resetExitus();
		}
		
		return getExitus();
	}
	
	public synchronized static boolean getExitus(){
		return EXITUS;
	}
	
	public synchronized static void resetExitus(){
		EXITUS = false;
	}

	protected synchronized static void setExitus(){
		EXITUS = true;
	}
	
}
