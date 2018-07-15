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
    
    HttpSession session;
    
    final static Logger logger = Logger.getLogger(Controller.class);
    
    Locale current = Locale.getDefault();    
    Locale lang = new Locale(current.getLanguage(), current.getCountry());
    ResourceBundle res = ResourceBundle.getBundle("text", lang);
      
    @Override
    public void init()
    {
        System.out.println("Initializing some data servlet...");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        System.out.println("Controller processRequest");
        
        System.out.println("HttpSession session");
        session = request.getSession();
        System.out.println("HttpSession session " + session.getId());
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        System.out.println("doGET");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        String action = request.getParameter("action");
        System.out.println(action);
        
        
        if(action == null)
        {
            System.out.println("start.jsp");
            request.getRequestDispatcher("start.jsp").forward(request, response);
        }
        else if("dologin".equals(action))
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            System.out.println("LOGIN " + email + " " + password);
            
            User user = new User(email, password);
            System.out.println(user.validate() + user.getMessage());
            
            session.setAttribute("email", email);
            System.out.println("session.setAttribute -> " + email);
            
            try
            {
                Database db = new Database();
                db.connect();
                
                if(user.validate())
                {
                    if(db.checkLogin(email, password))
                    {
                        System.out.println("cabinet.jsp");
                        request.setAttribute("email", email);
                        request.setAttribute("password", password);
                        request.getRequestDispatcher("cabinet.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("IncorrectPassword", res.getString("IncorrectPassword"));
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
                else
                {
                    request.setAttribute("IncorrectPassword", user.getMessage());
                    System.out.println("login.jsp");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            catch(IOException | ClassNotFoundException | SQLException | ServletException x)
            {
                System.out.println("dologin - user.validate() - db.connect()" + x);
            } 
        }
        else if("doregister".equals(action))
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            System.out.println("REGISTER " + email + " " + password + " " + repassword);
            
            User user = new User(email, password);
            
            if(password != null && repassword != null && !password.equals(repassword))
            {
                request.setAttribute("IncorrectPassword", res.getString("passwordsdonotmatch"));
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            else if(user.validate())
            {
                try 
                {
                    Database db = new Database();
                    db.connect();
                    
                    if(db.check(email))
                    {
                        db.add(email, password);
                        db.disconnect();
                        
                        request.setAttribute("IncorrectPassword", res.getString("Youareregistered"));
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("IncorrectPassword", res.getString("Loginalreadyexists"));
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                } 
                catch (SQLException | ClassNotFoundException ex) 
                {
                    System.out.println("\"doregister\".equals(action)" + ex);
                }
                
            }
            else
            {
                request.setAttribute("IncorrectPassword", user.getMessage());
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
        else if("doRequestApartment".equals(action))
        {
            String leaseTime = request.getParameter("leaseTime");
            String numberRooms = request.getParameter("numberRooms");
            String desiredPrice = request.getParameter("desiredPrice");
            String recommendation = request.getParameter("recommendation");
            String phone = request.getParameter("phone");
            
            System.out.println(leaseTime + " " + numberRooms + " " +  desiredPrice + " " + recommendation + " " + phone);
            
            RequestApartment requestApartment = new RequestApartment(leaseTime, numberRooms, desiredPrice, recommendation, phone);        
            
            if(requestApartment.validate())
            {
                RequestApartmentDAO requestApartmentDAO = new RequestApartmentDAO(requestApartment);
                
                try 
                {
                    requestApartmentDAO.connect();  
                    requestApartmentDAO.add((String) session.getAttribute("email"), "");
                    System.out.println("requestApartmentDAO.add " + (String) session.getAttribute("email"));
                    requestApartmentDAO.disconnect();
                } 
                catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("doRequestApartment - requestApartmentDAO.connect();" + ex);
                }
                
                request.setAttribute("request_accepted", requestApartment.getMessage());
                request.getRequestDispatcher("orders.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("request_accepted", requestApartment.getMessage());
                request.getRequestDispatcher("request_apartment.jsp").forward(request, response);
            }
        }
        else if("gocabinet".equals(action))
        {
            request.setAttribute("order_is_accepted", res.getString("Waitforyourordertobeprocessed"));
            request.getRequestDispatcher("wait_order.jsp").forward(request, response);
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
                request.getRequestDispatcher("wait_order.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex)
            {
                System.out.println(ex);
                request.getRequestDispatcher("wait_order.jsp").forward(request, response);
            }
            
        }
        else if("doadmin".equals(action))
        {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            
            System.out.println("LOGIN ADMIN " + login + " " + password);
            
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
                        request.getRequestDispatcher("ListAndOpen.jsp").forward(request, response);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Exception admin coonnect DB");
                    
                    request.setAttribute("empty_login_or_pass", res.getString("Thisaccountdoesnotexist"));
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
                finally
                {
                    try {
                        controller.getDatabase().disconnect();
                    } catch (SQLException ex) {
                        System.out.println("AdminDao disconnect" + ex);
                    }
                }
            }
            else
            {
                request.setAttribute("empty_login_or_pass", res.getString("Yourpasswordorloginisempty"));
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
        }
        else if("addroom".equals(action))
        {
            String address = request.getParameter("address");
            String numberrooms = request.getParameter("numberrooms");
            String price = request.getParameter("price");
            
            String login = (String) session.getAttribute("login");
            String password = (String) session.getAttribute("password");
            
            if(login != null && password != null)
            {
                Room room = new Room();
                try {
                    room.add(address, numberrooms, price);
                } catch (SQLException | ClassNotFoundException ex) {
                    System.out.println(ex);
                }
                request.getRequestDispatcher("ListAndOpen.jsp").forward(request, response);
            }
            request.getRequestDispatcher("start.jsp").forward(request, response);
        }
        else if("invoice".equals(action))
        {
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            
            String login = (String) session.getAttribute("login");
            String password = (String) session.getAttribute("password");
            
            if(login != null && password != null)
            {
                Room invoiceRoom = new Room();
                try 
                {
                    invoiceRoom.invoice(email, address);
                    request.setAttribute("invoice_info", res.getString("Paymenthasbeensenttothebuyer"));
                    request.getRequestDispatcher("ListAndOpen.jsp").forward(request, response);
                } 
                catch (ClassNotFoundException | SQLException ex) 
                {
                    System.out.println(ex);
                    request.setAttribute("invoice_info", res.getString("Paymentsendingerror"));
                    request.getRequestDispatcher("ListAndOpen.jsp").forward(request, response);
                }
            }
            request.getRequestDispatcher("start.jsp").forward(request, response);
        }
        
        processRequest(request, response);
    }
    
    @Override
    public void destroy() 
    {
        System.out.println("Destroy data servlet....");
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }

}
