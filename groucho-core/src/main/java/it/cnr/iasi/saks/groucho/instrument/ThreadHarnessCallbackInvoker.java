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
package it.cnr.iasi.saks.groucho.instrument;

import it.cnr.iasi.saks.groucho.callback.GovernanceManagerFactory;
import it.cnr.iasi.saks.groucho.callback.ThreadHarness;

public class ThreadHarnessCallbackInvoker {
	public static void invokeCallback_incThread(){
		ThreadHarness callback = GovernanceManagerFactory.getInstance().getThreadHarness();
		callback.incThreads();
	}

	public static void invokeCallback_decThread(){
		ThreadHarness callback = GovernanceManagerFactory.getInstance().getThreadHarness();
		callback.decThreads();
	}

	public static void invokeCallback_checkInTheConstructors(){
		ThreadHarness callback = GovernanceManagerFactory.getInstance().getThreadHarness();
		try {
			callback.enableEnactmentInvivoTestingSession();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
