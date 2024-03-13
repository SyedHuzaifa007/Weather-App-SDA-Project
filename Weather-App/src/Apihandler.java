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
class APIhandler1 {
    private String apikey;
    public APIhandler1(){
        apikey="16e0c1d404528d59f079dd4571275d8b";
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
            weatherData.setFeelsLike(feelsLikeStr);

            weatherData.setMinTemperature(Double.parseDouble(minTemperatureStr));
            weatherData.setMaxTemperature(Double.parseDouble(maxTemperatureStr));

            // Print current weather conditions
            System.out.println("Current weather conditions for " + cityName + ", " + countryName + ":");
            System.out.println("Temperature: " + weatherData.getTemperature() + " K");
            System.out.println("Feels Like: " + weatherData.getFeelsLike() + " K");
            System.out.println("Min Temperature: " + weatherData.getMinTemperature() + " K");
            System.out.println("Max Temperature: " + weatherData.getMaxTemperature() + " K");

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherData;
    }
}
class location {
    private double latitude;
    private double longitude;
    private String city;
    private String country;

    public location() {
    }

    public location(double latitude, double longitude, String city, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public location getCurrentLocation() {
        try {
            // Get the current IP address
            URL ipApiUrl = new URL("https://api.ipify.org?format=json");
            URLConnection ipApiConnection = ipApiUrl.openConnection();
            BufferedReader ipReader = new BufferedReader(new InputStreamReader(ipApiConnection.getInputStream()));
            String ipResponse = ipReader.readLine();
            ipReader.close();

            // Extract IP address from the response
            String ipAddress = ipResponse.split(":")[1].replaceAll("[^\\d.]", "").trim();

            // Get location data based on the IP address
            URL geoIpUrl = new URL("https://ipapi.co/" + ipAddress + "/json/");
            URLConnection geoIpConnection = geoIpUrl.openConnection();
            BufferedReader geoIpReader = new BufferedReader(new InputStreamReader(geoIpConnection.getInputStream()));
            StringBuilder geoIpResponse = new StringBuilder();
            String line;
            while ((line = geoIpReader.readLine()) != null) {
                geoIpResponse.append(line);
            }
            geoIpReader.close();

            // Extract country, city, latitude, and longitude from the response
            String[] parts = geoIpResponse.toString().split(",");
            for (String part : parts) {
                if (part.contains("\"country_name\"")) {
                    country = part.split(":")[1].replaceAll("\"", "").trim();
                }
                if (part.contains("\"city\"")) {
                    city = part.split(":")[1].replaceAll("\"", "").trim();
                }
                if (part.contains("\"latitude\"")) {
                    latitude = Double.parseDouble(part.split(":")[1]);
                }
                if (part.contains("\"longitude\"")) {
                    longitude = Double.parseDouble(part.split(":")[1]);
                }
            }

            // Return formatted string containing city, country, latitude, and longitude
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
public class Apihandler {
        public static void main(String[] args) {
            APIhandler1 apiHandler = new APIhandler1(); // Replace "API_KEY_HERE" with your actual API key

            // Create a Location object with desired city and country
            location location = new location(); // Replace with actual city name and country code

            // Call getCurrentData method to fetch weather data
            WeatherData weatherData = apiHandler.getCurrentData();
        }
}