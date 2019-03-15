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

import java.util.concurrent.locks.ReentrantLock;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.common.InVivoTestingSession;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;

public abstract class AbstractGovernanceManager implements ThreadHarness {
	
	private volatile InVivoTestingSession inVivoTestingSession;
	private volatile boolean pauseOtherThreads;
	
	private RuntimeEnvironmentShield environmentShield;

	private static volatile Object INTERNAL_LOCK = new Object();
	private static volatile Object THREAD_LOCKER = new Object();

	private static volatile ReentrantLock INVIVO_SESSION_LOCK = new ReentrantLock();

	private static volatile int THREAD_COUNTER = 0; // If this variable exists, it means that there is at least 1 thread. So it must start from 1
	private static volatile int THREAD_INVIVO_SESSION_COUNTER = 0; // Count the thread that will ask for an Invivo Session
	private static volatile int THREAD_THAT_WILL_PAUSE_COUNTER = 0; // Count the thread that will wait on the THREAD_LOCKER

	private static volatile int INTERNAL_LOCK_WAIT_MAX = 10000;

	public AbstractGovernanceManager(){
		this.inVivoTestingSession = new InVivoTestingSession();
		this.pauseOtherThreads = true;
		this.environmentShield = new RuntimeEnvironmentShield();		
	} 
	
	@Override
	public void enableEnactmentInvivoTestingSession () throws InterruptedException{
// TODO: Not sure yet about this change. I need to think about it longer.		
//		if ((!this.inVivoTestingSession.isInactive()) && (this.pauseOtherThreads)){
		if ((this.inVivoTestingSession.isActivating()) && (this.pauseOtherThreads)){
			boolean gotLock = INVIVO_SESSION_LOCK.tryLock();
			if (! gotLock){
				this.pauseMe();
			}		
		}	
	}
	
	@Override
	public void incThreads(){
		THREAD_COUNTER++;
	}

	@Override
	public void decThreads(){
		synchronized (INTERNAL_LOCK) {
			THREAD_COUNTER--;		
			INTERNAL_LOCK.notify();
		}	
	}
	
	public void runInvivoTestingSession(Context context){
		long ID = Thread.currentThread().getId();
		this.runInvivoTestingSession(ID, context);
	}
	
	public void runInvivoTestingSession(long ID){
		this.runInvivoTestingSession(ID, null);
	}

	private void runInvivoTestingSession(long ID, Context context){
		boolean gotLock = INVIVO_SESSION_LOCK.tryLock();
		if (gotLock){
			THREAD_INVIVO_SESSION_COUNTER ++;
			try{
				System.out.println("Invivo SimpleThread: " + ID);
				if (this.checkActivation(context)){
					this.updatePauseOtherThreads(context);
					this.inVivoTestingSession.resetConfiguration();
					this.inVivoTestingSession.goNextConfiguration();
					if (this.pauseOtherThreads){
						this.waitOtherThreadsPaused(ID);
					}	
				
					this.inVivoTestingSession.goNextConfiguration();
					System.out.println("the checkpoint of the considered object should be applied here ... ");		
					this.environmentShield.applyCheckpointOnContext(context);				
				
					System.out.println("... while its rollback is here.");		
					this.environmentShield.applyRollbackOnContext(context);
					
					this.notifyOtherThreads();
				}
			} finally {
					this.inVivoTestingSession.resetConfiguration();
					for (int unlockCount = INVIVO_SESSION_LOCK.getHoldCount(); unlockCount > 0; unlockCount--) {
						INVIVO_SESSION_LOCK.unlock();
					}
			}
			THREAD_INVIVO_SESSION_COUNTER --;
		}	
	}

	private void updatePauseOtherThreads(Context context) {
		if ( context!=null ){
			this.pauseOtherThreads = context.checkPauseForOtherThreads();
		}else{
			this.pauseOtherThreads = true;
		}	
	}

	private boolean checkActivation(Context context){
		boolean exitStatus;
		
		// TO-DO this has to be improved, so that to check if there exists any precondition to the activation of an Invivo Testing Session
		exitStatus = true;
				
		exitStatus = exitStatus && this.evaluateActivation(context);		
		return exitStatus;
	}
	
	private void waitOtherThreadsPaused(long ID){		
		try {
			synchronized (INTERNAL_LOCK) {
				while ( ! (THREAD_THAT_WILL_PAUSE_COUNTER + THREAD_INVIVO_SESSION_COUNTER >= THREAD_COUNTER) ){
System.out.println("["+ID+"] Waiting: " + THREAD_THAT_WILL_PAUSE_COUNTER + ", InvivoReq: "+ THREAD_INVIVO_SESSION_COUNTER +",Thread: "+ THREAD_COUNTER );					
					INTERNAL_LOCK.wait(INTERNAL_LOCK_WAIT_MAX);
				}
System.out.println("["+ID+"] Waiting: " + THREAD_THAT_WILL_PAUSE_COUNTER + ", InvivoReq: "+ THREAD_INVIVO_SESSION_COUNTER +",Thread: "+ THREAD_COUNTER );					
			}	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void waitOtherThreadsPausedOLD(long ID){		
//		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//		// this number is only an estimation and it may grows, so we should put a kind of loop around the sync block
//		int nThreads = threadSet.size();  
		
		try {
			synchronized (INTERNAL_LOCK) {
				int count = 0; // the current thread should not be considered, so counter starts from 1 and condition is >=
				while ( ! (count + THREAD_INVIVO_SESSION_COUNTER >= THREAD_COUNTER) ){
System.out.println("["+ID+"] Count: " + count + ", InvivoReq: "+ THREAD_INVIVO_SESSION_COUNTER +",Thread: "+ THREAD_COUNTER );					
					INTERNAL_LOCK.wait();
					count ++;
				}
System.out.println("["+ID+"] Count: " + count + ", InvivoReq: "+ THREAD_INVIVO_SESSION_COUNTER +",Thread: "+ THREAD_COUNTER );					
			}	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void notifyOtherThreads(){
		synchronized (THREAD_LOCKER) {
			System.out.println("Notifico tutti");
			THREAD_THAT_WILL_PAUSE_COUNTER = 0;
			THREAD_LOCKER.notifyAll();
		}
	}

	private void pauseMe() throws InterruptedException{
		System.out.println("Ci Provo ... ");
		synchronized (INTERNAL_LOCK) {
			INTERNAL_LOCK.notify();
		}
		System.out.println("... notificato ...");
		synchronized (THREAD_LOCKER) {
			THREAD_THAT_WILL_PAUSE_COUNTER ++;
			THREAD_LOCKER.wait();
		}
		System.out.println("... sbloccato ...");
	}
	
	protected abstract boolean evaluateActivation (Context context);
	
	protected abstract void doInvivoTestingSession(Context context);
}
