/* 
 * This file is part of the GROUCHO project.
 * 
 * GROUCHO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GROUCHO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GROUCHO.  If not, see <https://www.gnu.org/licenses/>
 *
 */
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
