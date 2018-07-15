package com.zhan.app1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestApartmentDAO 
{
    
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/app1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    
    private final String USER = "root";
    private final String PASS = "193728465asd@";
    
    private Connection conn;
    private Statement stmt;
    
    private final RequestApartment requestApartment;

    RequestApartmentDAO(RequestApartment requestApartment) {
        this.requestApartment = requestApartment;
    }
    
    public void connect() throws ClassNotFoundException, SQLException
    {
                
        Class.forName(JDBC_DRIVER);
        
        System.out.println("Connecting to database..."); 
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        
    }
    
    public void add(String email, String price) throws SQLException
    {
        
        String sql = "insert into requestapartment (leaseTime, \n" +
        "numberRooms, desiredPrice, recommendation, phone, price, email) \n" +
        "value (?,?,?,?,?,?,?);";
        
        System.out.println("public void add(String email) - RequestApartmentDAO");
        
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) 
        {
            preStatement.setString(1, requestApartment.getLeaseTime());
            preStatement.setString(2, requestApartment.getNumberRooms());
            preStatement.setString(3, requestApartment.getDesiredPrice());
            preStatement.setString(4, requestApartment.getRecommendation());
            preStatement.setString(5, requestApartment.getPhone());
            preStatement.setString(6, price);
            preStatement.setString(7, email);
            preStatement.executeUpdate();
            System.out.println("preStatement.setString");
        }
        catch(Exception x)
        {
            System.out.println("try (PreparedStatement preStatement = conn.prepareStatement(sql))" + x);
        }
        
    }
    
    public void delete(String email) throws SQLException{
        String sql = "DELETE FROM requestapartment WHERE email='" + email +  "';";
        stmt.executeUpdate(sql);
    }
    
    public void disconnect() throws SQLException
    {
        stmt.close();
        conn.close();
        System.out.println("CLOSE disconnect()");
    }
    
}
