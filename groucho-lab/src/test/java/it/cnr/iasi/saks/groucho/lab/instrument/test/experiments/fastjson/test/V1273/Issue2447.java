/*Fastjson V 1.2.73*/
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Issue2447 {

    VO vo;
    VO2 vo2;

    public Issue2447(){
        this.vo = new VO();
        this.vo.id = 123;
        this.vo.location = new Location(127, 37);

        this.vo2 = new VO2();
        vo2.id = 123;
        vo2.properties.put("latitude", 37);
        vo2.properties.put("longitude", 127);
    }

    public void configure(VO v){
        this.vo = v;
        this.vo2 = new VO2();
        this.vo2.id = v.id;
        this.vo2.properties.put("latitude", v.location.latitude);
        this.vo2.properties.put("longitude", v.location.longitude);
    }

    @Test
    public void test_for_issue() {
        VO vo = this.vo;
        Object obj = JSON.toJSON(vo);
        String text = JSON.toJSONString(obj);
        Assert.assertEquals("{\"latitude\":"+this.vo.location.latitude+",\"id\":"+this.vo.id+",\"longitude\":"+this.vo.location.longitude+"}", obj.toString());
    }

    @Test
    public void test_for_issue2() {
        VO2 vo = this.vo2;
        Object obj = JSON.toJSON(vo);

        Assert.assertEquals("{\"latitude\":"+this.vo2.properties.get("latitude")+",\"id\":"+this.vo2.id+",\"longitude\":"+this.vo2.properties.get("longitude")+"}", obj.toString());
    }

    public static class VO {

        public int id;

        @JSONField(unwrapped = true)
        public Location location;
    }

    public static class VO2 {
        public int id;

        @JSONField(unwrapped = true)
        public Map<String, Object> properties = new LinkedHashMap<String, Object>();
    }

    public static class Location {
        public int longitude;
        public int latitude;

        public Location() {}

        public Location(int longitude, int latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }
    }
}
