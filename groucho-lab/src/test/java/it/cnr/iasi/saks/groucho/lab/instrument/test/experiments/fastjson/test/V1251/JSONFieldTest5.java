package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1251;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONFieldTest5 {

    JSONObject jo = null;
    VO vo = new VO();

    public JSONFieldTest5(){
        this.vo.setID(123);
    }

    public void configure(JSONObject jo){
        this.jo = jo;
    }

    @Test
    public void test_jsonField() throws Exception {
        String text;
        String expected;
        ObjectMapper objectMapper = new ObjectMapper();

        if(this.jo != null){
            text = JSON.toJSONString(this.jo);
            expected = objectMapper.writeValueAsString(this.jo);
            Assert.assertEquals(expected, text);
        }else {
            text = JSON.toJSONString(this.vo);
            Assert.assertEquals("{\"iD\":123}", text);
        }

        if(this.jo != null){
            String key = this.jo.keySet().iterator().next();
            Assert.assertEquals(this.jo.get(key), JSON.parseObject(text, JSONObject.class).get(key));
        }else{
            Assert.assertEquals(123, JSON.parseObject(text, VO.class).getID());
        }
    }

    public static class VO {

        private int id;

        public int getID() {
            return id;
        }

        public void setID(int id) {
            this.id = id;
        }

    }
}
