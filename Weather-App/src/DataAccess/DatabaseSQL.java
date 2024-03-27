//package DataAccess;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseSQL {
//
//    public List<WeatherData> getAllWeatherData() {
//        List<WeatherData> weatherDataList = new ArrayList<>();
//
//        try {
//            // Get the connection from DatabaseConnection class
//            Connection con = DatabaseConnection.getConnection();
//
//            // Create a PreparedStatement object with the query
//            String query = "SELECT * FROM WeatherData";
//            PreparedStatement statement = con.prepareStatement(query);
//
//            // Execute the query and get the ResultSet
//            ResultSet resultSet = statement.executeQuery();
//
//            // Process the ResultSet
//            while (resultSet.next()) {
//                // Retrieve data from the result set
//                String timestamp = resultSet.getString("timestamp");
//                String location = resultSet.getString("location");
//                double latitude = resultSet.getDouble("latitude");
//                double longitude = resultSet.getDouble("longitude");
//                double temperature = resultSet.getDouble("temperature");
//                double minTemperature = resultSet.getDouble("minimum_temperature");
//                double maxTemperature = resultSet.getDouble("maximum_temperature");
//                String sunriseTime = resultSet.getString("sunrise_time");
//                String sunsetTime = resultSet.getString("sunset_time");
//
//                // Create a WeatherData object and add it to the list
//                WeatherData weatherData = new WeatherData(timestamp, location, latitude, longitude, temperature, minTemperature, maxTemperature, sunriseTime, sunsetTime);
//                weatherDataList.add(weatherData);
//            }
//
//            // Close ResultSet and PreparedStatement
//            resultSet.close();
//            statement.close();
//
//        } catch (SQLException e) {
//            // Handle any potential SQL exceptions here
//            e.printStackTrace();
//        }
//
//        return weatherDataList;
//    }
//}
