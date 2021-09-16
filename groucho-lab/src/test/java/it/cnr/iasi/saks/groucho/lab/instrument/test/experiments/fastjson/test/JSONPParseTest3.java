/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.json.JSONException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.jsonp.JSONParseTest3
 * distributed with Fastjson 1.2.54
 */

public class JSONPParseTest3 extends TestCase {

    LinkedHashMap<String,String> actual = new LinkedHashMap();
    LinkedHashMap<String,String> expected = new LinkedHashMap();

    public void test_f(byte[] array) throws Exception {
        String input = new String(array);
        String text = "parent.callback (" + input + ",1,2 );   /**/ ";

        JSONPObject jsonpObject = JSON.parseObject(text, JSONPObject.class);
        assertEquals("parent.callback", jsonpObject.getFunction());
        assertEquals(3, jsonpObject.getParameters().size());

        JSONObject param = (JSONObject) jsonpObject.getParameters().get(0);
        org.json.JSONObject expectedObject = new org.json.JSONObject(input);

        loopThroughFastJsonObject(param, null);
        loopThroughJsonObject(expectedObject, null);
        assertEquals(expected, actual);

        String json = JSON.toJSONString(jsonpObject, SerializerFeature.BrowserSecure);
        String expected = "/**/parent.callback(" + mockBrowserSecure(input) + "),1,2)";
        //String exp = new String(input.getBytes(StandardCharsets.UTF_8));
        //String expected = "/**/parent.callback(" + mockBrowserSecure(exp) + "),1,2)";
        assertEquals(expected, json);
    }

    //Mocks BrowserSecure Feature
    public String mockBrowserSecure(String input){
        String secure = input.replaceAll("\\(", "\\\\u0028");
        secure = secure.replaceAll("\\)", "\\\\u0029");
        secure = secure.replaceAll("<", "\\\\u003C");
        secure = secure.replaceAll(">", "\\\\u003E");
        return secure;
    }


    /*Iterator adapted for FastJson objects*/
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
                    //k = k +".";
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
