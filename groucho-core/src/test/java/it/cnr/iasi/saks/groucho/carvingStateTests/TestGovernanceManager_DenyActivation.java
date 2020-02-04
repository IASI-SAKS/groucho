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

import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;
import it.cnr.iasi.saks.groucho.callback.SmarterGovernanceManager;
import it.cnr.iasi.saks.groucho.common.Context;

public class TestGovernanceManager_DenyActivation extends SmarterGovernanceManager {
	
	private static final int MAX_TENTATIVES = 10; 
	private static final int MAX_SLEEP = 2000; 
	
	@Override
	public boolean evaluateActivation(Context context) {
		System.out.println("Starting carving on context: "+ context);
		boolean originalResult = super.evaluateActivation(context);
		return false;
	}
	
	/**
	 * The actual runInvivoTestingSession will contain locks and other synchronization 
	 * statements that may eventually neglect the execution of the in-vivo testing session.
	 * During the test it is important to be (almost) sure that the runInvivoTestingSession,
	 * is not skipped, otherwise it is likely that the tests become flaky. 
	 * 
	 * This method override reduce this risk by trying to run on in-vivo testing session
	 * several time (i.e. MAX_TENTATIVES). 
	 */
	@Override
	public void runInvivoTestingSession(Context context){
		boolean wasCarvedSomething = false;
		for (int count = 0; (count < MAX_TENTATIVES) && (!wasCarvedSomething); count++) {
			super.runInvivoTestingSession(context);
			wasCarvedSomething = !this.getCarvedState().isEmpty();
			if (!wasCarvedSomething) {
				System.out.println("Ooopps .... the In-vivo Testing Session was not launched. Trying again!");
				try {
					Thread.sleep(RandomGenerator.getInstance().nextInt(MAX_SLEEP));
				} catch (InterruptedException e) {
					// There is really nothing to to here!!!
//					e.printStackTrace();
				}
			}
		}
		if (!wasCarvedSomething) {
			System.out.println("Ooopps .... the test will fail!! Possibly not all the locks have been released.");
		}
	}

	public String getCarvedState (){
		return super.getCarvedState();
	}

}
