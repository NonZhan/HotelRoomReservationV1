package com.zhan.app1.tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

public class ListReqRooms extends BodyTagSupport{
    
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/app1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    
    private final String USER = "root";
    private final String PASS = "193728465asd@";
    
    private Connection conn;
    private Statement stmt;
    
    private final String Enter = "<br><br>";
    
    private final String Ho = "<h2>";
    private final String Hc = "</h2>";
        
    private void otherDoStartTagOperations() throws JspException, SQLException, ClassNotFoundException {
           try {
               JspWriter out = pageContext.getOut();
               
               Class.forName(JDBC_DRIVER);
        
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();

            String sql = "select * from requestapartment;";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                String idrequestapartment = rs.getString("idrequestapartment");
                String leaseTime = rs.getString("leaseTime");
                String numberRooms = rs.getString("numberRooms");
                String desiredPrice = rs.getString("desiredPrice");
                String recommendation = rs.getString("recommendation");
                String phone = rs.getString("phone");
                String price = rs.getString("price");
                String email = rs.getString("email");

                out.println(Ho + "ID = " + idrequestapartment + Enter);
                out.println("Lease Time = " + leaseTime + Enter);
                out.println("Number Rooms = " + numberRooms + Enter);
                out.println("Desired Price = " + desiredPrice + Enter);
                out.println("Recommendation = " + recommendation + Enter);
                out.println("Phone = " + phone + Enter);
                out.println("Price = " + price + Enter);
                out.println("EMAIL = " + email + Enter + Hc);
                
            }
        
            stmt.close();
            conn.close();
               
           } catch (IOException ex) {
               System.out.println("ListReqRooms otherDoStartTagOperations()" + ex);
           }
    }
    
    @Override
    public int doStartTag() throws JspException {
        try {
            otherDoStartTagOperations();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("ListReqRooms -> doStartTag() !!!" + ex);
        }
        
        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }
    
    private boolean theBodyShouldBeEvaluated() {
        return true;
    }
    
}
