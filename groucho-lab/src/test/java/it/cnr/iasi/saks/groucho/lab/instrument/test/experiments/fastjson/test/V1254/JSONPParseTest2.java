/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import junit.framework.TestCase;
import org.json.JSONException;

import java.nio.charset.StandardCharsets;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.jsonp.JSONParseTest2
 * distributed with Fastjson 1.2.54
 */

public class JSONPParseTest2 {

    protected byte[] array;
    protected LinkedHashMap<String,String> actual = new LinkedHashMap();
    protected LinkedHashMap<String,String> expected = new LinkedHashMap();

    public JSONPParseTest2() {
        this.array = new String("parent.callback ({'id':1, 'name':'idonans'} );   /**/ ").getBytes(StandardCharsets.UTF_8);
    }

    public void configureArray(byte[] array){
        this.array = array;
        System.out.println("... configuration done.");
    }

    @Test
    public void test_f() throws Exception {
        String input = new String(this.array);
        String text = "parent.callback (" + input + " );   /**/ ";

        JSONPObject jsonpObject = JSON.parseObject(text, JSONPObject.class);
        Assert.assertEquals("parent.callback", jsonpObject.getFunction());

        Assert.assertEquals(1, jsonpObject.getParameters().size());

        JSONObject param = (JSONObject) jsonpObject.getParameters().get(0);
        org.json.JSONObject expectedObject = new org.json.JSONObject(input);

        loopThroughFastJsonObject(param, null);
        loopThroughJsonObject(expectedObject, null);
        Assert.assertEquals(expected, actual);

        String json = JSON.toJSONString(jsonpObject);
        String expected = "parent.callback(" + input + ")";
        Assert.assertEquals(expected, json);
    }


    public void loopThroughFastJsonObject(Object input, String k) throws JSONException {

        if (input instanceof JSONObject) {

            JSONObject in = ( JSONObject) input;
            Iterator<?> keys = in.keySet().iterator();

            while (keys.hasNext()) {

                String key = (String) keys.next();

                if (!(in.get(key) instanceof JSONArray)){
                    if (in.get(key) instanceof JSONObject) {
                        loopThroughFastJsonObject(in.get(key), key);
                    } else{
                        String val = in.get(key).toString();
                        if(k != null)
                            key = k +"."+ key;
                        actual.put(key,val);
                    }
                }
                else {
                    JSONArray ja = (JSONArray) in.get(key);
                    loopThroughFastJsonObject(ja, key);
                }
            }
        }
        else if (input instanceof JSONArray) {
            JSONArray array = (JSONArray) input;

            for (int i = 0; i < array.size(); i++) {
                if(array.get(i) instanceof JSONObject){
                    JSONObject obj = (JSONObject) array.get(i);
                    loopThroughFastJsonObject(obj, k +"[" + i + "]");
                }else{
                    String val = array.toString();
                    actual.put(k, val);
                    break;
                }
            }
        }
    }

    public void loopThroughJsonObject(Object input, String k) throws JSONException {

        if (input instanceof org.json.JSONObject) {

            Iterator<?> keys = ((org.json.JSONObject) input).keys();

            while (keys.hasNext()) {

                String key = (String) keys.next();

                if (!(((org.json.JSONObject) input).get(key) instanceof org.json.JSONArray)){
                    if (((org.json.JSONObject) input).get(key) instanceof org.json.JSONObject) {
                        loopThroughJsonObject(((org.json.JSONObject) input).get(key), key);
                    } else {
                        String val = ((org.json.JSONObject) input).get(key).toString();
                        if(k != null)
                            key = k +"."+ key;
                        expected.put(key, val);
                    }
                }
                else {
                    loopThroughJsonObject(new org.json.JSONArray(((org.json.JSONObject) input).get(key).toString()), key);
                }
            }
        }
        if (input instanceof org.json.JSONArray) {

            for (int i = 0; i < ((org.json.JSONArray) input).length(); i++) {

                if(((org.json.JSONArray) input).get(i) instanceof org.json.JSONObject){
                    org.json.JSONObject a = ((org.json.JSONArray) input).getJSONObject(i);
                    loopThroughJsonObject(a, k +"[" + i + "]");
                }else{
                    String val = input.toString();
                    //k = k +".";
                    expected.put(k,val);
                    break;
                }
            }

        }

    }
}

