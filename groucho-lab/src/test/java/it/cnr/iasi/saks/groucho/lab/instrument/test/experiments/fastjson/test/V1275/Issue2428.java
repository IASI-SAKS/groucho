package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1275;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Random;

public class Issue2428 {
    String name;
    String id;
    com.alibaba.json.bvt.issue_2400.Issue2428 demoBean;

    public Issue2428(){
        this.demoBean = new com.alibaba.json.bvt.issue_2400.Issue2428();
        this.demoBean.setMyName("test name");
        this.name = "test name";
        this.demoBean.setNestedBean(new com.alibaba.json.bvt.issue_2400.Issue2428.NestedBean("test id"));
        this.id = "test id";
    }

    public void configure(HashMap<String, String> jsonLeaves){
        this.demoBean = new com.alibaba.json.bvt.issue_2400.Issue2428();
        Random r = new Random();
        String[] leavesAsArray = jsonLeaves.values().toArray(new String[0]);
        this.name = leavesAsArray[r.nextInt(leavesAsArray.length)];
        this.demoBean.setMyName(this.name);
        this.id = leavesAsArray[r.nextInt(leavesAsArray.length)];
        this.demoBean.setNestedBean(new com.alibaba.json.bvt.issue_2400.Issue2428.NestedBean(this.id));
    }

    public void test_for_issue() {

        String expected = "{\"nestedBean\":{\"myId\":\""+this.id +"\"},\"myName\":\""+this.name+"\"}";
        String actual = JSON.toJSONString(JSON.toJSON(demoBean), SerializerFeature.SortField);
        System.out.println("EXPECTED: "+expected);
        System.out.println("ACTUAL: "+actual);

        Assert.assertEquals(expected, actual);

        SerializeConfig serializeConfig = new SerializeConfig();
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String expected2 = "{\"my_name\":\""+this.name+"\",\"nested_bean\":{\"my_id\":\""+this.id +"\"}}";
        String actual2 =  JSON.toJSONString(JSON.toJSON(demoBean, serializeConfig), SerializerFeature.SortField);
        System.out.println("EXPECTED2 : "+expected2);
        System.out.println("ACTUAL2 : "+actual2);

        Assert.assertEquals(expected2, actual2);
    }
}
