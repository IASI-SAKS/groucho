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

import it.cnr.iasi.saks.groucho.lsh.ConcurrentStateObserverFactory;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;

public class ConcurrentStateObserverFactoryNoSingleton extends ConcurrentStateObserverFactory {

	protected ConcurrentStateObserverFactoryNoSingleton() throws LSHException {
	}

	public synchronized static void resetFactory() throws LSHException {
		csoFactory = null;
	}
}
