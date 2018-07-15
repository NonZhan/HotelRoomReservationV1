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

public class ListOpenRooms extends BodyTagSupport{
    
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/app1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    
    private final String USER = "root";
    private final String PASS = "193728465asd@";
    
    private Connection conn;
    private Statement stmt;
    
    private final String Enter = "<br><br>";
        
    private void otherDoStartTagOperations() throws JspException, SQLException, ClassNotFoundException {
           try {
               JspWriter out = pageContext.getOut();
               
               Class.forName(JDBC_DRIVER);
        
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();

            String sql = "select * from openrooms;";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                String address = rs.getString("address");
                String numberrooms = rs.getString("numberrooms");
                String price = rs.getString("price");
                String email = rs.getString("email");

                out.println("Address = " + address + Enter);
                out.println("Number Rooms = " + numberrooms + Enter);
                out.println("Price = " + price + Enter);
                out.println("Email = " + email + Enter);
                
                out.println("====================");
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
