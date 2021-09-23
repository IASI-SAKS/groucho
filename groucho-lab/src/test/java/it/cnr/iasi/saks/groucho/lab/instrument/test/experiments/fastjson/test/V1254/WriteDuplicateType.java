/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.writeClassName.WriteDuplicateType
 * distributed with Fastjson 1.2.54
 */

public class WriteDuplicateType extends TestCase {

    DianDianCart cart;

    public void configure( DianDianCart c){
        this.cart = c;
        System.out.println("... configuration done.");
    }

    //Temporarily mimics the driver to be implemented
    public void configureTestMap(){
       this.cart = new DianDianCart();
        cart.setId(1001);
        System.out.println("... configuration done.");
    }

    @Test
    public void test_dupType() throws Exception {
        DianDianCart cart = new DianDianCart();
        cart.setId(1001);
        
        LinkedHashMap<String, JSONObject> cartMap = new LinkedHashMap<String, JSONObject>();
        
        JSONObject obj = new JSONObject();
        obj.put("id", 1001);
        obj.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart");

        cartMap.put("1001", obj);
        
        String text1 = JSON.toJSONString(cartMap, SerializerFeature.WriteClassName);
        Assert.assertEquals("{\"@type\":\"java.util.LinkedHashMap\",\"1001\":{\"@type\":\"com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart\",\"id\":1001}}", text1);
        
    }
    @Test
    public void test_dupType2() throws Exception {
        configureTestMap();
        LinkedHashMap<String, HashMap<String, Object>> cartMap = new LinkedHashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> obj = new HashMap<String, Object>();
        obj.put("id", this.cart.getId());
        obj.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart");

        cartMap.put(Integer.toString(this.cart.getId()), obj);
        String text1 = JSON.toJSONString(cartMap, SerializerFeature.WriteClassName);

        Assert.assertEquals("{\"@type\":\"java.util.LinkedHashMap\",\"+" +Integer.toString(this.cart.getId())+ "\":{\"@type\":\"com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart\",\"id\":"+Integer.toString(this.cart.getId())+"}}", text1);
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
