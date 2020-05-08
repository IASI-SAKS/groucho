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
package it.cnr.iasi.saks.groucho.lab.instrument.test.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Assert;
import org.junit.Test;

import it.cnr.iasi.saks.groucho.lab.instrument.config.InstrumentModelJSONMapper;
import it.cnr.iasi.saks.groucho.lab.instrument.model.AnnotatedMethodModel;
import it.cnr.iasi.saks.groucho.lab.instrument.model.InjectableMethodList;
import it.cnr.iasi.saks.groucho.lab.instrument.model.TestableInvivoModel;

public class ModelJSONTest {

	private static String JSON_ORACLE = "{\"lst\":[{\"className\":\"className\",\"methodName\":\"methodName1\",\"annotation\":{\"invivoTestClass\":\"invivoTestClass1\",\"invivoTest\":\"invivoTest1\",\"carvingDepth\":1,\"pauseOtherThreads\":false}},{\"className\":\"className\",\"methodName\":\"methodName2\",\"annotation\":{\"invivoTestClass\":\"invivoTestClass2\",\"invivoTest\":\"invivoTest2\",\"carvingDepth\":1,\"pauseOtherThreads\":false}}]}";

	@Test
	public void saveModelToJSONTest(){
		TestableInvivoModel annotation1 = new TestableInvivoModel("invivoTestClass1", "invivoTest1");
		TestableInvivoModel annotation2 = new TestableInvivoModel("invivoTestClass2", "invivoTest2");
		
		AnnotatedMethodModel method1 = new AnnotatedMethodModel("className", "methodName1");
		method1.setAnnotation(annotation1);

		AnnotatedMethodModel method2 = new AnnotatedMethodModel("className", "methodName2");
		method2.setAnnotation(annotation2);
		
		InjectableMethodList lst = new InjectableMethodList();
		lst.addAnnotatedMethod(method1);
		lst.addAnnotatedMethod(method2);
		
		String lstJSONString = null;
		try {
			File tmpFile = File.createTempFile("foo", ".json");
			FileOutputStream stream = new FileOutputStream(tmpFile);			
			InstrumentModelJSONMapper.saveToJSON(lst, stream);
			
			FileInputStream inStream = new FileInputStream(tmpFile);
			InjectableMethodList lst2 = InstrumentModelJSONMapper.loadFromJSON(inStream);
			
			lstJSONString = InstrumentModelJSONMapper.saveToJSON(lst2);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} 
		
		Assert.assertEquals(JSON_ORACLE, lstJSONString);
	}
}
