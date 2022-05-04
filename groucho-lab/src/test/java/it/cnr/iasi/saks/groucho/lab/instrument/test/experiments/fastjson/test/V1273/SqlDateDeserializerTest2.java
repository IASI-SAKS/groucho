package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class SqlDateDeserializerTest2 {

    java.util.Date d;

    public SqlDateDeserializerTest2(){
        JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        JSON.defaultLocale = Locale.CHINA;
        this.d = new java.util.Date();
    }

    public void configure(java.util.Date d){
        this.d = d;
    }

    @Test
    public void test_sqlDate() {
        long millis = this.d.getTime();
        long millis2 = (millis / 1000)  * 1000;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", JSON.defaultLocale);
        dateFormat.setTimeZone(JSON.defaultTimeZone);
        String text = dateFormat.format(millis);
        text = text.replace(' ', 'T');
        
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", JSON.defaultLocale);
        dateFormat2.setTimeZone(JSON.defaultTimeZone);
        String text2 = dateFormat2.format(millis2);
        
        Assert.assertNull(JSON.parseObject("null", Date.class));
        Assert.assertNull(JSON.parseObject("\"\"", Date.class));
        Assert.assertNull(JSON.parseArray("null", Date.class));
        Assert.assertNull(JSON.parseArray("[null]", Date.class).get(0));
        Assert.assertNull(JSON.parseObject("{\"value\":null}", VO.class).getValue());
        
        Assert.assertEquals(new Date(millis), JSON.parseObject("" + millis, Date.class));
        Assert.assertEquals(new Date(millis), JSON.parseObject("{\"@type\":\"java.sql.Date\",\"val\":" + millis + "}", Date.class));
        Assert.assertEquals(new Date(millis), JSON.parseObject("\"" + millis + "\"", Date.class));
        Assert.assertEquals(new Date(millis2), JSON.parseObject("\"" + text2 + "\"", Date.class));
        Assert.assertEquals(new Date(millis), JSON.parseObject("\"" + text + "\"", Date.class));
        
        //System.out.println(JSON.toJSONString(new Time(millis), SerializerFeature.WriteClassName));
        
    }

    public static class VO {

        private Date value;

        public Date getValue() {
            return value;
        }

        public void setValue(Date value) {
            this.value = value;
        }

    }
}
