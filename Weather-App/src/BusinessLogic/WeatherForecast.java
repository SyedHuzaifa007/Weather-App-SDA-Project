package BusinessLogic;
import java.util.ArrayList;
import java.util.List;
public class WeatherForecast {

public void forecastData(location forecast){
    APIhandler api=new APIhandler();

    List<WeatherData> weather=new ArrayList<WeatherData>();
    weather=api.getFiveDayForecast(forecast);
    System.out.println("Five-day Weather Forecast:");
    for (WeatherData data : weather) {
        //System.out.println("Date: " + data.getDate());
        System.out.println("Max Temperature: " + data.getMaxTemperature(forecast));
        System.out.println("Min Temperature: " + data.getMinTemperature(forecast));
        //System.out.println("Weather Condition: " + data.getWeatherCondition());
        // Add more attributes as needed
        System.out.println();
    }

}
}
