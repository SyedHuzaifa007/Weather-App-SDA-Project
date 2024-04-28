package BusinessLogic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.ZoneOffset;



abstract  class APIhandler {
    protected String apikey;
    public APIhandler() {
        apikey = "16e0c1d404528d59f079dd4571275d8b";
    }
   protected String getResponse(location location) {
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
};

