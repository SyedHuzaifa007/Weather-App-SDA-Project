package BusinessLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AirPollutionData extends APIhandler {
    private double airQualityIndex;
    private double carbonMonoxide;
    private double nitrogenMonoxide;
    private double nitrogenDioxide;
    private double ozone;
    private double sulphurDioxide;
    private double ammonia;
    private double particulatePM25;
    private double particulatePM10;

    public AirPollutionData(location loc) {

        fetchAirPollutionData(loc);
    }

    private void fetchAirPollutionData(location loc) {
        try {
            String apiUrl = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + loc.getLatitude() + "&lon=" + loc.getLongitude() + "&appid=" + apikey;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            conn.disconnect();
            String responseData = response.toString();
            // Parse JSON response
            parseJSONResponse(responseData);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to fetch air pollution data.");
        }
    }

    private void parseJSONResponse(String jsonData) {

        // Parse JSON data and set air pollutant values
        this.airQualityIndex = getValueFromJSON(jsonData, "aqi");
        this.carbonMonoxide = getValueFromJSON(jsonData, "co");
        this.nitrogenMonoxide = getValueFromJSON(jsonData, "no");
        this.nitrogenDioxide = getValueFromJSON(jsonData, "no2");
        this.ozone = getValueFromJSON(jsonData, "o3");
        this.sulphurDioxide = getValueFromJSON(jsonData, "so2");
        this.ammonia = getValueFromJSON(jsonData, "nh3");
        this.particulatePM25 = getValueFromJSON(jsonData, "pm2_5");
        this.particulatePM10 = getValueFromJSON(jsonData, "pm10");
    }

    public double[] PollutionValues() {
        double values[] = { airQualityIndex, carbonMonoxide, nitrogenMonoxide, nitrogenDioxide, ozone, sulphurDioxide,
                ammonia, particulatePM25, particulatePM10 };
        return values;
    }

    private double getValueFromJSON(String jsonData, String key) {
        int index = jsonData.indexOf("\"" + key + "\":");
        if (index != -1) {
            int startIndex = index + key.length() + 3;
            int endIndex = jsonData.indexOf(',', startIndex);
            if (endIndex == -1) {
                endIndex = jsonData.indexOf('}', startIndex);
            }
            String valueStr = jsonData.substring(startIndex, endIndex).trim();

            // Remove non-numeric characters
            valueStr = valueStr.replaceAll("[^\\d.]", "");

            try {
                return Double.parseDouble(valueStr);
            } catch (NumberFormatException e) {
                System.out.println("Error parsing value for " + key + ": " + valueStr); // Error handling
                e.printStackTrace();
            }
        }
        return Double.NaN;
    }

    public double getAirQualityIndex() {
        return airQualityIndex;
    }


    public double getCarbonMonoxide() {
        return carbonMonoxide;
    }

    public double getNitrogenMonoxide() {
        return nitrogenMonoxide;
    }

    public double getNitrogenDioxide() {
        return nitrogenDioxide;
    }

    public double getOzone() {
        return ozone;
    }

    public double getSulphurDioxide() {
        return sulphurDioxide;
    }

    public double getAmmonia() {
        return ammonia;
    }

    public double getParticulatePM25() {
        return particulatePM25;
    }

    public double getParticulatePM10() {
        return particulatePM10;
    }

}