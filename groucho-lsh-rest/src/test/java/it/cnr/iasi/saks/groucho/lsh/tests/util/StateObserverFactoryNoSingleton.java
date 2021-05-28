package it.cnr.iasi.saks.groucho.lsh.tests.util;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.StateObserverFactory;
import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.jep.LSHInvivoJep;

public class StateObserverFactoryNoSingleton extends StateObserverFactory{
	
	private StateObserver soLocal = null;
	
	private StateObserverLSH soLSHLocal = null;
	
	protected StateObserverFactoryNoSingleton() throws LSHException {
		this.soLocal = new LSHInvivoJep();
		this.soLSHLocal = new LSHInvivoJep();
		
		this.soLocal.resetStateObserver();
		this.soLSHLocal.resetStateObserver();
	}

//	@Override
	public synchronized static StateObserverFactory getInstance() throws LSHException {
		StateObserverFactoryNoSingleton freshInstance = new StateObserverFactoryNoSingleton(); 		
		soFactory = freshInstance; 
		
		return soFactory;
	}
	
	@Override
	public StateObserver getStateObserver() {
		return this.soLocal;
	}

	@Override
	public StateObserverLSH getStateObserverLSH() {
		return this.soLSHLocal;
	}

}
