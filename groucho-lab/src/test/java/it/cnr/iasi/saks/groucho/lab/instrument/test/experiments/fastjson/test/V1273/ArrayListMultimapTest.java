package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.TreeMultimap;
import com.google.common.primitives.Ints;
import org.junit.Assert;

import java.util.Iterator;

public class ArrayListMultimapTest {

    ArrayListMultimap<String, Integer> multimap;

    public ArrayListMultimapTest(){
        this.multimap = ArrayListMultimap.create();
        this.multimap.putAll("b", Ints.asList(2, 4, 6));
        this.multimap.putAll("a", Ints.asList(4, 2, 1));
        this.multimap.putAll("c", Ints.asList(2, 5, 3));
    }

    public void configure(ArrayListMultimap<String, Integer> multimap){
        this.multimap = multimap;
    }

    public void test_for_multimap() throws Exception {

        String expected = buildExpected(this.multimap);
        String json = JSON.toJSONString(multimap);
        Assert.assertEquals(expected, json);

        TreeMultimap treeMultimap = TreeMultimap.create(multimap);
        String expected2 = buildExpected(treeMultimap);
        String json2 = JSON.toJSONString(treeMultimap);
        Assert.assertEquals(expected2, json2);
    }

    public String buildExpected(ArrayListMultimap<String, Integer> multimap){
        String expected = "{";
        Iterator<?> keys = multimap.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = multimap.get(key).toString().replaceAll(" ", "");

            if(keys.hasNext()){
                expected = expected+ "\"" +key + "\":" + value +",";

            }else {
                expected = expected+ "\"" +key + "\":" + value;
            }
        }
        expected = expected + "}";
        return expected;
    }

    public String buildExpected(TreeMultimap<String, Integer> treeMultimap){
        String expected = "{";
        Iterator<?> keys = treeMultimap.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = treeMultimap.get(key).toString().replaceAll(" ", "");

            if(keys.hasNext()){
                expected = expected+ "\"" +key + "\":" + value +",";

            }else {
                expected = expected+ "\"" +key + "\":" + value;
            }
        }
        expected = expected + "}";
        return expected;
    }


}
