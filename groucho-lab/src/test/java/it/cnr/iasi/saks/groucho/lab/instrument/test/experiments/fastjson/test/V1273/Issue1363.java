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
public class Issue1363 {

    com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO a;
    com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO b;

    public Issue1363() {
        this.a = new com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO("a", 1);
        this.b = new com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO("b", 2);
    }

    public void configure(com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO a, com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO b){
        this.a = a;
        this.b = b;
        System.out.println("... configuration done.");
    }

    @Test
    public void test_for_issue() throws Exception {
        this.b.value = this.a;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(this.a.name, this.a);
        this.b.value1 = map;

        String jsonStr = JSON.toJSONString(this.b);
        System.out.println(jsonStr);
        com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO obj = JSON.parseObject(jsonStr, com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO.class);
        Assert.assertEquals(jsonStr, JSON.toJSONString(obj));
    }

    @Test
    public void test_for_issue_1() throws Exception {

        this.b.value1 = this.a;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(this.a.name, this.a);
        this.b.value = map;

        String jsonStr = JSON.toJSONString(this.b);
        System.out.println(jsonStr);

        com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO obj = JSON.parseObject(jsonStr, com.alibaba.json.bvt.issue_1300.Issue1363.DataSimpleVO.class);
        System.out.println(obj.toString());
        Assert.assertNotNull(obj.value1);

        Assert.assertEquals(jsonStr, JSON.toJSONString(obj));
    }

}
