

package DAO;

import Connection.Database;
import Model.Apartment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;


public class ApartmentDAO
{
    private final Apartment apartment;
    private final static Logger log = Logger.getLogger(ApartmentDAO.class);
    
    public ApartmentDAO(Apartment apartment)
    {
        this.apartment = apartment;
    }
    
    public void add(String email, String price)
    {
        
        String sql = "insert into requestapartment (leaseTime, \n" +
        "numberRooms, desiredPrice, recommendation, phone, price, email) \n" +
        "value (?,?,?,?,?,?,?);";
        
        try
        {
            Connection connection = Database.getConnection();
            
            try (PreparedStatement preStatement = connection.prepareStatement(sql)) 
            {
                preStatement.setString(1, apartment.getLeaseTime());
                preStatement.setString(2, apartment.getNumberRooms());
                preStatement.setString(3, apartment.getDesiredPrice());
                preStatement.setString(4, apartment.getRecommendation());
                preStatement.setString(5, apartment.getPhone());
                preStatement.setString(6, price);
                preStatement.setString(7, email);
                preStatement.executeUpdate();
            }
            catch(Exception x)
            {
                log.error(x);
            } 
            
        }
        catch(SQLException x)
        {
            log.error(x);
        }
    }
    
    public void delete(String email)
    {
        String sql = "DELETE FROM requestapartment WHERE email='" + email +  "';";
        
        try
        {
            Connection connection = Database.getConnection();
            try (PreparedStatement preStatement = connection.prepareStatement(sql))
            {
                preStatement.executeUpdate(sql);
            }
        }
        catch(SQLException x)
        {
            log.error(x);
        }
    }
}