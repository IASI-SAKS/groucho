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

import it.cnr.iasi.saks.groucho.common.Context;
import org.apache.jcs.engine.memory.lru.LRUMemoryCache;

public class LRUMemoryCachePrinterInvivoTestClass {
	
	private static String COMPOSITE_CACHE_NAME = ""; 

	public boolean invivoTestMethod(Context c){
		String compositeCacheName = ((LRUMemoryCache) c.getInstrumentedObject()).getCompositeCache().getCacheName();
		int oldUpdateCount = ((LRUMemoryCache) c.getInstrumentedObject()).getCompositeCache().getUpdateCount();
		int newUpdateCount = oldUpdateCount+5;
		((LRUMemoryCache) c.getInstrumentedObject()).getCompositeCache().setUpdateCount(newUpdateCount);

		COMPOSITE_CACHE_NAME = compositeCacheName;
		
		String message = "("+compositeCacheName+") Invoked " + LRUMemoryCachePrinterInvivoTestClass.class.getCanonicalName() + ":invivoTestMethod";
		message += "\n " + oldUpdateCount +" --> "+ newUpdateCount; 
		System.out.println(message);
		
		return true;
	}
	
	public boolean anotherInvivoTestMethod(Context c){
		String message = "Invoked " + LRUMemoryCachePrinterInvivoTestClass.class.getCanonicalName() + ":anotherInvivoTestMethod"; 
		System.out.println(message);
		return true;
	}

	public static String getLastObservedCompositeCacheName(){
		return COMPOSITE_CACHE_NAME;
	}
}
