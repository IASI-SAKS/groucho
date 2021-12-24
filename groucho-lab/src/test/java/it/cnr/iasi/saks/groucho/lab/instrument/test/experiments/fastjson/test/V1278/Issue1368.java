package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1278;

import clojure.lang.Obj;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

/**
 * Created by kimmking on 03/08/2017.
 */
public class Issue1368 {

    Object obj;

    public Issue1368(){
        this.obj = new Object();
    }

    public void configure(JSONObject jo){
        this.obj = jo;
    }

    @Test
    public void test_for_issue() throws Exception {
        ExtendedServletRequestDataBinder binder = new ExtendedServletRequestDataBinder(this.obj);
        String json = JSON.toJSONString(binder);
        System.out.println(json);
        Assert.assertTrue(json.indexOf("$ref")>=0);
    }
}
