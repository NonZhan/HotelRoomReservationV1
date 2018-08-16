

package Filters;

import Manager.UserManager;
import DAO.UserDAO;
import Model.User;
import JSP.JSP;
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

public class FilterLogin implements Filter
{
    private final static Logger log = Logger.getLogger(FilterLogin.class);
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
        
        session = req.getSession();
        log.info("HttpSession session " + session.getId());
        
        String action = request.getParameter("action");
        
        if("dologin".equals(action))
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            
            User model = new User();
            UserDAO database = new UserDAO();
            UserManager user = new UserManager(model, database);
            
            user.getUserModel().setEmail(email);
            user.getUserModel().setPassword(password);
            
            if(user.getUserModel().validate())
            {
                if(user.getUserDAO().checkLogin(email, password))
                {
                    request.setAttribute("email", email);
                    request.setAttribute("password", password);
                    request.getRequestDispatcher(JSP.cabinet).forward(request, response);
                }
                else
                {
                    request.setAttribute("IncorrectPassword", res.getString("IncorrectPassword"));
                    request.getRequestDispatcher(JSP.login).forward(request, response);
                }
            }
            else
            {
                request.setAttribute("IncorrectPassword", user.getUserModel().getMessage());
                request.getRequestDispatcher(JSP.login).forward(request, response);
            }
            
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy()
    {
        //
    }

}