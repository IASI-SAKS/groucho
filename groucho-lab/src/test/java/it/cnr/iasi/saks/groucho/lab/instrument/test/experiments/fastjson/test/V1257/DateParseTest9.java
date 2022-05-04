package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1257;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;


public class DateParseTest9 {

    private static Random random = new Random();
    private TimeZone original = TimeZone.getDefault();
    private String[] zoneIds = TimeZone.getAvailableIDs();

    @Before
    public void setUp() {
        int index = random.nextInt(zoneIds.length);
        TimeZone timeZone = TimeZone.getTimeZone(zoneIds[index]);
        TimeZone.setDefault(timeZone);
        JSON.defaultTimeZone = timeZone;
    }

    @After
    public void tearDown () {
        TimeZone.setDefault(original);
        JSON.defaultTimeZone = original;
    }

    Date now;

    public DateParseTest9(){
        Calendar cal = Calendar.getInstance();
        now = cal.getTime();
    }

    public void configure(Date d){
        now = d;
    }

    @Test
    public void test_date() throws Exception {
        String text = "\"/Date(1242357713797+0800)/\"";
        Date date = JSON.parseObject(text, Date.class);
        Assert.assertEquals(date.getTime(), 1242357713797L);

        Assert.assertEquals(JSONToken.LITERAL_INT, CalendarCodec.instance.getFastMatchToken());

        text = "\"/Date(1242357713797+0545)/\"";
        date = JSON.parseObject(text, Date.class);
        Assert.assertEquals(date.getTime(), 1242357713797L);
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

    @Test
    public void test_dates_different_timeZones() {

        VO vo = new VO();
        vo.date = this.now;

        String json = JSON.toJSONString(vo);
        VO result = JSON.parseObject(json, VO.class);
        Assert.assertEquals(vo.date, result.date);

        // with iso-format
        json = JSON.toJSONString(vo, SerializerFeature.UseISO8601DateFormat);
        result = JSON.parseObject(json, VO.class);
        Assert.assertEquals(vo.date, result.date);
    }

    public static class VO {
        public Date date;
    }
}