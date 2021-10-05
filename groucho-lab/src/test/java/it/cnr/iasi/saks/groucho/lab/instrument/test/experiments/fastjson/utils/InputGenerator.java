package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254.WriteDuplicateType;

import java.text.SimpleDateFormat;
import java.util.*;


/* Generates input for the InVivo testing session from the Context data */

public class InputGenerator {

    public static HashMap<String, String> generateHashMap(byte[] array) throws JsonProcessingException {
        String text = new String(array);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(text);

        HashMap map = new HashMap<String,String>();

        Iterator<Map.Entry<String, JsonNode>> entries = obj.fields();

        while (entries.hasNext()) {
            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) entries.next();
            map.put(entry.getKey(), entry.getValue());
        }

        System.out.println("... input generation done!");

        return map;
    }

    //Generates a HashMap containing the textual representation of a JSON Object leaf nodes
    public static HashMap<String, String> generateSimpleHashMap(byte[] array) {
        String text = new String(array);
        org.json.JSONObject expectedObject = new org.json.JSONObject(text);

        HashMap map = new HashMap<String,String>();

        map = createHashMap(expectedObject, null, map);

        System.out.println("... input generation done!");
        return map;
    }

    public static Date generateDate(){
        System.out.println("... input generation done!");
        return new Date();
    }

    public static TimeZone generateTimeZone(){
        TimeZone tz = TimeZone.getDefault();
        System.out.println("... input generation done!");
        return tz;
    }

    public static Locale generateLocale(){
        Locale l = Locale.getDefault();
        System.out.println("... input generation done!");
        return l;
    }

    //Generates a cartMap containing zero or more elements
    public static LinkedHashMap<String, HashMap<String, Object>> generateCartMap(byte[] array) {
        LinkedHashMap<String, HashMap<String, Object>> cartMap = new LinkedHashMap<String, HashMap<String, Object>>();

        int elements = (int) (Math.random() * 5);

        for(int i = 0; i < elements; i++){
            HashMap<String, Object> obj = new HashMap<String, Object>();
            WriteDuplicateType.DianDianCart ddc = generateDianDianCart(array);
            obj.put("id", ddc.getId());
            obj.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.json.bvt.writeClassName.WriteDuplicateType$DianDianCart");
            cartMap.put(String.valueOf(ddc.getId()), obj);
        }

        System.out.println("... input generation done!");
        return cartMap;
    }

    public static WriteDuplicateType.DianDianCart generateDianDianCart(byte[] array){

        int rnd = new Random().nextInt(array.length);
        int id = (array[rnd]);
        WriteDuplicateType.DianDianCart cart = new WriteDuplicateType.DianDianCart();
        cart.setId(id);
        System.out.println("... input generation done!");
        return cart;
    }

    public static String generateStringArray(byte[] array) throws JsonProcessingException {

       ArrayList<String> values = new ArrayList<>();
       String elements = "";

       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
       String formattedDate = "\""+ sdf.format(generateDate()) +"\"";
       values.add(formattedDate);

       String key = generateRandomKey(array);
       String s = "\"" + key + "\"";
       values.add(s);

        Random r = new Random();
        String b = String.valueOf(r.nextBoolean());
        values.add(b);

        values.add("null");

        values.add("{}");

        int arraySize = (int) (Math.random() * 5);

        for(int i = 0; i < arraySize; i++){

            int rIndex = (int) (Math.random() * values.size()-1);

            if(i == arraySize - 1){
                elements = elements + values.get(rIndex);
                //System.out.println("elements" + elements);
            }
            else{
                elements = elements + values.get(rIndex) + ", ";
                //System.out.println("elements" + elements);
            }
        }

        String ar = "[" + elements + "]";
        System.out.println("... input generation done!");
        return ar;
    }

    public static String generateRandomKey(byte[] array) throws JsonProcessingException {
        ArrayList<String> keys = new ArrayList<>();
        String text  = new String(array);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(text);

        Iterator<Map.Entry<String, JsonNode>> entries = obj.fields();

        while (entries.hasNext()){
            String entryKey = entries.next().getKey();
            keys.add(entryKey);
        }

        Random r = new Random();
        int rIndex = r.nextInt(keys.size()-1);

        String randomKey = keys.get(rIndex);
        System.out.println("... input generation done!");

        return randomKey;

    }

    public static HashMap<String,String> createHashMap(Object input, String k, HashMap<String,String> map) throws org.json.JSONException {

        if (input instanceof org.json.JSONObject) {

            Iterator<?> keys = ((org.json.JSONObject) input).keys();

            while (keys.hasNext()) {

                String key = (String) keys.next();

                if (!(((org.json.JSONObject) input).get(key) instanceof org.json.JSONArray)){
                    if (((org.json.JSONObject) input).get(key) instanceof org.json.JSONObject) {
                        createHashMap(((org.json.JSONObject) input).get(key), key, map);
                    } else {
                        String val = ((org.json.JSONObject) input).get(key).toString();
                        map.put(key, val);
                    }
                }
                else {
                    createHashMap(new org.json.JSONArray(((org.json.JSONObject) input).get(key).toString()), key, map);
                }
            }
        }
        if (input instanceof org.json.JSONArray) {

            for (int i = 0; i < ((org.json.JSONArray) input).length(); i++) {

                if(((org.json.JSONArray) input).get(i) instanceof org.json.JSONObject){
                    org.json.JSONObject a = ((org.json.JSONArray) input).getJSONObject(i);
                    createHashMap(a, k +"[" + i + "]", map);
                }else{
                   break;
                }
            }

        }
        return map;
    }


}
