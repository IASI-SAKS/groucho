/*Fastjson V 1.2.73*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import java.io.Serializable;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1400.Issue1492
 * distributed with Fastjson 1.2.73
 */

public class Issue1492 extends TestCase {
    JSONObject obj;
    JSONArray arr;

    public void configure(JSONObject o,  JSONArray a){
        this.obj = o;
        this.arr = a;
        System.out.println("... configuration done.");
    }

    //Temporarily mimics the driver to be implemented
    public void mockConfigure(){
        this.obj = new JSONObject();
        obj.put("key1","value1");
        obj.put("key2","value2");

        this.arr = new JSONArray();
        arr.add("key1");
        arr.add("key2");

        System.out.println("... configuration done.");
    }

    @Test
    public void test_for_issue() throws Exception {
        DubboResponse resp = new DubboResponse();

        // test for JSONObject
        resp.setData(this.obj);
        String str = JSON.toJSONString(resp);
        System.out.println(str);
        DubboResponse resp1 = JSON.parseObject(str, DubboResponse.class);
        Assert.assertEquals(str, JSON.toJSONString(resp1));

        // test for JSONArray
        resp.setData(this.arr);
        String str2 = JSON.toJSONString(resp);
        System.out.println(str2);
        DubboResponse resp2 = JSON.parseObject(str2, DubboResponse.class);
        Assert.assertEquals(str2, JSON.toJSONString(resp2));
    }

    public static final class DubboResponse implements Serializable {

        private String message;

        private String error;

        private JSON data;

        private boolean success;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public JSON getData() {
            return data;
        }

        public void setData(JSON data) {
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}
