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
		
	private LSHInvivoJep so = null;	
			
	public StateObserverFactory() throws LSHException {
		this.so = new LSHInvivoJep();
	}

	public StateObserver getStateObserver() {
		return this.so;
	}

	public void refreshFactoryState() throws LSHException {
		this.disposeFactoryState();
		
		this.so = new LSHInvivoJep();
	}

	public void disposeFactoryState() throws LSHException {
		this.disposeStateObserver();
	}

	protected void disposeStateObserver() throws LSHException {
		if (this.so != null) {
			((LSHInvivoJep)this.so).detachJEP();
		}
	}

	public StateObserver getFreshStateObserver() throws LSHException {
		return new LSHInvivoJep();
	}

	public StateObserverLSH getStateObserverLSH() {
		return this.so;
	}

	protected void disposeStateObserverLSH() throws LSHException {
		this.disposeStateObserver();
	}
	
	public StateObserverLSH getFreshStateObserverLSH() throws LSHException {
		return new LSHInvivoJep();		
	}
	
}
