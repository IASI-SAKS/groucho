/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import clojure.lang.Obj;
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

public class Issue1480 {

    protected JSONObject map;

    public Issue1480() {
        this.map = new JSONObject();
        this.map.put("1", 10);
        this.map.put("2", 4);
        this.map.put("3", 5);
        this.map.put("4", 5);
        this.map.put("37306", 98);
        this.map.put("36796", 9);
    }

    public void configure(JSONObject o){
        this.map = o;
        System.out.println("... configuration done.");
    }

    @Test
    public void test_for_issue() throws Exception {

        Map<String,Object> map = this.map;
        String json = JSON.toJSONString(map);
        String jsonExpected = buildExpected(map);

        Assert.assertEquals(jsonExpected,json);
        Map<String,Object> map1 = JSON.parseObject(json, new TypeReference<HashMap<String,Object>>() {});
        Map<String,Object>  map1Expected = JSON.parseObject(jsonExpected, new TypeReference<HashMap<String,Object>>() {});

        Iterator<?> keys = map1.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            Assert.assertEquals(map1.get(key),map1Expected.get(key));
        }

        //Add element
        String stringActual = "{\"35504\":1,"+json.substring(1, json.length()-1)+ "\n}";
        JSONObject map2 = JSON.parseObject(stringActual);
        String stringExpected = "{\"35504\":1,"+jsonExpected.substring(1, json.length()-1)+ "\n}";
        JSONObject map2Expected = JSON.parseObject(stringExpected);


        Iterator<?> keys2 = map2.keySet().iterator();


        while (keys2.hasNext()) {
            String key = (String) keys2.next();
            Assert.assertEquals(map2.get(String.valueOf(key)),map2Expected.get(String.valueOf(key)));
        }

    }

    public String buildExpected(Map<String,Object> map){
        Iterator<?> mapkeys = map.keySet().iterator();
        String jsonExpected = "";
        while (mapkeys.hasNext()) {
            String key = (String) mapkeys.next();
            if (mapkeys.hasNext()){
                jsonExpected = jsonExpected  +JSON.toJSONString(key) + ":" +JSON.toJSONString(map.get(key))+ ",";
            }else{
                jsonExpected = jsonExpected +JSON.toJSONString(key) + ":" +JSON.toJSONString(map.get(key));
                jsonExpected = "{" + jsonExpected +"}";
            }
        }
        return jsonExpected;
    }

}
