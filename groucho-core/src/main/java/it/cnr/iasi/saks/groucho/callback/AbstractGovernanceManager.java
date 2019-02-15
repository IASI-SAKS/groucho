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
