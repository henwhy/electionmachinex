package vaalikone;


import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
  
// This class can be used to initialize the database connection 
public class Uusi { 
    public static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
        // Initialize all the information regarding 
        // Database Connection 
        String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = "jdbc:mysql://localhost:3306/vaalikone"; 
        // Database name to access 
        String dbName = "ehdokkaat"; 
        String dbUsername = "root"; 
        String dbPassword = ""; 
  
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL,// + dbName, 
                                                     dbUsername,  
                                                     dbPassword); 
        return con; 
    } 
}