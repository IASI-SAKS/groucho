/*FastJSON V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.*;
import com.jayway.jsonpath.*;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * This class is a re-implementation of the original Unit Test:
 * com.alibaba.json.bvt.issue_1100.Issue1177_2
 * distributed with Fastjson 1.2.54
 */
public class Issue1177_2 {

    Map<String, Model> m;
    String text;

    public Issue1177_2() {
        this.text = "{\"a\":{\"x\":\"y\"},\"b\":{\"x\":\"y\"}}";
        this.m =  JSONObject.parseObject(this.text, new TypeReference<Map<String, Model>>(){});
    }

    public void configure(HashMap<String, String> m){

        HashMap<String, String> simpleMap = m;
        Iterator<?> keys = simpleMap.keySet().iterator();

        this.text = "{";
        int i = 0;

        while (keys.hasNext()) {
            i ++;
            String id = "a"+i;
            String key = (String) keys.next();
            String replacement = simpleMap.get(key);
            if(keys.hasNext()){
                this.text = this.text + "\""+id+"\":" + "{\"x\":\"" +replacement +"\"},";
            }else {
                this.text = this.text + "\""+id+"\":" + "{\"x\":\"" +replacement +"\"}}";
            }
        }
        this.m =  JSONObject.parseObject(this.text, new TypeReference<Map<String, Model>>(){});

        System.out.println("... configuration done.");
    }

    public void test_for_issue() throws Exception {

        Map<String, Model> jsonObject = this.m;

        String jsonpath = "$..x";
        String value="y2";

        JSONPath.set(jsonObject, jsonpath, value);
        Object expectedObject = JsonPath.parse(this.text).set(jsonpath, value).json();

        Assert.assertEquals(JSON.toJSONString(expectedObject), JSON.toJSONString(jsonObject));
    }

    public static class Model {
        public String x;
    }

}
