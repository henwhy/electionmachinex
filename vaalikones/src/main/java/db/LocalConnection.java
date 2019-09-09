package db;

import java.sql.Connection;
import java.sql.SQLException;

// todo credentials
public class LocalConnection {
    public static Connection getDevConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/bocekexam?verifyServerCertificate=false&useSSL=false&serverTimezone=UTC", "root", "");
        return conn;
    }
}
