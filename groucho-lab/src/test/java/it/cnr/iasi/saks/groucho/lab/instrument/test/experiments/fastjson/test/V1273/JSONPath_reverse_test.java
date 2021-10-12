package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class JSONPath_reverse_test
{
    String JsonString;
    String JsonString3;

    public JSONPath_reverse_test(){
        this.JsonString = "{\"id\":1001,\"name\":\"ljw\",\"age\":50}";
        this.JsonString3 = "{\"player\":{\"id\":1001,\"name\":\"ljw\",\"age\":50}}";
    }

    public void configureSimple(String s){
        this.JsonString = s;
    }

    public void configureNested(String s){
        this.JsonString3 = s;
    }

    @Test
    public void test_reserve() throws Exception {
        JSONObject object = JSON.parseObject(this.JsonString);
        List<String> keysAsList = new ArrayList(object.keySet());

        Random r = new Random();
        int rElement = r.nextInt(keysAsList.size());
        String key1 = keysAsList.get(rElement);
        keysAsList.remove(rElement);
        int rElement2 = r.nextInt(keysAsList.size());
        String key2 = keysAsList.get(rElement2);
        keysAsList.remove(rElement2);

        //1st check
        String expected = "[\"" +object.get(key1) + "\",\"" +object.get(key2) +"\"]";
        String actual = JSONPath.reserveToArray(object, key1, key2).toString();
        System.out.println("epxected1: " +expected);
        System.out.println("actual1: " +actual );
        Assert.assertEquals(expected, actual);

        //2nd check
        String expectedReverse = "[\"" +object.get(key2) + "\",\"" +object.get(key1) +"\"]";
        String actualReverse = JSONPath.reserveToArray(object, key2, key1).toString();
        System.out.println("epxected2: " +expectedReverse);
        System.out.println("actual2: " +actualReverse);
        Assert.assertEquals(expectedReverse, actualReverse);

        //3rd check
        Iterator<String> keys = object.keySet().iterator();
        String expectedNested = "[\"" +object.get(key1) + "\",[";

        while (keys.hasNext()) {
           String nextKey = keys.next();
           if(keys.hasNext())
            expectedNested = expectedNested + "\"" + object.get(nextKey) + "\",";
           else
               expectedNested = expectedNested + "\"" + object.get(nextKey) + "\"";
        }
        expectedNested = expectedNested + "]]";
        String actualNested = JSONPath.reserveToArray(object, key1, "*").toString();
        System.out.println("expected3: " +expectedNested);
        System.out.println("actual3: " +actualNested);
         Assert.assertEquals(expectedNested, actualNested);
    }

    @Test
    public void test_reserve2() throws Exception {
        JSONObject object = JSON.parseObject("{\"id\":1001,\"name\":\"ljw\",\"age\":50}");

        Assert.assertEquals("{\"id\":1001,\"name\":\"ljw\"}", JSONPath.reserveToObject(object, "id", "name").toString());
        Assert.assertEquals("{\"name\":\"ljw\",\"id\":1001}", JSONPath.reserveToObject(object, "name", "id").toString());
    }

    @Test
    public void test_reserve3() {
        JSONObject parentObject = JSON.parseObject(this.JsonString3);
        Iterator<String> keys = parentObject.keySet().iterator();
        String parentKey = keys.next();

        JSONObject nestedObjects = (JSONObject) parentObject.get(parentKey);
        List<String> keysAsList = new ArrayList(nestedObjects.keySet());

        Random r = new Random();
        int rElement = r.nextInt(keysAsList.size());
        String key1 = keysAsList.get(rElement);
        keysAsList.remove(rElement);
        int rElement2 = r.nextInt(keysAsList.size());
        String key2 = keysAsList.get(rElement2);

        String expected = "{\""+ parentKey + "\":{"
             + "\"" + key1 + "\":" + "\"" + nestedObjects.get(key1) + "\","
             + "\"" + key2 + "\":" + "\"" + nestedObjects.get(key2) + "\"}}";

        String actual = JSONPath.reserveToObject(parentObject, parentKey+'.'+key2, parentKey+'.'+key1).toString();
        String actual2 = JSONPath.reserveToObject(parentObject, parentKey+'.'+key1, parentKey+'.'+key2, "ab.c").toString();
        System.out.println("epxected nested: " +expected);
        System.out.println("actual nested: " +actual );

        Assert.assertEquals(expected, actual);

        System.out.println("actual nested2: " +actual2 );
        Assert.assertEquals(expected, actual2);
    }


}
