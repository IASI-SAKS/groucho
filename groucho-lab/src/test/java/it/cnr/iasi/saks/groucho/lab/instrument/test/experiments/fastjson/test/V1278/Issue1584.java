package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1278;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;

public class Issue1584 {

    String json;
    HashMap<String,Object> jsonMap;

    public Issue1584(){
        this.json = "{\"k\":1,\"v\":\"A\"}";
        this.jsonMap = new HashMap<>();
        this.expectedMap(this.json);
    }

    public void configure(String s){
        this.json = s;
        this.jsonMap = new HashMap<>();
        this.expectedMap(this.json);
    }

    @Test
    public void test_for_issue() throws Exception {

        ParserConfig config = new ParserConfig();

        //Retrieve first entry
        String firstKey = this.jsonMap.keySet().iterator().next();
        {
            Map.Entry entry = JSON.parseObject(json, Map.Entry.class, config);
            Assert.assertEquals(firstKey, entry.getKey());
            Assert.assertEquals(this.jsonMap.get(firstKey), entry.getValue());
        }

        config.putDeserializer(Map.Entry.class, new ObjectDeserializer() {
            public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
                JSONObject object = parser.parseObject();
                List<String> keysAsArray = new ArrayList<>(object.keySet());

                if(keysAsArray.size() > 1){
                    Object k = object.get(keysAsArray.get(0));
                    Object v = object.get(keysAsArray.get(1));
                    return (T) Collections.singletonMap(k, v).entrySet().iterator().next();
                }
                return null;
            }

            public int getFastMatchToken() {
                return 0;
            }
        });

        Map.Entry entry = JSON.parseObject(json, Map.Entry.class, config);

        List<String> keysAsArray = new ArrayList<>(this.jsonMap.keySet());
        if(keysAsArray.size() > 1){
            Object expectedKey = this.jsonMap.get(keysAsArray.get(0));
            System.out.println("expectedKey");
            System.out.println(expectedKey);

            Object expectedVal = this.jsonMap.get(keysAsArray.get(1));

            Assert.assertEquals(expectedKey, entry.getKey());
            Assert.assertEquals(expectedVal, entry.getValue());
        }
    }

    public void expectedMap (String input){
       JSONObject object = JSON.parseObject(input);
        Iterator<?> keys = object.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next().toString();
            Object value = object.get(key);
            jsonMap.put(key, value);
        }
    }
}
