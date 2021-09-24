/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * This class is a re-implementation of the original Unit Test:
 * com.alibaba.json.bvt.serializer.date.DateTest5_iso8601
 * distributed with Fastjson 1.2.54
 */

public class DateTest5_iso8601 {

    Date d;

    public DateTest5_iso8601() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.d = sdf.parse("2018-09-12 15:10:19");
       }

    public void configure(Date d){
        this.d = d;
    }

    @Test
    public void test_date() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //"2018-09-12"
        String formattedDate =sdf.format(this.d);
        String date1String = "{\"gmtCreate\":\""+formattedDate+"\"}";
        Date date1 = JSON.parseObject(date1String, VO.class).getGmtCreate();

        Assert.assertNotNull(date1);

        String date2String = "{\"gmtCreate\":\""+formattedDate+"T15:10:19+00:00\"}"; // "2018-09-12T15:10:19+00:00"
        Date date2 = JSON.parseObject(date2String, VO.class).getGmtCreate();

        String date3String = "{\"gmtCreate\":\""+formattedDate+"T15:10:19Z\"}"; // "2018-09-12T15:10:19Z"
        Date date3 = JSON.parseObject(date3String, VO.class).getGmtCreate();

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd"); // "20180912T151019Z"
        String formattedDate4 =sdf2.format(this.d);
        String date4String = "{\"gmtCreate\":\""+formattedDate4+"T151019Z\"}";
        Date date4 = JSON.parseObject(date4String, VO.class).getGmtCreate();

        String date5String = "{\"gmtCreate\":\""+formattedDate+"T15:10:19Z\"}"; // "2018-09-12T15:10:19Z"
        Date date5 = JSON.parseObject(date5String, VO.class).getGmtCreate();

        String date6String = "{\"gmtCreate\":\""+formattedDate4+"\"}"; // "20180912"
        Date date6 = JSON.parseObject(date6String, VO.class).getGmtCreate();

        //This should only pass for Timezone: Asia/Shanghai, Locale: CHINA
        long delta_2_1 = date2.getTime() - date1.getTime();
        Assert.assertEquals(83419000, delta_2_1);

        //This should only pass for Timezone: Asia/Shanghai, Locale: CHINA
        long delta_3_1 = date3.getTime() - date1.getTime();
        Assert.assertEquals(83419000, delta_3_1);

        long delta_4_3 = date4.getTime() - date3.getTime();
        Assert.assertEquals(0, delta_4_3);

        long delta_5_4 = date5.getTime() - date4.getTime();
        Assert.assertEquals(0, delta_5_4);

        long delta_6_1 = date6.getTime() - date1.getTime();
        Assert.assertEquals(0, delta_6_1);
    }

    public static class VO {

        private Date gmtCreate;

        public Date getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

    }
}
