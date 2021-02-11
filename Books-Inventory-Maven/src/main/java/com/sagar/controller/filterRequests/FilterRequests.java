package com.sagar.controller.filterRequests;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class FilterRequests
 */

@WebFilter("/")
public class FilterRequests implements Filter {
	
	

    /**
     * Default constructor. 
     */
    public FilterRequests() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		Logger logger = Logger.getLogger(FilterRequests.class);
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		RequestDispatcher rd=null;
		
		if (session.getAttribute("email") == null) {
			logger.info("=================not logged in ======================");
			rd=req.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp");
			rd.forward(req, resp);
		   // resp.sendRedirect(req.getContextPath() + "/login"); // Not logged in, redirect to login page.
		} else {
			
			logger.info("================= logged in ======================");
			
		    chain.doFilter(request, response); // Logged in, just continue chain.
		}			
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}