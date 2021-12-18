package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1275;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Bug_for_xiayucai2012 {

    Date d;
    String formattedDate;

    public Bug_for_xiayucai2012(){
        this.d = new Date(0000,00,00, 00,00,00);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.formattedDate = sdf.format(this.d);
    }

    public void configure(Date d){
        this.d = d;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.formattedDate = sdf.format(this.d);
    }

    protected void setUp() throws Exception {
        JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        JSON.defaultLocale = Locale.CHINA;
    }

    @Test
    public void test_for_xiayucai2012() throws Exception {
        String text = "{\"date\":\""+formattedDate+"\"}";
        JSONObject json = JSON.parseObject(text);
        Date date = json.getObject("date", Date.class);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT, JSON.defaultLocale);
        dateFormat.setTimeZone(JSON.defaultTimeZone);
        
        Assert.assertEquals(dateFormat.parse(json.getString("date")), date);
    }
}
