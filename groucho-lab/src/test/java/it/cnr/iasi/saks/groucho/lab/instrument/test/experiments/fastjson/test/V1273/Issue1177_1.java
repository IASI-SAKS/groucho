/*FastJSON V 1.2.73*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Issue1177_1 {

    JSONObject m;
    String text;
    String jsonpath;

    public Issue1177_1() {
        this.text = "{\"a\":{\"x\":\"y\"},\"b\":{\"x\":\"y\"}}";
        this.m =  JSONObject.parseObject(this.text);
        this.jsonpath = "$..x";
    }

    public void configure(JSONObject m) throws JsonProcessingException {
        this.m =  m;
        ObjectMapper om = new ObjectMapper();
        this.text = om.writeValueAsString(m);
        this.jsonpath = selectPath(m);
        System.out.println("... configuration done.");
    }

    @Test
    public void test_for_issue() throws Exception {
        JSONObject jsonObject = this.m;
        System.out.println(jsonObject);

        String jsonpath = this.jsonpath;
        String value="y2";
        JSONPath.set(jsonObject, jsonpath, value);
        Object expectedObject = JsonPath.parse(this.text).set(this.jsonpath, value).json();

        ObjectMapper om = new ObjectMapper();
        String expectedString = om.writeValueAsString(expectedObject);

        System.out.println("Expected: ");
        System.out.println(expectedString);

        System.out.println("Actual: ");
        System.out.println(jsonObject);

        Assert.assertEquals(expectedString, jsonObject.toString());
    }

    //Chooses first path (of depth 2 if possible)
    public String selectPath(JSONObject input) {

        if (input instanceof JSONObject) {

            List<String> parentKeys = new ArrayList(input.keySet());
            String parentKey = parentKeys.get(0);

            if((input).get(parentKey) instanceof JSONObject){
                JSONObject object = (JSONObject) ((input).get(parentKey));
                List<String> keys = new ArrayList(object.keySet());
                //If object is not simple
                if (keys.size() > 0) {
                    parentKey = keys.get(0);
                }
            }
            return parentKey;
        }
        return null;
    }
}
