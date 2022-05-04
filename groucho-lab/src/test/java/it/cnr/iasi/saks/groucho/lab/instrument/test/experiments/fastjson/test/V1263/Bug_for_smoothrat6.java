package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1263;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Bug_for_smoothrat6 {

    Entity entity = new Entity();
    Set<Object> set;
    String value;

    public Bug_for_smoothrat6() {
      this.set = new HashSet<Object>();
      this.set.add(3L);
      this.set.add(4L);
      this.value = "3L,4L";
      this.entity.setValue(set);
    }

    public void configure(HashMap<String, String> input) {
        this.set = new HashSet<>();
        this.value = new String();
       Iterator<String> keys = input.keySet().iterator();
        while (keys.hasNext()) {
            String nextKey = keys.next();
            this.set.add(input.get(nextKey));

            if(keys.hasNext()){
                this.value = this.value+ "\""+input.get(nextKey)+"\",";
            }else {
                this.value = this.value+ "\""+ input.get(nextKey)+"\"";
            }
        }
        this.entity.setValue(set);
    }

    @Test
    public void test_set() throws Exception {

        String text = JSON.toJSONString(entity, SerializerFeature.WriteClassName);
        System.out.println("ACTUAL:");
        System.out.println(text);

        String expected = "{\"@type\":\"it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1263.Bug_for_smoothrat6$Entity\",\"value\":Set["+this.value+"]}";
        System.out.println("EXPECTED");
        System.out.println(expected +'\n');
        Assert.assertEquals(expected, text);

        Entity entity2 = JSON.parseObject(text, Entity.class);
        Assert.assertEquals(this.set, entity2.getValue());
    }

    public static class Entity {

        private Object value;

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
