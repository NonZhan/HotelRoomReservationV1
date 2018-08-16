

package Filters.ADMIN;

import DAO.AdminDAO;
import JSP.JSP;
import Manager.AdminManager;
import Model.Admin;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
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


public class FilterAdmin implements Filter
{
    private final static Logger log = Logger.getLogger(FilterAdmin.class);
    
    private HttpSession session;
    
    private final Locale current = Locale.getDefault();    
    private final Locale lang = new Locale(current.getLanguage(), current.getCountry());
    private final ResourceBundle res = ResourceBundle.getBundle("text", lang);

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
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
            
        session = req.getSession(false);
        
        session.setAttribute("login", login);
        session.setAttribute("password", password);
        
        log.info("LOGIN ADMIN " + login + " " + password);
        
        Admin model = new Admin();
        AdminDAO dao = new AdminDAO();
        AdminManager manager = new AdminManager(model, dao);
        
        manager.getModel().setLogin(login);
        manager.getModel().setPassword(password);
        
        
        if(manager.validation())
        {
            if(manager.getDao().checkLogin(login, password))
            {
                request.getRequestDispatcher(JSP.ListAndOpen).forward(request, response);
            }
            else
            {
                request.setAttribute("empty_login_or_pass", res.getString("Thisaccountdoesnotexist"));
                request.getRequestDispatcher(JSP.admin).forward(request, response);
            }
        }
        else
        {
            request.setAttribute("empty_login_or_pass", res.getString("Thisaccountdoesnotexist"));
            request.getRequestDispatcher(JSP.admin).forward(request, response);
        }
 
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy()
    {
        //
    }

}