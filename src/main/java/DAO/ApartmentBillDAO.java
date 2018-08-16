

package DAO;

import Connection.Database;
import Filters.FilterApartmentBill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


public class ApartmentBillDAO
{
    private final static Logger log = Logger.getLogger(FilterApartmentBill.class);
    
    private String address;
    private String numberrooms;
    private String price;
    
    Connection connection;
        
    public void add(String address, String num, String price) throws SQLException
    {
        String sql = "insert into openrooms (address, numberrooms, price)"
                + " value (?,?,?);";
        
            connection = Database.getConnection();
            
            try (PreparedStatement preStatement = connection.prepareStatement(sql)) 
            {
                preStatement.setString(1, address);
                preStatement.setString(2, num);
                preStatement.setString(3, price);
                preStatement.executeUpdate();
            }
            catch(Exception x)
            {
                log.info(x);
            }
    }
    
    public void invoice(String email, String address) throws SQLException
    {
        String sql = "update openrooms set email='" + email + "' where address = '" + address + "';";
        
        String sqlDelete = "DELETE FROM requestapartment WHERE email='" + email + "';";
        
        
        connection = Database.getConnection();

        try (PreparedStatement preStatement = connection.prepareStatement(sql))
        {
            preStatement.executeUpdate(sql);
        }

        try (PreparedStatement preStatement = connection.prepareStatement(sql))
        {
            preStatement.executeUpdate(sqlDelete);
        }
    }
    
    public void viewInvoice(String email)
    {
        String sql = "SELECT * FROM app1.openrooms where email = '" + email + "';";
        
        try
        {
            connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next())
            {
                setAddress(rs.getString("address"));
                setNumberrooms(rs.getString("numberrooms"));
                setPrice(rs.getString("price"));
            }
        }
        catch(SQLException x)
        {
            log.error(x);
        }
    }
    
    public void delete(String address) throws SQLException
    {
        String sql = "delete from app1.openrooms where address = \"" + address + "\";";
        
        connection = Database.getConnection();
        
        try (PreparedStatement preStatement = connection.prepareStatement(sql))
        {
            preStatement.executeUpdate(sql);
        }
        

    }

    // model
    public String getAddress()
    {
        return address;
    }

    public String getNumberrooms()
    {
        return numberrooms;
    }

    public String getPrice()
    {
        return price;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setNumberrooms(String numberrooms)
    {
        this.numberrooms = numberrooms;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
}