package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","sa", "Password123");
            // Perform operations with the connection if needed
            System.out.println("Connection established successfully!");
            // Close the connection when done
            con.close();
        } catch (SQLException e) {
            // Handle any potential SQL exceptions here
            e.printStackTrace();
        }
    }
}
