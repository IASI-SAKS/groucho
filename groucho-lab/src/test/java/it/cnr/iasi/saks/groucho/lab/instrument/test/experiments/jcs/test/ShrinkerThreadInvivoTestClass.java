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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.jcs.test;

import it.cnr.iasi.saks.groucho.common.Context;
import it.cnr.iasi.saks.groucho.isolation.RuntimeEnvironmentShield;
import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.JCSLRUCacheFactory;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.jcs.engine.memory.lru.LRUMemoryCache;
import org.junit.Ignore;
import org.junit.Test;

public class ShrinkerThreadInvivoTestClass {
	private static boolean EXIT_STATUS = false;
	
	private static List<String> FAILED_TESTS = new ArrayList<String>();
	
	private static final String LABEL_INDEXED_DISK_CACHE_PATH = "jcs.auxiliary.indexedDiskCache.attributes.DiskPath";
	private static final String LABEL_INDEXED_DISK_CACHE2_PATH = "jcs.auxiliary.indexedDiskCache2.attributes.DiskPath";
	
	private String backupDir;
	private Map<String, String> backupDirMap = null;
	private boolean flagCopied = false;

			
	public boolean simpleInvivoTestMethod(Context c) throws InvocationTargetException{
		this.configure();

		LRUMemoryCache memCache = ((LRUMemoryCache) c.getInstrumentedObject());


		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);			

//		this.testSimpleShrinkInvivo(memCache);
//
		this.testSimpleShrinkMutipleInvivo(memCache);
//
//		this.testSimpleShrinkMutipleWithEventHandlerInvivo(memCache);
		
		return getExitStatus();
	}

	public boolean invivoTestMethod(Context c) throws InvocationTargetException{
		this.configure();

		LRUMemoryCache memCache = ((LRUMemoryCache) c.getInstrumentedObject());

		TestGovernanceManager_ActivationWithProbability.setActivationProbability(0);			

		this.testAll(memCache);

		return getExitStatus();
	}

	private void configure() {
		setExitStatus();
		FAILED_TESTS.clear();
		this.backupDir = null;
		this.backupDirMap = new HashMap<String, String>();
	}

	private void testSimpleShrinkInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrink ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrink();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}
	
	private void testSimpleShrinkMutipleInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrinkMutiple ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrinkMutiple();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}

	private void testSimpleShrinkMutipleWithEventHandlerInvivo(LRUMemoryCache memCache) {
		System.out.println("Testing invivo: testSimpleShrinkMutipleWithEventHandler ... ");

		ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();
		unitTest.configureMemoryCache(memCache);
//		unitTest.configureMemoryCacheWithAMock();
		
		try{
			unitTest.testSimpleShrinkMutipleWithEventHandler();
		}catch(Throwable t) {
			resetExitStatus();
			System.out.println(t.getMessage());
			System.out.println("... ops!!!!!!!!!!");
		}

		System.out.println("... done!");
	}

	private void testAll(LRUMemoryCache memCache) throws InvocationTargetException {
		String mName = this.getCurrentMethodName();
		System.out.println("["+mName+"] Testing invivo ...");

		RuntimeEnvironmentShield shield = new RuntimeEnvironmentShield();
		
		int i =0;
		for (Method m : ShrinkerThreadUnitTest.class.getMethods()){
			String fullReflectiveMethodName = m.getDeclaringClass().getCanonicalName()+"@"+m.getName();
			if (m.isAnnotationPresent(Test.class) && (! m.isAnnotationPresent(Ignore.class))){	
				System.out.println("["+mName+"] Starting the invivo testing session on: " + fullReflectiveMethodName);
				
//if (fullReflectiveMethodName.endsWith("@testSimpleShrinkMutiple")) {
//if (! fullReflectiveMethodName.endsWith("@testSimpleShrink")) {

				ShrinkerThreadUnitTest unitTest = new ShrinkerThreadUnitTest();

				try {
					shield.applyCheckpoint(memCache);

					unitTest.configureMemoryCache(memCache);

					this.saveDataFromPersistency();

					System.out.println("Before ... "+fullReflectiveMethodName);
					m.invoke(unitTest);
				} catch (IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
					FAILED_TESTS.add(mName + " --> Reflection Error on " + fullReflectiveMethodName + " (" + e.getMessage() +")");
				} catch (InvocationTargetException e) {
System.out.println("stampo questo:"+e.getCause().getMessage());					
					e.getCause().printStackTrace();
					if (e.getCause() instanceof java.lang.AssertionError) {
						FAILED_TESTS.add(fullReflectiveMethodName);						
					}else {
						FAILED_TESTS.add(mName + " --> Some Failure on " + fullReflectiveMethodName + " (" + e.getCause().getMessage() +")");						
					}
				} catch (IOException e) {
					e.getCause().printStackTrace();
					FAILED_TESTS.add(mName + " --> Error while trying to save data from the persistency (" + e.getMessage() +")");
				} finally {
					try {
//this.toy();
						this.restoreDataFromPersistency();
					} catch (IOException e) {
						InvocationTargetException targetException = new InvocationTargetException(e.getCause(), mName + " --> Data have not been restored and they may result corrupted. File System Shield Error on " + fullReflectiveMethodName);
						throw targetException;
					}finally {
						shield.applyRollback(memCache);
					}	
				}

//}	
				i++;
				System.out.println("CHK ... "+i);
			}
		}
		
		System.out.println("["+mName+"] ... done!");
			
	}
	
	private void toy() throws IOException {
	}
	
	private void restoreDataFromPersistency() throws IOException {
		if (flagCopied) {
			for (String originalDir : this.backupDirMap.keySet()) {
				String copyDir = this.backupDirMap.get(originalDir);

				File copyDirFile = new File(copyDir);
				File originalDirFile = new File(originalDir);

				FileUtils.copyDirectory(copyDirFile, originalDirFile);				
//				FileUtils.deleteDirectory(copyDirFile);
//				copyDirFile.delete();
			}

			this.flagCopied = false;

			File destDir = new File(this.backupDir);
			FileUtils.deleteQuietly(destDir);
			this.backupDir = null;
			
			this.backupDirMap.clear();
		}
	}

	private void saveDataFromPersistency() throws IOException {		
		Properties p = new Properties();
		p.load(this.getClass().getResourceAsStream(JCSLRUCacheFactory.getConfFile()));
		String diskCachePath = p.getProperty(LABEL_INDEXED_DISK_CACHE_PATH);
		String diskCache2Path = p.getProperty(LABEL_INDEXED_DISK_CACHE2_PATH);
		
		this.backupDirMap.clear();
		this.backupDir = JCSLRUCacheFactory.generateTmpDir("jcs-copy");
		File destDir = new File(this.backupDir);
		File sourceDir;
		
		if (diskCachePath != null) { 
			sourceDir = new File(diskCachePath);
			File destSubDir = JCSLRUCacheFactory.generateRandomSubDir(destDir);
//			FileUtils.copyDirectoryToDirectory(sourceDir, destSubDir);
			FileUtils.copyDirectory(sourceDir, destSubDir);
			this.backupDirMap.put(diskCachePath, destSubDir.getAbsolutePath());
		}
		
		if (diskCache2Path != null) { 
			sourceDir = new File(diskCache2Path);
			File destSubDir2 = JCSLRUCacheFactory.generateRandomSubDir(destDir);
//			FileUtils.copyDirectoryToDirectory(sourceDir, destSubDir2);
			FileUtils.copyDirectory(sourceDir, destSubDir2);
			this.backupDirMap.put(diskCache2Path, destSubDir2.getAbsolutePath());
		}
		
		this.flagCopied = true;
	}

	private String getCurrentMethodName() {
		 String nameofCurrMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
		 return nameofCurrMethod;
	}

	
	public synchronized static boolean getExitStatus(){
		return EXIT_STATUS;
	}
	
	public synchronized static void resetExitStatus(){
		EXIT_STATUS = false;
		FAILED_TESTS.clear();
	}

	protected synchronized static void setExitStatus(){
		EXIT_STATUS = true;
	}

	public synchronized static List<String> getFailedTests() {
		List<String> list = new ArrayList<String>();
		for (String failedTest : FAILED_TESTS) {
			list.add(failedTest);
		}
		return list;
	}
}
