/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import junit.framework.TestCase;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1400.testIssue1480
 * distributed with Fastjson 1.2.54
 */

public class Issue1480 extends TestCase {

    protected HashMap<Integer,Integer> map;

    public void configure(HashMap<Integer,Integer> m){
        this.map = m;
        System.out.println("... configuration done.");
    }

    //Temporarily mimics the driver to be implemented
    public void mockConfigure(){
        this.map = new HashMap<Integer,Integer>();
        this.map.put(1,10);
        this.map.put(2,4);
        this.map.put(3,5);
        this.map.put(4,5);
        this.map.put(37306,98);
        this.map.put(36796,9);
        System.out.println("... configuration done.");
    }

    @Test
    public void test_for_issue() throws Exception {
        this.mockConfigure();  //Temporary configuration for map
        Map<Integer,Integer> map = this.map;

        String json = JSON.toJSONString(map);
        //Example of map.toString: {1=10, 2=4, 3=5, 4=5, 37306=98, 36796=9}
        String jsonExpected = map.toString();
        jsonExpected = jsonExpected.replaceAll("=", ":").replaceAll("\\s", "");

        //Flaky assert - the HashMap does not guarantee the iterating order when generating a String representation
        // Assert.assertEquals("{1:10,2:4,3:5,4:5,37306:98,36796:9}",json);
        Assert.assertEquals(jsonExpected,json);

        Map<Integer,Integer> map1 = JSON.parseObject(json,new TypeReference<HashMap<Integer,Integer>>() {});
        Map<Integer,Integer> map1Expected = JSON.parseObject(jsonExpected,new TypeReference<HashMap<Integer,Integer>>() {});

        Iterator<?> keys = map1.keySet().iterator();

        while (keys.hasNext()) {
            Integer key = (Integer) keys.next();
            Assert.assertEquals(map1.get(Integer.valueOf(key)),map1Expected.get(Integer.valueOf(key)));
        }

        JSONObject map2 = JSON.parseObject("{35504:1,1:10,2:4,3:5,4:5,37306:98,36796:9\n" + "}");
        String stringExpected = new String("{35504:1,"+json.substring(1, json.length()-1)+ "\n}");
        JSONObject map2Expected = JSON.parseObject(stringExpected);

        Iterator<?> keys2 = map2.keySet().iterator();

        while (keys2.hasNext()) {
            Integer key = (Integer) keys2.next();
            Assert.assertEquals(map2.get(Integer.valueOf(key)),map2Expected.get(Integer.valueOf(key)));
        }
    }
}
