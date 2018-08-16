

package DAO;

import Connection.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


public class UserDAO
{
    private final static Logger log = Logger.getLogger(UserDAO.class);
    
    public boolean check(String email)
    {
        String sql = "select * from client where email = \"" + email +"\";";
        
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if(rs.next() == false)
            {
                return true;
            }
        }
        catch(SQLException x)
        {
            log.error(x);
        }
        
        return false;
    }
    
    public void add(String email, String pass)
    {
        String sql = "insert into client (email, pass) value(?, ?);";
        
        try
        {      
            Connection conn = Database.getConnection();

            try (PreparedStatement preStatement = conn.prepareStatement(sql)) 
            {
                preStatement.setString(1, email);
                preStatement.setString(2, pass);
                preStatement.executeUpdate();
                System.out.println("preStatement.setString");
            }
        }
        catch(SQLException x)
        {
            log.error(x);
        }
    }
    
    public boolean checkLogin (String email, String pass)
    {
        String sql = "select * from client where email = \"" + email +"\";";
        
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next())
            {
                String emailDB = rs.getString("email");
                String passDB = rs.getString("pass");

                if(email.equals(emailDB))
                {
                    if(pass.equals(passDB))
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
}