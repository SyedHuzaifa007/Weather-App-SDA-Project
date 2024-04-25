package BusinessLogic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.time.*;
import java.util.Scanner;
import java.util.TimeZone;
//import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.format.DateTimeFormatter;

public class WeatherInfo extends APIhandler{
    private double temperature;
    private double feelsLike;
    private double minTemperature;
    private double maxTemperature;
    private LocalTime sunriseTime;
    private LocalTime sunsetTime;
    private LocalTime timestamp;


    public WeatherInfo() {
        this.temperature = 0.0;
        this.feelsLike = 0.0;
        this.minTemperature = 0.0;
        this.maxTemperature = 0.0;
        this.sunriseTime = null;
        this.sunsetTime = null;
        this.timestamp = null;
    }
    public WeatherInfo(double temp)
    {
        temperature=temp;
    }
    public WeatherInfo(double temperature, double feelsLike, double minTemperature, double maxTemperature,
                       LocalTime sunriseTime, LocalTime sunsetTime, LocalTime timestamp) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.timestamp = timestamp;
    }
    public double getmaxtemperature(location location) {
        try {
            String response;
            if (location == null) {
                // If location is not provided, use current location
                location loc = new location();
                try {
                    loc.getCurrentLocation();
                    response = getResponse(loc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1; // Handle error gracefully
                }
            } else {
                response = getResponse(location);
            }

            if (response != null) {
                // Extract max temperature from the response
                String maxTemperatureStr = response.split("\"temp_max\":")[1].split(",")[0];
                return Double.parseDouble(maxTemperatureStr) - 273.15;
            } else {
                return -1; // Handle error gracefully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error gracefully
        }
    }
    public LocalTime getTimestamp(location location) {
        try {
            // Fetch current timestamp from an external service
            URL url = new URL("https://worldtimeapi.org/api/ip");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response to get the current timestamp
            String json = response.toString();
            String timestampStr = json.split("\"datetime\":\"")[1].split("\"")[0];

            // Convert timestamp string to LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
            LocalTime timeStamp = LocalTime.parse(timestampStr, formatter);
            timestamp=timeStamp;
            return timestamp;

        } catch (Exception e) {
            e.printStackTrace();
            // Returning null may not be ideal, consider handling errors more gracefully
            return null;
        }
    }
    public double getmintemperature(location location) {
        try {
            String response;
            if (location == null) {
                // If location is not provided, use current location
                location loc = new location();
                try {
                    loc.getCurrentLocation();
                    response = getResponse(loc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1; // Handle error gracefully
                }
            } else {
                response = getResponse(location);
            }

            if (response != null) {
                // Extract min temperature from the response
                String minTemperatureStr = response.split("\"temp_min\":")[1].split(",")[0];
                return Double.parseDouble(minTemperatureStr) - 273.15;
            } else {
                return -1; // Handle error gracefully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error gracefully
        }
    }
    public double feelsliketemperature(location location) {
        try {
            String response;
            if (location == null) {
                // If location is not provided, use current location
                location loc = new location();
                try {
                    loc.getCurrentLocation();
                    response = getResponse(loc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1; // Handle error gracefully
                }
            } else {
                response = getResponse(location);
            }

            if (response != null) {
                // Extract feels like temperature from the response
                String feelslikeStr = response.split("\"feels_like\":")[1].split(",")[0];
                return Double.parseDouble(feelslikeStr) - 273.15;
            } else {
                return -1; // Handle error gracefully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error gracefully
        }
    }
    public double gettemperature(location location) {
        try {
            String response;
            if (location == null) {
                // If location is not provided, use current location
                location loc = new location();
                try {
                    loc.getCurrentLocation();
                    response = getResponse(loc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1; // Handle error gracefully
                }
            } else {
                response = getResponse(location);
            }

            if (response != null) {
                // Extract temperature from the response
                String TemperatureStr = response.split("\"temp\":")[1].split(",")[0];
                return Double.parseDouble(TemperatureStr) - 273.15;
            } else {
                return -1; // Handle error gracefully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error gracefully
        }
    }


    public  String getsunset(location location) {
        String urlString = "https://api.sunrisesunset.io/json?lat=" +location.getLatitude() + "&lng=" +location.getLongitude();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "Error: Unable to fetch sunset time. Response code: " + responseCode;
            }

            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject results = jsonResponse.getJSONObject("results");
            String sunsetTime = results.getString("sunset");
            String[] sunsetComponents = sunsetTime.split(":");
            String hours = String.format("%02d", Integer.parseInt(sunsetComponents[0]));
            String minutes = String.format("%02d", Integer.parseInt(sunsetComponents[1]));

            return " "+hours + ":" + minutes  ;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    public String getsunrise(location location) {
        String urlString = "https://api.sunrisesunset.io/json?lat=" +location.getLatitude() + "&lng=" +location.getLongitude();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "Error: Unable to fetch sunrise time. Response code: " + responseCode;
            }

            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject results = jsonResponse.getJSONObject("results");
            String sunriseTime = results.getString("sunrise");
            String[] sunriseComponents = sunriseTime.split(":");
            String hours = String.format("%02d", Integer.parseInt(sunriseComponents[0]));
            String minutes = String.format("%02d", Integer.parseInt(sunriseComponents[1]));

            return " "+hours + ":" + minutes ;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}

