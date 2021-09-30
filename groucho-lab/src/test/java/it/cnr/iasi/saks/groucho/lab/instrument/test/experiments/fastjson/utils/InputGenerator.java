package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254.WriteDuplicateType;
import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
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

    //TODO: Generate Map
    public static WriteDuplicateType.DianDianCart generateDianDianCart(Object o){
        byte[] array = SerializationUtils.serialize((Serializable) o);
        int rnd = new Random().nextInt(array.length);
        int id = (array[rnd]);
        WriteDuplicateType.DianDianCart cart = new WriteDuplicateType.DianDianCart();
        cart.setId(id);
        System.out.println("... input generation done!");
        return cart;
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
                        map.put(key, val.toString());
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
                    String val = input.toString();
                    break;
                }
            }

        }
        return map;
    }


}
