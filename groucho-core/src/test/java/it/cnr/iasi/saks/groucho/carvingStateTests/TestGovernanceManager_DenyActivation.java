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
