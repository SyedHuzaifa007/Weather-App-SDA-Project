package BusinessLogic;
import BusinessLogic.WeatherData;
import BusinessLogic.location;
public class NotificationManager {
   public void GenerateWeatherNotificattions(String location){
       APIhandler apiHandler1 = new APIhandler();
       location loc1=new location();
       loc1.setCity(location);
      double temp=apiHandler1.getTemperatures(loc1);
        if(temp>=45)
        {
            System.out.println("The temperature Conditions are very Hot");

        }
        else if(temp<=-10)
        {
            System.out.println("The temperature Conditions are very Cold");
        }
    }
    void generateAirQualityNotification(){

    }
}
