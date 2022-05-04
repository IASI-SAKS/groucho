/*Fastjson V 1.2.73*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1278;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializeWriter;
import org.junit.Assert;
import org.junit.Test;

public class MaxBufSizeTest {

    SerializeWriter writer;

    public MaxBufSizeTest(){
        this.writer = new SerializeWriter();
    }

    public void configure(SerializeWriter sw){
        this.writer = sw;
    }

    @Test
    public void test_max_buf() throws Exception {

        Throwable error = null;
        try {
            writer.setMaxBufSize(1);
        } catch (JSONException e) {
            error = e;
            System.out.println(e);
        }
        Assert.assertNotNull(error);
    }
}
