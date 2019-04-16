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
package it.cnr.iasi.saks.groucho.callback;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.common.StateCarver;

public class SmarterGovernanceManager extends SimpleGovernanceManager {

	private String carvedState = "";
	
	private void carveTheState(Context context) throws IllegalArgumentException, ClassNotFoundException{
		StateCarver carver = new StateCarver(context);
		this.carvedState = carver.carveAllFields();		
	}

/**
 * Implement the evaluation policy in this method
 * 	
 * @param context
 * @return
 * @throws IllegalArgumentException
 * @throws ClassNotFoundException
 */
	private boolean performEvaluation(Context context) throws IllegalArgumentException, ClassNotFoundException{
		this.carveTheState(context);
		System.out.println("Carved State From the inside: "+ this.carvedState);
		return true;
	}
	
	protected String getCarvedState(){
		return this.carvedState;
	}
	
	@Override
	public boolean evaluateActivation(Context context) {
		boolean result = false; 
		try {
			result = this.performEvaluation(context);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
