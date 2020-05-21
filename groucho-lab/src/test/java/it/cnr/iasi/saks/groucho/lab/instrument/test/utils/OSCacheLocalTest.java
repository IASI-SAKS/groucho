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

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.opensymphony.oscache.web.filter.CacheFilter;
import com.opensymphony.oscache.web.filter.ResponseContent;

import it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache.SimpleOSCacheTest_IT;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.web.ServletCacheAdministrator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OSCacheLocalTest{
	

	@Test
	public void doFilterOSCacheTest(){
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		DummyHttpServletResponseWrapper responseWrapper = new DummyHttpServletResponseWrapper(response); 		
		Locale l = new Locale(SimpleOSCacheTest_IT.LOCALE_LANG_CONF);
		responseWrapper.setLocale(l);
		
		FilterChain chain = mock(FilterChain.class);

		ResponseContent respContent = mock(ResponseContent.class);
		when(respContent.getLastModified()).thenReturn(Long.MIN_VALUE);

		Cache cache = mock(Cache.class);
		
		try {
			when(cache.getFromCache(any(), anyInt())).thenReturn(respContent);
		} catch (NeedsRefreshException e) {
			Assert.fail(e.getMessage());
		}

		ServletCacheAdministrator admin = mock(ServletCacheAdministrator.class);
		lenient().when(admin.getCache(any(HttpServletRequest.class), anyInt())).thenReturn(cache);

		ServletContext context = mock(ServletContext.class);
		FilterConfig filterConfig = mock(FilterConfig.class);
		when(filterConfig.getServletContext()).thenReturn(context);
		when(filterConfig.getInitParameter(anyString())).thenReturn(SimpleOSCacheTest_IT.SCOPE_CONF);

		CacheFilter filter = new CacheFilter();
		filter.init(filterConfig);

		try {
			FieldSetter.setField(filter, CacheFilter.class.getDeclaredField("admin"), admin);

			filter.doFilter(request, responseWrapper, chain);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} catch (ServletException e) {
			Assert.fail(e.getMessage());
		} catch (NoSuchFieldException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		}
		
		boolean condition = (responseWrapper.getLocale().getLanguage().equals(SimpleOSCacheTest_IT.LOCALE_LANG_CONF)) && (!responseWrapper.containsHeader(SimpleOSCacheTest_IT.HEADER_FOO_PARAM));
		Assert.assertTrue(condition);
	}
}
