package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.date.DateTest_tz
 * distributed with Fastjson 1.2.54
 */

public class DateTest_tz {

    Date d;

    public DateTest_tz() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.d = sdf.parse("2016-04-29");
    }

    public void configure(Date d){
     this.d = d;
    }

    @Test
    public void test_codec() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //"2016-04-29"
        String formattedDate =sdf.format(this.d);
        JSONReader reader = new JSONReader(new StringReader("{\"value\":\""+formattedDate+"\"}"));

        reader.setLocale(Locale.CHINA);
        reader.setTimzeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        com.alibaba.json.bvt.date.DateTest_tz.Model model = reader.readObject(com.alibaba.json.bvt.date.DateTest_tz.Model.class);
        Assert.assertNotNull(model.value);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        Date date = format.parse(formattedDate);
        Assert.assertEquals(date.getTime(), model.value.getTime());

        Assert.assertEquals(TimeZone.getTimeZone("Asia/Shanghai"), reader.getTimzeZone());
        Assert.assertEquals(Locale.CHINA, reader.getLocal());
        reader.close();
    }
    
    public static class Model {
        public Date value;
    }
}
