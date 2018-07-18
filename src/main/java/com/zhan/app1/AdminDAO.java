package com.zhan.app1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO 
{
    
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/app1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    
    private final String USER = "root";
    private final String PASS = "193728465asd@";
    
    private Connection conn;
    private Statement stmt;
    
    public void connect() throws ClassNotFoundException, SQLException
    {
                
        Class.forName(JDBC_DRIVER);
        
        System.out.println("Connecting to database..."); 
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        
    }
    
    public void add(String login, String price) throws SQLException
    {
        
        String sql = "insert into admins (login, password) " +
        "value (?,?);";
        
        System.out.println("add DB - AdminDAO");
        
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) 
        {
            preStatement.setString(1, price);
            preStatement.setString(2, login);
            preStatement.executeUpdate();
            System.out.println("preStatement.setString - ADMINDAO");
        }
        catch(Exception x)
        {
            System.out.println("try (PreparedStatement preStatement = conn.prepareStatement(sql))" + x);
        }
        
    }
    
    public boolean checkLogin (String login, String password) throws SQLException
    {
        String sql = "select * from admins where login = \"" + login +"\";";
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next())
        {
            String loginDB = rs.getString("login");
            String passwordDB = rs.getString("password");
            System.out.println("loginDB = " + loginDB);
            System.out.println("passwordDB = " + passwordDB);
            
            if(login.equals(loginDB))
            {
                if(password.equals(passwordDB))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void delete(String login) throws SQLException{
        String sql = "DELETE FROM admins WHERE login='" + login +  "';";
        stmt.executeUpdate(sql);
    }
    
    public void disconnect() throws SQLException
    {
        stmt.close();
        conn.close();
        System.out.println("CLOSE disconnect()");
    }
}
