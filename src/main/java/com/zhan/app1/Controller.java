package com.zhan.app1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class Controller extends HttpServlet 
{
    
    private HttpSession session;
    
    private final static Logger log = Logger.getLogger(Controller.class);
    
    private final Locale current = Locale.getDefault();    
    private final Locale lang = new Locale(current.getLanguage(), current.getCountry());
    private final ResourceBundle res = ResourceBundle.getBundle("text", lang);
    
    private final String startJsp = "start.jsp";
    private final String loginJsp = "login.jsp";
    private final String cabinetJsp = "cabinet.jsp";
    private final String registerJsp = "register.jsp";
    private final String ordersJsp = "orders.jsp";
    private final String ListAndOpenJsp = "ListAndOpen.jsp";
    private final String adminJsp = "admin.jsp";
    private final String waitOrderJsp = "wait_order.jsp";
    private final String requestApartmentJsp = "request_apartment.jsp";
    
      
    @Override
    public void init()
    {
        log.info("Initializing some data servlet...");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        log.info("Controller processRequest");
        
        log.info("HttpSession session");
        session = request.getSession();
        log.info("HttpSession session " + session.getId());
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        log.info("doGET");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        
        String action = request.getParameter("action");
        log.info(action);
        
        
        if(action == null)
        {
            log.info(startJsp);
            request.getRequestDispatcher(startJsp).forward(request, response);
        }
        else if("dologin".equals(action))
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            log.info("LOGIN " + email + " " + password);
            
            User user = new User(email, password);
            log.info(user.validate() + user.getMessage());
            
            session.setAttribute("email", email);
            log.info("session.setAttribute -> " + email);
            
            try
            {
                UserDatabase db = new UserDatabase();
                db.connect();
                
                if(user.validate())
                {
                    if(db.checkLogin(email, password))
                    {
                        log.info(cabinetJsp);
                        request.setAttribute("email", email);
                        request.setAttribute("password", password);
                        request.getRequestDispatcher(cabinetJsp).forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("IncorrectPassword", res.getString("IncorrectPassword"));
                        request.getRequestDispatcher(loginJsp).forward(request, response);
                    }
                }
                else
                {
                    request.setAttribute("IncorrectPassword", user.getMessage());
                    log.info(loginJsp);
                    request.getRequestDispatcher(loginJsp).forward(request, response);
                }
            }
            catch(IOException | ClassNotFoundException | SQLException | ServletException x)
            {
                log.info("dologin - user.validate() - db.connect()" + x);
            } 
        }
        else if("doregister".equals(action))
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            log.info("REGISTER " + email + " " + password + " " + repassword);
            
            User user = new User(email, password);
            
            if(password != null && repassword != null && !password.equals(repassword))
            {
                request.setAttribute("IncorrectPassword", res.getString("passwordsdonotmatch"));
                request.getRequestDispatcher(registerJsp).forward(request, response);
            }
            else if(user.validate())
            {
                try 
                {
                    UserDatabase db = new UserDatabase();
                    db.connect();
                    
                    if(db.check(email))
                    {
                        db.add(email, password);
                        db.disconnect();
                        
                        request.setAttribute("IncorrectPassword", res.getString("Youareregistered"));
                        request.getRequestDispatcher(registerJsp).forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("IncorrectPassword", res.getString("Loginalreadyexists"));
                        request.getRequestDispatcher(registerJsp).forward(request, response);
                    }
                } 
                catch (SQLException | ClassNotFoundException ex) 
                {
                    log.info("\"doregister\".equals(action)" + ex);
                }
                
            }
            else
            {
                request.setAttribute("IncorrectPassword", user.getMessage());
                request.getRequestDispatcher(registerJsp).forward(request, response);
            }
        }
        else if("doRequestApartment".equals(action))
        {
            String leaseTime = request.getParameter("leaseTime");
            String numberRooms = request.getParameter("numberRooms");
            String desiredPrice = request.getParameter("desiredPrice");
            String recommendation = request.getParameter("recommendation");
            String phone = request.getParameter("phone");
            
            log.info(leaseTime + " " + numberRooms + " " +  desiredPrice + " " + recommendation + " " + phone);
            
            RequestApartment requestApartment = new RequestApartment(leaseTime, numberRooms, desiredPrice, recommendation, phone);        
            
            if(requestApartment.validate())
            {
                RequestApartmentDAO requestApartmentDAO = new RequestApartmentDAO(requestApartment);
                
                try 
                {
                    requestApartmentDAO.connect();  
                    requestApartmentDAO.add((String) session.getAttribute("email"), "");
                    log.info("requestApartmentDAO.add " + (String) session.getAttribute("email"));
                    requestApartmentDAO.disconnect();
                } 
                catch (ClassNotFoundException | SQLException ex) {
                    log.info("doRequestApartment - requestApartmentDAO.connect();" + ex);
                }
                
                request.setAttribute("request_accepted", requestApartment.getMessage());
                request.getRequestDispatcher(ordersJsp).forward(request, response);
            }
            else
            {
                request.setAttribute("request_accepted", requestApartment.getMessage());
                request.getRequestDispatcher(requestApartmentJsp).forward(request, response);
            }
        }
        else if("gocabinet".equals(action))
        {
            request.setAttribute("order_is_accepted", res.getString("Waitforyourordertobeprocessed"));
            request.getRequestDispatcher(waitOrderJsp).forward(request, response);
        }
        else if("gocalculate".equals(action))
        {   
            String email  = (String) session.getAttribute("email");
            
            Room room = new Room();
            
            try
            {
                room.viewInvoice(email);
                request.setAttribute("address", room.getAddress());
                request.setAttribute("numberrooms", room.getNumberrooms());
                request.setAttribute("price", room.getPrice());
                request.getRequestDispatcher(waitOrderJsp).forward(request, response);
            } catch (ClassNotFoundException | SQLException ex)
            {
                log.info(ex);
                request.getRequestDispatcher(waitOrderJsp).forward(request, response);
            }
            
        }
        else if("doadmin".equals(action))
        {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            
            log.info("LOGIN ADMIN " + login + " " + password);
            
            Admin model = new Admin(); 
            AdminView view = new AdminView();
            AdminDAO database = new AdminDAO();
            
            AdminController controller = new AdminController(model, view, database);
            
            controller.getModel().setLogin(login);
            controller.getModel().setPassword(password);
            
            if(controller.validation())
            {
                try {
                    controller.getDatabase().connect();
                    if(controller.getDatabase().checkLogin(login, password))
                    {
                        request.getRequestDispatcher(ListAndOpenJsp).forward(request, response);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    log.info("Exception admin coonnect DB");
                    
                    request.setAttribute("empty_login_or_pass", res.getString("Thisaccountdoesnotexist"));
                    request.getRequestDispatcher(adminJsp).forward(request, response);
                }
                finally
                {
                    try {
                        controller.getDatabase().disconnect();
                    } catch (SQLException ex) {
                        log.info("AdminDao disconnect" + ex);
                    }
                }
            }
            else
            {
                request.setAttribute("empty_login_or_pass", res.getString("Yourpasswordorloginisempty"));
                request.getRequestDispatcher(adminJsp).forward(request, response);
            }
        }
        else if("addroom".equals(action))
        {
            String address = request.getParameter("address");
            String numberrooms = request.getParameter("numberrooms");
            String price = request.getParameter("price");
            
            String login = (String) session.getAttribute("login");
            String password = (String) session.getAttribute("password");
            
            log.info(login + " " + password + " session");
            
            if(login != null && password != null)
            {
                Room room = new Room();
                try {
                    room.add(address, numberrooms, price);
                } catch (SQLException | ClassNotFoundException ex) {
                    log.info(ex);
                }
                request.getRequestDispatcher(ListAndOpenJsp).forward(request, response);
            }
            request.getRequestDispatcher(startJsp).forward(request, response);
        }
        else if("invoice".equals(action))
        {
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            
            String login = (String) session.getAttribute("login");
            String password = (String) session.getAttribute("password");
            
            log.info(login + " " + password + " session");
            
            if(login != null && password != null)
            {
                Room invoiceRoom = new Room();
                try 
                {
                    invoiceRoom.invoice(email, address);
                    request.setAttribute("invoice_info", res.getString("Paymenthasbeensenttothebuyer"));
                    request.getRequestDispatcher(ListAndOpenJsp).forward(request, response);
                } 
                catch (ClassNotFoundException | SQLException ex) 
                {
                    log.info(ex);
                    request.setAttribute("invoice_info", res.getString("Paymentsendingerror"));
                    request.getRequestDispatcher(ListAndOpenJsp).forward(request, response);
                }
            }
            request.getRequestDispatcher(startJsp).forward(request, response);
        }

        processRequest(request, response);
    }
    
    @Override
    public void destroy() 
    {
        log.info("Destroy data servlet....");
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }

}
