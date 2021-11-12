package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1278;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

public class HashMultimapTest extends TestCase {

    HashMultimap map;

    public HashMultimapTest() {
       this.map = HashMultimap.create();
        map.put("name", "a");
        map.put("name", "b");
    }

    public void configure(HashMultimap map) {
        this.map = map;
    }

    @Test
    public void test_for_multimap() throws Exception {
        String json = JSON.toJSONString(this.map);
        System.out.println("Actual: ");
        System.out.println(json);
        String expected = buildExpected(this.map);
        System.out.println("Expected: ");
        System.out.println(expected);
        assertEquals(expected, json);
    }

    public String buildExpected(HashMultimap multimap){
        String expected = "{";
        Iterator<?> keys = multimap.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            Set value = multimap.get(key);
            Iterator<?> mapValues = value.iterator();
            expected = expected+ "\"" +key+ "\":[";

            while (mapValues.hasNext()) {
                String mapValue = mapValues.next().toString();
                if(mapValues.hasNext()){
                    expected = expected+ "\"" + mapValue + "\"" +",";
                }else {
                    expected = expected+ "\"" + mapValue + "\"";
                }
            }
            if(keys.hasNext()){
                expected = expected+ "],";
            }else {
                expected = expected+ "]";
            }

        }
        expected = expected + "}";
        return expected;
    }

}
