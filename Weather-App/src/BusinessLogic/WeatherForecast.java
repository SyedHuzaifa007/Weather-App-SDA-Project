package BusinessLogic;
import java.util.List;

public class WeatherForecast {

    public void forecastData(location forecast) {
        APIhandler api = new APIhandler();

        List<Double> temperatureForecast = api.getFiveDayForecast(forecast);
        System.out.println("Five-day Weather Forecast:");

        int i=1;
        for (double temperature : temperatureForecast) {
            System.out.println("Temperature for Day"+i+": " + temperature);
            i++;

        }
    }
}
