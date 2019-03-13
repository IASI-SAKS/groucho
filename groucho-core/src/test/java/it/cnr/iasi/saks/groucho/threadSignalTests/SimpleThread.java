package it.cnr.iasi.saks.groucho.threadSignalTests;

import org.junit.Test;

import it.cnr.iasi.saks.groucho.callback.AbstractGovernanceManager;
import it.cnr.iasi.saks.groucho.callback.GovernanceManagerFactory;
import it.cnr.iasi.saks.groucho.callback.ThreadHarnessCallbackInvoker;

public class SimpleThread implements Runnable{
	
	private boolean isAlive;
	private SimpleClass c;
	private static int COUNTER;
	private int id;

	public SimpleThread(){
		this.genID();
		System.out.println("Created SimpleThread: " + this.id);
		this.c = new SimpleClass();
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_checkInTheConstructors();
// *************************************************************		
	}
	
	@Override
	public void run() {
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_IncThread();
// *************************************************************		
		System.out.println("Launched SimpleThread: " + this.id);
		this.isAlive = true;
		while (this.isAlive){
			this.doSomething();
			double rnd = Math.random();
			this.isAlive = (rnd >= 0.5f);
		}	
		System.out.println("Closing SimpleThread: " + this.id);
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_decThread();
// *************************************************************		
	}
	
	private synchronized void genID(){
		COUNTER++;
		this.id = COUNTER;
	}
	
	private void doSomething() {
		this.c = new SimpleClass();
		this.c.wasteCPU();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double rnd = Math.random();
		if (rnd <= 0.5f){
			AbstractGovernanceManager gm = GovernanceManagerFactory.getInstance().getGovernanceManager();
			gm.runInvivoTestingSession(id);
		}
	}

	@Test
	public void firstSimpleTest(){
		long startTS = System.currentTimeMillis();
		double rnd;
		Thread t = null;
		for (int i = 0; i < 50; i++) {
			rnd = Math.random();
			if (rnd <= 0.9f){
				SimpleThread s = new SimpleThread();
				t = new Thread(s);
				t.start();
			}			
		}
		try {
			System.out.println("------------------------- Invocata la Join");
			if (t!=null){
				t.join();
			}
			System.out.println("------------------------- done");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long finishTS = System.currentTimeMillis();
		float elapsedSec = (finishTS - startTS)/1000;

		System.out.println("Completed in almost: " + elapsedSec + " sec.");		
	}
	
}

class SimpleClass {
	public SimpleClass (){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// *************************************************************		
		ThreadHarnessCallbackInvoker.invokeCallback_checkInTheConstructors();
// *************************************************************		
	}

	public void wasteCPU(){
		int i = 1000;
		while (i>=0){
			i--;
		}
	}
}