/*Fastjson V. 1.2.51*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1600.Issue1679
 * distributed with Fastjson 1.2.54
 */

public class Issue1679 extends TestCase {

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l){
        JSON.defaultTimeZone = tz;
        JSON.defaultLocale = l;
    }

    @Test
    public void test_for_issue() throws Exception {
        String json = "{\"create\":\"2018-01-10 08:30:00\"}";
        User user = JSON.parseObject(json, User.class);
        Assert.assertEquals("\"2018-01-10T08:30:00+08:00\"", JSON.toJSONString(user.create, SerializerFeature.UseISO8601DateFormat));
    }

    public static class User{
        public Date create;
    }
}
