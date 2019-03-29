package it.cnr.iasi.saks.groucho.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.objectweb.asm.ClassReader;

public class InterfacesMapStore {

	private HashMap<String, List<String>> interfacesMap;
	private HashMap<String, String> superClassMap;
	
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
