package it.cnr.iasi.saks.groucho.lsh;

import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.jep.threads.LSHInvivoJepWorker;

public class ConcurrentStateObserverFactory {
	
	protected static ConcurrentStateObserverFactory csoFactory;
	
	private static LSHInvivoJepWorker jepWorker = null; 
	
	protected ConcurrentStateObserverFactory() throws LSHException {
		jepWorker = activateFreshJepWorker();
	}
	
	public synchronized static ConcurrentStateObserverFactory getInstance() throws LSHException {
		if (csoFactory == null) {
			csoFactory = new ConcurrentStateObserverFactory();
		}
		
		return csoFactory;
	}
	
	public StateObserver getStateObserver() {
		return jepWorker;
	}
	
	public StateObserverLSH getStateObserverLSH() {
		return jepWorker;
	}
	
	private static LSHInvivoJepWorker activateFreshJepWorker () {
		if (jepWorker != null) {
			jepWorker.stopWorker();
		}
		
		LSHInvivoJepWorker jepWorkerFresh = new LSHInvivoJepWorker();
		Thread workerThreadFresh = new Thread(jepWorkerFresh);		
		workerThreadFresh.start();
		jepWorker = jepWorkerFresh;
		
		return jepWorker;
	}

}
