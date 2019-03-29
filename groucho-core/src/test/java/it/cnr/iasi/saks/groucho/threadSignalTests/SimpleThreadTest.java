package it.cnr.iasi.saks.groucho.threadSignalTests;

import org.junit.Ignore;
import org.junit.Test;

public class SimpleThreadTest {

	@Ignore
	@Test
	public void firstSimpleTestLong(){
		this.firstSimpleTest(50);
	}
	
	@Test
	public void firstSimpleTest(){
		this.firstSimpleTest(10);
	}

	private void firstSimpleTest(int size){
		long startTS = System.currentTimeMillis();
		double rnd;
		Thread t = null;
		for (int i = 0; i < size; i++) {
			rnd = Math.random();
			if (rnd <= 0.9f){
				SimpleThread s = new SimpleThread();
				t = new Thread(s);
				t.start();
			}			
		}
		try {
			System.out.println("------------------------- Join Invoked");
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
