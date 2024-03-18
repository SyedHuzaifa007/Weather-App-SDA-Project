package BusinessLogic;
import BusinessLogic.WeatherData;
import BusinessLogic.location;
public class NotificationManager {
   public void GenerateWeatherNotificattions(String location1){
       APIhandler apiHandler1 = new APIhandler();
       location loc1=new location();
       loc1.setCity(location1);
      double temp=apiHandler1.gettemperature(loc1);
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
      /*/  double airQualityIndex =fetchAirPollutionData;

        // Define thresholds for different air quality levels
        int good_air_quality = 50;
        int medium_air_quality = 100;
        int bad_air_quality = 150;
        // Add more thresholds as needed

        // Check the air quality index against the thresholds and generate appropriate notifications
        if (airQualityIndex <= good_air_quality) {
            System.out.println("Air quality is good. No notification needed.");
        } else if (airQualityIndex <= medium_air_quality) {
            System.out.println("Air quality is moderate. Consider taking precautions.");
            // Generate notification code here
        } else if (airQualityIndex <= bad_air_quality) {
            System.out.println("Air quality is unhealthy. Take necessary actions.");
            // Generate notification code here
        } else {
            System.out.println("Air quality is very unhealthy or hazardous. Take immediate actions.");
            // Generate notification code here  / */
        }
    }

