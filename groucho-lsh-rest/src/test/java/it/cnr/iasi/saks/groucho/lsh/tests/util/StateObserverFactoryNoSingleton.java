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
