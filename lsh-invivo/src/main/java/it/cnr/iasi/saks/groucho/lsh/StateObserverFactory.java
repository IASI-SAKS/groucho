package it.cnr.iasi.saks.groucho.lsh;

import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.jep.LSHInvivoJep;

public class StateObserverFactory {
	
	protected static StateObserverFactory soFactory = null;
	
	private static StateObserver so = null;
	
	private static StateObserverLSH soLSH = null;
			
	protected StateObserverFactory() throws LSHException {
		so = new LSHInvivoJep();
		soLSH = new LSHInvivoJep();
	}

	public synchronized static StateObserverFactory getInstance() throws LSHException {
		if (soFactory == null) {
			soFactory = new StateObserverFactory();
		}
		return soFactory;
	}
	
	public StateObserver getStateObserver() {
		return so;
	}

	public StateObserver getFreshStateObserver() throws LSHException {
		return new LSHInvivoJep();
	}

	public StateObserverLSH getStateObserverLSH() {
		return soLSH;
	}

	public StateObserverLSH getFreshStateObserverLSH() throws LSHException {
		return new LSHInvivoJep();		
	}
}
