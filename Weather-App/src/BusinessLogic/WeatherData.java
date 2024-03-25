package BusinessLogic;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class WeatherData implements BusinessLogic {
    private double temperature;
    private double feelsLike;
    private double minTemperature;
    private double maxTemperature;
    private LocalTime sunriseTime;
    private LocalTime sunsetTime;
    private LocalTime timestamp;


    public WeatherData() {
        this.temperature = 0.0;
        this.feelsLike = 0.0;
        this.minTemperature = 0.0;
        this.maxTemperature = 0.0;
        this.sunriseTime = null;
        this.sunsetTime = null;
        this.timestamp = null;
    }

    public WeatherData(double temp)
    {
        temperature=temp;
    }
    public WeatherData(double temperature, double feelsLike, double minTemperature, double maxTemperature,
                       LocalTime sunriseTime, LocalTime sunsetTime, LocalTime timestamp) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.timestamp = timestamp;
    }


    // Getter methods
    public double getTemperature(location location) {
        APIhandler apIhandler=new APIhandler();
        temperature= apIhandler.gettemperature(location);
        return temperature;
    }

    public double getFeelsLike(location location) {
        APIhandler apIhandler=new APIhandler();
        feelsLike=  apIhandler.feelsliketemperature(location);
        return feelsLike;
    }

    public double getMinTemperature(location location) {
        APIhandler apIhandler=new APIhandler();
        minTemperature= apIhandler.getmintemperature(location);
        return minTemperature;
    }

    public double getMaxTemperature(location location) {
        APIhandler apIhandler=new APIhandler();
         maxTemperature= apIhandler.getmaxtemperature(location);
         return maxTemperature;
    }

    public LocalTime getSunriseTime(location location) {
        APIhandler apIhandler=new APIhandler();
       sunriseTime=apIhandler.getSunrise(location);
       return sunriseTime;
    }

    public LocalTime getSunsetTime(location location) {
        APIhandler apIhandler=new APIhandler();
        sunsetTime= apIhandler.getSunset(location);
        return sunsetTime;
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


}
