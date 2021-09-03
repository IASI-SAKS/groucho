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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.*;
import it.cnr.iasi.saks.foo.JSONWrapper;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.resources.*;

public class FastjsonDriver_IT {

	@Test
	public void driver() throws IOException, InterruptedException {
		System.out.println("Running driver for Fastjson.");
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);

		Group group = new Group();
		group.setId(0L);
		group.setName("admin");

		User guestUser = new User();
		guestUser.setId(2L);
		guestUser.setName("guest");

		User rootUser = new User();
		rootUser.setId(3L);
		rootUser.setName("root");

		group.addUser(guestUser);
		group.addUser(rootUser);

		String jsonString = (new JSONWrapper()).toJSONStringWrapper(group);
		boolean condition = FastjsonInvivoTestClass.getExitStatus();
		Assert.assertTrue(condition);
		System.out.println("DONE");
	}
}