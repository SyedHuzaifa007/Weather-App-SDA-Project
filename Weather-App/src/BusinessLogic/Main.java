package BusinessLogic;

import javax.xml.stream.Location;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        APIhandler apiHandler = new APIhandler(); // Replace "API_KEY_HERE" with your actual API key


        // Create a Location object with desired city and country
        location location = new location(); // Replace with actual city name and country code
        location.setCountry("Pakistan");
        location.setCity("Faisalabad");
        WeatherForecast forecast=new WeatherForecast();
        forecast.forecastData(location);
        System.exit(0);
        // Call getCurrentData method to fetch weather data
        // System.out.println(apiHandler.getmaxtemperature(location));
        BusinessLogic businessLogic=new WeatherData();
        //System.out.println( businessLogic.getSunsetTime(location));

                        // ( Data_Access_Layer Logic)
        /////////////////////////////////////////////////////////////////////////

                        // (Get the location)
        String Location = location.getCity();

                        // (Initialized Manager)
        CacheManager manager = new CacheManager(Location);

                        // (get Data from Cache)
        boolean status = false;
        status = manager.getData(Location);

                        // (if cache does have required data stored and Location File is also
                        //  not present, we create new file of location and update cache)
        if(!status)
        {
                        // (API Calls and store data from API to variables)
            String temp = String.valueOf(businessLogic.getTemperature(location));
            String feel = String.valueOf(businessLogic.getFeelsLike(location));
            String min = String.valueOf(businessLogic.getMinTemperature(location));
            String max = String.valueOf(businessLogic.getMaxTemperature(location));
            String sunrise = String.valueOf(businessLogic.getSunriseTime(location));
            String sunset = String.valueOf(businessLogic.getSunsetTime(location));
            String stamp = String.valueOf(businessLogic.getTimestamp(location));

                        // (store new data in file and update cache)
            manager.storeData(Location,temp,feel,min,max,sunrise,sunset,stamp);
        }

        ////////////////////////////////////////////////////////////////////////
    }
}
