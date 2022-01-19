package it.cnr.iasi.saks.groucho.lsh.jep.threads;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.StateObserverFactory;
import it.cnr.iasi.saks.groucho.lsh.StateObserverLSH;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;

public class LSHInvivoJepWorker implements Runnable, StateObserver, StateObserverLSH{
	
	private static Lock JEP_WORKER_LOCK = new ReentrantLock();
//	private HashMap<Condition,ConditionPair> waitingConditions;
	private ConcurrentLinkedQueue<ConditionToken> waitingRequests;
	private boolean isStateUnknownLastResult;
	private boolean isAlive;
	
	private Condition workerCondition;
	
	private volatile boolean resultFetched;
//	private volatile boolean isResultReady;
	private volatile boolean isWorking;
	
	public LSHInvivoJepWorker() {
		this.workerCondition = JEP_WORKER_LOCK.newCondition();
		
		this.resultFetched = false;
//		this.isResultReady=false;
		this.isWorking=false;

		this.waitingRequests = new ConcurrentLinkedQueue<ConditionToken>();
		
		this.isAlive = true;
	}
		
	@Override
	public void run() {
		while (this.isAlive) {
			try {
				StateObserverFactory soFactory = StateObserverFactory.getInstance();
				StateObserver stateObserver = soFactory.getStateObserver();

				while (!this.waitingRequests.isEmpty()) {
					ConditionToken token = this.waitingRequests.remove();

					if (token.isIsStateUnknownConditionPair()) {
						String carvedState = token.getCarvedStateConditionPair();
						this.isStateUnknownLastResult = stateObserver.isStateUnknown(carvedState);
					} else if (token.isMarkStateConditionPair()) {
						String carvedState = token.getCarvedStateConditionPair();
						stateObserver.markState(carvedState);
					} else if (token.isResetStateObserverConditionPair()) {
						stateObserver.resetStateObserver();
					}

					JEP_WORKER_LOCK.lock();
					try {
						this.resultFetched = false;
//						this.isResultReady = true;
						token.setResultReady();
						Condition fetchingResultCondition = token.getWaitingCondition();
						fetchingResultCondition.signal();
						while (!this.resultFetched) fetchingResultCondition.await();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					} finally {
						JEP_WORKER_LOCK.unlock();
					}
				}

				JEP_WORKER_LOCK.lock();
				try {
					this.workerCondition.awaitNanos(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JEP_WORKER_LOCK.unlock();
				}

			} catch (LSHException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void stopWorker() {
		this.isAlive = false;
	}

	@Override
	public boolean isStateUnknown(String carvedState) throws LSHException {
		boolean result;
		
		Condition c = JEP_WORKER_LOCK.newCondition();
		ConditionToken token = ConditionToken.getIsStateUnknownConditionPair(c, carvedState);		

		result = this.requesterSyncronizationLogic(token);
		
//		JEP_WORKER_LOCK.lock();
//		try {
//			this.waitingRequests.add(token);
//			this.workerCondition.signal();
//			while (!token.isResultReady()) c.await();
//			result = this.isStateUnknownLastResult;
//			this.resultFetched=true;
//			c.signal();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			result = false;
//		} finally {
//			JEP_WORKER_LOCK.unlock();
//		}

		return result;
	}

	@Override
	public void markState(String carvedState) throws LSHException {
		Condition c = JEP_WORKER_LOCK.newCondition();
		ConditionToken token = ConditionToken.getMarkStateConditionPair(c, carvedState);		
		
		// In this case the Return Value should be ignored
		this.requesterSyncronizationLogic(token);
	}

	@Override
	public void resetStateObserver() throws LSHException {
		Condition c = JEP_WORKER_LOCK.newCondition();
		ConditionToken token = ConditionToken.getResetStateObserverConditionPair(c);		
		
		// In this case the Return Value should be ignored
		this.requesterSyncronizationLogic(token);
	}

	private boolean requesterSyncronizationLogic(ConditionToken token) throws LSHException {
		boolean result=false;
		
		Condition c = token.getWaitingCondition();
		
		JEP_WORKER_LOCK.lock();
		try {
			this.waitingRequests.add(token);
			this.workerCondition.signal();
			while (!token.isResultReady()) c.await();
			if (token.isIsStateUnknownConditionPair()) {
				result = this.isStateUnknownLastResult;
			}
			this.resultFetched=true;
			c.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		} finally {
			JEP_WORKER_LOCK.unlock();
		}

		return result;
	}


}
