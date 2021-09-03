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

import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import serializers.*;
import serializers.json.*;
import serializers.jackson.*;

public class BenchmarkDriver_IT extends MediaItemBenchmark {

	@Test
	public void driver() throws IOException, InterruptedException {
		System.out.println("Running BenchmarkDriver_IT.");
		TestGroups groups = new TestGroups();
		addTests(groups);
		String inputPath = System.getProperty("user.dir")+"/src/test/resources/input/";
		runBenchmark(new String[]{new String(inputPath+"media.1.json")});
		System.out.println("DONE");
	}

	@Override
	protected void addTests(TestGroups groups)
	{
		FastJSONDatabind.register(groups);
		FastJSONArrayDatabind.register(groups);
		System.out.println("added" +groups);
	}
}
