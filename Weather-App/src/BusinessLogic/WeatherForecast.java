package BusinessLogic;
import java.util.ArrayList;
import java.util.List;
public class WeatherForecast {

    public void forecastData(location forecast) {
        APIhandler api = new APIhandler();

        List<WeatherData> weather = new ArrayList<WeatherData>();
        weather = api.getFiveDayForecast(forecast);
        System.out.println("Five-day Weather Forecast:");

        for (WeatherData data : weather) {
            System.out.println("Temperature: " + data.getTemperature(forecast));
            // Print other weather data attributes as needed
        }
    }
}
