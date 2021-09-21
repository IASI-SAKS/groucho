/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.serializer.date.DateTest5_iso8601
 * distributed with Fastjson 1.2.54
 */

public class DateTest5_iso8601 extends TestCase {

    //TimeZone.getTimezone("Asia/Shangai") and Locale.CHINA
    public void configure(TimeZone tz, Locale l){
        JSON.defaultTimeZone = tz;
        JSON.defaultLocale = l;
    }

    @Test
    public void test_date() throws Exception {
        Date date1 = JSON.parseObject("{\"gmtCreate\":\"2018-09-12\"}", VO.class).getGmtCreate();
        Assert.assertNotNull(date1);
        Date date2 = JSON.parseObject("{\"gmtCreate\":\"2018-09-12T15:10:19+00:00\"}", VO.class).getGmtCreate();
        Date date3 = JSON.parseObject("{\"gmtCreate\":\"2018-09-12T15:10:19Z\"}", VO.class).getGmtCreate();
        Date date4 = JSON.parseObject("{\"gmtCreate\":\"20180912T151019Z\"}", VO.class).getGmtCreate();
        Date date5 = JSON.parseObject("{\"gmtCreate\":\"2018-09-12T15:10:19Z\"}", VO.class).getGmtCreate();
        Date date6 = JSON.parseObject("{\"gmtCreate\":\"20180912\"}", VO.class).getGmtCreate();

        long delta_2_1 = date2.getTime() - date1.getTime();
        Assert.assertEquals(83419000, delta_2_1);

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
