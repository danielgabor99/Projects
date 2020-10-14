package webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException {
        // Initialize all the information regarding
        // Database Connection
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/mydb?user=postgres&password=postgres&ssl=false";
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
}
