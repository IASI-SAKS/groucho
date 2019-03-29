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
