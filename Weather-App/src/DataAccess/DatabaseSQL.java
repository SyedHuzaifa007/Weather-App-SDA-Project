package DataAccess;//package DataAccess;
//
//import DataAccess.DatabaseConnection;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class DatabaseSQL {
//    public void insertDataFromCacheFile(String filePath) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            Connection con = DatabaseConnection.getConnection();
//
//            // PreparedStatements for each table
//            PreparedStatement weatherDataStatement = con.prepareStatement("INSERT INTO WeatherData (Location, Longitude, Latitude, Temperature, FeelsLike, MinimumTemperature, MaximumTemperature, SunriseTime, SunsetTime, Timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//            PreparedStatement weatherForecastStatement = con.prepareStatement("INSERT INTO WeatherForecast (Location, Day1Forecast, Day2Forecast, Day3Forecast, Day4Forecast, Day5Forecast) VALUES (?, ?, ?, ?, ?, ?)");
//            PreparedStatement airPollutionStatement = con.prepareStatement("INSERT INTO AirPollutionData (Location, AirQualityIndex, CarbonMonoxide, NitrogenMonoxide, NitrogenDioxide, Ozone, SulphurDioxide, Ammonia, ParticulatePM25, ParticulatePM10) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//
//            while ((line = reader.readLine()) != null) {
//                // Split the line by new line character
//                String[] values = line.split(",");
//
//                if (values.length > 0) {
//                    // Determine the table type based on the first value
//                    String tableName = values[0].trim();
//
//                    switch (tableName) {
//                        case "WeatherData":
//                            // Insert data into WeatherData table
//                            insertWeatherData(weatherDataStatement, values);
//                            break;
//                        case "WeatherForecast":
//                            // Insert data into WeatherForecast table
//                            insertWeatherForecast(weatherForecastStatement, values);
//                            break;
//                        case "AirPollutionData":
//                            // Insert data into AirPollutionData table
//                            insertAirPollutionData(airPollutionStatement, values);
//                            break;
//                        default:
//                            // Invalid table name
//                            System.out.println("Invalid table name: " + tableName);
//                            break;
//                    }
//                }
//            }
//
//            // Close resources
//            weatherDataStatement.close();
//            weatherForecastStatement.close();
//            airPollutionStatement.close();
//            con.close();
//
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void insertWeatherData(PreparedStatement statement, String[] values) throws SQLException {
//        // Assuming the values array contains Location, Longitude, Latitude, Temperature, FeelsLike, MinimumTemperature, MaximumTemperature, SunriseTime, SunsetTime, Timestamp
//        statement.setString(1, values[1].trim());
//        statement.setDouble(2, Double.parseDouble(values[2].trim()));
//        statement.setDouble(3, Double.parseDouble(values[3].trim()));
//        statement.setDouble(4, Double.parseDouble(values[4].trim()));
//        statement.setDouble(5, Double.parseDouble(values[5].trim()));
//        statement.setDouble(6, Double.parseDouble(values[6].trim()));
//        statement.setDouble(7, Double.parseDouble(values[7].trim()));
//        statement.setString(8, values[8].trim());
//        statement.setString(9, values[9].trim());
//        statement.setString(10, values[10].trim());
//
//        statement.executeUpdate();
//    }
//
//    private void insertWeatherForecast(PreparedStatement statement, String[] values) throws SQLException {
//        // Assuming the values array contains Location, Day1Forecast, Day2Forecast, Day3Forecast, Day4Forecast, Day5Forecast
//        statement.setString(1, values[1].trim());
//        statement.setString(2, values[2].trim());
//        statement.setString(3, values[3].trim());
//        statement.setString(4, values[4].trim());
//        statement.setString(5, values[5].trim());
//        statement.setString(6, values[6].trim());
//
//        statement.executeUpdate();
//    }
//
//    private void insertAirPollutionData(PreparedStatement statement, String[] values) throws SQLException {
//        // Assuming the values array contains Location, AirQualityIndex, CarbonMonoxide, NitrogenMonoxide, NitrogenDioxide, Ozone, SulphurDioxide, Ammonia, ParticulatePM25, ParticulatePM10
//        statement.setString(1, values[1].trim());
//        statement.setString(2, values[2].trim());
//        statement.setString(3, values[3].trim());
//        statement.setString(4, values[4].trim());
//        statement.setString(5, values[5].trim());
//        statement.setString(6, values[6].trim());
//        statement.setString(7, values[7].trim());
//        statement.setString(8, values[8].trim());
//        statement.setString(9, values[9].trim());
//        statement.setString(10, values[10].trim());
//
//        statement.executeUpdate();
//    }
//}
//

//import DataAccess.DatabaseConnection;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class DatabaseSQL {
//    public static void main(String[] args) {
//        // Provide the file path as a command-line argument
//        if (args.length != 1) {
//            System.out.println("Usage: java DatabaseSQL <file_path>");
//            return;
//        }
//
//        String filePath = args[0];
//        DatabaseSQL databaseSQL = new DatabaseSQL();
//        databaseSQL.insertDataFromCacheFile(filePath);
//    }
//
//    public void insertDataFromCacheFile(String filePath) {
//        try (BufferedReader reader = new BufferedReader(new FileReader("CacheFile.txt"))) {
//            String line;
//            Connection con = DatabaseConnection.getConnection();
//
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(",");
//
//                if (values.length > 0) {
//                    String tableName = values[0].trim();
//
//                    switch (tableName) {
//                        case "Islamabad":
//                            if (values.length == 11) {
//                                insertWeatherData(con, values);
//                            } else if (values.length == 6) {
//                                insertWeatherForecast(con, values);
//                            } else if (values.length == 10) {
//                                insertAirPollutionData(con, values);
//                            } else {
//                                System.out.println("Invalid data format: " + line);
//                            }
//                            break;
//                        default:
//                            System.out.println("Invalid table name: " + tableName);
//                            break;
//                    }
//                }
//            }
//
//            con.close();
//
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void insertWeatherData(Connection con, String[] values) throws SQLException {
//        PreparedStatement statement = con.prepareStatement("INSERT INTO WeatherData (Location, Longitude, Latitude, Temperature, FeelsLike, MinimumTemperature, MaximumTemperature, SunriseTime, SunsetTime, Timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//        for (int i = 1; i < values.length; i++) {
//            statement.setString(i, values[i].trim());
//        }
//        statement.executeUpdate();
//        statement.close();
//    }
//
//    private void insertWeatherForecast(Connection con, String[] values) throws SQLException {
//        PreparedStatement statement = con.prepareStatement("INSERT INTO WeatherForecast (Location, Day1Forecast, Day2Forecast, Day3Forecast, Day4Forecast, Day5Forecast) VALUES (?, ?, ?, ?, ?, ?)");
//        for (int i = 1; i < values.length; i++) {
//            statement.setString(i, values[i].trim());
//        }
//        statement.executeUpdate();
//        statement.close();
//    }
//
//    private void insertAirPollutionData(Connection con, String[] values) throws SQLException {
//        PreparedStatement statement = con.prepareStatement("INSERT INTO AirPollutionData (Location, AirQualityIndex, CarbonMonoxide, NitrogenMonoxide, NitrogenDioxide, Ozone, SulphurDioxide, Ammonia, ParticulatePM25, ParticulatePM10) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//        for (int i = 1; i < values.length; i++) {
//            statement.setString(i, values[i].trim());
//        }
//        statement.executeUpdate();
//        statement.close();
//    }
//}

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DatabaseSQL {
    public static Timestamp parseTimestamp(String timeString) throws ParseException {
        if (timeString == null) {
            return null; // Return null if the input string is null
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date parsedDate = dateFormat.parse(timeString);
        return new Timestamp(parsedDate.getTime());
    }

    public static Float parseFloat(String floatString) throws ParseException {
        if (floatString == null) {
            return null; // Return null if the input string is null
        }
        return Float.parseFloat(floatString);
    }

    public static void insertWeatherData(Connection connection, String[] data) throws Exception {
        String query = "INSERT INTO WeatherData (Location, Longitude, Latitude, Temperature, FeelsLike, " +
                "MinimumTemperature, MaximumTemperature, SunriseTime, SunsetTime, Timestamp) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < data.length; i++) {
            if (i == 7 || i == 8 || i == 9) { // Assuming 7, 8, 9 are the indices for date/time strings
                // Parse date/time string to Timestamp object
                Timestamp timestamp = parseTimestamp(data[i]); // Implement parseTimestamp() method accordingly
                preparedStatement.setTimestamp(i + 1, timestamp);
            } else {
                preparedStatement.setString(i + 1, data[i]);
            }
        }
        preparedStatement.executeUpdate();
    }


    public static void insertWeatherForecast(Connection connection, String[] data) throws Exception {
        String query = "INSERT INTO WeatherForecast (Location, Day1Forecast, Day2Forecast, " +
                "Day3Forecast, Day4Forecast, Day5Forecast) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < data.length; i++) {
            preparedStatement.setString(i + 1, data[i]);
        }
        preparedStatement.executeUpdate();
    }

//    public static void insertAirPollutionData(Connection connection, String[] data) throws Exception {
//        String query = "INSERT INTO AirPollutionData (Location, AirQualityIndex, CarbonMonoxide, " +
//                "NitrogenMonoxide, NitrogenDioxide, Ozone, SulphurDioxide, Ammonia, ParticulatePM25, ParticulatePM10) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        for (int i = 0; i < data.length; i++) {
//            preparedStatement.setString(i + 1, data[i]);
//        }
//        preparedStatement.executeUpdate();
//    }

    public static void insertAirPollutionData(Connection connection, String[] data) throws Exception {
        String query = "INSERT INTO AirPollutionData (Location, AirQualityIndex, CarbonMonoxide, " +
                "NitrogenMonoxide, NitrogenDioxide, Ozone, SulphurDioxide, Ammonia, ParticulatePM25, ParticulatePM10) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < data.length; i++) {
            if (i > 0) { // Skip parsing for the Location column at index 0
                Float floatValue = parseFloat(data[i]);
                preparedStatement.setFloat(i, floatValue); // Adjusted index to start from 1
            } else {
                preparedStatement.setString(1, data[0]); // Set Location column
            }
        }
        preparedStatement.executeUpdate();
    }


    public static void main(String[] args) {
        String fileName = "CacheFile.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Connection connection = DatabaseConnection.getConnection(); // Assuming you have a method to get connection
                if (data.length == 10) {
                    insertWeatherData(connection, data);
                } else if (data.length == 6) {
                    insertWeatherForecast(connection, data);
                } else if (data.length == 10) {
                    insertAirPollutionData(connection, data);
                }
                connection.close();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
