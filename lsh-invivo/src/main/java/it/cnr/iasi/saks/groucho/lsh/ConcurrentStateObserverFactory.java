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
