package BusinessLogic;
import java.time.*;
import java.util.TimeZone;

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
    public String getTimeZoneId(double latitude, double longitude) {
        int rawOffsetMillis = (int) (TimeZone.getDefault().getRawOffset() + (longitude / 15.0 * 60 * 60 * 1000)); // Adjust based on longitude
        String[] ids = TimeZone.getAvailableIDs(rawOffsetMillis);
        return ids.length > 0 ? ids[0] : null;
    }
    public LocalTime getSunrise(location location) {
        LocalDate today = LocalDate.now();
        ZoneId zoneId = ZoneId.of(getTimeZoneId(location.getLatitude(),location.getLongitude())); // Replace YOUR_TIMEZONE_ID_HERE with the timezone ID of the location

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
            String sunriseTimeUTC = json.split("\"sunrise\":\"")[1].split("\"")[0];

            // Convert sunrise time from UTC to LocalTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
            LocalTime sunriseUTC = LocalTime.parse(sunriseTimeUTC, formatter);
            ZonedDateTime zonedDateTime = ZonedDateTime.of(today, sunriseUTC, ZoneOffset.UTC);
            LocalTime sunrise = zonedDateTime.withZoneSameInstant(zoneId).toLocalTime();

            return sunrise;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LocalTime getSunset(location location) {
        LocalDate today = LocalDate.now();
        ZoneId zoneId = ZoneId.of(getTimeZoneId(location.getLatitude(),location.getLongitude())); // Replace YOUR_TIMEZONE_ID_HERE with the timezone ID of the location

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
            String sunsetTimeUTC = json.split("\"sunset\":\"")[1].split("\"")[0];

            // Convert sunset time from UTC to LocalTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
            LocalTime sunsetUTC = LocalTime.parse(sunsetTimeUTC, formatter);
            ZonedDateTime zonedDateTime = ZonedDateTime.of(today, sunsetUTC, ZoneOffset.UTC);
            LocalTime sunset = zonedDateTime.withZoneSameInstant(zoneId).toLocalTime();

            return sunset;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
