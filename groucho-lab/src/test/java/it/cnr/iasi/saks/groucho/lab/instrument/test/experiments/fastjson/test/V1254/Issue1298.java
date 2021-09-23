/*FastJSON V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1200.Issue1298
 * distributed with Fastjson 1.2.54
 */

public class Issue1298 extends TestCase {

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l){
        JSON.defaultTimeZone = tz;
        JSON.defaultLocale = l;
    }

    @Test
    public void test_for_issue() throws Exception {
        JSONObject object = new JSONObject();

        object.put("date", "2017-06-29T08:06:30.000+05:30");

        Date date = object.getObject("date", Date.class);

        Assert.assertEquals("\"2017-06-29T10:36:30+08:00\"", JSON.toJSONString(date, SerializerFeature.UseISO8601DateFormat));
    }

    @Test
    public void test_for_issue_1() throws Exception {
        JSONObject object = new JSONObject();

        object.put("date", "2017-08-15 20:00:00.000");

        Date date = object.getObject("date", Date.class);

        Assert.assertEquals("\"2017-08-15T20:00:00+08:00\"", JSON.toJSONString(date, SerializerFeature.UseISO8601DateFormat));

        JSON.parseObject("\"2017-08-15 20:00:00.000\"", Date.class);
    }
}
