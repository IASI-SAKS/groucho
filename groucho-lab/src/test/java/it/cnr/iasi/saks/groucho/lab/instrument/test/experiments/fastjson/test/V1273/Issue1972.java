package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Issue1972 {

    JSONObject jsonObject = new JSONObject();
    JSONObject a = new JSONObject();
    JSONObject b = new JSONObject();
    String path = null;

    public Issue1972(){
        this.a.put("b", this.b);
        this.b.put("c", "2018-04");
        this.b.put("d", new JSONArray());
        this.jsonObject.put("a", a);
        this.path = "$.a.b[c = '2018-04'].d";
    }

    public void configure(JSONObject b){
        this.jsonObject = b;
        List<String> keysAsList = new ArrayList(this.jsonObject.keySet());
        Random random = new Random();
        int r = random.nextInt(keysAsList.size());
        String key = keysAsList.get(r);
        this.path = "$." + key;
        this.jsonObject.put(key, new JSONArray());
    }

    @Test
    public void test_for_issue() throws Exception {
        Integer obj = Integer.valueOf(123);
        System.out.println("Path");
        System.out.println(path);
        JSONPath.arrayAdd(this.jsonObject, this.path, obj);
        String expected = buildExpected(this.jsonObject);
        System.out.println("Expected");
        System.out.println(buildExpected(this.jsonObject));
        Assert.assertEquals(expected, this.jsonObject.toString());
    }

    public String buildExpected(JSONObject object){
        String expected = "{";

        Iterator<?> keys = object.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            if(object.get(key) instanceof String){
                if(keys.hasNext()){
                    expected = expected+ "\"" +key + "\":" + "\""+object.get(key).toString()+"\",";

                }else {
                    expected = expected+ "\"" +key + "\":" + "\""+object.get(key).toString()+"\"";
                }
            }else{
                if(keys.hasNext()){
                    expected = expected + "\"" +key + "\":" + object.get(key).toString() +",";

                }else {
                    expected = expected + "\"" +key + "\":" + object.get(key).toString();
                }
            }
        }
        expected = expected + "}";
        return expected;
    }

}