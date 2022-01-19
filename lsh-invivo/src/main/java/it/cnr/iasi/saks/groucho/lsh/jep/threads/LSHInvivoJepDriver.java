package it.cnr.iasi.saks.groucho.lsh.jep.threads;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;

public class LSHInvivoJepDriver implements StateObserver, StateObserverLSH{

	@Override
	public boolean isStateUnknown(String carvedState) throws LSHException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void markState(String carvedState) throws LSHException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetStateObserver() throws LSHException {
		// TODO Auto-generated method stub
		
	}

}
