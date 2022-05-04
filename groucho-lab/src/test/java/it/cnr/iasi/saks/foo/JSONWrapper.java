package it.cnr.iasi.saks.foo;

import com.alibaba.fastjson.JSON;
public class JSONWrapper {

	public String toJSONStringWrapper(Object o) {
		String r = JSON.toJSONString(o);
		return (r);
	}
}
