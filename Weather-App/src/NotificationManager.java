import BusinessLogic.WeatherData;
import BusinessLogic.location;
public class NotificationManager {
    void GenerateWeatherNotificattions(String location){

        if(location.equals("Lahore"))
        {
            System.out.println("This city is not good");
        }
        System.out.println(location);

    }
    void generateAirQualityNotification(){

    }
}
