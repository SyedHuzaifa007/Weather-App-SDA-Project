package DataAccess;

import DataAccess.DatabaseConnection;

import javax.xml.crypto.Data;
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
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH.mm");
        try {
            Date parsedDate = dateFormat1.parse(timeString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            // Try the second format
            Date parsedDate = dateFormat2.parse(timeString);
            return new Timestamp(parsedDate.getTime());
        }
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
            if (i == 1 || i == 2) { // Indices for Longitude and Latitude
                preparedStatement.setString(i + 1, data[i]);
            } else if (i == 7 || i == 8 || i == 9) { // Indices for time-related data
                // Parse date/time string to Timestamp object
                Timestamp timestamp = parseTimestamp(data[i]); // Implement parseTimestamp() method accordingly
                preparedStatement.setTimestamp(i + 1, timestamp);
            } else {
                preparedStatement.setString(i + 1, data[i]);
            }
        }
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static void insertWeatherForecast(Connection connection, String[] data) throws Exception {
        String query = "INSERT INTO WeatherForecast (Location, Day1Forecast, Day2Forecast, " +
                "Day3Forecast, Day4Forecast, Day5Forecast) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < data.length; i++) {
            preparedStatement.setString(i + 1, data[i]);
        }
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static void insertAirPollutionData(Connection connection, String[] data) throws Exception {
        String query = "INSERT INTO AirPollutionData (Location, AirQualityIndex, CarbonMonoxide, " +
                "NitrogenMonoxide, NitrogenDioxide, Ozone, SulphurDioxide, Ammonia, ParticulatePM25, ParticulatePM10) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < data.length; i++) {
            preparedStatement.setString(i + 1, data[i]);
        }
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static void main(String[] args) {
        String fileName = "DBTxt.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Connection connection = DatabaseConnection.getConnection(); // Assuming you have a method to get connection
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 10) {
                    insertWeatherData(connection, data);
                } else if (data.length == 6) {
                    insertWeatherForecast(connection, data);
                } else if (data.length == 10) {
                    insertAirPollutionData(connection, data);
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
