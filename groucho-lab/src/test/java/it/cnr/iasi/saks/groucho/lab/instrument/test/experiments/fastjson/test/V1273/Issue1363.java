/*FastJSON V 1.2.73*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1300.Issue1363
 * distributed with Fastjson 1.2.73
 */
public class Issue1363 extends TestCase {

    DataSimpleVO a;
    DataSimpleVO b;

    public void configure(DataSimpleVO a, DataSimpleVO b){
        this.a = a;
        this.b = b;
        System.out.println("... configuration done.");
    }
    //Temporarily mimics the driver to be implemented
    public void mockConfigure(){
        Random r = new Random();
        char c1 = (char)(r.nextInt(26) +'a');
        String s1 = Character.toString(c1);
        char c2 = (char)(r.nextInt(26) +'a');
        String s2 = Character.toString(c2);
        int i1 = (int) ((Math.random() * (100 - 1) +1));
        int i2 = (int) ((Math.random() * (100 - 1) +1));
        this.a = new DataSimpleVO(s1, i1);;
        this.b = new DataSimpleVO(s2, i2);
        System.out.println("... configuration done.");
    }

    @Test
    public void test_for_issue() throws Exception {

        b.value = a;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(a.name, a);
        b.value1 = map;

        String jsonStr = JSON.toJSONString(b);
        System.out.println(jsonStr);
        DataSimpleVO obj = JSON.parseObject(jsonStr, DataSimpleVO.class);
        Assert.assertEquals(jsonStr, JSON.toJSONString(obj));
    }

    @Test
    public void test_for_issue_1() throws Exception {

        b.value1 = a;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(a.name, a);
        b.value = map;

        String jsonStr = JSON.toJSONString(b);
        System.out.println(jsonStr);
        DataSimpleVO obj = JSON.parseObject(jsonStr, DataSimpleVO.class);
        System.out.println(obj.toString());
        Assert.assertNotNull(obj.value1);
        Assert.assertEquals(jsonStr, JSON.toJSONString(obj));
    }

    public static class DataSimpleVO {
        public String name;
        public Object value;
        public Object value1;

        public DataSimpleVO() {
        }

        public DataSimpleVO(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString() {
            return "DataSimpleVO [name=" + name + ", value=" + value + ", value1=" + value1 + "]";
        }

    }
}
