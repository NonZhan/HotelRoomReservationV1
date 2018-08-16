

package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import JSP.JSP;


public class FilterStartPage implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String action = req.getParameter(JSP.action);
        
        if(action == null)
        {
            req.getRequestDispatcher(JSP.start).forward(req, res);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy()
    {
        //
    }
    
}