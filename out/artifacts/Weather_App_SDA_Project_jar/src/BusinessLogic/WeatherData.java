package BusinessLogic;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class WeatherData implements BusinessLogic {

    public String GenerateWeatherNotificattions(String location1){
        NotificationManager notificationManager=new NotificationManager();
        return notificationManager.GenerateWeatherNotificattions(location1);
    }
    public String generateAirQualityNotification(location location1)
    {
        NotificationManager notificationManager=new NotificationManager();
        return notificationManager.generateAirQualityNotification(location1);

    }// Getter methods
    public double getTemperature(location location) {
        WeatherInfo apIhandler=new WeatherInfo();
        return apIhandler.gettemperature(location);

    }
    public double[] PollutionValues(location location) {

        AirPollutionData air=new AirPollutionData(location);
        double values[] = { air.getAirQualityIndex(),air.getCarbonMonoxide(),air.getNitrogenMonoxide(),air.getNitrogenDioxide(),air.getOzone(), air.getSulphurDioxide(),
         air.getAmmonia(),air.getParticulatePM25(),air.getParticulatePM10()};
        return values;
    }
    public double[] getDayForecast(location location){
   WeatherForecast forecast=new WeatherForecast();
      double values[]={forecast.getDay1Forecast(location),forecast.getDay2Forecast(location),forecast.getDay3Forecast(location),forecast.getDay4Forecast(location),forecast.getDay5Forecast(location) };
       return values;

    }
    public double getFeelsLike(location location) {
        WeatherInfo apIhandler=new WeatherInfo();
        return   apIhandler.feelsliketemperature(location);
         }

    public double getMinTemperature(location location) {
        WeatherInfo apIhandler=new WeatherInfo();
        return  apIhandler.getmintemperature(location);
          }

    public double getMaxTemperature(location location) {
        WeatherInfo apIhandler=new WeatherInfo();
        return apIhandler.getmaxtemperature(location);
    }

    public String getSunriseTime(location location) {
        WeatherInfo apIhandler=new WeatherInfo();
        return apIhandler.getsunrise(location);
    }

    public String getSunsetTime(location location) {
        WeatherInfo apIhandler=new WeatherInfo();
        return apIhandler.getsunset(location);
    }

    public LocalTime getTimestamp(location location) {
        WeatherInfo apIhandler=new WeatherInfo();
        return apIhandler.getTimestamp(location);
    }


}
