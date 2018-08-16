

package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import JSP.JSP;


public class FilterLinks implements Filter
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
        
        String link = req.getParameter("link");
        
        if("doLoginJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.login).forward(req, response);
        }
        else if("doRegisterJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.register).forward(req, response);
        }
        else if("doStartJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.start).forward(req, response);
        }
        else if("doWait_orderJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.wait_order).forward(req, response);
        }
        else if("doRequest_apartmentJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.request_apartment).forward(req, response);
        }
        else if("doCabinetJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.cabinet).forward(req, response);
        }
        else if("doAdminJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.admin).forward(req, response);
        }
        else if("doAddopenroomJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.addopenroom).forward(req, response);
        }
        else if("doListAndOpen".equals(link))
        {
            req.getRequestDispatcher(JSP.ListAndOpen).forward(req, response);
        }
        else if("doDeleteRoomJSP".equals(link))
        {
            req.getRequestDispatcher(JSP.deleteopenroom).forward(req, response);
        }
        
        chain.doFilter(req, response);
    }

    @Override
    public void destroy()
    {
        //
    }

}