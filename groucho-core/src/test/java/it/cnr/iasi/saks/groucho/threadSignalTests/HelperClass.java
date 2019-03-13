package it.cnr.iasi.saks.groucho.threadSignalTests;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class HelperClass {
     
    //The Object used for synchronization among threads.
    public final static Object obj = new Object();
     
    public static class WaitingThread extends Thread {
         
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    System.out.println("[WaitingThread]: Waiting for another thread "
                                    + "to notify me...");
                    obj.wait();
                    System.out.println("[WaitingThread]: Successfully notified!");
                }
                catch (InterruptedException ex) {
                    System.err.println("[WaitingThread]: An InterruptedException was caught: "
                                    + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
     
    public static class WakingThread extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    System.out.println("[WakingThread]: Sleeping for some time...");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("[WakingThread]: Woke up!");
                     
                    System.out.println("[WakingThread]: About to notify another thread...");
                    obj.notify();
                    System.out.println("[WakingThread]: Successfully notified some other thread!");
                }
                catch (InterruptedException ex) {
                    System.err.println("[WaitingThread]: An InterruptedException was caught: "
                            + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
    
    @Test
    public void runTheTest(){
        Thread waitThread = new WaitingThread();
        Thread wakingThread = new WakingThread();
        Thread waitThread1 = new WaitingThread();
        Thread wakingThread1 = new WakingThread();
        Thread waitThread2 = new WaitingThread();
        Thread wakingThread2 = new WakingThread();
        Thread waitThread3 = new WaitingThread();
        Thread wakingThread3 = new WakingThread();
         
        //Start the execution.
        waitThread.start();
        wakingThread.start();
        waitThread1.start();
        wakingThread1.start();
        waitThread2.start();
        wakingThread2.start();
        waitThread3.start();
        wakingThread3.start();
         
        //Wait for all threads to terminate.
        try {
			waitThread.join();
	        wakingThread.join();    	
			waitThread1.join();
	        wakingThread1.join();    	
			waitThread2.join();
	        wakingThread2.join();    	
			waitThread3.join();
	        wakingThread3.join();    	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}