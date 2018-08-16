

package Filters;

import DAO.ApartmentDAO;
import JSP.JSP;
import Model.Apartment;
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


public class FilterRequestApartment implements Filter
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
        
        String leaseTime = request.getParameter("leaseTime");
        String numberRooms = request.getParameter("numberRooms");
        String desiredPrice = request.getParameter("desiredPrice");
        String recommendation = request.getParameter("recommendation");
        String phone = request.getParameter("phone");
        
        Apartment apartment = new Apartment(leaseTime, numberRooms, desiredPrice, recommendation, phone);
        ApartmentDAO apartmentDAO = new ApartmentDAO(apartment);
        
        
        session = req.getSession(false);
        
        if(apartment.validate())
        {
            apartmentDAO.add((String) session.getAttribute("email"), "");
            request.setAttribute("request_accepted", apartment.getMessage());
            request.getRequestDispatcher(JSP.orders).forward(request, response);
        }
        else
        {
            request.setAttribute("request_accepted", apartment.getMessage());
            request.getRequestDispatcher(JSP.request_apartment).forward(request, response);
        }
        
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy()
    {
        //
    }
    
}