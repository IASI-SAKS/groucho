package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class Issue1780_JSONObject {

	String JsonString;

	public Issue1780_JSONObject(){
		this.JsonString = "{\"name\":\"name11\",\"id\":1111}";
	}

	public void configure(org.json.JSONObject object){
		this.JsonString =  String.valueOf(object);
	}

	@Test
	public void test_for_issue() {
		org.json.JSONObject req = new org.json.JSONObject(JsonString);

		String expected = "{";
		Iterator<?> keys = req.keys();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			req.put(key, req.get(key));

			if(req.get(key) instanceof String){
				if(keys.hasNext()){
					expected = expected+ "\"" +key + "\":" + "\""+req.get(key).toString()+"\",";

				}else {
					expected = expected+ "\"" +key + "\":" + "\""+req.get(key).toString()+"\"";
				}
			}else{
				if(keys.hasNext()){
					expected = expected + "\"" +key + "\":" + req.get(key).toString() +",";

				}else {
					expected = expected + "\"" +key + "\":" + req.get(key).toString();
				}
			}
		}
		expected = expected + "}";

		Assert.assertEquals(expected, JSON.toJSONString(req));
	}
}
