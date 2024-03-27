package BusinessLogic;
import BusinessLogic.WeatherData;
import BusinessLogic.location;
public class NotificationManager {
    public String GenerateWeatherNotificattions(String location1) {
        APIhandler apiHandler1 = new APIhandler();
        location loc1 = new location();
        loc1.setCity(location1);
        String text="Normal Temperatures";
        double temp = apiHandler1.gettemperature(loc1);
        if (temp >= 45) {
            text="Hot Temperatures";
            return text;

        } else if (temp <= -10) {
            text="Cold temperatures";
            return text;
        }
        return text;
    }

    public String generateAirQualityNotification(location location1) {
        location loc1 = new location();
        loc1 = location1;
        AirPollutionData a = new AirPollutionData(loc1);

        double values[]=a.PollutionValues();
        double[] breakpoints = {0.0, 50.0, 100.0, 150.0, 200.0, 300.0, 400.0, 500.0};
        double[] AQIValues = {0.0, 50.0, 100.0, 150.0, 200.0, 300.0, 400.0, 500.0};

        // Find the AQI for each pollutant and store the maximum AQI
        int maxAQI = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            // Find the appropriate AQI category based on concentration
            int category = -1;
            for (int j = 0; j < breakpoints.length; j++) {
                if (values[i] <= breakpoints[j]) {
                    category = j;
                    break;
                }
            }

            // Interpolate AQI value based on the category
            double AQI;
            if (category == 0 || category == -1) {
                AQI = AQIValues[0];
            } else if (category == breakpoints.length - 1) {
                AQI = AQIValues[category];
            } else {
                AQI = ((AQIValues[category] - AQIValues[category - 1]) / (breakpoints[category] - breakpoints[category - 1])) *
                        (values[i] - breakpoints[category - 1]) + AQIValues[category - 1];
            }

            // Update the maximum AQI
            if (Math.round(AQI) > maxAQI) {
                maxAQI = (int) Math.round(AQI);
            }
        }
        String text;
        if(maxAQI>100) {
            text="Hazardous Air Quality ";
            return text;
        }
        else
        {
            text = "Safe Air Quality ";
            return text;
        }
    }
}
