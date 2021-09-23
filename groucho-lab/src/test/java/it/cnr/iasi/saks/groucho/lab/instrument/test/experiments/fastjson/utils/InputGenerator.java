package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;


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
        return map;
    }
}
