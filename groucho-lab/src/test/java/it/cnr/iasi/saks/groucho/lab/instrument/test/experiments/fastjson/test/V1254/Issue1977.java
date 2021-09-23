/*Fastjson V 1.2.51*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.TimeZone;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1900.Issue1977
 * distributed with Fastjson 1.2.54
 */

public class Issue1977 extends TestCase {

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l){
        JSON.defaultTimeZone = tz;
        JSON.defaultLocale = l;
    }

    @Test
    public void test_for_issue() throws Exception {
        java.sql.Date date = new java.sql.Date(1533265119604L);
        String json = JSON.toJSONString(date, SerializerFeature.UseISO8601DateFormat);
        Assert.assertEquals("\"2018-08-03T10:58:39.604+08:00\"", json);
//        new java.sql.Date();
    }
}
