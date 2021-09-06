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
