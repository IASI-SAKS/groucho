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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.rest_client.restTemplate.ApiClient;
import it.cnr.iasi.saks.groucho.lsh.rest_client.restTemplate.client.IsStateUnknownApi;
import it.cnr.iasi.saks.groucho.lsh.rest_client.restTemplate.client.MarkStateApi;
import it.cnr.iasi.saks.groucho.lsh.rest_client.restTemplate.client.ResetStateObserverApi;

public class StateObserverLSHRestClient implements StateObserverLSH{

	
	private IsStateUnknownApi isStateUnknownAPI;
	private MarkStateApi markStateApi;
	private ResetStateObserverApi resetStateObserverApi;

	public StateObserverLSHRestClient(String targetURLBasePath) {
        ApiClient apiClient = new ApiClient();
        if ((targetURLBasePath != null) && (!targetURLBasePath.isEmpty())){
        	apiClient= apiClient.setBasePath(targetURLBasePath); 
        }
		
		this.isStateUnknownAPI = new IsStateUnknownApi(apiClient);
		this.markStateApi = new MarkStateApi(apiClient);
	    this.resetStateObserverApi = new ResetStateObserverApi(apiClient);

	}
	
	@Override
	public boolean isStateUnknown(String carvedStateLSH) throws LSHException {
		String carvedStateLSH_encoded = this.encodeURL(carvedStateLSH);		
		boolean exitus = false; 
		try {
			Object result = this.isStateUnknownAPI.isStateUnknownLSH(carvedStateLSH_encoded);
			exitus = (boolean) result;
		} catch (Exception e) {
			LSHException lshEx = new LSHException(e);
			throw lshEx;
		}		

		return exitus;
	}

	@Override
	public void markState(String carvedStateLSH) throws LSHException {		
		String carvedStateLSH_encoded = this.encodeURL(carvedStateLSH);		
		boolean exitus = false; 
		try {
			Object result = this.markStateApi.markStateLSH(carvedStateLSH_encoded);
			exitus = (boolean) result;
		} catch (Exception e) {
			LSHException lshEx = new LSHException(e);
			throw lshEx;
		}
		if (!exitus) {
			LSHException lshEx = new LSHException("Unmarked Stated: " + carvedStateLSH);
			throw lshEx;			
		}
	}

	@Override
	public void resetStateObserver() throws LSHException {
		try {
			Object result = this.resetStateObserverApi.resetStateObserver();
		} catch (Exception e) {
			LSHException lshEx = new LSHException(e);
			throw lshEx;
		}
	}

	private String encodeURL(String carvedStateLSH) {
		String carvedStateLSH_encoded = carvedStateLSH;
		try {
			carvedStateLSH_encoded = URLEncoder.encode(carvedStateLSH, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e1) {
			System.out.println("********************************** WARNING FAILED TO ENCODE:"+ carvedStateLSH +"***********************************************");
		}
		return carvedStateLSH_encoded;
	}
}
