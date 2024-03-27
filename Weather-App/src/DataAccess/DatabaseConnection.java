package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:sqlserver://103.31.104.114:1433;databaseName=Weather;user=user;password=12345678;encrypt=true;trustServerCertificate=true;";

    // Static method to get connection
    public static Connection getConnection() throws SQLException {
        // Establish the connection
        Connection con = DriverManager.getConnection(JDBC_URL);
        System.out.println("Connection established successfully!");
        return con;
    }

    public static void main(String args[]) {
        try {
            // Get the connection
            Connection con = getConnection();

            // Close the connection when done
            con.close();
        } catch (SQLException e) {
            // Handle any potential SQL exceptions here
            e.printStackTrace();
        }
    }
}
