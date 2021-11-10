package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1275;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("rawtypes")
public class TypeUtilsTest {
    String time;

    public TypeUtilsTest (){
        this.time = "08:00:00";
    }
    public void configure (Date d){
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       long date = d.getTime();
       this.time = sdf.format(date);
       System.out.println("Time" + this.time);
    }

    @Test
    public void test_0() throws Exception {
        HashMap map = new HashMap();

        Assert.assertTrue(map == TypeUtils.castToJavaBean(map, Map.class));
    }

    @Test
    public void test_1() throws Exception {
        JSONObject map = new JSONObject();
        Assert.assertTrue(map == TypeUtils.castToJavaBean(map, Map.class));
    }

    @Test
    public void test_2() throws Exception {
        JSONObject map = new JSONObject();
        map.put("id", 1);
        map.put("name", "panlei");

        User user = TypeUtils.castToJavaBean(map, User.class);
        Assert.assertEquals(1L, user.getId());
        Assert.assertEquals("panlei", user.getName());
    }

    @Test
    public void test_cast_Integer() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1L);
        Assert.assertEquals(new Integer(1), json.getObject("id", int.class));
    }
    @Test
    public void test_cast_Integer_2() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1L);
        Assert.assertEquals(new Integer(1), json.getObject("id", Integer.class));
    }
    @Test
    public void test_cast_to_long() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Long(1), json.getObject("id", long.class));
    }
    @Test
    public void test_cast_to_Long() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Long(1), json.getObject("id", Long.class));
    }
    @Test
    public void test_cast_to_short() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Short((short) 1), json.getObject("id", short.class));
    }
    @Test
    public void test_cast_to_Short() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Short((short) 1), json.getObject("id", Short.class));
    }
    @Test
    public void test_cast_to_byte() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Byte((byte) 1), json.getObject("id", byte.class));
    }
    @Test
    public void test_cast_to_Byte() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new Byte((byte) 1), json.getObject("id", Byte.class));
    }
    @Test
    public void test_cast_to_BigInteger() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new BigInteger("1"), json.getObject("id", BigInteger.class));
    }
    @Test
    public void test_cast_to_BigDecimal() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(new BigDecimal("1"), json.getObject("id", BigDecimal.class));
    }
    @Test
    public void test_cast_to_boolean() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(Boolean.TRUE, json.getObject("id", boolean.class));
    }
    @Test
    public void test_cast_to_Boolean() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals(Boolean.TRUE, json.getObject("id", Boolean.class));
    }
    @Test
    public void test_cast_null() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", null);
        Assert.assertEquals(null, json.getObject("id", Boolean.class));
    }
    @Test
    public void test_cast_to_String() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        Assert.assertEquals("1", json.getObject("id", String.class));
    }
    @Test
    public void test_cast_to_Date() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", millis);
        Assert.assertEquals(new Date(millis), json.getObject("date", Date.class));
    }
    @Test
    public void test_cast_to_SqlDate() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", millis);
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }
    @Test
    public void test_cast_to_SqlDate_string() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", Long.toString(millis));
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }
    @Test
    public void test_cast_to_SqlDate_null() throws Exception {
        JSONObject json = new JSONObject();
        json.put("date", null);
        Assert.assertEquals(null, json.getObject("date", java.sql.Date.class));
    }
    @Test
    public void test_cast_to_SqlDate_null2() throws Exception {
        Assert.assertEquals(null, TypeUtils.castToSqlDate(null));
    }
    @Test
    public void test_cast_to_SqlDate_util_Date() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", new Date(millis));
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }
    @Test
    public void test_cast_to_SqlDate_sql_Date() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", new java.sql.Date(millis));
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }
    @Test
    public void test_cast_to_SqlDate_sql_Date2() throws Exception {
        long millis = System.currentTimeMillis();

        java.sql.Date date = new java.sql.Date(millis);
        Assert.assertEquals(date, TypeUtils.castToSqlDate(date));
    }
    @Test
    public void test_cast_to_SqlDate_calendar() throws Exception {
        long millis = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        JSONObject json = new JSONObject();
        json.put("date", calendar);
        Assert.assertEquals(new java.sql.Date(millis), json.getObject("date", java.sql.Date.class));
    }
    @Test
    public void test_cast_to_SqlDate_error() throws Exception {
        JSONObject json = new JSONObject();
        json.put("date", 0);

        JSONException error = null;
        try {
            json.getObject("date", java.sql.Date.class);
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }
    @Test
    public void test_cast_to_Timestamp() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", millis);
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }
    @Test
    public void test_cast_to_Timestamp_string() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", Long.toString(millis));
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }
    @Test
    public void test_cast_to_Timestamp_number() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", new BigDecimal(Long.toString(millis)));
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }
    @Test
    public void test_cast_to_Timestamp_null() throws Exception {
        JSONObject json = new JSONObject();
        json.put("date", null);
        Assert.assertEquals(null, json.getObject("date", Timestamp.class));
    }
    @Test
    public void test_cast_to_Timestamp_null2() throws Exception {
        Assert.assertEquals(null, TypeUtils.castToTimestamp(null));
    }
    @Test
    public void test_cast_to_Timestamp_1970_01_01_00_00_00() throws Exception {
       Assert.assertEquals(new Timestamp(0), TypeUtils.castToTimestamp("1970-01-01 01:"+this.time));
    }
    @Test
    public void test_cast_to_BigDecimal_same() throws Exception {
        BigDecimal value = new BigDecimal("123");
        Assert.assertEquals(true, value == TypeUtils.castToBigDecimal(value));
    }
    @Test
    public void test_cast_to_BigInteger_same() throws Exception {
        BigInteger value = new BigInteger("123");
        Assert.assertEquals(true, value == TypeUtils.castToBigInteger(value));
    }
    @Test
    public void test_cast_Array() throws Exception {
        Assert.assertEquals(Integer[].class, TypeUtils.cast(new ArrayList(), Integer[].class, null).getClass());
    }
    @Test
    public void test_cast_to_Timestamp_util_Date() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", new Date(millis));
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }
    @Test
    public void test_cast_to_Timestamp_sql_Date() throws Exception {
        long millis = System.currentTimeMillis();

        JSONObject json = new JSONObject();
        json.put("date", new java.sql.Date(millis));
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }
    @Test
    public void test_cast_to_Timestamp_sql_Timestamp() throws Exception {
        long millis = System.currentTimeMillis();

        Timestamp date = new Timestamp(millis);
        Assert.assertEquals(date, TypeUtils.castToTimestamp(date));
    }
    @Test
    public void test_cast_to_Timestamp_calendar() throws Exception {
        long millis = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        JSONObject json = new JSONObject();
        json.put("date", calendar);
        Assert.assertEquals(new Timestamp(millis), json.getObject("date", Timestamp.class));
    }
    @Test
    public void test_cast_to_Timestamp_error() throws Exception {
        JSONObject json = new JSONObject();
        json.put("date", -1);

        JSONException error = null;
        try {
            json.getObject("date", Timestamp.class);
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }
    @Test
    public void test_cast_ab() throws Exception {
        B b = new B();

        JSONObject json = new JSONObject();
        json.put("value", b);
        Assert.assertEquals(b, json.getObject("value", A.class));
    }
    @Test
    public void test_cast_ab_1() throws Exception {
        B b = new B();

        JSONObject json = new JSONObject();
        json.put("value", b);
        Assert.assertEquals(b, json.getObject("value", IA.class));
    }
    @Test
    public void test_cast_ab_error() throws Exception {
        A a = new A();

        JSONObject json = new JSONObject();
        json.put("value", a);

        JSONException error = null;
        try {
            json.getObject("value", B.class);
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }
    @Test
    public void test_error() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);

        JSONException error = null;
        try {
            TypeUtils.castToJavaBean(json, C.class, ParserConfig.getGlobalInstance());
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }
    @Test
    public void test_error_2() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);

        Method method = TypeUtilsTest.class.getMethod("f", List.class);

        Throwable error = null;
        try {
            TypeUtils.cast(json, method.getGenericParameterTypes()[0], ParserConfig.getGlobalInstance());
        } catch (JSONException ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }
    @Test
    public void test_3() throws Exception {
        JSONObject map = new JSONObject();
        map.put("id", 1);
        map.put("name", "panlei");

        User user = JSON.toJavaObject(map, User.class);
        Assert.assertEquals(1L, user.getId());
        Assert.assertEquals("panlei", user.getName());
    }

    public static class User {

        private long   id;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class A implements IA {

    }

    public static interface IA {

    }

    public static class B extends A {

    }

    public static class C extends B {

        public int getId() {
            throw new UnsupportedOperationException();
        }

        public void setId(int id) {
            throw new UnsupportedOperationException();
        }
    }

    public static void f(List<?> list) {

    }
}
