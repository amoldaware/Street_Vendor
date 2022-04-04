package com.nbfc.helper;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebFilter(urlPatterns = {"/*"},description = "X-Frame-Fliter")
public class XFrameFilter implements Filter {
	private static final Logger logger= Logger.getLogger(XFrameFilter.class);
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		String sessionid = ((HttpServletRequest) req).getSession().getId();
		//logger.info("Filter Class is Called....");
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
        response.setHeader("X-XSS-Protection", "1; mode=block");
        response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains"); 
        response.setHeader("X-Content-Type-Options", "nosniff"); 
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", 0);
    	response.setHeader ("X-Powered-By", "****");
    	response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; HttpOnly");
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) req), resp);
        //logger.info("Exit from XFrameFilter");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
