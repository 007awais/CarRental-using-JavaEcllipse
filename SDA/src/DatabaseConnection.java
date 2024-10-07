import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/CarRentalDB";
    private static final String USER = "root"; 
    private static final String PASSWORD = "admin007"; // Your MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
           // System.out.println("Connected to the database.");
        } catch (SQLException e) {
            //System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }
}
