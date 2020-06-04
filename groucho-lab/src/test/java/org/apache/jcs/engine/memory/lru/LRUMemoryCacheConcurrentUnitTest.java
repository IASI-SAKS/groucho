package org.apache.jcs.engine.memory.lru;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import junit.extensions.ActiveTestSuite;
import junit.framework.Test;
import junit.framework.TestCase;

import java.io.InputStream;
import java.util.Properties;

import org.apache.jcs.engine.CacheElement;
import org.apache.jcs.engine.behavior.ICacheElement;
import org.apache.jcs.engine.control.CompositeCache;
import org.apache.jcs.engine.control.CompositeCacheManager;

/**
 * Test which exercises the LRUMemory cache. This one uses three different
 * regions for three threads.
 *
 * @version $Id: LRUMemoryCacheConcurrentUnitTest.java 536904 2007-05-10 16:03:42Z tv $
 */
public class LRUMemoryCacheConcurrentUnitTest
    extends TestCase
{
    /**
     * Number of items to cache, twice the configured maxObjects for the memory
     * cache regions.
     */
//    private static int items = 200;
    
    /**
     * Constructor for the TestDiskCache object.
     *
     * @param testName
     */
    public LRUMemoryCacheConcurrentUnitTest( String testName )
    {
        super( testName );
    }

    /**
     * Main method passes this test to the text test runner.
     *
     * @param args
     */
    public static void main( String args[] )
    {
        String[] testCaseName = { LRUMemoryCacheConcurrentUnitTest.class.getName() };
        junit.textui.TestRunner.main( testCaseName );
    }

    /**
     * A unit test suite for JUnit
     *
     * @return The test suite
     */
    public static Test suite()
    {
        ActiveTestSuite suite = new ActiveTestSuite();

        suite.addTest( new LRUMemoryCacheConcurrentUnitTest( "testLRUMemoryCache" )
        {
            public void runTest()
                throws Exception
            {
                this.runTestForRegion( "indexedRegion1" );
            }
        } );

        /*
         * suite.addTest( new TestDiskCache( "testIndexedDiskCache2" ) { public
         * void runTest() throws Exception { this.runTestForRegion(
         * "indexedRegion2" ); } } );
         *
         * suite.addTest( new TestDiskCache( "testIndexedDiskCache3" ) { public
         * void runTest() throws Exception { this.runTestForRegion(
         * "indexedRegion3" ); } } );
         */
        return suite;
    }

    /**
     * Test setup
     */
    public void setUp()
    {
        //JCS.setConfigFilename( "/TestDiskCache.ccf" );
    }

    /**
     * Adds items to cache, gets them, and removes them. The item count is more
     * than the size of the memory cache, so items should be dumped.
     *
     * @param region
     *            Name of the region to access
     *
     * @exception Exception
     *                If an error occurs
     */
    public void runTestForRegion( String region )
        throws Exception
    {
    	
        CompositeCacheManager cacheMgr = CompositeCacheManager.getUnconfiguredInstance();
//        cacheMgr.configure( "/TestDiskCache.ccf" );
        cacheMgr.configure( "/test-conf/TestDiskCache.ccf");
        
        int items = cacheMgr.getDefaultCacheAttributes().getMaxObjects() * 2;
        int purgedItems = items/2 + 2;
        
        CompositeCache cache = cacheMgr.getCache( region );

        LRUMemoryCache lru = new LRUMemoryCache();
        lru.initialize( cache );

        // Add items to cache

        for ( int i = 0; i < items; i++ )
        {
            ICacheElement ice = new CacheElement( cache.getCacheName(), i + ":key", region + " data " + i );
            ice.setElementAttributes( cache.getElementAttributes() );
            lru.update( ice );
        }

        System.out.println(lru.getSize());
        // Test that initial items have been purged

        for ( int i = 0; i < purgedItems; i++ )
        {
            assertNull( lru.get( i + ":key" ) );
        }

        // Test that last items are in cache

        for ( int i = purgedItems; i < items; i++ )
        {
            String value = (String) lru.get( i + ":key" ).getVal();
            assertEquals( region + " data " + i, value );
        }

        // Remove all the items

        for ( int i = 0; i < items; i++ )
        {
            lru.remove( i + ":key" );
        }

        // Verify removal

        for ( int i = 0; i < items; i++ )
        {
            assertNull( "Removed key should be null: " + i + ":key", lru.get( i + ":key" ) );
        }
    }

    
}
