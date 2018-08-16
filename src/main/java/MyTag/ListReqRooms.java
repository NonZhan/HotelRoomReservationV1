

package MyTag;

import Connection.Database;
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


public class ListReqRooms extends BodyTagSupport
{
    private final static Logger log = Logger.getLogger(ListReqRooms.class);
    
    private final String Enter = "<br><br>";
    private final String Ho = "<h2>";
    private final String Hc = "</h2>";
    
    private final String sql = "select * from requestapartment;";
    Connection connection;
    
    private void otherDoStartTagOperations() throws JspException, SQLException, ClassNotFoundException {
        try
        {
            JspWriter out = pageContext.getOut();
               
            connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next())
            {
                String idrequestapartment = rs.getString("idrequestapartment");
                String leaseTime = rs.getString("leaseTime");
                String numberRooms = rs.getString("numberRooms");
                String desiredPrice = rs.getString("desiredPrice");
                String recommendation = rs.getString("recommendation");
                String phone = rs.getString("phone");
                String price = rs.getString("price");
                String email = rs.getString("email");

                out.println(Ho + "ID = " + idrequestapartment + Enter);
                out.println("Lease Time = " + leaseTime + Enter);
                out.println("Number Rooms = " + numberRooms + Enter);
                out.println("Desired Price = " + desiredPrice + Enter);
                out.println("Recommendation = " + recommendation + Enter);
                out.println("Phone = " + phone + Enter);
                out.println("Price = " + price + Enter);
                out.println("EMAIL = " + email + Enter + Hc);  
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