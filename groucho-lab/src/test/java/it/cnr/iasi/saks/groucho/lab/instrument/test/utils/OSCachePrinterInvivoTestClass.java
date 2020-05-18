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

import java.util.List;

import javax.servlet.http.HttpServletResponseWrapper;

import com.opensymphony.oscache.web.filter.CacheFilter;

public class OSCachePrinterInvivoTestClass {
	
	private static int COMPUTED_HASHCODE = -1; 

	public boolean invivoTestMethod(Context c){
		CacheFilter currentCacheFilterInstance = ((CacheFilter) c.getInstrumentedObject());
		
		List<Object> l = c.getOtherReferencesInContext();
		if (l == null) {
			System.out.println("lista nulla");
		}else {
			System.out.println("la size è :" +l.size());
		}
		
		int oldHashCode = currentCacheFilterInstance.hashCode();

		currentCacheFilterInstance = new CacheFilter();
		COMPUTED_HASHCODE = currentCacheFilterInstance.hashCode();

		String message = "(hashcode: "+COMPUTED_HASHCODE+") Invoked " + OSCachePrinterInvivoTestClass.class.getCanonicalName() + ":invivoTestMethod";
		message += "\n " + oldHashCode +" --> "+ COMPUTED_HASHCODE; 
		System.out.println(message);		
		
		
		return true;
	}
	
	public boolean anotherInvivoTestMethod(Context c){
		String message = "Invoked " + OSCachePrinterInvivoTestClass.class.getCanonicalName() + ":anotherInvivoTestMethod"; 
		System.out.println(message);
		return true;
	}

	public static int getLastComputedHashCode(){
		return COMPUTED_HASHCODE;
	}
}
