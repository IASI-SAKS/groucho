/*Fastjson V. 1.2.51*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Issue1679 extends TestCase {

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l){
        JSON.defaultTimeZone = tz;
        JSON.defaultLocale = l;
    }

    public void test_for_issue() throws Exception {
        String json = "{\"create\":\"2018-01-10 08:30:00\"}";
        User user = JSON.parseObject(json, User.class);
        assertEquals("\"2018-01-10T08:30:00+08:00\"", JSON.toJSONString(user.create, SerializerFeature.UseISO8601DateFormat));
    }

    public static class User{
        public Date create;
    }
}
