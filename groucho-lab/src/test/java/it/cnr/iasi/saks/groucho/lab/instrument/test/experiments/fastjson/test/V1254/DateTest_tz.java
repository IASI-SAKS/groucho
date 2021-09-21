package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import junit.framework.TestCase;
import org.junit.Assert;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTest_tz extends TestCase {

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l){
     JSON.defaultTimeZone = tz;
     JSON.defaultLocale = l;
    }

    public void test_codec() throws Exception {
        JSONReader reader = new JSONReader(new StringReader("{\"value\":\"2016-04-29\"}"));
        reader.setLocale(Locale.CHINA);
        reader.setTimzeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        com.alibaba.json.bvt.date.DateTest_tz.Model model = reader.readObject(com.alibaba.json.bvt.date.DateTest_tz.Model.class);
        Assert.assertNotNull(model.value);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = format.parse("2016-04-29");
        Assert.assertEquals(date.getTime(), model.value.getTime());

        Assert.assertEquals(TimeZone.getTimeZone("Asia/Shanghai"), reader.getTimzeZone());
        Assert.assertEquals(Locale.CHINA, reader.getLocal());

        reader.close();
    }
    
    public static class Model {
        public Date value;
    }
}
