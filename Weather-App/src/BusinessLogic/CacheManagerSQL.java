package BusinessLogic;
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
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM WeatherData WHERE Location = ? AND Timestamp >= DATE_SUB(NOW(), INTERVAL 1 DAY)")) {
                statement.setString(1, loc);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // Data exists in cache and is up-to-date
                    // Fetch data and return
                    String location = resultSet.getString("Location");
                    double longitude = resultSet.getDouble("Longitude");
                    double latitude = resultSet.getDouble("Latitude");
                    double temperature = resultSet.getDouble("Temperature");
                    double feelsLike = resultSet.getDouble("FeelsLike");
                    // Continue fetching other columns as needed

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

        public void storeData(String Location, String longi, String lati, String temp, String feel, String min, String max, String sunrise, String sunset, String stamp, String day1, String day2, String day3, String day4, String day5, String aqi, String CO, String NO, String NO2, String O3, String SO2, String NH3, String PM25, String PM10, String n_weather, String n_air, String date) {
            try (Connection connection = dbConnection.getConnection()) {
                // Insert or update data in WeatherData, WeatherForecast, and AirPollutionData tables
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO WeatherData (Location, Longitude, Latitude, Temperature, FeelsLike, MinTemp, MaxTemp, Sunrise, Sunset, Timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insertStatement.setString(1, Location);
                insertStatement.setDouble(2, Double.parseDouble(longi));
                insertStatement.setDouble(3, Double.parseDouble(lati));
                insertStatement.setDouble(4, Double.parseDouble(temp));
                insertStatement.setDouble(5, Double.parseDouble(feel));
                // Set other parameters accordingly

                insertStatement.executeUpdate();
                // Handle insertion into other tables if needed

                System.out.println("Data stored successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exception
            }
        }
    }

}
