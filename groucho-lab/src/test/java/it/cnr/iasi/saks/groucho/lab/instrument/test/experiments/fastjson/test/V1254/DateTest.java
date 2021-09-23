/*FastJSON V 1.2.54*/
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
 *  com.alibaba.json.bvt.serializer.date.DateTest
 * distributed with Fastjson 1.2.54
 */

public class DateTest extends TestCase {

    Date date;
    Locale locale;
    TimeZone timezone;

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l, Date d){
        this.locale = l;
        this.timezone = tz;
        JSON.defaultTimeZone = tz;
        JSON.defaultLocale = l;
        this.date = d;
    }

    @Test
    public void test_date() throws Exception {

        //long millis = 1324138987429L;
        //Date date = new Date(millis);

        Date date = this.date;
        long millis = date.getTime();
        String stringMillis = Long.toString(millis);

        Assert.assertEquals(stringMillis, JSON.toJSONString(date));
        Assert.assertEquals("new Date("+stringMillis +")", JSON.toJSONString(date, SerializerFeature.WriteClassName));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", this.locale);
        sdf.setTimeZone(this.timezone);
        String formattedDate ="\""+ sdf.format(date)+"\"";
        Assert.assertEquals(formattedDate, JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat));

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", this.locale);
        sdf2.setTimeZone(this.timezone);
        String formattedDate2 ="\""+ sdf2.format(date)+"\"";
        Assert.assertEquals(formattedDate2, JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss.SSS"));

        String formattedDate3 ="\'"+ sdf2.format(date)+"\'";
         Assert.assertEquals(formattedDate3, JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss.SSS",
                                                SerializerFeature.UseSingleQuotes));
    }
}
