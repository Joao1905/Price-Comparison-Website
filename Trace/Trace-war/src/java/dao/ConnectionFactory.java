package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.Serializable;

public class ConnectionFactory implements Serializable{
    
    public ConnectionFactory() {
    
    }
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://XXXXX:XXXXX/trace?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            "XXXXX", "XXXXX");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}