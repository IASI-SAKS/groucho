/*FastJSON V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import clojure.lang.Obj;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;
import com.jayway.jsonpath.*;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/*
 * This class is a re-implementation of the original Unit Test:
 * com.alibaba.json.bvt.issue_1100.Issue1177_2
 * distributed with Fastjson 1.2.54
 */
public class Issue1177_2 {

    String text;
    String key;

    public Issue1177_2() {
        this.text = "{\"a\":{\"x\":\"y\"},\"b\":{\"x\":\"y\"}}";
        this.key = "x";
    }

    public void configure(String text, String key){
        this.text = text;
        this.key = key;
        System.out.println("... configuration done.");
    }

    public void test_for_issue() throws Exception {
        String text = this.text;

        Map<String, Model> jsonObject = JSONObject.parseObject(text, new TypeReference<Map<String, Model>>(){});

        String jsonpath = "$.."+key;
        String value="y2";
        JSONPath.set(jsonObject, jsonpath, value);

        Object expectedObject = JsonPath.parse(text).set(jsonpath, value).json();

        Assert.assertEquals(JSON.toJSONString(expectedObject), JSON.toJSONString(jsonObject));
    }

    public static class Model {
        public String x;
    }
}
