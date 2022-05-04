package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JSONObjectTest_readObject {

    JSONObject jsonObject;

    public JSONObjectTest_readObject(){
        this.jsonObject = new JSONObject();
        this.jsonObject.put("val", new Character[]{});
        this.jsonObject.put("cls", Number.class);
        this.jsonObject.put("nums", new Number[] {});
    }
    public void configure(JSONObject o){
        this.jsonObject = o;
    }

    @Test
    public void test_0() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 123);
        jsonObject.put("obj", new JSONObject());

        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(bytesOut);
        objOut.writeObject(jsonObject);
        objOut.flush();

        byte[] bytes = bytesOut.toByteArray();

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn = new ObjectInputStream(bytesIn);

        Object obj = objIn.readObject();

        Assert.assertEquals(JSONObject.class, obj.getClass());
        Assert.assertEquals(jsonObject, obj);
    }

    @Test
    public void test_2() throws Exception {
        JSONObject jsonObject = JSON.parseObject("{123:345}");

        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(bytesOut);
        objOut.writeObject(jsonObject);
        objOut.flush();

        byte[] bytes = bytesOut.toByteArray();

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn = new ObjectInputStream(bytesIn);

        Object obj = objIn.readObject();

        Assert.assertEquals(JSONObject.class, obj.getClass());
        Assert.assertEquals(jsonObject, obj);
    }

    @Test
    public void test_3() throws Exception {
        JSONObject jsonObject = JSON.parseObject("{123:345,\"items\":[1,2,3,4]}");

        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(bytesOut);
        objOut.writeObject(jsonObject);
        objOut.flush();

        byte[] bytes = bytesOut.toByteArray();

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn = new ObjectInputStream(bytesIn);

        Object obj = objIn.readObject();

        Assert.assertEquals(JSONObject.class, obj.getClass());
        Assert.assertEquals(jsonObject, obj);
    }

    @Test
    public void test_4() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("val", new Byte[]{});

        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(bytesOut);
        objOut.writeObject(jsonObject);
        objOut.flush();

        byte[] bytes = bytesOut.toByteArray();

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn = new ObjectInputStream(bytesIn);

        Object obj = objIn.readObject();

        Assert.assertEquals(JSONObject.class, obj.getClass());
        Assert.assertEquals(jsonObject.toJSONString(), JSON.toJSONString(obj));
    }

    @Test
    public void test_5() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("val", new byte[]{});

        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(bytesOut);
        objOut.writeObject(jsonObject);
        objOut.flush();

        byte[] bytes = bytesOut.toByteArray();

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn = new ObjectInputStream(bytesIn);

        Object obj = objIn.readObject();

        Assert.assertEquals(JSONObject.class, obj.getClass());
        Assert.assertEquals(jsonObject.toJSONString(), JSON.toJSONString(obj));
    }

    @Test
    public void test_6() throws Exception {

        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(bytesOut);
        objOut.writeObject(this.jsonObject);
        objOut.flush();

        byte[] bytes = bytesOut.toByteArray();

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn = new ObjectInputStream(bytesIn);

        Object obj = objIn.readObject();

        Assert.assertEquals(JSONObject.class, obj.getClass());
        Assert.assertEquals(jsonObject.toJSONString(), JSON.toJSONString(obj));
    }

    @Test
    public void test_7() throws Exception {
        ParserConfig.global.setSafeMode(true);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("m", new java.util.HashMap());

            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(bytesOut);
            objOut.writeObject(jsonObject);
            objOut.flush();

            byte[] bytes = bytesOut.toByteArray();

            ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
            ObjectInputStream objIn = new ObjectInputStream(bytesIn);

            Object obj = objIn.readObject();
        } finally {
            ParserConfig.global.setSafeMode(false);
        }
    }
}
