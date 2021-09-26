/*FastJSON V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1200.Issue1298
 * distributed with Fastjson 1.2.54
 */

public class Issue1298 {

    String formattedDate;
    String formattedDate_1;

    public Issue1298() {
        this.formattedDate = "2017-06-29T08:06:30.000+05:30";
        this.formattedDate_1 = "2017-08-15 20:00:00.000";
    }

    public void configure(Date d){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.formattedDate = sdf.format(d);

        SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.formattedDate_1 = sdf_1.format(d);

        System.out.println("Configuration done.");
    }

    @Test
    public void test_for_issue() throws Exception {
        JSONObject object = new JSONObject();

        object.put("date", formattedDate);

        Date date = object.getObject("date", Date.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String expected = "\"" + sdf.format(date) + "+08:00\""; // +08:00 is Asia/Shanghai GMT Offset

        Assert.assertEquals(expected, JSON.toJSONString(date, SerializerFeature.UseISO8601DateFormat));
    }

    @Test
    public void test_for_issue_1() throws Exception {
        JSONObject object = new JSONObject();

        object.put("date", formattedDate_1);

        Date date = object.getObject("date", Date.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String expected = "\"" + sdf.format(date) + "+08:00\""; // +08:00 is Asia/Shanghai GMT Offset

        Assert.assertEquals(expected, JSON.toJSONString(date, SerializerFeature.UseISO8601DateFormat));

        JSON.parseObject("\""+formattedDate_1+"\"", Date.class);
    }
}
