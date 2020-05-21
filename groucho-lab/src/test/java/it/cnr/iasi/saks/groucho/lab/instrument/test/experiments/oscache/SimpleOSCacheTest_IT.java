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
package it.cnr.iasi.saks.groucho.lab.instrument.test.experiments.oscache;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;

import it.cnr.iasi.saks.groucho.annotation.TestableInVivo;
import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.DummyHttpServletResponseWrapper;
import it.cnr.iasi.saks.groucho.lab.instrument.test.utils.OSCachePrinterInvivoTestClass;
import it.cnr.iasi.saks.groucho.performanceOverheadTest.TestGovernanceManager_ActivationWithProbability;
import it.cnr.iasi.saks.groucho.tests.util.PropertyUtilNoSingleton;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import com.opensymphony.oscache.web.filter.CacheFilter;
import com.opensymphony.oscache.web.filter.ResponseContent;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RunWith(MockitoJUnitRunner.class)
public class SimpleOSCacheTest_IT {
	private String ORACLE_INVIVO_TEST_CLASS = "it.cnr.iasi.saks.groucho.lab.instrument.test.utils.OSCachePrinterInvivoTestClass";
	private String ORACLE_INVIVO_TEST = "invivoTestMethod";
	private int ORACLE_INVIVO_CARVING_DEPTH = 1;
	private boolean ORACLE_INVIVO_PAUSE_OTHER_THREADS = false;
	
	public static final String HEADER_FOO_PARAM = "myfooHeader";
	public static final String LOCALE_LANG_CONF = "it";
	public static final String SCOPE_CONF = "session";
	
	private TestableInVivo retrieveInvivoAnnotation(Class<?> c, String methodName, Class<?>... classArray) {
		Method reflectedMethod = null;
		try {
			System.out.println("-->" + CacheFilter.class.getCanonicalName());
			reflectedMethod = c.getMethod(methodName, classArray);
		} catch (NoSuchMethodException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		}

		TestableInVivo invivoAnnotation = reflectedMethod.getAnnotation(TestableInVivo.class);

		return invivoAnnotation;
	}

	@Test	
	public void taggingWithInvivoTestAnnotationMethodUntagged() {
		Class<CacheFilter> reflectiveClass = CacheFilter.class;
		String methodName = "doFilter";
		Class<?>[] paramListClassArray = new Class<?>[3];
		paramListClassArray[0] = ServletRequest.class;
		paramListClassArray[1] = ServletResponse.class;
		paramListClassArray[2] = FilterChain.class;

		TestableInVivo invivoAnnotation = this.retrieveInvivoAnnotation(reflectiveClass, methodName,
				paramListClassArray);

		String errMessage = "the method:" + reflectiveClass.getCanonicalName() + "->" + methodName
				+ " has not been tagged";
		Assert.assertNotNull(errMessage, invivoAnnotation);

		Assert.assertEquals(ORACLE_INVIVO_TEST_CLASS, invivoAnnotation.invivoTestClass());
		Assert.assertEquals(ORACLE_INVIVO_TEST, invivoAnnotation.invivoTest());
		Assert.assertEquals(ORACLE_INVIVO_CARVING_DEPTH, invivoAnnotation.carvingDepth());
		Assert.assertEquals(ORACLE_INVIVO_PAUSE_OTHER_THREADS, invivoAnnotation.pauseOtherThreads());
	}

	@Test
	public void invokeInvivoTest() {
		// the following statement binds the PropertyUtil to a No Singleton instance.
		// in other words, all the other classes accessing to
		// PropertyUtil.getInstance();
		// will not be affected by singleton
		PropertyUtilNoSingleton prop = PropertyUtilNoSingleton.getInstance();
		TestGovernanceManager_ActivationWithProbability.setActivationProbability(1);

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		DummyHttpServletResponseWrapper responseWrapper = new DummyHttpServletResponseWrapper(response); 		
		Locale l = new Locale(LOCALE_LANG_CONF);
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
		when(filterConfig.getInitParameter(anyString())).thenReturn(SCOPE_CONF);

		CacheFilter filter = new CacheFilter();
		int hashcodeFilter = filter.hashCode();
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
		
		boolean condition = (responseWrapper.getLocale().getLanguage().equals(LOCALE_LANG_CONF)) && (!responseWrapper.containsHeader(HEADER_FOO_PARAM)) && (hashcodeFilter == OSCachePrinterInvivoTestClass.getLastComputedHashCode() );
		Assert.assertTrue(condition);
	}
}
