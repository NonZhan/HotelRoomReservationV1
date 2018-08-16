

package Filters.ADMIN;

import JSP.JSP;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


public class FilterLoginAdmin implements Filter
{
    private final static Logger log = Logger.getLogger(FilterAdmin.class);
    
    private HttpSession session;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        session = req.getSession();
        log.info(session.getId());
        
        request.getRequestDispatcher(JSP.admin).forward(request, response);
        
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy()
    {
        //
    }

}