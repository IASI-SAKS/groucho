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

import com.alibaba.json.bvt.asm.SortFieldTest;
import com.alibaba.json.bvt.bug.*;
import com.alibaba.json.bvt.date.DateTest;
import com.alibaba.json.bvt.guava.ArrayListMultimapTest;
import com.alibaba.json.bvt.guava.HashMultimapTest;
import com.alibaba.json.bvt.issue_1100.Issue1177_1;
import com.alibaba.json.bvt.issue_1100.Issue1177_2;
import com.alibaba.json.bvt.issue_1200.Issue1298;
import com.alibaba.json.bvt.date.DateTest_tz;
import com.alibaba.json.bvt.issue_1300.Issue1363;

import com.alibaba.json.bvt.issue_1700.Issue1769;
import com.alibaba.json.bvt.issue_2400.Issue2428;
import com.alibaba.json.bvt.issue_2400.Issue2447;
import com.alibaba.json.bvt.jsonp.JSONPParseTest3;
import com.alibaba.json.bvt.parser.DefaultExtJSONParser_parseArray;
import com.alibaba.json.bvt.parser.TypeUtilsTest;
import com.alibaba.json.bvt.parser.deser.SqlDateDeserializerTest2;
import com.alibaba.json.bvt.parser.deser.date.DateParseTest9;
import com.alibaba.json.bvt.path.JSONPath_reverse_test;
import com.alibaba.json.bvt.serializer.JSONFieldTest5;
import com.alibaba.json.bvt.serializer.JSONSerializerTest2;
import com.alibaba.json.bvt.serializer.MaxBufSizeTest;
import com.alibaba.json.bvt.serializer.date.*;
import com.alibaba.json.bvt.writeClassName.V1254.WriteDuplicateType;
import com.alibaba.json.bvt.writeClassName.V1273.WriteClassNameTest_Map;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1278.Issue1584;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1278.Issue3082;
import org.junit.Test;
import com.alibaba.json.bvt.issue_1400.*;
import com.alibaba.json.bvt.issue_1600.*;
import com.alibaba.json.bvt.issue_1900.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.text.ParseException;

public class LabDriver_IT {
	@Test
	public void runBench() throws ParseException {
		System.out.println("Running LabDriver_IT.");
		for(int i = 0; i < 10000; i++){

			/*V1.2.51*/
			//JSONSerializerTest2 unitTest = new JSONSerializerTest2();
			//Issue1769 unitTest = new Issue1769();
			//JSONFieldTest5 unitTest = new JSONFieldTest5();
			//Issue_717 unitTest = new Issue_717();

			/*V1.2.54*/
			//Issue1480 unitTest = new Issue1480();
			//Issue1679 unitTest = new Issue1679();
			//Issue1977 unitTest = new Issue1977();
			//Issue1298 unitTest = new Issue1298();
			//DateTest_tz unitTest = new DateTest_tz();
			//DateTest4_indian unitTest = new DateTest4_indian();
			//DateTest5_iso8601 unitTest = new DateTest5_iso8601();
			//DateTest unitTest = new DateTest();
			//JSONPParseTest2 unitTest = new JSONPParseTest2();
			//JSONPParseTest3 unitTest = new JSONPParseTest3();
			//WriteDuplicateType unitTest = new WriteDuplicateType();
			//Issue1177_2 unitTest = new Issue1177_2();
			//DefaultExtJSONParser_parseArray unitTest = new DefaultExtJSONParser_parseArray();
			//Bug_for_smoothrat6 unitTest= new Bug_for_smoothrat6();

			/*V1.2.57*/
			//DateParseTest9 unitTest = new DateParseTest9();

			/*V1.2.73*/
			//Issue1177_1 unitTest = new Issue1177_1();
			//Issue1363 unitTest = new Issue1363();
			//Issue1492 unitTest = new Issue1492();
			//Issue1780_JSONObject unitTest = new Issue1780_JSONObject();
			//Issue1780_Module unitTest = new Issue1780_Module();
			//Issue1972 unitTest = new Issue1972();
			//Issue2447 unitTest = new Issue2447();
			//JSONObjectTest_readObject unitTest = new JSONObjectTest_readObject();
			//JSONPath_reverse_test unitTest = new JSONPath_reverse_test();
			//SqlDateDeserializerTest2 unitTest = new SqlDateDeserializerTest2();
			//WriteDuplicateType unitTest = new WriteDuplicateType();
			//Bug_for_yangzhou unitTest = new Bug_for_yangzhou();
			//FeatureCollectionTest unitTest = new FeatureCollectionTest();
			//WriteClassNameTest_Map unitTest= new WriteClassNameTest_Map();

			/*V1.2.75*/
			//Issue2428 unitTest = new Issue2428();
			//TypeUtilsTest unitTest = new TypeUtilsTest();
			//Bug_for_xiayucai2012 unitTest = new Bug_for_xiayucai2012();
			//Issue1493 unitTest = new Issue1493();
			Bug_for_issue_447 unitTest = new Bug_for_issue_447();

			/*V1.2.78*/
			//HashMultimapTest unitTest = new HashMultimapTest();
			//Issue1584 unitTest = new Issue1584();
			//Issue3082 unitTest = new Issue3082();
			//MaxBufSizeTest unitTest = new MaxBufSizeTest();
			//SortFieldTest unitTest = new SortFieldTest();

			Result res = JUnitCore.runClasses(unitTest.getClass());
			for(Failure f: res.getFailures()){
				System.out.println("FAILURES");
				System.out.println(f.toString());
			}
			System.out.println("Was Succesful:" +res.wasSuccessful());
		}
	}

}
