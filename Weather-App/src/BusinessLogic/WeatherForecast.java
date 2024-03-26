package BusinessLogic;
import java.util.List;

public class WeatherForecast extends APIhandler{

    public double getDay1Forecast(location location) {
        APIHandlerInterface api = new APIhandler();
         return api.getForecastForDay(location, 1);

    }

    public double getDay2Forecast(location location) {
        APIhandler api = new APIhandler();
        return api.getForecastForDay(location, 2);

    }
    public double getDay3Forecast(location location) {
        APIhandler api = new APIhandler();
        return api.getForecastForDay(location, 3);

    }
    public double getDay4Forecast(location location) {
        APIhandler api = new APIhandler();
        return api.getForecastForDay(location, 4);

    }
    public double getDay5Forecast(location location) {
        APIhandler api = new APIhandler();
        return api.getForecastForDay(location, 5);

    }
//    public void forecastForDay1(location forecast) {
//        APIhandler api = new APIhandler();
//
//        List<Double> temperatureForecast = api.getFiveDayForecast(forecast);
//        System.out.println("Five-day Weather Forecast:");
//
//        int i=1;
//        for (double temperature : temperatureForecast) {
//            System.out.println("Temperature for Day"+i+": " + temperature);
//            break;
//
//        }
//    }
}
