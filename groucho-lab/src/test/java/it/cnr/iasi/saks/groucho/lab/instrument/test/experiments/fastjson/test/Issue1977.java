/*Fastjson V 1.2.51*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;

import java.util.Locale;
import java.util.TimeZone;

public class Issue1977 extends TestCase {

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l){
        JSON.defaultTimeZone = tz;
        JSON.defaultLocale = l;
    }

    public void test_for_issue() throws Exception {
        java.sql.Date date = new java.sql.Date(1533265119604L);
        String json = JSON.toJSONString(date, SerializerFeature.UseISO8601DateFormat);
        assertEquals("\"2018-08-03T10:58:39.604+08:00\"", json);
//        new java.sql.Date();
    }
}
