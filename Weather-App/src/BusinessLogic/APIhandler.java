package BusinessLogic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.ZoneOffset;

 public class APIhandler implements APIHandlerInterface {
    private String apikey;
    public APIhandler() {
        apikey = "16e0c1d404528d59f079dd4571275d8b";
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
    public LocalTime getSunrise(location location) {
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

            // Convert sunrise time to LocalTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
            LocalTime sunrise = LocalTime.parse(sunriseTime, formatter);

            return  sunrise;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LocalTime getSunset(location location) {
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
            String sunsetTime = json.split("\"sunset\":\"")[1].split("\"")[0];

            // Convert sunset time to LocalTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
            LocalTime sunset = LocalTime.parse(sunsetTime, formatter);

            return sunset;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getResponse(location location) {
        try {
            String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + location.getCity() + "," + location.getCountry() + "&appid=" + apikey;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            String response = sb.toString();
            conn.disconnect();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isForecastForDay(long currentTime, long forecastTime, int day) {
        // Calculate the timestamp for the start of the day
        LocalDate currentDate = LocalDate.now();
        long startOfDay = currentDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond() + (day - 1) * 24 * 60 * 60;

        // Check if the forecast time falls within the desired day
        return forecastTime >= startOfDay && forecastTime < startOfDay + 24 * 60 * 60;
    }
    public double getForecastForDay(location location, int day) {
        try {
            String response = getResponse(location);
            if (response != null) {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray forecastList = jsonResponse.getJSONArray("list");
                long currentTime = System.currentTimeMillis() / 1000; // Convert milliseconds to seconds

                List<Double> temperatureForecast = new ArrayList<>();

                for (int i = 0; i < forecastList.length(); i++) {
                    JSONObject forecastData = forecastList.getJSONObject(i);
                    long forecastTime = forecastData.getLong("dt");

                    // Check if the forecast is for the desired day
                    if (isForecastForDay(currentTime, forecastTime, day) && temperatureForecast.size() < 5) {
                        JSONObject mainData = forecastData.getJSONObject("main");
                        double temperature = mainData.getDouble("temp");

                        // Convert temperature from Kelvin to Celsius
                        temperature = temperature - 273.15;

                        return temperature;
                    }
                }
            } else {
                System.out.println("Failed to fetch forecast data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while fetching forecast data: " + e.getMessage());
        }
        return Double.NaN;
    }

}
