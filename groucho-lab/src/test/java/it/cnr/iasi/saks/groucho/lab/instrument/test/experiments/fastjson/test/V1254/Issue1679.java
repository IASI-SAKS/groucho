/*Fastjson V. 1.2.51*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Assert;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.issue_1600.Issue1679
 * distributed with Fastjson 1.2.54
 */

public class Issue1679 {

    Date d;

    public Issue1679() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.d = sdf.parse("2018-01-10 08:30:00");
    }

    public void configure(Date d){
        this.d = d;
    }

    @Test
    public void test_for_issue() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate =sdf.format(this.d);

        String json = "{\"create\":\""+formattedDate+"\"}";
        User user = JSON.parseObject(json, User.class);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate2 =sdf2.format(this.d);

        //It should only pass for Timezone: Asia/Shanghai, Locale: CHINA
        Assert.assertEquals("\""+formattedDate2+"+08:00\"", JSON.toJSONString(user.create, SerializerFeature.UseISO8601DateFormat));
    }

    public static class User{
        public Date create;
    }
}
