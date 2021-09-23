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

    protected HashMap<String,String> map;

    public void configure(HashMap<String,String> m){
        this.map = m;
        System.out.println("... configuration done.");
    }

    @Test
    public void test_for_issue() throws Exception {

        Map<String,String> map = this.map;

        String json = JSON.toJSONString(map);

        String jsonExpected = new String();
        Iterator<?> mapkeys = map.keySet().iterator();

        while (mapkeys.hasNext()) {
            String key = (String) mapkeys.next();
            if (mapkeys.hasNext()){
                jsonExpected = jsonExpected  +JSON.toJSONString(key) + ":" +JSON.toJSONString(map.get(key))+ ",";
            }else{
                jsonExpected = jsonExpected +JSON.toJSONString(key) + ":" +JSON.toJSONString(map.get(key));
                jsonExpected = "{" + jsonExpected +"}";
            }
        }

        Assert.assertEquals(jsonExpected,json);

        Map<String,String> map1 = JSON.parseObject(json,new TypeReference<HashMap<String,String>>() {});
        Map<String,String>  map1Expected = JSON.parseObject(jsonExpected,new TypeReference<HashMap<String,String>>() {});

        Iterator<?> keys = map1.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            Assert.assertEquals(map1.get(key),map1Expected.get(key));
        }

        String stringActual = new String("{\"35504\":\"1\","+json.substring(1, json.length()-1)+ "\n}");
        JSONObject map2 = JSON.parseObject(stringActual);
        String stringExpected = new String("{\"35504\":\"1\","+jsonExpected.substring(1, json.length()-1)+ "\n}");
        JSONObject map2Expected = JSON.parseObject(stringExpected);

        Iterator<?> keys2 = map2.keySet().iterator();

        while (keys2.hasNext()) {
            String key = (String) keys2.next();
            Assert.assertEquals(map2.get(String.valueOf(key)),map2Expected.get(String.valueOf(key)));
        }
    }
}
