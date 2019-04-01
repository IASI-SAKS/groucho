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
package it.cnr.iasi.saks.groucho.carvingStateTests;

import it.cnr.iasi.saks.groucho.callback.SmarterGovernanceManager;
import it.cnr.iasi.saks.groucho.common.Context;

public class TestGovernanceManager_DenyActivation extends SmarterGovernanceManager {

	@Override
	public boolean evaluateActivation(Context context) {
		boolean originalResult = super.evaluateActivation(context);
		return false;
	}
	
	public String getCarvedState (){
		return super.getCarvedState();
	}

}
