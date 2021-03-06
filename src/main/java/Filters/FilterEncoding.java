

package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class FilterEncoding implements Filter
{
    private final String encoding = "UTF-8";
    private final String contentType = "text/html; charset=UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {        
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        request.setCharacterEncoding(encoding);
        
        response.setCharacterEncoding(encoding);
        
        response.setContentType(contentType);
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy()
    {
        //
    }
    
}