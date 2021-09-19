package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test;

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

    TimeZone timezone;
    Locale locale;

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l){
        this.timezone = tz;
       this.locale = l;
    }

    public void test_codec() throws Exception {
        JSONReader reader = new JSONReader(new StringReader("{\"value\":\"2016-04-29\"}"));
        reader.setLocale(this.locale);
        reader.setTimzeZone(this.timezone);
        
        Model model = reader.readObject(Model.class);
        Assert.assertNotNull(model.value);
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", this.locale);
        format.setTimeZone(this.timezone);
        Date date = format.parse("2016-04-29");
        Assert.assertEquals(date.getTime(), model.value.getTime());
        
        Assert.assertEquals(this.timezone, reader.getTimzeZone());
        Assert.assertEquals(this.locale, reader.getLocal());
        
        reader.close();
    }
    
    public static class Model {
        public Date value;
    }
}
