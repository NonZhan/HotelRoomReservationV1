

package Connection;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class Database
{
    private static final BasicDataSource ds = new BasicDataSource();
    
    static
    {
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/app1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("193728465asd@");
        ds.setMaxActive(-1);
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    private Database()
    {
        //
    }
}