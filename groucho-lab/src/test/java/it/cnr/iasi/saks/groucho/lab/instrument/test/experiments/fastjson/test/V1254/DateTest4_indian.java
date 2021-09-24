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
 *  com.alibaba.json.bvt.serializer.date.DateTest4_indian
 * distributed with Fastjson 1.2.54
 */

public class DateTest4_indian {

    Date d;

    public DateTest4_indian() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.d = sdf.parse("2018-09-11 23:54:36");
    }

    public void configure(Date d){
        this.d = d;
    }

    @Test
    public void test_date() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //"2021-09-23T23:54:36"
        String formattedDate =sdf.format(this.d);

        String date1String = "{\"gmtCreate\":\""+formattedDate+"+0530\"}";
        Date date1 = JSON.parseObject(date1String, VO.class).getGmtCreate();

        Assert.assertNotNull(date1);

        String date2String = "{\"gmtCreate\":\""+formattedDate+"+0500\"}";
        Date date2 = JSON.parseObject(date2String, VO.class).getGmtCreate();

        String date3String = "{\"gmtCreate\":\""+formattedDate+"+0545\"}";
        Date date3 = JSON.parseObject(date3String, VO.class).getGmtCreate();

        String date4String = "{\"gmtCreate\":\""+formattedDate+"+1245\"}";
        Date date4 = JSON.parseObject(date4String, VO.class).getGmtCreate();

        String date5String = "{\"gmtCreate\":\""+formattedDate+"+1345\"}";
        Date date5 = JSON.parseObject(date5String, VO.class).getGmtCreate();

        //These should only pass for Timezone: Asia/Shanghai, Locale: CHINA
        long delta_2_1 = date2.getTime() - date1.getTime();
        Assert.assertEquals(1800000, delta_2_1);

        long delta_3_1 = date3.getTime() - date1.getTime();
        Assert.assertEquals(-900000, delta_3_1); //

        long delta_4_3 = date4.getTime() - date3.getTime();
        Assert.assertEquals(-25200000, delta_4_3);

        long delta_5_4 = date5.getTime() - date4.getTime();
        Assert.assertEquals(17100000, delta_5_4);
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
