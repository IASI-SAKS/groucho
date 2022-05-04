package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1278;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;

public class Issue3082 {
    HashSet<Map.Entry<String, Map.Entry<String, String>>> nestedSet;


    public Issue3082(){
        this.nestedSet = new HashSet<>();
        nestedSet.add(new AbstractMap.SimpleEntry<>("a", new AbstractMap.SimpleEntry<String, String>("b", "c")));
        nestedSet.add(new AbstractMap.SimpleEntry<>("d", new AbstractMap.SimpleEntry<String, String>("e", "f")));
    }

    public void configure(HashSet<Map.Entry<String, Map.Entry<String, String>>> hs){
        this.nestedSet = new HashSet<>();
        this.nestedSet = hs;
        System.out.println(hs);
    }

    @Test
    public void test_for_issue_entry() throws Exception {
        String str = "{\"k\":{\"k\":\"v\"}}";
        Map.Entry<String, Map.Entry<String, String>> entry = JSON.parseObject(str, new TypeReference<Map.Entry<String, Map.Entry<String, String>>>() {});
        Assert.assertEquals("v", entry.getValue().getValue());
    }

    @Test
    public void test_for_issue() throws Exception {
       String content = JSON.toJSONString(nestedSet);
        System.out.println(content);

        HashSet<Map.Entry<String, Map.Entry<String, String>>> deserializedNestedSet;
        Type type = new TypeReference<HashSet<Map.Entry<String, Map.Entry<String, String>>>>() {}.getType();
        deserializedNestedSet = JSON.parseObject(content, type);
        ArrayList<String> expectedKeys = this.expectedKeys();

        Iterator<Map.Entry<String, Map.Entry<String, String>>> nestedsetIterator = deserializedNestedSet.iterator();

        for (int i = 0; i < expectedKeys.size(); i++){

            String expectedKey = expectedKeys.get(i);
            String actualKey = nestedsetIterator.next().getValue().getKey();
            Assert.assertEquals(expectedKey, actualKey);
        }
    }

    public ArrayList<String> expectedKeys (){
        ArrayList<String> keysAsArray = new ArrayList<>();

        Iterator<Map.Entry<String, Map.Entry<String, String>>> entries = this.nestedSet.iterator();

        while (entries.hasNext()){
            String entryKey = entries.next().getValue().getKey();
            keysAsArray.add(entryKey);
        }
        return keysAsArray;
    }
}
