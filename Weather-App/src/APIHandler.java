import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class APIHandler {
    public static void main(String[] args) {
        try {
            String apiKey = "16e0c1d404528d59f079dd4571275d8b";
            String city = "Lahore";
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extract and print the temperature
            if (jsonResponse.has("main")) {
                JSONObject mainObject = jsonResponse.getJSONObject("main");
                if (mainObject.has("temp")) {
                    double temperature = mainObject.getDouble("temp");
                    System.out.println("Temperature in " + city + ": " + temperature + " °C");
                }
            } else {
                System.out.println("Unable to fetch temperature information.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
