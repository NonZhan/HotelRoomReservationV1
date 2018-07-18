package com.zhan.app1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterEncoding implements Filter  
{
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException 
    {
        System.out.println("Initializing FilterEncoding...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        System.out.println("Check charset FilterEncoding...");
        System.out.println("");
        
        String encodingReq = req.getCharacterEncoding();
        String encodingRes = res.getCharacterEncoding();
        
        System.out.println("encoding " + encodingReq + " " + encodingRes);
        
        if(!StandardCharsets.UTF_8.name().equals(encodingReq))
        {
            req.setCharacterEncoding(StandardCharsets.UTF_8.name());
            System.out.println("req.setCharacterEncoding(StandardCharsets.UTF_8.name())");
        }
        if(!StandardCharsets.UTF_8.name().equals(encodingRes))
        {
            res.setCharacterEncoding(StandardCharsets.UTF_8.name());
            System.out.println("res.setCharacterEncoding(StandardCharsets.UTF_8.name())");
        }
        
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() 
    {
        System.out.println("Destroy FilterEncoding....");
    }
    
}
