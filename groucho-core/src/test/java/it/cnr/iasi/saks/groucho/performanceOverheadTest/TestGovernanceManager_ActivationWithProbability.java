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
package it.cnr.iasi.saks.groucho.performanceOverheadTest;

import it.cnr.iasi.saks.groucho.callback.SimpleGovernanceManager;
import it.cnr.iasi.saks.groucho.common.Context;

public class TestGovernanceManager_ActivationWithProbability extends SimpleGovernanceManager {

	private static float probability = 0.5f;
	private int activationCounter;
	
	public TestGovernanceManager_ActivationWithProbability(){
		this.activationCounter = 0;
	}
	
	@Override
	public boolean evaluateActivation(Context context) {
		double rnd = Math.random();
		boolean result = (rnd <= TestGovernanceManager_ActivationWithProbability.probability);
		if (result){
			synchronized (this) {
				this.activationCounter++;
			}			
		}
		return result;
	}
	
	public synchronized static float getActivationProbability() {
		return probability;
	}

	public synchronized static void setActivationProbability(float probability) {
		TestGovernanceManager_ActivationWithProbability.probability = probability;
	}

	public synchronized void resetActivationCounter() {
		this.activationCounter = 0;
	}
	public synchronized int getActivationCounter() {
		return this.activationCounter;
	}
}
