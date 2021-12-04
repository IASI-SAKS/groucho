/*Fastjson V 1.2.73*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class WriteDuplicateType {

    LinkedHashMap<String, JSONObject> cartMap;

    public WriteDuplicateType(){
        this.cartMap = new LinkedHashMap();
    }

    public void configure(LinkedHashMap<String, JSONObject> c){
        this.cartMap = c;
        System.out.println("... configuration done.");
    }

    @Test
    public void test_dupType() throws Exception {

        com.alibaba.json.bvt.writeClassName.V1254.WriteDuplicateType.DianDianCart cart = new com.alibaba.json.bvt.writeClassName.V1254.WriteDuplicateType.DianDianCart();
        cart.setId(1001);

        LinkedHashMap<String, JSONObject> cartMap = this.cartMap;

        JSONObject obj = new JSONObject();
        obj.put("id", 1001);
        obj.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart");
        cartMap.put("1001", obj);

        String text1 = JSON.toJSONString(cartMap, SerializerFeature.WriteClassName);
        String expected = "{\"@type\":\"java.util.LinkedHashMap\",";

        Iterator<?> keys = this.cartMap.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            if(cartMap.get(key) instanceof JSONObject){
                JSONObject jo =cartMap.get(key);
                jo.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.fastjson.JSONObject");
              }
        }

        ObjectMapper om = new ObjectMapper();
        String cartMapAsString = om.writeValueAsString(cartMap);
        //Remove initial parenthesis
        expected = expected + cartMapAsString.substring(1);

        System.out.println("Expected: ");
        System.out.println(expected);
        System.out.println("Actual: ");
        System.out.println(text1);

        Assert.assertEquals(expected, text1);

    }

    @Test
    public void test_dupType2() throws Exception {
        DianDianCart cart = new DianDianCart();
        cart.setId(1001);
        
        LinkedHashMap<String, HashMap<String, Object>> cartMap = new LinkedHashMap<String, HashMap<String, Object>>();
        
        HashMap<String, Object> obj = new LinkedHashMap<String, Object>();
        obj.put("id", 1001);
        obj.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart");
        cartMap.put("1001", obj);
        
        String text1 = JSON.toJSONString(cartMap, SerializerFeature.WriteClassName);
        Assert.assertEquals("{\"@type\":\"java.util.LinkedHashMap\",\"1001\":{\"id\":1001,\"@type\":\"com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart\"}}", text1);
        
    }

    @Test
    public void test_dupType3() throws Exception {
        DianDianCart cart = new DianDianCart();
        cart.setId(1001);
        
        LinkedHashMap<String, LinkedHashMap<String, Object>> cartMap = new LinkedHashMap<String, LinkedHashMap<String, Object>>();
        
        LinkedHashMap<String, Object> obj = new LinkedHashMap<String, Object>();
        obj.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart");
        obj.put("id", 1001);
        cartMap.put("1001", obj);
        
        String text1 = JSON.toJSONString(cartMap, SerializerFeature.WriteClassName);
        Assert.assertEquals("{\"@type\":\"java.util.LinkedHashMap\",\"1001\":{\"@type\":\"com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart\",\"id\":1001}}", text1);
        
    }

    public static class DianDianCart {

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
}
