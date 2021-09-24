package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils;


import com.alibaba.fastjson.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/* Generates input for the InVivo testing session from the Context data */

public class InputGenerator {

    public static HashMap<String, String> generateMap(byte[] array) throws JsonProcessingException {
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

}
