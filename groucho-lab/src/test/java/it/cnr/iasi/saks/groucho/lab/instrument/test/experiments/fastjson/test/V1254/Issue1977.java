/*Fastjson V 1.2.51*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1900.Issue1977
 * distributed with Fastjson 1.2.54
 */

public class Issue1977 {

    Long millis;

    public Issue1977() {
        this.millis = 1533265119604L;
    }

    public void configure(Date d){
      this.millis = d.getTime();
    }

    @Test
    public void test_for_issue() throws Exception {
        java.sql.Date date = new java.sql.Date(this.millis);
        String json = JSON.toJSONString(date, SerializerFeature.UseISO8601DateFormat);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String expected = "\"" + sdf.format(date) + "+08:00\"";  // +08:00 is Asia/Shanghai GMT Offset

        Assert.assertEquals(expected, json);
//        new java.sql.Date();
    }
}
