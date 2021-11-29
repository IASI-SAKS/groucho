/*FastJSON V 1.2.51*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1251;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.json.bvt.serializer.JSONSerializerMapTest;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializerTest2 {

    JSONSerializer serializer;

    public JSONSerializerTest2(){
        this.serializer = new JSONSerializer();
    }

    public void configure(JSONSerializer js){
     this.serializer = js;
    }

    @Test
    public void test_0() throws Exception {

        int size = JSONSerializerMapTest.size(this.serializer.getMapping());
        this.serializer.config(SerializerFeature.WriteEnumUsingToString, false);
        this.serializer.config(SerializerFeature.WriteEnumUsingName, false);
        this.serializer.write(Type.A);

        Assert.assertTrue(size < JSONSerializerMapTest.size(this.serializer.getMapping()));

        Assert.assertEquals(Integer.toString(Type.A.ordinal()), serializer.getWriter().toString());
    }

    @Test
    public void test_1() throws Exception {
        JSONSerializer serializer = new JSONSerializer();
        serializer.config(SerializerFeature.WriteEnumUsingToString, false);
        serializer.config(SerializerFeature.WriteEnumUsingName, false);
        serializer.write(new A(Type.B));

        Assert.assertEquals("{\"type\":" + Integer.toString(Type.B.ordinal()) + "}", serializer.getWriter().toString());

        A a = JSON.parseObject(serializer.getWriter().toString(), A.class);
        Assert.assertEquals(a.getType(), Type.B);
    }
    @Test
    public void test_2() throws Exception {
        JSONSerializer serializer = new JSONSerializer();
        serializer.write(new C());

        Assert.assertEquals("{}", serializer.getWriter().toString());
    }
    @Test
    public void test_3() throws Exception {
        JSONSerializer serializer = new JSONSerializer();
        serializer.config(SerializerFeature.WriteEnumUsingToString, true);
        serializer.write(new A(Type.B));

        Assert.assertEquals("{\"type\":\"B\"}", serializer.getWriter().toString());

        A a = JSON.parseObject(serializer.getWriter().toString(), A.class);
        Assert.assertEquals(a.getType(), Type.B);
    }
    @Test
    public void test_error() throws Exception {
        Exception error = null;
        try {
            JSONSerializer.write(new Writer() {

                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {
                    throw new IOException();
                }

                @Override
                public void flush() throws IOException {
                    throw new IOException();
                }

                @Override
                public void close() throws IOException {
                    throw new IOException();
                }

            }, (Object) "abc");
        } catch (Exception ex) {
            error = ex;
        }
        Assert.assertNotNull(error);
    }

    public static enum Type {
        A, B
    }

    public static class A {

        private Type type;

        public A(){

        }

        public A(Type type){
            super();
            this.type = type;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

    }

    public static class C {

    }
}
