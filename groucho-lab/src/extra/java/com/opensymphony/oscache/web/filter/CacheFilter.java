/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.oscache.web.filter;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.web.ServletCacheAdministrator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * CacheFilter is a filter that allows for server-side caching of post-processed servlet content.<p>
 *
 * It also gives great programatic control over refreshing, flushing and updating the cache.<p>
 *
 * @author <a href="mailto:sergek@lokitech.com">Serge Knystautas</a>
 * @author <a href="mailto:mike@atlassian.com">Mike Cannon-Brookes</a>
 * @version $Revision$
 */
public class CacheFilter implements Filter {
    private final Log log = LogFactory.getLog(this.getClass());

    // Header
    public static final String HEADER_LAST_MODIFIED = "Last-Modified";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_EXPIRES = "Expires";
    public static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String HEADER_CACHE_CONTROL = "Cache-control";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    
    // filter variables
    private FilterConfig config;
    private ServletCacheAdministrator admin = null;
    private int cacheScope = PageContext.APPLICATION_SCOPE; // filter scope - default is APPLICATION
    private int time = 60 * 60; // time before cache should be refreshed - default one hour (in seconds)

    /**
     * Filter clean-up
     */
    public void destroy() {
        //Not much to do...
    }

    /**
     * The doFilter call caches the response by wrapping the <code>HttpServletResponse</code>
     * object so that the output stream can be caught. This works by splitting off the output
     * stream into two with the {@link SplitServletOutputStream} class. One stream gets written
     * out to the response as normal, the other is fed into a byte array inside a {@link ResponseContent} object.
     *
     * @param request The servlet request
     * @param response The servlet response
     * @param chain The filter chain
     * @throws ServletException IOException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (log.isInfoEnabled()) {
            log.info("<cache>: filter in scope " + cacheScope);
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String key = admin.generateEntryKey(null, httpRequest, cacheScope);
        Cache cache = admin.getCache(httpRequest, cacheScope);

        try {
            ResponseContent respContent = (ResponseContent) cache.getFromCache(key, time);

            if (log.isInfoEnabled()) {
                log.info("<cache>: Using cached entry for " + key);
            }

            long clientLastModified = httpRequest.getDateHeader("If-Modified-Since"); // will return -1 if no header...

            if (( clientLastModified != -1 ) && ( clientLastModified >= respContent.getLastModified())) { 
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }

            respContent.writeTo(response);
        } catch (NeedsRefreshException nre) {
            boolean updateSucceeded = false;

            try {
                if (log.isInfoEnabled()) {
                    log.info("<cache>: New cache entry, cache stale or cache scope flushed for " + key);
                }

                CacheHttpServletResponseWrapper cacheResponse = new CacheHttpServletResponseWrapper((HttpServletResponse) response);
                chain.doFilter(request, cacheResponse);
                cacheResponse.flushBuffer();

                // Only cache if the response was 200
                if (cacheResponse.getStatus() == HttpServletResponse.SC_OK) {
                    //Store as the cache content the result of the response
                    cache.putInCache(key, cacheResponse.getContent());
                    updateSucceeded = true;
                }
            } finally {
                if (!updateSucceeded) {
                    cache.cancelUpdate(key);
                }
            }
        }
    }

    /**
     * Initialize the filter. This retrieves a {@link ServletCacheAdministrator}
     * instance and configures the filter based on any initialization parameters.<p>
     * The supported initialization parameters are:
     * <ul>
     * <li><b>time</b> - the default time (in seconds) to cache content for. The default
     * value is 3600 seconds (one hour).</li>
     * <li><b>scope</b> - the default scope to cache content in. Acceptable values
     * are <code>application</code> (default), <code>session</code>, <code>request</code> and
     * <code>page</code>.
     *
     * @param filterConfig The filter configuration
     */
    public void init(FilterConfig filterConfig) {
        //Get whatever settings we want...
        config = filterConfig;
        admin = ServletCacheAdministrator.getInstance(config.getServletContext());

        //Will work this out later
        try {
            time = Integer.parseInt(config.getInitParameter("time"));
        } catch (Exception e) {
            log.info("Could not get init paramter 'time', defaulting to one hour.");
        }

        try {
            String scopeString = config.getInitParameter("scope");

            if (scopeString.equals("session")) {
                cacheScope = PageContext.SESSION_SCOPE;
            } else if (scopeString.equals("application")) {
                cacheScope = PageContext.APPLICATION_SCOPE;
            } else if (scopeString.equals("request")) {
                cacheScope = PageContext.REQUEST_SCOPE;
            } else if (scopeString.equals("page")) {
                cacheScope = PageContext.PAGE_SCOPE;
            }
        } catch (Exception e) {
            log.info("Could not get init paramter 'scope', defaulting to 'application'");
        }
    }
}
