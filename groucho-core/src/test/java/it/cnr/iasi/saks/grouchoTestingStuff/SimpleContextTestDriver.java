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
package it.cnr.iasi.saks.grouchoTestingStuff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;
import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;

public class SimpleContextTestDriver {

	private int oracle_i;
	private boolean oracle_b;
	private float oracle_f;
	private long oracle_l;
	private double oracle_d;
	private char oracle_c;
	private byte oracle_byte;
	private short oracle_s;
	private SimpleClass oracle_mySimpleObject;
	
	private boolean PARAMS_VALIDATED;
	private int RANDOM_TOKEN;
	
	private final static String MARKER = ";";
	
	private File tmpFileValidationResults;
	
	public SimpleContextTestDriver() throws IOException{
		this.PARAMS_VALIDATED = false;
		
		this.tmpFileValidationResults = File.createTempFile("validationResults", ".txt");
	}
	
	@TestableInVivo(invivoTestClass = "it.cnr.iasi.saks.grouchoTestingStuff.SimpleContextTestDriverInvivoTest", invivoTest = "validateContextInvivoTest")
	public void doSomething(int i, boolean b, float f, long l, double d, char c, byte b1, short s, SimpleClass mySimpleObject) {
		mySimpleObject.wasteCPU();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	@TestableInVivo(invivoTestClass = "it.cnr.iasi.saks.groucho.performanceOverheadTest.DummyInvivoTest", invivoTest = "fooTest")
	public void doSomething(SimpleClass mySimpleObject) {
		mySimpleObject.wasteCPU();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public synchronized void validateParams(int i, boolean b, float f, long l, double d, char c, byte b1, short s, SimpleClass mySimpleObject) {

		this.PARAMS_VALIDATED = (this.oracle_i == i) &&
								(this.oracle_b == b) &&
								(this.oracle_f == f) &&
								(this.oracle_l == l) &&
								(this.oracle_d == d) &&
								(this.oracle_c == c) &&
								(this.oracle_byte == b1) &&
								(this.oracle_s == s) &&
								(this.oracle_mySimpleObject.hashCode() == mySimpleObject.hashCode());

		// Possibly at this point variables are subject to Checkpointing by Crochet.
		// Current values/update will be deleted during the Rollback.
		// Data that should persist to Checkpointing/Rollback are cached on disk
        this.cacheComputedValues();
	}

	public synchronized void setParams(int i, boolean b, float f, long l, double d, char c, byte b1, short s, SimpleClass mySimpleObject) {
		this.oracle_i = (new Integer(i)).intValue();
		this.oracle_b = b;
		this.oracle_f = f;
		this.oracle_l = l;
		this.oracle_d = d;
		this.oracle_c = c;
		this.oracle_byte = b1;
		this.oracle_s = s;
		this.oracle_mySimpleObject = mySimpleObject;
		
		this.RANDOM_TOKEN = RandomGenerator.getInstance().nextInt();
	}

	public synchronized boolean areParamsValidated() {
        boolean exitus = false;
		try {
			// Possibly variables were subject to Checkpointing by Crochet.
			// Current values/update may have been deleted during the Rollback.
			// Data are retrieved form a cached on disk
			String[] tokens = this.retrieveDataFromCache();
			
			this.PARAMS_VALIDATED = Boolean.parseBoolean(tokens[0]);
			
			int saved_PARAMS_CHECKER = Integer.parseInt(tokens[1]);
			
			exitus = (this.PARAMS_VALIDATED) && (this.RANDOM_TOKEN == saved_PARAMS_CHECKER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exitus;
	}

	private void cacheComputedValues() {
		try {
			PrintWriter stream = new PrintWriter(this.tmpFileValidationResults);
			stream.println(this.PARAMS_VALIDATED + MARKER +this.RANDOM_TOKEN);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String[] retrieveDataFromCache() throws FileNotFoundException, IOException {
		BufferedReader stream = new BufferedReader(new FileReader(tmpFileValidationResults));
		String readLine = stream.readLine();			
		stream.close();
		String[] tokens = readLine.split(MARKER);
		return tokens;
	}

}

