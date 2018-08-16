

package Filters.ADMIN;

import DAO.ApartmentBillDAO;
import JSP.JSP;
import java.io.IOException;
import java.sql.SQLException;
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


public class FilterInvoice implements Filter
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
        
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        session = req.getSession(false);
        
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
        
        if(login != null && password != null)
        {
            ApartmentBillDAO dao = new ApartmentBillDAO();
            
            try
            {
                dao.invoice(email, address);
                request.setAttribute("invoice_info", res.getString("Paymenthasbeensenttothebuyer"));
                request.getRequestDispatcher(JSP.ListAndOpen).forward(request, response);
            }
            catch(IOException | SQLException | ServletException x)
            {
                log.error(x);
                request.setAttribute("invoice_info", res.getString("Paymentsendingerror"));
                request.getRequestDispatcher(JSP.ListAndOpen).forward(request, response);
            }
        }
        else
        {
            request.getRequestDispatcher(JSP.start).forward(request, response);
        }
        
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy()
    {
        //
    }

}