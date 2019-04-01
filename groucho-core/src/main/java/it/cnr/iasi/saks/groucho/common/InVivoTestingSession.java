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
package it.cnr.iasi.saks.groucho.common;

public class InVivoTestingSession {
	
	private InVivoTestingSessionStates currentState;
	
	public InVivoTestingSession(){
		this.currentState = InVivoTestingSessionStates.INACTIVE;
	}

	protected InVivoTestingSessionStates getState(){
		return this.currentState;
	}

	public boolean isInactive(){
		return (this.currentState == InVivoTestingSessionStates.INACTIVE);
	}

	public boolean isActive(){
		return (this.currentState == InVivoTestingSessionStates.ACTIVE);
	}

	public boolean isActivating(){
		return (this.currentState == InVivoTestingSessionStates.ACTIVATING);
	}
	
	public void goNextConfiguration(){
		switch (this.currentState) {
		case INACTIVE:
			this.currentState = InVivoTestingSessionStates.ACTIVATING;
			break;
		case ACTIVATING:
			this.currentState = InVivoTestingSessionStates.ACTIVE;
			break;
		case ACTIVE:
			this.currentState = InVivoTestingSessionStates.INACTIVE;			
			break;
		default:
			this.currentState = InVivoTestingSessionStates.INACTIVE;
			break;
		}
	}

	public void resetConfiguration(){
		this.currentState = InVivoTestingSessionStates.INACTIVE;
	}
}
