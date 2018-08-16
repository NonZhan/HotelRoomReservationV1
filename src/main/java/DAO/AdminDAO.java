

package DAO;

import Connection.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


public class AdminDAO
{
    private final static Logger log = Logger.getLogger(AdminDAO.class);
    
    public void add(String login, String price)
    {
        
        String sql = "insert into admins (login, password) " +
        "value (?,?);";
        
        try
        {
            Connection connection = Database.getConnection();

            try (PreparedStatement preStatement = connection.prepareStatement(sql)) 
            {
                preStatement.setString(1, price);
                preStatement.setString(2, login);
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
    
    public boolean checkLogin (String login, String password)
    {
        String sql = "select * from admins where login = \"" + login +"\";";
        
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next())
            {
                String loginDB = rs.getString("login");
                String passwordDB = rs.getString("password");

                if(login.equals(loginDB))
                {
                    if(password.equals(passwordDB))
                    {
                        return true;
                    }
                }
            }
        }
        catch(SQLException x)
        {
            log.error(x);
        }
        
        return false;
    }
    
    public void delete(String login)
    {
        String sql = "DELETE FROM admins WHERE login='" + login +  "';";
        
        try
        {
            Connection connection = Database.getConnection();
            
            try (PreparedStatement preStatement = connection.prepareStatement(sql))
            {
                preStatement.executeUpdate(sql);
            }
            
        }
        catch (SQLException x)
        {
            log.error(x);
        } 
    }
}