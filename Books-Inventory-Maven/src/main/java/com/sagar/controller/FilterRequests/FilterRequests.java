package com.sagar.controller.FilterRequests;
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
		/*
		 * System.err.
		 * println("==========================Filter Method Invoked============================"
		 * ); HttpServletRequest req=(HttpServletRequest)request; HttpServletResponse
		 * resp=(HttpServletResponse)response; BookServlet obj=new BookServlet();
		 * boolean status=obj.loginValidator(req,resp);
		 * 
		 * if(status) { // pass the request along the filter chain
		 * System.out.println("Servlet Path in Filter is================"+req.
		 * getContextPath()); RequestDispatcher
		 * rd=req.getRequestDispatcher(req.getContextPath()+"/list"); rd.forward(req,
		 * resp); }else { RequestDispatcher
		 * rd=request.getRequestDispatcher("userLogin.jsp"); rd.forward(req, resp); }
		 */
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession();
		RequestDispatcher rd=null;
		
		if (session.getAttribute("email") == null) {
			System.out.println("=================not logged in ======================");
			rd=req.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp");
			rd.forward(req, resp);
		   // resp.sendRedirect(req.getContextPath() + "/login"); // Not logged in, redirect to login page.
		} else {
			
			System.out.println("================= logged in ======================");
			
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