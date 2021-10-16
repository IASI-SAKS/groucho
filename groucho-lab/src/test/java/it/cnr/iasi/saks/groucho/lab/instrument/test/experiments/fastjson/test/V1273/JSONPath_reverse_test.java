package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
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

    public void configureSimple(String string){
        this.JsonString = string;
    }

    public void configureNested(org.json.JSONObject object){
         this.JsonString3 = String.valueOf(object);
    }

    @Test
    public void test_reserve() {
        JSONObject object = JSON.parseObject(this.JsonString);
        System.out.println("Input: " +this.JsonString);

        List<String> allKeys = new ArrayList(object.keySet());
        List<String> randomKeys = new ArrayList();

        //Select random keys
        int nOfKeys = 0;
        Random r = new Random();
        if(allKeys.size() == 1){
            nOfKeys = 1;
        }else {
            nOfKeys = allKeys.size() - 1;
        }
        for(int i = 0; i < nOfKeys; i++){
            int rIndex = r.nextInt(allKeys.size());
            randomKeys.add(allKeys.get(rIndex));
            allKeys.remove(rIndex);
        }

        //Build expected string
        String expected = new String();

        for(int i = 0; i < randomKeys.size(); i++){
            String k = randomKeys.get(i);

                if(object.get(k) instanceof String){
                    if(i == randomKeys.size()-1){
                        expected = expected+ "\""+object.get(k).toString()+"\"";

                    }else {
                        expected = expected+ "\""+object.get(k).toString()+"\",";
                    }
                }else{
                    if(i == randomKeys.size()-1){
                        expected = expected + object.get(k).toString();

                    }else {
                        expected = expected + object.get(k).toString() +",";
                    }
                }
        }
        expected = "[" +expected +"]";

        //Test parameters
        String[] params = new String[randomKeys.size()];
        for (int i = 0; i<randomKeys.size(); i++){
            params[i] = randomKeys.get(i);

        }

        //1st & 2nd assert
        String actual = JSONPath.reserveToArray(object, params).toString();
        Assert.assertEquals(expected, actual);

        //3rd check
        if(object.keySet().size() > 0) {
            String randomParentKey = randomKeys.get(0);

            Iterator<String> keys = object.keySet().iterator();
            String expectedNested = "";

            if (object.get(randomParentKey) instanceof String) {
                expectedNested = "[\"" + object.get(randomParentKey) + "\",[";
            } else {
                expectedNested = "[" + object.get(randomParentKey) + ",[";
            }

            while (keys.hasNext()) {
                String nextKey = keys.next();
                if (keys.hasNext())
                    if (object.get(randomParentKey) instanceof String) {
                        expectedNested = expectedNested + "\"" + object.get(nextKey) + "\",";
                    } else {
                        expectedNested = expectedNested + object.get(nextKey) + ",";
                    }
                else {
                    if (object.get(randomParentKey) instanceof String) {
                        expectedNested = expectedNested + "\"" + object.get(nextKey) + "\"";
                    } else {
                        expectedNested = expectedNested + object.get(nextKey);
                    }
                }
            }
            expectedNested = expectedNested + "]]";
            String actualNested = JSONPath.reserveToArray(object, randomParentKey, "*").toString();
            Assert.assertEquals(expectedNested, actualNested);

        }
    }

    @Test
    public void test_reserve2() throws Exception {
        JSONObject object = JSON.parseObject("{\"id\":1001,\"name\":\"ljw\",\"age\":50}");

        Assert.assertEquals("{\"id\":1001,\"name\":\"ljw\"}", JSONPath.reserveToObject(object, "id", "name").toString());
        Assert.assertEquals("{\"name\":\"ljw\",\"id\":1001}", JSONPath.reserveToObject(object, "name", "id").toString());
    }

    @Test
    public void test_reserve3() {

        JSONObject object = JSON.parseObject(this.JsonString3);
        String parentKey = object.keySet().iterator().next();

        JSONObject nestedObjects = (JSONObject) object.get(parentKey);
        List<String> allKeys = new ArrayList(nestedObjects.keySet());
        List<String> randomKeys = new ArrayList();

        //Select random keys (object size -1)
        Random r = new Random();
        int nOfKeys = allKeys.size() -1;
        for(int i = 0; i < nOfKeys; i++){
            int rIndex = r.nextInt(allKeys.size());
            randomKeys.add(allKeys.get(rIndex));
            allKeys.remove(rIndex);
        }
        String expected = String.valueOf(object);

        for(String k:allKeys){
            if(!randomKeys.contains(k)) {
                String toRemove = "";
                String toRemove2 = "";
                String toRemove3 = "";

                if(nestedObjects.get(k) instanceof String){
                    toRemove = ",\"" + k + "\":" + "\""+nestedObjects.get(k).toString()+"\"";
                    toRemove2 = "\"" + k + "\":" + "\""+nestedObjects.get(k).toString()+"\",";
                    toRemove3 = "\"" + k + "\":" + "\""+nestedObjects.get(k).toString()+"\"";
                }else{
                    toRemove = ",\"" + k + "\":" + nestedObjects.get(k).toString();
                    toRemove2 = "\"" + k + "\":" + nestedObjects.get(k).toString() +",";
                    toRemove3 = "\"" + k + "\":" + nestedObjects.get(k).toString();

                }
                expected = expected.replace(toRemove, "");
                expected = expected.replace(toRemove2, "");
                expected = expected.replace(toRemove3, "");

            }
        }
        //System.out.println("expected: " +expected );

        //Test parameters
        String[] params = new String[randomKeys.size()];
        String[] params2 = new String[randomKeys.size()+1];

        for (int i = 0; i<randomKeys.size(); i++){
           params[i] = parentKey+'.'+randomKeys.get(i);
           params2[i] = parentKey+'.'+randomKeys.get(i);
        }

       String actual = JSONPath.reserveToObject(object, params).toString();
       Assert.assertEquals(expected, actual);

       params2[randomKeys.size()] = "ab.c";
       String actual2 = JSONPath.reserveToObject(object, params2).toString();
       Assert.assertEquals(expected, actual2);
    }

}
