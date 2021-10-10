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

import it.cnr.iasi.saks.jvmserializers.MyFastJSONArrayDatabind;
import it.cnr.iasi.saks.jvmserializers.MyFastJSONDatabind;
import org.junit.Test;
import java.io.IOException;
import serializers.*;

public class BenchmarkDriver_IT extends MediaItemBenchmark {
	@Test
	public void runBench() throws IOException, InterruptedException {
		System.out.println("Running BenchmarkDriver_IT.");
		TestGroups groups = new TestGroups();
		addTests(groups);
		String inputPath = System.getProperty("user.dir") + "/src/test/resources/input/";
		String[] args = new String[1];
		args[0] = inputPath + "media.1.json";
		runBenchmark(args);
		System.out.println("DONE");
	}
	@Override
	protected void addTests (TestGroups groups)
	{
		MyFastJSONDatabind.register(groups);
	}

}
