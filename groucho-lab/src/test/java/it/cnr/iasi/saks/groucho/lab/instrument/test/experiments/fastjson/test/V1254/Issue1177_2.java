/*FastJSON V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import junit.framework.TestCase;

import java.util.Map;

/**
 * Created by wenshao on 05/05/2017.
 */
public class Issue1177_2 extends TestCase {

    public void test_for_issue() throws Exception {
        String text = "{\"a\":{\"x\":\"y\"},\"b\":{\"x\":\"y\"}}";
        Map<String, Model> jsonObject = JSONObject.parseObject(text, new TypeReference<Map<String, Model>>(){});
        System.out.println(JSON.toJSONString(jsonObject));
        System.out.println(text);
        String jsonpath = "$..x";
        String value="y2";
        JSONPath.set(text, jsonpath, value);
        assertEquals("{\"a\":{\"x\":\"y2\"},\"b\":{\"x\":\"y2\"}}}", JSON.toJSONString(text));
    }

    public static class Model {
        public String x;
    }
}
