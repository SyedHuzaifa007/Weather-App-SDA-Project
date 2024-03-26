package BusinessLogic;

import UI.GUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        APIhandler apiHandler = new APIhandler(); // Replace "API_KEY_HERE" with your actual API key


        // Create a Location object with desired city and country
        location location = new location();

        location.getCurrentLocation();

        // air pollution class methods
        AirPollutionData air = new AirPollutionData(location);

        // weather Forecast class methods
        WeatherForecast forecast = new WeatherForecast();

        //Business Logic method
        BusinessLogic businessLogic = new WeatherData();


                         //( Data_Access_Layer Logic)
        ///////////////////////////////////////////////////////////////////////

                        // (Initialized Manager)
        CacheManager manager = new CacheManager(location.getCity());

                        // (get Data from Cache)
        boolean status = false;
        status = manager.getData(location.getCity());
                        // if cant find data store it first
        if(!status)
        {
            // getting airPollution values in values
            double[] values = air.PollutionValues();

                        // (store new data in file and update cache)
            manager.storeData(location.getCity(),String.valueOf(location.getLongitude()),String.valueOf(location.getLatitude()),String.valueOf(businessLogic.getTemperature(location)),
                                String.valueOf(businessLogic.getFeelsLike(location)),String.valueOf(businessLogic.getMinTemperature(location)),String.valueOf(businessLogic.getMaxTemperature(location)) ,
                                String.valueOf(businessLogic.getSunriseTime(location)),String.valueOf(businessLogic.getSunsetTime(location)), String.valueOf(businessLogic.getTimestamp(location)),

                                String.valueOf(forecast.getDay1Forecast(location)), String.valueOf(forecast.getDay2Forecast(location)), String.valueOf(forecast.getDay3Forecast(location)),
                                String.valueOf(forecast.getDay4Forecast(location)), String.valueOf(forecast.getDay5Forecast(location)),

                                String.valueOf(values[0]),String.valueOf(values[1]),String.valueOf(values[2]),String.valueOf(values[3]), String.valueOf(values[4]), String.valueOf(values[5]),
                                String.valueOf(values[6]), String.valueOf(values[7]), String.valueOf(values[8]));
        }

        //file reading

        FileReader filereader = new FileReader("CacheFile.txt");
        BufferedReader reader = new BufferedReader(filereader);

        String loc = reader.readLine();
        String longi = reader.readLine();
        String lati = reader.readLine();

        String temp = reader.readLine();
        String feel = reader.readLine();
        String min = reader.readLine();
        String max = reader.readLine();
        String rise = reader.readLine();
        String set = reader.readLine();
        String stamp = reader.readLine();

        String day1 = reader.readLine();
        String day2 = reader.readLine();
        String day3 = reader.readLine();
        String day4 = reader.readLine();
        String day5 = reader.readLine();

        String aqi = reader.readLine();
        String CO = reader.readLine();
        String NO = reader.readLine();
        String NO2 = reader.readLine();
        String O3 = reader.readLine();
        String SO2 = reader.readLine();
        String NH3 = reader.readLine();
        String PM25 = reader.readLine();
        String PM10 = reader.readLine();

        ////////////////////////////////////////////////////////////////////////

        GUI G = new GUI();
    }
}
