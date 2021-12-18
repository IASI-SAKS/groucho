package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1275;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Bug_for_issue_447 {

    Date d;
    long millis;

    public Bug_for_issue_447(){
       this.millis = 1460563200000L;
       this.d = new Date(this.millis);
    }

    public void configure(Date d){
        this.d = d;
        this.millis = d.getTime();
    }

    @Before
    protected void setUp() throws Exception {
        System.out.println("setUp()");
        JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        JSON.defaultLocale = Locale.CHINA;
    }

    @Test
    public void test_for_issue() throws Exception {
        Calendar calendar = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
        calendar.setTimeInMillis(this.millis);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Foo foo = new Foo();
        foo.setCreateDate(calendar.getTime());
        String date = JSON.toJSONString(foo, SerializerFeature.UseISO8601DateFormat);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String expected = sdf.format(this.d);

        Assert.assertEquals("{\"createDate\":\""+expected+"+08:00\"}", date);
        Foo foo2 = JSON.parseObject(date, Foo.class, Feature.AllowISO8601DateFormat);
        Assert.assertEquals("{\"createDate\":\""+expected+" 00:00:00\"}", JSON.toJSONString(foo2, SerializerFeature.WriteDateUseDateFormat));
    }

    public static class Foo {

        private String name;
        private Date   createDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }

    }
}
