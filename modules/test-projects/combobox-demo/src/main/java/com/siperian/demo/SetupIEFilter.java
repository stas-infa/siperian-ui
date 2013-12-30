//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Informatica Inc. (c) 2003-2011.  All rights reserved.
//=====================================================================

package com.siperian.demo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexander Chigrinets
 * @since 27.01.12
 */
public class SetupIEFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // We don;t need special initialization
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setContentType("text/html; charset=UTF-8");
        // We force browsers not to cache pages so that user couldn't request visited page pressing BACK button without
        // sending reqeust to server.
        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        //We force all pages to be rendered by IE8 in IE7 compatibility mode.
        // This header will be ignored by other browsers.
        response.setHeader("X-UA-Compatible", "IE=7");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        // we do not need extra actions here
    }
}
