/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;

import java.nio.charset.StandardCharsets;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.jsonp.JSONParseTest3
 * distributed with Fastjson 1.2.54
 */

public class JSONPParseTest3 extends TestCase {
    public void test_f(byte[] array) throws Exception {
        String input = new String(array);

        String text = "parent.callback (" + input + ",1,2 );   /**/ ";

        JSONPObject jsonpObject = (JSONPObject) JSON.parseObject(text, JSONPObject.class);
        assertEquals("parent.callback", jsonpObject.getFunction());

        assertEquals(3, jsonpObject.getParameters().size());
        JSONObject param = (JSONObject) jsonpObject.getParameters().get(0);

       // assertEquals(1, param.get("id"));
       // assertEquals("ido)nans", param.get("name"));

        String json = JSON.toJSONString(jsonpObject, SerializerFeature.BrowserSecure);
        String exp = new String(input.getBytes(StandardCharsets.UTF_8));
        String expected = "/**/parent.callback(" + mockBrowserSecure(exp) + "),1,2)";
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
}
