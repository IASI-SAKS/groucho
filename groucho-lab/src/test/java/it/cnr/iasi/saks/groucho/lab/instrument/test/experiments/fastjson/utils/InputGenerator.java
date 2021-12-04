package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashMultimap;
import com.google.common.primitives.Ints;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254.WriteDuplicateType;
import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273.Issue2447;
import com.google.common.collect.ArrayListMultimap;
import java.text.SimpleDateFormat;
import java.util.*;


/* Generates input for the InVivo testing session from the Context data */

public class InputGenerator {

    /* Generates a simple date */
    public static Date generateDate(){
        System.out.println("... input generation done!");
        return new Date();
    }

    /* Generates a simple sql date */
    public static java.util.Date generateSqlDate(){
        System.out.println("... input generation done!");
        return new java.util.Date();
    }

    /* Generates a simple Timezone */
    public static TimeZone generateTimeZone(){
        TimeZone tz = TimeZone.getDefault();
        System.out.println("... input generation done!");
        return tz;
    }

    /* Generates a simple Locale */
    public static Locale generateLocale(){
        Locale l = Locale.getDefault();
        System.out.println("... input generation done!");
        return l;
    }

    // Generates a string representation of a JSON Object
    public static String generateString(byte[] array) {
        String text = new String(array);
        System.out.println("... input generation done!");
        return text;
    }

    // Generates an alibaba JSON Object
    public static JSONObject generateAlibabaJSONObject(byte[] array) {
        String text = "";
        text =  new String(array);
        JSONObject alibabaJsonObject = JSON.parseObject(text);
        return alibabaJsonObject;
    }

    // Generates a simple alibaba JSON Object (leaf nodes)
    public static JSONObject generateSimpleAlibabaJSONObject(byte[] array) {
        JSONObject alibabaJsonObject = new JSONObject();
        HashMap<String,String> leafnodes = generateSimpleHashMap(array);
        Iterator<?> keys = leafnodes.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next().toString();
            String value = leafnodes.get(key);
            alibabaJsonObject.put(key,value);
        }
        return alibabaJsonObject;
    }

    // Generates an org.JSON.JSONObject (0 = simple, 1 = nested)
     public static org.json.JSONObject generateJSONObject(int type, byte[] array) {

        String text = "";

        //Simple object
        if(type == 0){
        text =  new String(array);
        }
        //Nested object
        else if(type == 1){
            text =  "{\"parent\":" +new String(array) + "}";
        }
        org.json.JSONObject object = new org.json.JSONObject(text);

        System.out.println("... input generation done!");
        return object;
    }

    /* Generates a JSON Object (random elements from byte[] array) */
    public static JSONObject generateRandomJSONObject(byte[] array){
        String text = new String(array);
        JSONObject jo = JSON.parseObject(text);
        JSONObject randomJo = new JSONObject();
        List<String> keysAsList = new ArrayList(jo.keySet());

        Random r = new Random();
        int elements = r.nextInt(keysAsList.size());
        for(int i = 0; i < elements; i++){
            Random r2 = new Random();
            String rKey = keysAsList.get(r2.nextInt(keysAsList.size()));
            Object rObject = jo.get(rKey);
            randomJo.put(rKey, rObject);
        }

        System.out.println("... input generation done!");
        return randomJo;
    }

    /* Generates a JSON Array (random elements from byte[] array) */
    public static JSONArray generateJSONArray(byte[] array){
        String text = new String(array);
        JSONObject jo = JSON.parseObject(text);
        JSONArray randomJa = new JSONArray();

        List<String> keysAsList = new ArrayList(jo.keySet());

        Random r = new Random();
        int elements = r.nextInt(keysAsList.size());
        for(int i = 0; i < elements; i++){
            Random r2 = new Random();
            String rKey = keysAsList.get(r2.nextInt(keysAsList.size()));
            Object rObject = jo.get(rKey);
            randomJa.add(rObject);
        }

        System.out.println("... input generation done!");
        return randomJa;
    }

    /* Generates a HashMap<String String> from a JSONObject (depth 1) */
    public static HashMap<String, String> generateHashMap(byte[] array) throws JsonProcessingException {
        String text = new String(array);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(text);

        HashMap map = new HashMap<String,String>();

        Iterator<Map.Entry<String, JsonNode>> entries = obj.fields();

        while (entries.hasNext()) {
            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) entries.next();
            map.put(entry.getKey(), entry.getValue().toString());
        }

        System.out.println("... input generation done!");

        return map;
    }


    /* Generates a LinkedHashMap<String, JSONObject> from a JSONObject (depth 1)
    * It encodes the JSON.DEFAULT_TYPE_KEY information needed
    *  */
    public static LinkedHashMap<String, JSONObject> generateLinkedHashMapOfJSONObject(byte[] array) throws JsonProcessingException {
        String text = new String(array);
        JSONObject alibabaJsonObject = JSON.parseObject(text);

        LinkedHashMap map = new LinkedHashMap<String,JSONObject>();


        Iterator<?> keys = alibabaJsonObject.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next().toString();
            if(alibabaJsonObject.get(key) instanceof  JSONObject){
                JSONObject value = (JSONObject) alibabaJsonObject.get(key);
                map.put(key,value);
            }
        }
        System.out.println("... input generation done!");
        return map;
    }

    public static  LinkedHashMap<String, HashMap<String, Object>> generateLinkedHashMapOfHashMap(byte[] array) throws JsonProcessingException {
        String text = new String(array);
        JSONObject alibabaJsonObject = JSON.parseObject(text);

        LinkedHashMap map = new  LinkedHashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> hm = new HashMap<>();

        Iterator<?> keys = alibabaJsonObject.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next().toString();
            if(alibabaJsonObject.get(key) instanceof  JSONObject){
                JSONObject value = (JSONObject) alibabaJsonObject.get(key);
                value.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.fastjson.JSONObject");
                hm.put(key,value);
                hm.put(JSON.DEFAULT_TYPE_KEY, "com.alibaba.fastjson.JSONObject");
            }
        }
        map.put("id", hm);
        System.out.println("... input generation done!");
        return map;
    }

    /* Generates a HashMap containing the textual representation of a JSON Object leaf nodes */
    public static HashMap<String, String> generateSimpleHashMap(byte[] array) {
        String text = new String(array);
        org.json.JSONObject expectedObject = new org.json.JSONObject(text);

        HashMap map = new HashMap<String,String>();

        map = createHashMap(expectedObject, null, map);

        System.out.println("... input generation done!");
        return map;
    }

    /* Generates an ArrayListMultimap with random elements */
    public static ArrayListMultimap<String, Integer> generateArrayListMultimap(byte[] array) {

        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        HashMap<String, String> jsonKeys = generateSimpleHashMap(array);
        List<String> keysAsArray = new ArrayList<>(jsonKeys.keySet());

        //Number of ArrayListMultimap elements
        int mapElements = new Random().nextInt(10);

        for (int i = 0; i < mapElements; i++){
            //generate a random key from context
            Random r = new Random();
            String key = jsonKeys.get(keysAsArray.get(r.nextInt(keysAsArray.size())));

            //generate random elements
            int intElements = new Random().nextInt(10);
            int[] params = new int[intElements];

            for (int j = 0; j < intElements; j++){
                int randomInteger = new Random().nextInt(1000);
                params[j] = randomInteger;
            }
            multimap.putAll(key, Ints.asList(params));
        }

        System.out.println("... input generation done!");
        return multimap;
    }

    //Generate nested set from leaf nodes
    public static HashSet<Map.Entry<String, Map.Entry<String, String>>> generateNestedSet(byte[] array) throws JsonProcessingException {
        HashSet<Map.Entry<String, Map.Entry<String, String>>> nestedSet = new HashSet<>();
        HashMap<String,String> leafnodes = generateSimpleHashMap(array);
        Iterator<?> keys = leafnodes.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next().toString();
            String value = leafnodes.get(key);
            AbstractMap.SimpleEntry<String, String> sEntry =  new AbstractMap.SimpleEntry<>(key, value);
            Random r = new Random();
            char c = (char)(r.nextInt(26)+'a');
            nestedSet.add(new AbstractMap.SimpleEntry<>(Character.toString(c), sEntry));
        }
        return nestedSet;
    }

    //Geneate HashMultimap from json object
    public static HashMultimap<String, String> generateHashMultimap(byte[] array) throws JsonProcessingException {

        HashMultimap<String, String> hashmultimap = HashMultimap.create();
        HashMap<String,String> leafnodes = generateHashMap(array);
        Iterator<?> keys = leafnodes.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next().toString();
            String value = leafnodes.get(key).toString();
            hashmultimap.put(key,value);
        }
        return hashmultimap;
    }

    //Geneate HashMultimap from json object leaf nodes
    public static HashMultimap<String, String> generateSimpleHashMultimap(byte[] array) {
        HashMultimap<String, String> hashmultimap = HashMultimap.create();
        HashMap<String,String> leafnodes = generateSimpleHashMap(array);
        Iterator<?> keys = leafnodes.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next().toString();
            String value = leafnodes.get(key);
            hashmultimap.put(key,value);
        }
        return hashmultimap;
    }

    /* Generates a DianDianCart with a random id */
    public static WriteDuplicateType.DianDianCart generateDianDianCart(byte[] array){

        int rnd = new Random().nextInt(array.length);
        int id = (array[rnd]);
        WriteDuplicateType.DianDianCart cart = new WriteDuplicateType.DianDianCart();
        cart.setId(id);
        System.out.println("... input generation done!");
        return cart;
    }

    /* Generates a VO with a random id, lat and long */
    public static Issue2447.VO generateVO (byte[] array){
        Random rnd = new Random();

        int id = (array[rnd.nextInt(array.length)]);

        int lat = (array[rnd.nextInt(array.length)]);

        int lon = (array[rnd.nextInt(array.length)]);

        Issue2447.VO vo = new Issue2447.VO();

        vo.id =  Math.abs(id);
        vo.location = new Issue2447.Location(Math.abs(lon), Math.abs(lat));

        System.out.println("... input generation done!");
        return vo;
    }

    /* Generates a DataSimpleVO with a random String id and value */
    public static com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO generateDataSimpleVO (byte[] array){

        HashMap<String, String> jsonHashMap = generateSimpleHashMap(array);
        List<String> keysAsArray = new ArrayList<>(jsonHashMap.keySet());

        Random r = new Random();
        String key = jsonHashMap.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
        Object value =  jsonHashMap.get(key);

        com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO vo = new com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO(key, value);
        System.out.println("... input generation done!");
        return vo;
    }

    /* Generates a String representation of an array composed of predefined elements */
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

    /* Generates a random key from a JSONObject */
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


        //Retrieves leaf nodes
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
