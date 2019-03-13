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

import java.util.List;

import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;

public abstract class AbstractGovernanceManager {
	
	private boolean isInVivoTestingEnabled;

	private RuntimeEnvironmentShield environmentShield;
	
	public AbstractGovernanceManager(){
		this.isInVivoTestingEnabled = false;
		this.environmentShield = new RuntimeEnvironmentShield();
	} 
	
	public void runInvivoTestingSession(Context context){
		if (this.checkActivation(context)){
			System.out.println("the checkpoint of the considered object should be applied here ... ");		
			this.environmentShield.applyCheckpointOnContext(context);
			
			this.doInvivoTestingSession(context);
			
			System.out.println("... while its rollback is here.");		
			this.environmentShield.applyRollbackOnContext(context);
		}
		this.isInVivoTestingEnabled = false;
	}
	
	private boolean checkActivation(Context context){
		boolean exitStatus;
		
		// TO-DO this has to be improved, so that to check if there exists any precondition to the activation of an Invivo Testing Session
		this.isInVivoTestingEnabled = true;
		
		exitStatus = this.isInVivoTestingEnabled && this.evaluateActivation(context);		
		return exitStatus;
	}
	
	public abstract boolean evaluateActivation (Context context);
	
	protected abstract void doInvivoTestingSession(Context context);
}
