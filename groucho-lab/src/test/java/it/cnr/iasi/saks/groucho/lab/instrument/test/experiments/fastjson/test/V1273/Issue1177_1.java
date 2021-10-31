/*FastJSON V 1.2.73*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.*;

public class Issue1177_1 {

    String text;
    String keyToReplace;
    int depth;
    String jsonpath;
    String value;

    public Issue1177_1() {
        this.text = "{\"a\":{\"x\":{\"z\":\"y\"}},\"b\":{\"x\":\"y\"}}";
        this.keyToReplace = "a";
        this.depth = 0;
        this.jsonpath = "$.a";
        this.value="y2";
    }

    public void configure(org.json.JSONObject object){
        this.text =  String.valueOf(object);
        //Init path and depth
         this.selectRandomPath(JSONObject.parseObject(text), 0);
         String path = "$";
         for (int i = 0; i < depth+1; i++){
             path = path+'.';
         }
         this.jsonpath = path+keyToReplace;
    }

    public void test_for_issue() throws Exception {
        String text = this.text;
        System.out.println("INPUT: " +text +'\n');
        System.out.println("JSONPATH: " +this.jsonpath +'\n');

        JSONObject inputObject = JSONObject.parseObject(text);
        JSONObject expectedObj = this.replacePath(inputObject, this.keyToReplace, this.depth, 0);

        //Build expected string
        String expected = this.objToString(expectedObj, "");
        System.out.println("EXPECTED: "+expected +'\n');

        JSONObject jsonObject = JSONObject.parseObject(text);

        JSONPath.set(jsonObject, this.jsonpath, this.value);
        Assert.assertEquals(expected, jsonObject.toString());
    }

    //Chooses a random path (<Key, Depth> values).
    public void selectRandomPath(JSONObject input, int currentDepth) {

        if (input instanceof JSONObject) {

            Random r = new Random();
            List<String> parentKeys = new ArrayList(input.keySet());
            String rKey = parentKeys.get(r.nextInt(parentKeys.size()));

            this.keyToReplace = rKey;
            this.depth = currentDepth;

            if((input).get(rKey) instanceof JSONObject){
                JSONObject rObject = (JSONObject) ((input).get(rKey));

                //Decide whether we go deeper
                Iterator<?> keys = rObject.keySet().iterator();
                //If rObject is not simple
                if (keys.hasNext()) {
                    boolean goDeeper = r.nextBoolean();
                    if (goDeeper) {
                        selectRandomPath(rObject, currentDepth +1);
                    }
                }
            }
       }
    }

    //Replaces the jsonPath with a value
    public JSONObject replacePath(JSONObject input, String targetKey, int targetDepth, int currentDepth) {

        if (input instanceof JSONObject) {
            if (currentDepth <= targetDepth){
                Iterator<?> keys = input.keySet().iterator();
                while (keys.hasNext()){
                    String key = (String) keys.next();
                    //If it reached target depth check for the key
                    if(currentDepth == targetDepth){
                        if (key == targetKey){
                            input.replace(key, this.value);
                        }
                    } //If it hasn't reached target depth
                    else {
                        if((input).get(key) instanceof JSONObject){
                            JSONObject subObject = (JSONObject) ((input).get(key));
                            replacePath(subObject, targetKey, targetDepth, currentDepth+1);
                        }
                        else if((input).get(key) instanceof JSONArray){

                            JSONArray ja = (JSONArray) (input).get(key);

                            for (int i = 0; i < ja.size(); i++) {
                                if (ja.get(i) instanceof JSONObject){
                                    JSONObject subJo = (JSONObject) ja.get(i);
                                    replacePath(subJo, targetKey, targetDepth, currentDepth+1);
                                }
                            }
                        }
                    }
                }
            }

        }
        return input;
    }

    //Parses a json object to String
    public String objToString(JSONObject object, String expected) {

        expected = "{";

        Iterator<?> keys = object.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            if(object.get(key) instanceof String){
                if(keys.hasNext()){
                    expected = expected+ "\"" +key + "\":" + "\""+object.get(key).toString()+"\",";
                }else {
                    expected = expected+ "\"" +key + "\":" + "\""+object.get(key).toString()+"\"";
                }
            }
            else if(object.get(key) instanceof JSONObject) {
                if(keys.hasNext()){
                    expected = expected + "\"" +key + "\":" + objToString((JSONObject) object.get(key), expected)+ ",";
                }else {
                    expected = expected + "\"" +key + "\":" + objToString((JSONObject) object.get(key), expected);
                }
            }
            else{
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
