package DataAccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatabaseSQL {

    public static Timestamp parseTimestamp(String timeString) throws ParseException {
        if (timeString == null || timeString.isEmpty()) {
            return null; // Return null if the input string is null or empty
        }
        SimpleDateFormat[] dateFormats = {
                new SimpleDateFormat("HH:mm:ss"),
                new SimpleDateFormat("HH.mm")
        };
        for (SimpleDateFormat dateFormat : dateFormats) {
            try {
                return new Timestamp(dateFormat.parse(timeString).getTime());
            } catch (ParseException ignored) {
                // Try the next format
            }
        }
        throw new ParseException("Unparseable date: " + timeString, 0);
    }

    public static Float parseFloat(String floatString) throws ParseException {
        if (floatString == null || floatString.isEmpty()) {
            return null; // Return null if the input string is null or empty
        }
        return Float.parseFloat(floatString);
    }

    public static void insertWeatherData(Connection connection, String[] data) throws Exception {
        String query = "INSERT INTO WeatherData (Location, Longitude, Latitude, Temperature, FeelsLike, " +
                "MinimumTemperature, MaximumTemperature, SunriseTime, SunsetTime, Timestamp) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < data.length; i++) {
                if (i == 1 || i == 2 || i == 7 || i == 8 || i == 9) {
                    preparedStatement.setObject(i + 1, data[i]);
                } else {
                    preparedStatement.setString(i + 1, data[i]);
                }
            }
            preparedStatement.executeUpdate();
        }
    }

    public static void insertWeatherForecast(Connection connection, String[] data) throws Exception {
        String query = "INSERT INTO WeatherForecast (Location, Day1Forecast, Day2Forecast, " +
                "Day3Forecast, Day4Forecast, Day5Forecast) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < data.length; i++) {
                preparedStatement.setString(i + 1, data[i]);
            }
            preparedStatement.executeUpdate();
        }
    }

    public static void insertAirPollutionData(Connection connection, String[] data) throws Exception {
        String query = "INSERT INTO AirPollutionData (Location, AirQualityIndex, CarbonMonoxide, " +
                "NitrogenMonoxide, NitrogenDioxide, Ozone, SulphurDioxide, Ammonia, ParticulatePM25, ParticulatePM10) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < data.length; i++) {
                preparedStatement.setString(i + 1, data[i]);
            }
            preparedStatement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        String fileName = "DBTxt.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Connection connection = DatabaseConnection.getConnection(); // Assuming you have a method to get connection
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    if (data.length == 10) {
                        insertWeatherData(connection, data);
                    } else if (data.length == 6) {
                        insertWeatherForecast(connection, data);
                    } else if (data.length == 11) { // Adjusted length for air pollution data
                        insertAirPollutionData(connection, data);
                    } else {
                        System.err.println("Invalid data format: " + String.join(",", data));
                    }
                } catch (Exception e) {
                    System.err.println("Error inserting data: " + e.getMessage());
                }
            }
            connection.close();
        } catch (Exception e) {
            System.err.println("Error reading file or closing connection: " + e.getMessage());
        }
    }
}