package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1251;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.json.bvt.parser.deser.date.DateParseTest14.VO;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


public class DateParseTest9 {

    long millis;

    public DateParseTest9(){
        this.millis = 1242357713797L;
    }

    public void configure(Date d){
        this.millis = d.getTime();
    }

    @Test
    public void test_date() throws Exception {
        String text = "\"/Date("+this.millis+"+0800)/\"";
        Date date = JSON.parseObject(text, Date.class);
        Assert.assertEquals(this.millis, date.getTime());
        Assert.assertEquals(JSONToken.LITERAL_INT, CalendarCodec.instance.getFastMatchToken());
    }

    @Test
    public void test_error() throws Exception {
        Exception error = null;
        try {
            JSON.parseObject("{\"date\":\"/Date(1242357713797A0800)/\"}", VO.class);
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_error_1() throws Exception {
        Exception error = null;
        try {
            JSON.parseObject("{\"date\":\"/Date(1242357713797#0800)/\"}", VO.class);
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }
}
