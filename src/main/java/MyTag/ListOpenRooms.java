

package MyTag;

import Connection.Database;
import Filters.FilterApartmentBill;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import org.apache.log4j.Logger;


public class ListOpenRooms extends BodyTagSupport
{
    private final String Enter = "<br><br>";
    private final String sql = "select * from openrooms;";
    private final static Logger log = Logger.getLogger(ListOpenRooms.class);
    Connection connection;
    
    private void otherDoStartTagOperations() throws JspException, SQLException, ClassNotFoundException
    {
       try
       {
           JspWriter out = pageContext.getOut();

           connection = Database.getConnection();
           PreparedStatement statement = connection.prepareStatement(sql);
           ResultSet rs = statement.executeQuery(sql);

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
                out.println(Enter);
            }

       }
       catch (IOException x)
       {
           log.error(x);
       }
    }
    
    @Override
    public int doStartTag() throws JspException
    {
        try
        {
            otherDoStartTagOperations();
        }
        catch (SQLException | ClassNotFoundException x)
        {
            log.info(x);
        }
        
        if (theBodyShouldBeEvaluated())
        {
            return EVAL_BODY_BUFFERED;
        }
        else
        {
            return SKIP_BODY;
        }
    }
    
    private boolean theBodyShouldBeEvaluated()
    {
        return true;
    }
}