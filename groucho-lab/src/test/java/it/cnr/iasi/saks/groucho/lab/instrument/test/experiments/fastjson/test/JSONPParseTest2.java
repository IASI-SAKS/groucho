/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import junit.framework.TestCase;
import java.nio.charset.StandardCharsets;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.jsonp.JSONParseTest2
 * distributed with Fastjson 1.2.54
 */

public class JSONPParseTest2 extends TestCase {
    public void test_f(byte[] array) throws Exception {
        String input = new String(array);
        String text = "parent.callback (" + input + " );   /**/ ";

        JSONPObject jsonpObject = JSON.parseObject(text, JSONPObject.class);
        assertEquals("parent.callback", jsonpObject.getFunction());

        assertEquals(1, jsonpObject.getParameters().size());
        JSONObject param = (JSONObject) jsonpObject.getParameters().get(0);

        //These asserts must be adapted
        //assertEquals(1, param.get("id"));
        //assertEquals("idonans", param.get("name"));

        String json = JSON.toJSONString(jsonpObject);
        String exp = new String(input.getBytes(StandardCharsets.UTF_8));
        String expected = "parent.callback(" + exp + ")";
        assertEquals(expected, json);
    }
}

