package it.cnr.iasi.saks.groucho.lab.instrument.test.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class DummyHttpServletResponseWrapper extends HttpServletResponseWrapper {

	private Locale l;
	
	private Map<String, String> headers;
	
	public DummyHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
		this.headers = new HashMap<String, String>();
	}

	@Override
	public void addHeader(String name, String value){
		this.setHeader(name, value);
	}
	
	@Override
	public void setHeader(String name, String value){
		this.headers.put(name, value);
	}
	
	@Override
	public boolean containsHeader(String name){
		return this.headers.containsKey(name);
	}

	@Override
	public void setLocale(Locale loc){
		this.l = loc;
	}
	
	@Override
	public Locale getLocale(){
		return this.l; 
	}
	
}
