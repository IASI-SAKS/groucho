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
package it.cnr.iasi.saks.groucho.lsh.rest_client;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;

public class StateObserverRestClientFactory {
		
	private StateObserverRestClient so = null;	
	private StateObserverLSHRestClient soLSH = null;	
			
	public StateObserverRestClientFactory() {
// MAYBE IMPROVE DEFAULT CONSTRUCTOR WITH A REFENCE TO THIS LABEL
//		PropertyUtil.getInstance().getProperty(PropertyUtil.LSH_REST_CLIENT_URL_BASE_PATH_LABEL);
		this(null);
	}

	public StateObserverRestClientFactory(String targetURLBasePath) {
		this.refreshFactoryState(targetURLBasePath);
	}

	public StateObserver getStateObserver() {
		return this.so;
	}

	public StateObserverLSH getStateObserverLSH() {
		return this.soLSH;
	}
	
	public void refreshFactoryState(String targetURLBasePath) {
		this.so = new StateObserverRestClient(targetURLBasePath); 
		this.soLSH = new StateObserverLSHRestClient(targetURLBasePath);
	}

}
