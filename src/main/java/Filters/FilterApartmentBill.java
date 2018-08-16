

package Filters;

import DAO.ApartmentBillDAO;
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


public class FilterApartmentBill implements Filter
{    
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
        
        session = req.getSession(false);
        
        String email  = (String) session.getAttribute("email");
        
        ApartmentBillDAO dao = new ApartmentBillDAO();
        
        dao.viewInvoice(email);
        request.setAttribute("address", dao.getAddress());
        request.setAttribute("numberrooms", dao.getNumberrooms());
        request.setAttribute("price", dao.getPrice());
        request.getRequestDispatcher(JSP.wait_order).forward(request, response);
        
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy()
    {
        //
    }
    
}