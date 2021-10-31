package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.fastjson.test.V1273;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.spi.Module;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;

public class Issue1780_Module {

	String JsonString;

	public Issue1780_Module(){
		this.JsonString = "{\"name\":\"name11\",\"id\":1111}";
	}

	public void configure(org.json.JSONObject object){
		this.JsonString =  String.valueOf(object);
	}

	@Test
	public void test_for_issue() {
		org.json.JSONObject req = new org.json.JSONObject(JsonString);

		SerializeConfig config = new SerializeConfig();
		config.register(new myModule());

		String expected = "{";


		Iterator<?> keys = req.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();

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

		Assert.assertEquals(expected, JSON.toJSONString(req, config));
	}

	public class myModule implements Module {

		@SuppressWarnings("rawtypes")
		@Override
		public ObjectDeserializer createDeserializer(ParserConfig config, Class type) {
			return null;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public ObjectSerializer createSerializer(SerializeConfig config, Class type) {
			return new ObjectSerializer() {

				@Override
				public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
						int features) throws IOException {
					System.out.println("-------------myModule.createSerializer-------------------");
					org.json.JSONObject req = (org.json.JSONObject) object;
					serializer.out.write(req.toString());
				}
			};
		}

	}
}
