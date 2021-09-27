/*FastJSON V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.serializer.date.DateTest
 * distributed with Fastjson 1.2.54
 */

public class DateTest {

    Date d;

    public DateTest() {
        this.d = new Date(1324138987429L);
    }

    public void configure(Date d){
        this.d = d;
    }

    @Test
    public void test_date() throws Exception {

        //The TZ and the Locale are set to replicate the original test oracle.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        String formattedDate = sdf.format(this.d);
        Date date = sdf.parse(formattedDate);
        String dateMillis = Long.toString(date.getTime());

        Assert.assertEquals(dateMillis, JSON.toJSONString(date));
        Assert.assertEquals("new Date("+dateMillis +")", JSON.toJSONString(date, SerializerFeature.WriteClassName));

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        sdf2.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        String formattedDate2 ="\""+ sdf2.format(date)+"\"";
        Assert.assertEquals(formattedDate2, JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat));

        String formattedDate3 ="\""+ formattedDate +"\"";
        Assert.assertEquals(formattedDate3, JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss.SSS"));

        String formattedDate4 ="\'"+ formattedDate +"\'";
         Assert.assertEquals(formattedDate4, JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss.SSS",
                                                SerializerFeature.UseSingleQuotes));
    }
}
