package BusinessLogic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;

class APIhandler {
    private String apikey;

    public APIhandler() {
        apikey = "16e0c1d404528d59f079dd4571275d8b";
    }

    public WeatherData getCurrentData() {
        WeatherData weatherData = new WeatherData();
        location loc = new location();
        String cityName;
        String countryName;

        try {
            loc.getCurrentLocation();
            cityName = loc.getCity();
            countryName = loc.getCountry();

            // Construct the URL for the API call

            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "," + countryName + "&appid=" + apikey;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // Extract weather information from the response
            String response = sb.toString();
            // Assuming the response contains temperature, feels like, min temperature, and max temperature values as strings
            String temperatureStr = response.split("\"temp\":")[1].split(",")[0];
            String feelsLikeStr = response.split("\"feels_like\":")[1].split(",")[0];
            String minTemperatureStr = response.split("\"temp_min\":")[1].split(",")[0];
            String maxTemperatureStr = response.split("\"temp_max\":")[1].split(",")[0];

            // Convert string values to doubles
            weatherData.setTemperature(Double.parseDouble(temperatureStr));
            weatherData.setFeelsLike(Double.parseDouble(feelsLikeStr));

            weatherData.setMinTemperature(Double.parseDouble(minTemperatureStr));
            weatherData.setMaxTemperature(Double.parseDouble(maxTemperatureStr));

            // Print current weather conditions
            System.out.println("Current weather conditions for " + cityName + ", " + countryName + ":");
            System.out.println("Temperature: " + (weatherData.getTemperature() - 273.15) + " C");
            System.out.println("Feels Like: " + (weatherData.getFeelsLike() - 273.15) + " C");
            System.out.println("Min Temperature: " + (weatherData.getMinTemperature() - 273.15) + " C");
            System.out.println("Max Temperature: " + (weatherData.getMaxTemperature() - 273.15) + " C");
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherData;
    }
}



