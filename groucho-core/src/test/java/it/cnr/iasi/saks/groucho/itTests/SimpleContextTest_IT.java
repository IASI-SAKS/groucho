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
package it.cnr.iasi.saks.groucho.itTests;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.carvingStateTests.RandomGenerator;
import it.cnr.iasi.saks.groucho.config.PropertyUtil;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import it.cnr.iasi.saks.groucho.tests.util.PropertyUtilNoSingleton;
import it.cnr.iasi.saks.grouchoTestingStuff.SimpleClass;
import it.cnr.iasi.saks.grouchoTestingStuff.SimpleContextTestDriver;

public class SimpleContextTest_IT {
	private static final String GM_ACTIVATION_WITH_PROBABILITY_CLASSNAME = "it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability";
    private static final float ACTIVATION_PROBABILITY_WHEN_VARYING_NUMER_OF_THERADS = 1f;

	private int oracle_i;
	private boolean oracle_b;
	private float oracle_f;
	private long oracle_l;
	private double oracle_d;
	private char oracle_c;
	private byte[] oracle_byteVector;
	private short oracle_s;
	private SimpleClass oracle_mySimpleObject;

	@Test
	public void validateContextTestIT() throws IOException {
		PropertyUtilNoSingleton prop = PropertyUtilNoSingleton.getInstance();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(ACTIVATION_PROBABILITY_WHEN_VARYING_NUMER_OF_THERADS);
		
		prop.setProperty(PropertyUtil.GOVERNANCE_MANAGER_CLASS_LABEL, GM_ACTIVATION_WITH_PROBABILITY_CLASSNAME);

		
		RandomGenerator rnd = RandomGenerator.getInstance();
		this.oracle_i = rnd.nextInt();
		this.oracle_b = rnd.nextBoolean();
		this.oracle_f = rnd.nextFloat();
		this.oracle_l = rnd.nextLong();
		this.oracle_d = rnd.nextDouble();
		this.oracle_c = rnd.nextChar();
		this.oracle_byteVector = new byte[1];
		rnd.nextBytes(this.oracle_byteVector);
		this.oracle_s = 6;
		this.oracle_mySimpleObject = new SimpleClass();

		System.out.println(this.oracle_i + " ; " + this.oracle_b + " ; " + this.oracle_f + " ; " + this.oracle_l + " ; "
				+ this.oracle_d + " ; " + this.oracle_c + " ; " + this.oracle_byteVector[0] + " ; " + this.oracle_s
				+ " ; " + this.oracle_mySimpleObject.hashCode());

		SimpleContextTestDriver testDriver = new SimpleContextTestDriver();

		testDriver.setParams(this.oracle_i, this.oracle_b, this.oracle_f, this.oracle_l, this.oracle_d, this.oracle_c,
				this.oracle_byteVector[0], this.oracle_s, this.oracle_mySimpleObject);
		testDriver.doSomething(this.oracle_i, this.oracle_b, this.oracle_f, this.oracle_l, this.oracle_d, this.oracle_c,
				this.oracle_byteVector[0], this.oracle_s, this.oracle_mySimpleObject);

		Assert.assertTrue(testDriver.areParamsValidated());
	}

}
