package BusinessLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AirPollutionData {
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
        String apiKey = "16e0c1d404528d59f079dd4571275d8b";
        fetchAirPollutionData(loc, apiKey);
    }

    private void fetchAirPollutionData(location loc, String apiKey) {
        try {
            String apiUrl = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + loc.getLatitude() + "&lon=" + loc.getLongitude() + "&appid=" + apiKey;
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

    private void parseJSONResponse(String jsonData){

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
public double[] PollutionValues()
{
    double values[]={airQualityIndex,carbonMonoxide,nitrogenMonoxide,nitrogenDioxide
    ,ozone,sulphurDioxide,ammonia,particulatePM25,particulatePM10};
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

    // Print method to display the data
    public void printAirPollutionData() {
        System.out.println("Air Quality Index: " + airQualityIndex);
        System.out.println("Carbon Monoxide: " + carbonMonoxide);
        System.out.println("Nitrogen Monoxide: " + nitrogenMonoxide);
        System.out.println("Nitrogen Dioxide: " + nitrogenDioxide);
        System.out.println("Ozone: " + ozone);
        System.out.println("Sulphur Dioxide: " + sulphurDioxide);
        System.out.println("Ammonia: " + ammonia);
        System.out.println("Particulate PM2.5: " + particulatePM25);
        System.out.println("Particulate PM10: " + particulatePM10);
    }
}