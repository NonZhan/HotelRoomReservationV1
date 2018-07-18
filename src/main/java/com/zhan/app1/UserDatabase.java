package com.zhan.app1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDatabase 
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
    
    public boolean check(String email) throws SQLException
    {
        String sql = "select * from client where email = \"" + email +"\";";
        ResultSet rs = stmt.executeQuery(sql);
        
        if(rs.next() == false)
        {
            return true;
        }
        
        return false;
    }
    
    public void add(String email, String pass) throws SQLException
    {
        String sql = "insert into client (email, pass) value(?, ?);";
        
        System.out.println("public void add String email, String pass");
        
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) 
        {
            preStatement.setString(1, email);
            preStatement.setString(2, pass);
            preStatement.executeUpdate();
            System.out.println("preStatement.setString");
        }
        
    }
    
    public boolean checkLogin (String email, String pass) throws SQLException
    {
        String sql = "select * from client where email = \"" + email +"\";";
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next())
        {
            String emailDB = rs.getString("email");
            String passDB = rs.getString("pass");
            System.out.println("emailDB = " + emailDB);
            System.out.println("passDB = " + passDB);
            
            if(email.equals(emailDB))
            {
                if(pass.equals(passDB))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void disconnect() throws SQLException
    {
        stmt.close();
        conn.close();
        System.out.println("CLOSE disconnect()");
    }
}
