package BusinessLogic;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecast extends APIhandler{

    public double getDay1Forecast(location location) {
         return getForecastForDay(location, 1);

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

    public double getDay2Forecast(location location) {

        return getForecastForDay(location, 2);

    }
    public double getDay3Forecast(location location) {
        return getForecastForDay(location, 3);

    }
    public double getDay4Forecast(location location) {
        return getForecastForDay(location, 4);

    }
    public double getDay5Forecast(location location) {
        return getForecastForDay(location, 5);

    }

}
