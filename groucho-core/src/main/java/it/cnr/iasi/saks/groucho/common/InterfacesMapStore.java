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
package it.cnr.iasi.saks.groucho.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InterfacesMapStore {

	private HashMap<String, List<String>> interfacesMap;
	
	private static InterfacesMapStore INSTANCE = null;
	
	private InterfacesMapStore(){
		this.interfacesMap = new HashMap<String, List<String>>();
	}
	
	protected synchronized HashMap<String, List<String>> getMap() {
		return this.interfacesMap;
	}

	public static synchronized InterfacesMapStore getInstance(){
		if (INSTANCE == null){
			INSTANCE = new InterfacesMapStore();
		}
		return INSTANCE;
	}
	
	public synchronized void put(String className, String interfaceName){
		List<String> lst;  
		if (this.hasBeenProcessed(className)){
			lst = this.interfacesMap.get(className);
		}else{
			lst = new ArrayList<String>();
		}
		
		if (! lst.contains(interfaceName)){
			lst.add(interfaceName);
		}
		
		this.interfacesMap.put(className, lst);
	}

	public synchronized void put(String className, String[] interfaces){
		for (String interfaceName : interfaces) {
			this.put(className, interfaceName);
		}
	}

	public synchronized boolean hasBeenProcessed(String className) {
		boolean result = this.interfacesMap.containsKey(className);

		return result;
	}
	
	public synchronized boolean isImplemented(String className, String interfaceName) {
		boolean result = false;
		List<String> lst = this.interfacesMap.get(className);
		if (lst != null){
			result = lst.contains(interfaceName);
		}
		return result;
	}

	public synchronized List<String> dumpListOfAllInterfaces(String className) {
		List<String> dump = new ArrayList<String>();
		if (this.hasBeenProcessed(className)){
			for (String i : this.interfacesMap.get(className)) {
				dump.add(i);
			}
		}	
		return dump;
	}

}
