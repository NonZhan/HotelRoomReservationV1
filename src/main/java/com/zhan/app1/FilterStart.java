package com.zhan.app1;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterStart implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException 
    {
        System.out.println("Initializing FilterStart...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        System.out.println("FilterStart ...");
        
        String action = req.getParameter("action");
        
        if(action == null)
        {
            request.getRequestDispatcher("start.jsp").forward(request, response);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() 
    {
        System.out.println("Destroy FilterStart...");
    }
    
}
