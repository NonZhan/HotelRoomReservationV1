package com.zhan.app1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Room {
    
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/app1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    
    private final String USER = "root";
    private final String PASS = "193728465asd@";
    
    private Connection conn;
    private Statement stmt;
        
    private String address;
    private String numberrooms;
    private String price;

    public String getAddress() {
        return address;
    }

    public String getNumberrooms() {
        return numberrooms;
    }

    public String getPrice() {
        return price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumberrooms(String numberrooms) {
        this.numberrooms = numberrooms;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public void add(String address, String num, String price) throws SQLException, ClassNotFoundException
    {
        
        Class.forName(JDBC_DRIVER);
        
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        stmt = conn.createStatement();

        String sql = "insert into openrooms (address, numberrooms, price)"
                + " value (?,?,?);";
        
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) 
        {
            preStatement.setString(1, address);
            preStatement.setString(2, num);
            preStatement.setString(3, price);
            preStatement.executeUpdate();
        }
        catch(Exception x)
        {
            System.out.println(x);
        }
        stmt.close();
        conn.close();
    }
    
    public void invoice(String email, String address) throws ClassNotFoundException, SQLException
    {
        
        Class.forName(JDBC_DRIVER);
        
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        stmt = conn.createStatement();
        
        String sql = "update openrooms set email='" + email + "' where address = '" + address + "';";
        
        stmt.executeUpdate(sql);
        
        String sqlDelete = "DELETE FROM requestapartment WHERE email='" + email + "';";
        
        stmt.executeUpdate(sqlDelete);
        
        stmt.close();
        conn.close();
    }
    
    public void viewInvoice(String email) throws ClassNotFoundException, SQLException{
        
        Class.forName(JDBC_DRIVER);
        
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        stmt = conn.createStatement();
        
        String sql = "SELECT * FROM app1.openrooms where email = '" + email + "';";
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next())
        {
            setAddress(rs.getString("address"));
            setNumberrooms(rs.getString("numberrooms"));
            setPrice(rs.getString("price"));
        }
        
        stmt.close();
        conn.close();

    }
    
}
