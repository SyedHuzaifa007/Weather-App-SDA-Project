package BusinessLogic;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataAccess.DatabaseConnection;
import java.time.LocalDate;



public class CacheManagerSQL {
        private DatabaseConnection dbConnection;

        public CacheManagerSQL(DatabaseConnection dbConnection) {
            this.dbConnection = dbConnection;
        }

        public void getData(String loc) {
            try (Connection connection = dbConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM WeatherData WHERE Location = ? ORDER BY Timestamp DESC LIMIT 1")) {
                statement.setString(1, loc);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // Data exists in cache and is up-to-date
                    // Fetch data and return
                    String location = resultSet.getString("Location");
                    String longitude = resultSet.getString("Longitude");
                    String latitude = resultSet.getString("Latitude");
                    String temperature = resultSet.getString("Temperature");
                    String feelsLike = resultSet.getString("FeelsLike");
                    String minimumtemp = resultSet.getString("MinimumTemperature");
                    String maximumtemp = resultSet.getString("MaximumTemperature");
                    String sunrisetime = resultSet.getString("SunriseTime");
                    String sunsettime = resultSet.getString("SunsetTime");
                    String Timestamp = resultSet.getString("Timestamp");

                    // Example of how to use the fetched data
                    System.out.println("Location: " + location);
                    System.out.println("Temperature: " + temperature);
                    // Handle other data accordingly
                } else {
                    // Data does not exist in cache or is outdated
                    // Fetch data from API or another source
                    // This section will depend on your specific implementation
                    System.out.println("Data not found in cache or outdated. Fetching from API...");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exception
            }
        }

    private void writeDataToFile(ResultSet resultSet) throws SQLException, IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CacheFileSQL.txt"))) {
            // Write data line by line
            writer.write(resultSet.getString("Location"));
            writer.newLine();
            writer.write(resultSet.getString("Longitude"));
            writer.newLine();
            writer.write(resultSet.getString("Latitude"));
            writer.newLine();
            writer.write(resultSet.getString("Temperature"));
            writer.newLine();
            writer.write(resultSet.getString("FeelsLike"));
            writer.newLine();
            writer.write(resultSet.getString("MinTemp"));
            writer.newLine();
            writer.write(resultSet.getString("MaxTemp"));
            writer.newLine();
            writer.write(resultSet.getString("Sunrise"));
            writer.newLine();
            writer.write(resultSet.getString("Sunset"));
            writer.newLine();
            writer.write(resultSet.getString("Timestamp"));
            writer.newLine();
            // Write other data accordingly
        }
    }
}
