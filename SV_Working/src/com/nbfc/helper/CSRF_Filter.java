package com.nbfc.helper;
import com.google.common.cache.Cache;
import com.raistudies.domain.CustomExceptionHandler;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
public class CSRF_Filter implements Filter{
	private static final Logger LOG = Logger.getLogger(CSRF_Filter.class); 
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
	        HttpServletRequest httpReq = (HttpServletRequest) request;
	        HttpServletResponse httpRes = (HttpServletResponse) response;
	        // Get the salt sent with the request
	        String salt = (String) httpReq.getParameter("csrfPreventionSalt");
	        LOG.info("CSRF Salt Value:"+salt);
	        // Validate that the salt is in the cache
	        @SuppressWarnings("unchecked")
			Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)
	            httpReq.getSession().getAttribute("csrfPreventionSaltCache");
	      
	        if (csrfPreventionSaltCache != null &&
	                salt != null &&
	                csrfPreventionSaltCache.getIfPresent(salt) != null){
	            // If the salt is in the cache, we move on
	        	LOG.info("Correct Salt Value for CSRF");
	            chain.doFilter(request, response);
	        } else {
	        	LOG.info("Incorrect Salt Value for CSRF");
	        	httpRes.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid authentication.");
	        	//ModelAndView mav =  new ModelAndView("redirect:/SVLogin.html");
	            //throw new ModelAndViewDefiningException(mav);	        	
	        }
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }

	    @Override
	    public void destroy() {
	    }

}
