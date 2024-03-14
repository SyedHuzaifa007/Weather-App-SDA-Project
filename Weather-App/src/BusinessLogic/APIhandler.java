package BusinessLogic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
class APIhandler {
    private String apikey;

    public APIhandler() {
        apikey = "16e0c1d404528d59f079dd4571275d8b";
    }

    public WeatherData getCurrentData(location location) {
        WeatherData weatherData = new WeatherData();
        String cityName;
        String countryName;

        // If location is not provided, use current location
        if (location == null ) {
            location loc = new location();
            try {
                loc.getCurrentLocation();
                cityName = loc.getCity();
                countryName = loc.getCountry();

            } catch (Exception e) {
                e.printStackTrace();
                return null; // Handle error gracefully
            }
        } else {
            // Parse the location string (assuming it's in the format "city,country")
            cityName = location.getCity();
            countryName = location.getCountry();        }

        try {
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
            weatherData.setTemperature(Double.parseDouble(temperatureStr)- 273.15);
            weatherData.setFeelsLike(Double.parseDouble(feelsLikeStr)- 273.15);
            weatherData.setMinTemperature(Double.parseDouble(minTemperatureStr)- 273.15);
            weatherData.setMaxTemperature(Double.parseDouble(maxTemperatureStr)- 273.15);

            // Print current weather conditions
            System.out.println("Current weather conditions for " + cityName + ", " + countryName + ":");
            System.out.println("Temperature: " + (weatherData.getTemperature()) + " C");
            System.out.println("Feels Like: " + (weatherData.getFeelsLike() ) + " C");
            System.out.println("Min Temperature: " + (weatherData.getMinTemperature() ) + " C");
            System.out.println("Max Temperature: " + (weatherData.getMaxTemperature() ) + " C");
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle error gracefully
        }

        return weatherData;
    }


    public String getSunsetSunrise(location location) {
        LocalDate today = LocalDate.now();

        try {
            URL url = new URL("https://api.sunrise-sunset.org/json?lat=" + location.getLatitude() + "&lng=" + location.getLongitude() + "&date=" + today);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            String json = response.toString();
            String sunriseTime = json.split("\"sunrise\":\"")[1].split("\"")[0];
            String sunsetTime = json.split("\"sunset\":\"")[1].split("\"")[0];

            // Convert sunrise and sunset times to LocalTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
            LocalTime sunrise = LocalTime.parse(sunriseTime, formatter);
            LocalTime sunset = LocalTime.parse(sunsetTime, formatter);

            return "Sunrise: " + sunrise.toString() + ", Sunset: " + sunset.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching sunrise and sunset times: " + e.getMessage();
        }
    }
}




