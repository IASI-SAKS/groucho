package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class Issue1780_JSONObject {

	org.json.JSONObject req;

	public Issue1780_JSONObject(){
		this.req = new org.json.JSONObject();
		req.put("id", 1111);
		req.put("name", "name11");
	}

	public void configure(org.json.JSONObject object){
		this.req = object;
	}

	@Test
	public void test_for_issue() {
		org.json.JSONObject req = this.req;

		String expected = buildExpected(req);
		Assert.assertEquals(expected, JSON.toJSONString(req));
	}

	public String buildExpected(org.json.JSONObject object){
		String expected = "{";

		Iterator<?> keys = object.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();

			if(object.get(key) instanceof String){
				if(keys.hasNext()){
					expected = expected+ "\"" +key + "\":" + "\""+object.get(key).toString()+"\",";

				}else {
					expected = expected+ "\"" +key + "\":" + "\""+object.get(key).toString()+"\"";
				}
			}else{
				if(keys.hasNext()){
					expected = expected + "\"" +key + "\":" + object.get(key).toString() +",";

				}else {
					expected = expected + "\"" +key + "\":" + object.get(key).toString();
				}
			}
		}
		expected = expected + "}";
		return expected;
	}


}
