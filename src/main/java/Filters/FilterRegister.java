

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


public class FilterRegister implements Filter
{
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
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        
        String action = request.getParameter("action");
        
        User model = new User();
        UserDAO database = new UserDAO();
        UserManager user = new UserManager(model, database);
        
        user.getUserModel().setEmail(email);
        user.getUserModel().setPassword(password);
        
        if("doregister".equals(action))
        {
            if(password != null && repassword != null && !password.equals(repassword))
            {
                request.setAttribute("IncorrectPassword", res.getString("passwordsdonotmatch"));
                request.getRequestDispatcher(JSP.register).forward(request, response);
            }
            else if(user.getUserModel().validate())
            {
                if(user.getUserDAO().check(email))
                {
                    user.getUserDAO().add(email, password);
                    
                    request.setAttribute("IncorrectPassword", res.getString("Youareregistered"));
                    request.getRequestDispatcher(JSP.register).forward(request, response);
                }
                else
                {
                    request.setAttribute("IncorrectPassword", res.getString("Loginalreadyexists"));
                    request.getRequestDispatcher(JSP.register).forward(request, response);
                }
            }
            else
            {
                request.setAttribute("IncorrectPassword", user.getUserModel().getMessage());
                request.getRequestDispatcher(JSP.register).forward(request, response);
            }
        }
        
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy()
    {
        //
    }

}