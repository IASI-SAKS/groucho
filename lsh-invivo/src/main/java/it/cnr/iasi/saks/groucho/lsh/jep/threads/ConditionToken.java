package it.cnr.iasi.saks.groucho.lsh.jep.threads;

import java.util.concurrent.locks.Condition;

public class ConditionToken {
	private String carvedState;
	private String stateObserverOperation;
	
	private volatile boolean isResultReady;
	private Condition waitingCondition;
	
	private final static String IS_STATE_UNKNOWN = "isStateUnknown";
	private final static String MARK_STATE = "markState";
	private final static String RESET_STATE_OBSERVER = "resetStateObserver";
	
	private ConditionToken(Condition waitingCondition, String stateObserverOperation, String carvedState) {
		this.waitingCondition = waitingCondition;
		
		this.carvedState = carvedState;
		this.stateObserverOperation = stateObserverOperation;
		this.isResultReady = false;
	}
	
	private ConditionToken(Condition waitingCondition, String stateObserverOperation) {
		this(waitingCondition, stateObserverOperation, null);
	}

	public static ConditionToken getIsStateUnknownConditionPair(Condition waitingCondition, String carvedState) {
		ConditionToken c = new ConditionToken(waitingCondition, IS_STATE_UNKNOWN, carvedState);
		return c;
	}

	public static ConditionToken getMarkStateConditionPair(Condition waitingCondition, String carvedState) {
		ConditionToken c = new ConditionToken(waitingCondition, MARK_STATE, carvedState);
		return c;
	}
	
	public static ConditionToken getResetStateObserverConditionPair(Condition waitingCondition) {
		ConditionToken c = new ConditionToken(waitingCondition, RESET_STATE_OBSERVER);
		return c;
	}
	
	public boolean isIsStateUnknownConditionPair() {
		return this.stateObserverOperation.equals(IS_STATE_UNKNOWN);
	}

	public boolean isMarkStateConditionPair() {
		return this.stateObserverOperation.equals(MARK_STATE);
	}
	
	public boolean isResetStateObserverConditionPair() {
		return this.stateObserverOperation.equals(RESET_STATE_OBSERVER);
	}
	
	public String getCarvedStateConditionPair() {
		return this.carvedState;
	}
	
	public Condition getWaitingCondition() {
		return this.waitingCondition;
	}
	
	public synchronized boolean isResultReady() {
		return this.isResultReady;
	}

	public synchronized void setResultReady() {
		this.isResultReady = true;
	}
	public synchronized void resetResultReady() {
		this.isResultReady = false;
	}
}
