package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.Time;
import java.util.*;


/* Generates input for the InVivo testing session from the Context data */

public class InputGenerator {

    public static HashMap<String, String> generateMap(byte[] array){
        String text = new String(array);
        JSONObject obj = JSON.parseObject(text);
        HashMap map = new HashMap<String,String>();

        Iterator<?> keys = obj.keySet().iterator();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, obj.get(key));
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
