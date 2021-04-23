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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.oscache.base.algorithm.LRUCache;
import com.opensymphony.oscache.base.persistence.PersistenceListener;
import com.opensymphony.oscache.plugins.diskpersistence.AbstractDiskPersistenceListener;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.OSCacheLRUCacheFactory;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

public class LRUCacheInvivoTestClass {
	private static boolean EXIT_STATUS = false;
	private static List<String> FAILED_TESTS = new ArrayList<String>();
	
	private File destDir = null;
	private File sourceDir = null;
	private boolean flagCopied = false;

	public boolean invivoTestMethod(Context c) throws InvocationTargetException{
		configure();
		
		LRUCache memCache = ((LRUCache) c.getInstrumentedObject());

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);			

//		this.dummyTestInvivo(memCache);
//
//		this.randomFailureDummyTest(memCache);
//
//		this.testClear_oneInvivo(memCache);
//
//		this.testAllDummyInvivo(memCache);
//
		this.testAllInvivo(memCache);
		
		return getExitStatus();
	}

	private void testAllInvivo(LRUCache memCache) throws InvocationTargetException {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		
		int i =0;
		for (Method m : ConfigurableLRUCacheUnitTest.class.getMethods()){
//		for (Method m : LRUCacheTest.class.getMethods()){
			String fullReflectiveMethodName = m.getDeclaringClass().getCanonicalName()+"@"+m.getName();
			if (m.isAnnotationPresent(Test.class) && (! m.isAnnotationPresent(Ignore.class))){	
				System.out.println("["+mName+"] Starting the invivo testing session on: " + fullReflectiveMethodName);
				try {
					shield.applyCheckpoint(memCache);
					this.saveDataFromPersistency(memCache);
					
					ConfigurableLRUCacheUnitTest unitTest = new ConfigurableLRUCacheUnitTest();
					unitTest.updateLRUCache(memCache);
					
					System.out.println("Before ... "+fullReflectiveMethodName);
					m.invoke(unitTest);
				} catch (IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
					FAILED_TESTS.add(mName + " --> Reflection Error on " + fullReflectiveMethodName + " (" + e.getMessage() +")");
				} catch (InvocationTargetException e) {
//					e.printStackTrace();
					e.getCause().printStackTrace();
					if (e.getCause() instanceof java.lang.AssertionError) {
						FAILED_TESTS.add(fullReflectiveMethodName);						
					}else {
						FAILED_TESTS.add(mName + " --> Some Failure on " + fullReflectiveMethodName + " (" + e.getCause().getMessage() +")");						
					}
				}catch (IOException e) {
					e.printStackTrace();
					FAILED_TESTS.add(mName + " --> Data have not been copied and they may result corrupted. File System Shield Error on " + fullReflectiveMethodName + " (" + e.getMessage() +")");
				} finally {
					shield.applyRollback(memCache);
					try {
						this.restoreDataFromPersistency(memCache);
					} catch (IOException e) {
						InvocationTargetException targetException = new InvocationTargetException(e.getCause(), mName + " --> Data have not been restored and they may result corrupted. File System Shield Error on " + fullReflectiveMethodName);
						throw targetException;
					}
				} 				
				i++;
				System.out.println("CHK ... "+i);
			}
		}
		
		System.out.println("["+mName+"] ... done!");
	}

	private void testClear_oneInvivo(LRUCache memCache) {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		ConfigurableLRUCacheUnitTest unitTest = new ConfigurableLRUCacheUnitTest();
		unitTest.updateLRUCache(memCache);
		
		try{
			unitTest.testClear_one();
		}catch(Throwable t) {
			FAILED_TESTS.add(mName);
			resetExitStatus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("["+mName+"] ... done!");
	}

	private void dummyTestInvivo(LRUCache memCache) {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		ConfigurableLRUCacheUnitTest unitTest = new ConfigurableLRUCacheUnitTest();
		unitTest.updateLRUCache(memCache);
		
		try{
			unitTest.dummyTest();
		}catch(Throwable t) {
			FAILED_TESTS.add(mName);
			resetExitStatus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("["+mName+"] ... done!");
	}

	private void randomFailureDummyTest(LRUCache memCache) {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		ConfigurableLRUCacheUnitTest unitTest = new ConfigurableLRUCacheUnitTest();
		unitTest.updateLRUCache(memCache);
		
		try{
			unitTest.randomFailureDummyTest();
		}catch(Throwable t) {
			FAILED_TESTS.add(mName);
			resetExitStatus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("["+mName+"] ... done!");
	}

	private void failureDummyTest(LRUCache memCache) {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		ConfigurableLRUCacheUnitTest unitTest = new ConfigurableLRUCacheUnitTest();
		unitTest.updateLRUCache(memCache);
		
		try{
			unitTest.dummyTest();
			throw new Exception();
		}catch(Throwable t) {
			FAILED_TESTS.add(mName);
			resetExitStatus();
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("["+mName+"] ... done!");
	}

	private void testAllDummyInvivo(LRUCache memCache) {		
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		for (Method m : LRUCacheInvivoTestClass.class.getDeclaredMethods()){
			String declaredMethodName = m.getName(); 
			if (declaredMethodName.matches(".*[dD]ummyTest.*")) {
				try {
//					m.invoke(this,memCache);
					m.invoke(this,declaredMethodName);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					FAILED_TESTS.add(mName + " --> Reflection Error (" + e.getMessage() +")");
				} 
				
			}
		}
		System.out.println("["+mName+"] ... done!");
	}

	private void saveDataFromPersistency(LRUCache memCache) throws IOException{
		PersistenceListener listener = memCache.getPersistenceListener();
		if (( listener instanceof AbstractDiskPersistenceListener ) && (!this.flagCopied)){
			AbstractDiskPersistenceListener diskListener =  (AbstractDiskPersistenceListener) listener;
			String destinationDir = OSCacheLRUCacheFactory.GENERATE_CACHE_PATH("osc-copy");
			this.destDir = new File(destinationDir);
			this.sourceDir = diskListener.getCachePath();
			
			FileUtils.copyDirectory(this.sourceDir, this.destDir);
			this.flagCopied = true;
		}		
	}

	private void restoreDataFromPersistency(LRUCache memCache) throws IOException{
		if (flagCopied) {
			try {
				FileUtils.copyDirectory(this.destDir, this.sourceDir);
				FileUtils.deleteQuietly(this.destDir);
//				FileUtils.deleteDirectory(this.destDir);
//				this.destDir.delete();
			} finally {
				this.destDir = null;
				this.sourceDir = null;
				this.flagCopied = false;
			}
		}				
	}
	
	public synchronized static boolean getExitStatus(){
        System.out.println("is it here ?!?! ...");
		return EXIT_STATUS;
	}
	
	public synchronized static void resetExitStatus(){
		EXIT_STATUS = false;
	}

	protected synchronized static void setExitStatus(){
		EXIT_STATUS = true;
	}

	protected synchronized static void configure(){
		setExitStatus();
		FAILED_TESTS.clear();		
	}

	public synchronized static List<String> getFailedTests() {
		List<String> list = new ArrayList<String>();
		for (String failedTest : FAILED_TESTS) {
			list.add(failedTest);
		}
		return list;
	}
	
	private String getCurrentMethodName() {
		 String nameofCurrMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
		 return nameofCurrMethod;
	}

//	@Test
//	public void foo() {
//		for (Method m : LRUCacheInvivoTestClass.class.getDeclaredMethods()){
//			String mName = m.getName(); 
//			boolean flag = mName.matches(".*[dD]ummyTest.*");
//			System.out.println(mName + " ... " + flag);
//		}
//
//	}
}
