package DataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String args[]) {
        try {

            Connection con = DriverManager.getConnection("jdbc:sqlserver://103.31.104.114:1433;databaseName=Weather;user=user;password=12345678;encrypt=true;trustServerCertificate=true;");
            System.out.println("Connection established successfully!");
            // Close the connection when done
            con.close();
        } catch (SQLException e) {
            // Handle any potential SQL exceptions here
            e.printStackTrace();
        }
    }
}