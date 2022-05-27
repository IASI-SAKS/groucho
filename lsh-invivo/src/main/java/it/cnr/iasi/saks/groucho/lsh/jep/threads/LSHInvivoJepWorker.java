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
	private Condition stoppingCondition;
	
	private static int TIMEOUT = 20000;
	
	private volatile boolean resultFetched;
//	private volatile boolean isResultReady;
	private volatile boolean isWorking;
	
	public LSHInvivoJepWorker() {
		this.workerCondition = JEP_WORKER_LOCK.newCondition();
		this.stoppingCondition = JEP_WORKER_LOCK.newCondition();
		
		this.resultFetched = false;
//		this.isResultReady=false;
		this.isWorking=false;

		this.waitingRequests = new ConcurrentLinkedQueue<ConditionToken>();

		this.isAlive = true;
	}
		
	@Override
	public void run() {
		StateObserverFactory soFactory = null;
		try {
			JEP_WORKER_LOCK.lock();
			soFactory = new StateObserverFactory();
			this.isWorking = true;
		} catch (LSHException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.isAlive = false;
		} finally {
			JEP_WORKER_LOCK.unlock();
		}
		
		while (this.isAlive) {
			try {
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
					this.workerCondition.awaitNanos(TIMEOUT);
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
		
// Dispose the JEP Instance		
		try {
			JEP_WORKER_LOCK.lock();
			soFactory.disposeFactoryState();
System.out.println("Facory Disposed");
		} catch (LSHException e) {
System.out.println("Facory have been disposed abnormally!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.isWorking = false;
			this.stoppingCondition.signalAll();
			JEP_WORKER_LOCK.unlock();
		}		
	}
	
	public void stopWorker() {
		JEP_WORKER_LOCK.lock();
		this.isAlive = false;
System.out.println("JEP Worker will stop soon ...");
		try {
// Waiting that the JEP Instance have been actually disposed		
//			this.stoppingCondition.awaitNanos(TIMEOUT);
			while (this.isWorking) this.stoppingCondition.await();
System.out.println("... done!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JEP_WORKER_LOCK.unlock();			
		}
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
