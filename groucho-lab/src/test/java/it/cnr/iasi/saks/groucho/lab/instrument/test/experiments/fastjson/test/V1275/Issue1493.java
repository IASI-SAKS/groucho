package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1275;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Issue1493 {

    Date d;
    String stime2;

    public Issue1493() throws ParseException {
         this.stime2 = "2017-09-22T15:08:56";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.d = sdf.parse(this.stime2);
    }

    public void configure(Date d){
        this.d = d;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.stime2 = sdf.format(this.d);
    }

    @Before
    public void setUp() throws Exception {
       JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
       JSON.defaultLocale = Locale.CHINA;
    }

    @Test
    public void test_for_issue() throws Exception {
        TestBean test = new TestBean();

        LocalDateTime time1 = LocalDateTime.now();
        time1 = time1.minusNanos(10L);

        LocalDateTime time2 = LocalDateTime.parse(stime2);
        test.setTime1(time1);
        test.setTime2(time2);

        String t1 = JSON.toJSONString(time1, SerializerFeature.WriteDateUseDateFormat);

        String json = JSON.toJSONString(test, SerializerFeature.WriteDateUseDateFormat);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String t2 = sdf2.format(this.d);

        Assert.assertEquals("{\"time1\":"+t1+",\"time2\":\""+t2+"\"}",json);

        //String default_format = JSON.DEFFAULT_LOCAL_DATE_TIME_FORMAT;
        //JSON.DEFFAULT_LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        //String stime1 = DateTimeFormatter.ofPattern(JSON.DEFFAULT_LOCAL_DATE_TIME_FORMAT, Locale.CHINA).format(time1);

        json = JSON.toJSONString(test, SerializerFeature.WriteDateUseDateFormat);
        Assert.assertEquals("{\"time1\":"+ JSON.toJSONString(time1, SerializerFeature.WriteDateUseDateFormat) +",\"time2\":\""+t2+"\"}",json);


        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        String stime1 = DateTimeFormatter.ofPattern(pattern, Locale.CHINA).format(time1);

        json = JSON.toJSONStringWithDateFormat(test, "yyyy-MM-dd'T'HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
        Assert.assertEquals("{\"time1\":\""+stime1+"\",\"time2\":\""+stime2+"\"}",json);

        //JSON.DEFFAULT_LOCAL_DATE_TIME_FORMAT = default_format;
    }

    public static class TestBean {
        LocalDateTime time1;
        LocalDateTime time2;

        public LocalDateTime getTime1() {
            return time1;
        }

        public void setTime1(LocalDateTime time1) {
            this.time1 = time1;
        }

        public LocalDateTime getTime2() {
            return time2;
        }

        public void setTime2(LocalDateTime time2) {
            this.time2 = time2;
        }
    }
}
