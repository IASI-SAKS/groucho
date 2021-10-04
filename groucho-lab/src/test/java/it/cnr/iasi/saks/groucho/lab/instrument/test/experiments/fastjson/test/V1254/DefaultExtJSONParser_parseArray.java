/*Fastjson V 1.2.54*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1254;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONToken;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/*
 * This class is a re-implementation of the original Unit Test:
 *  com.alibaba.json.bvt.parser.DefaultExtJSONParser_parseArray
 * distributed with Fastjson 1.2.54
 */

public class DefaultExtJSONParser_parseArray {

    String formattedDate;
    String array;

    public DefaultExtJSONParser_parseArray() throws ParseException {
        //JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        this.formattedDate = "2011-01-09T13:49:53.254";
        this.array = "[\"2011-01-09T13:49:53.254\", \"xxx\", true, false, null, {}]";
      }

    public void configureDate(Date d){
       //JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
       this.formattedDate = sdf.format(d);
       System.out.println("Configuration done.");
    }
    public void configureArray(String array){
        this.array = array;
        System.out.println("Configuration done.");
    }

    @Test
    public void test_0() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,2,,,3]");
        List list = new ArrayList();
        parser.parseArray(int.class, list);
        Assert.assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    public void test_1() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,2,3]");
        parser.config(Feature.AllowArbitraryCommas, true);
        List list = new ArrayList();
        parser.parseArray(int.class, list);
        Assert.assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    public void test_2() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("['1','2','3']");
        parser.config(Feature.AllowArbitraryCommas, true);
        List list = new ArrayList();
        parser.parseArray(String.class, list);
        Assert.assertEquals("[1, 2, 3]", list.toString());
        Assert.assertEquals("1", list.get(0));
    }

    @Test
    public void test_3() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,2,3]");
        parser.config(Feature.AllowArbitraryCommas, true);
        List list = new ArrayList();
        parser.parseArray(BigDecimal.class, list);
        Assert.assertEquals("[1, 2, 3]", list.toString());
        Assert.assertEquals(new BigDecimal("1"), list.get(0));
    }

    @Test
    public void test_4() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,2,3,null]");
        parser.config(Feature.AllowArbitraryCommas, true);
        List list = new ArrayList();
        parser.parseArray(BigDecimal.class, list);
        Assert.assertEquals("[1, 2, 3, null]", list.toString());
        Assert.assertEquals(new BigDecimal("1"), list.get(0));
    }

    @Test
    public void test_5() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,2,3,null]");
        Object[] array = parser.parseArray(new Type[] { Integer.class, BigDecimal.class, Long.class, String.class });
        Assert.assertEquals(new Integer(1), array[0]);
        Assert.assertEquals(new BigDecimal("2"), array[1]);
        Assert.assertEquals(new Long(3), array[2]);
        Assert.assertEquals(null, array[3]);
    }

    @Test
    public void test_error() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("{}");
        Exception error = null;
        try {
            parser.parseArray(new ArrayList());
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_6() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1.2]");
        parser.config(Feature.UseBigDecimal, false);
        ArrayList list = new ArrayList();
        parser.parseArray(list);
        Assert.assertEquals(Double.valueOf(1.2), list.get(0));
    }

    @Test
    public void test_7() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser(array);
        parser.config(Feature.AllowISO8601DateFormat, true);
        ArrayList list = new ArrayList();
        parser.parseArray(list);

        String input = this.array.substring(1, array.length()-1);
        String[] tokens = input.split(", ");

        if(tokens.length == 1 && tokens[0] == ""){
            Assert.assertEquals(this.array.trim(), list.toString());
        }else {
            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i];
                if (token.startsWith("\"")) {
                    String formattedToken = token.replaceAll("\"", "");
                    if (isDate(formattedToken)) {
                        //The TZ is set to replicate the original test oracle.
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                        Date expectedDate = sdf.parse(formattedToken);
                        Assert.assertEquals(expectedDate, list.get(i));
                    } else {
                        Assert.assertEquals(formattedToken, list.get(i));
                    }

                } else if (token == "null") {
                    Assert.assertEquals(null, list.get(i));
                } else if (token == "true" || token == "false") {
                    Assert.assertEquals(Boolean.parseBoolean(token), list.get(i));
                } else if (token == "{}") {
                    Assert.assertEquals(new JSONObject(), list.get(i));
                }
            }
        }

    }

    public boolean isDate(String datestr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
           sdf.parse(datestr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Test
    public void test_8() throws Exception {

        DefaultJSONParser parser = new DefaultJSONParser("\""+this.formattedDate+"\"");
        parser.config(Feature.AllowISO8601DateFormat, true);

        //The TZ is set to replicate the original test oracle.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date expected = sdf.parse(this.formattedDate);

        Object value = parser.parse();
        Assert.assertEquals(expected, value);
    }

    @Test
    public void test_9() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("");
        parser.config(Feature.AllowISO8601DateFormat, true);
        Object value = parser.parse();
        Assert.assertEquals(null, value);
    }

    @Test
    public void test_error_2() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("{}");
        Exception error = null;
        try {
            parser.accept(JSONToken.NULL);
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_10() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,2,3]");
        Object[] array = parser.parseArray(new Type[] { Integer[].class });
        Integer[] values = (Integer[]) array[0];
        Assert.assertEquals(new Integer(1), values[0]);
        Assert.assertEquals(new Integer(2), values[1]);
        Assert.assertEquals(new Integer(3), values[2]);
    }

    @Test
    public void test_11() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1]");
        Object[] array = parser.parseArray(new Type[] { String.class });
        Assert.assertEquals("1", array[0]);
    }

    @Test
    public void test_12() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("['1']");
        Object[] array = parser.parseArray(new Type[] { int.class });
        Assert.assertEquals(new Integer(1), array[0]);
    }

    @Test
    public void test_13() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("['1']");
        Object[] array = parser.parseArray(new Type[] { Integer.class });
        Assert.assertEquals(new Integer(1), array[0]);
    }

    @Test
    public void test_14() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[]");
        Object[] array = parser.parseArray(new Type[] {});
        Assert.assertEquals(0, array.length);
    }

    @Test
    public void test_15() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,null]");
        ArrayList list = new ArrayList();
        parser.config(Feature.AllowISO8601DateFormat, false);
        parser.parseArray(String.class, list);
        Assert.assertEquals("1", list.get(0));
        Assert.assertEquals(null, list.get(1));
    }

    @Test
    public void test_16() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[[1]]");
        parser.config(Feature.AllowISO8601DateFormat, false);
        Object[] array = parser.parseArray(new Type[] { new TypeReference<List<Integer>>() {
        }.getType() });
        Assert.assertEquals(new Integer(1), ((List<Integer>) (array[0])).get(0));
    }

    @Test
    public void test_17() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[]");
        Object[] array = parser.parseArray(new Type[] { Integer[].class });
        Integer[] values = (Integer[]) array[0];
        Assert.assertEquals(0, values.length);
    }

    @Test
    public void test_18() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("null");
        parser.config(Feature.AllowISO8601DateFormat, false);
        List<Integer> list = (List<Integer>) parser.parseArrayWithType(new TypeReference<List<Integer>>() {
        }.getType());
        Assert.assertEquals(null, list);
    }

    @Test
    public void test_error_var() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,2,null }");
        parser.config(Feature.AllowISO8601DateFormat, false);

        Exception error = null;
        try {
            Object[] array = parser.parseArray(new Type[] { Integer[].class });
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_error_3() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,null }");
        ArrayList list = new ArrayList();
        parser.config(Feature.AllowISO8601DateFormat, false);

        Exception error = null;
        try {
            parser.parseArray(String.class, list);
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_error_4() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,null }");
        parser.config(Feature.AllowISO8601DateFormat, false);

        Exception error = null;
        try {
            parser.parseArray(new Type[] { String.class });
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_error_5() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,null }");
        ArrayList list = new ArrayList();
        parser.config(Feature.AllowISO8601DateFormat, false);

        Exception error = null;
        try {
            parser.parseArray(String.class, list);
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_error_6() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("{1,null }");
        parser.config(Feature.AllowISO8601DateFormat, false);

        Exception error = null;
        try {
            parser.parseArray(new Type[] { String.class });
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_error_7() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("{1}");
        parser.config(Feature.AllowISO8601DateFormat, false);

        Exception error = null;
        try {
            parser.parseArray(new Type[] {});
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    @Test
    public void test_error_8() throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser("[1,2,3 4]");
        parser.config(Feature.AllowISO8601DateFormat, false);

        Exception error = null;
        try {
            parser.parseArray(new Type[] { Integer.class });
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }
}
