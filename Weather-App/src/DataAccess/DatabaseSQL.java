import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSQL {

    public void insertDataFromCacheFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Connection con = DatabaseConnection.getConnection();

            // PreparedStatements for each table
            PreparedStatement weatherDataStatement = con.prepareStatement("INSERT INTO WeatherData (Location, Longitude, Latitude, Temperature, FeelsLike, MinimumTemperature, MaximumTemperature, SunriseTime, SunsetTime, Timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement weatherForecastStatement = con.prepareStatement("INSERT INTO WeatherForecast (Location, Day1Forecast, Day2Forecast, Day3Forecast, Day4Forecast, Day5Forecast) VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement airPollutionStatement = con.prepareStatement("INSERT INTO AirPollutionData (Location, AirQualityIndex, CarbonMonoxide, NitrogenMonoxide, NitrogenDioxide, Ozone, SulphurDioxide, Ammonia, ParticulatePM25, ParticulatePM10) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            while ((line = reader.readLine()) != null) {
                // Split the line by comma
                String[] values = line.split(",");

                if (values.length > 0) {
                    // Determine the table type based on the first value
                    String tableName = values[0].trim();

                    switch (tableName) {
                        case "WeatherData":
                            // Insert data into WeatherData table
                            insertWeatherData(weatherDataStatement, values);
                            break;
                        case "WeatherForecast":
                            // Insert data into WeatherForecast table
                            insertWeatherForecast(weatherForecastStatement, values);
                            break;
                        case "AirPollutionData":
                            // Insert data into AirPollutionData table
                            insertAirPollutionData(airPollutionStatement, values);
                            break;
                        default:
                            // Invalid table name
                            System.out.println("Invalid table name: " + tableName);
                            break;
                    }
                }
            }

            // Close resources
            weatherDataStatement.close();
            weatherForecastStatement.close();
            airPollutionStatement.close();
            con.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }