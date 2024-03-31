package BusinessLogic;

import DataAccess.DBTxtManager;
import DataAccess.DatabaseSQL;
import UI.GUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.round;

public class Main {
    public static void main(String[] args) throws IOException {

        // Create a Location object with desired city and country
        location location = new location();

        location.getCurrentLocation();

        // Business Logic method
        BusinessLogic businessLogic = new WeatherData();

        // ( Data_Access_Layer Logic)
        ///////////////////////////////////////////////////////////////////////

        // (Initialized Manager)
        CacheManager manager = new CacheManager(location.getCity());

        // (get Data from Cache)
        boolean status = false;
        status = manager.getData(location.getCity());
        // if cant find data store it first
        if (!status) {
            // getting forecast and air pollution data
            double[] forecast = businessLogic.getDayForecast(location);
            double[] values = businessLogic.PollutionValues(location);

            // (store new data in file and update cache)
            manager.storeData(location.getCity(),
                    String.valueOf(location.getLongitude()),
                    String.valueOf(location.getLatitude()),
                    String.valueOf(round(businessLogic.getTemperature(location))),
                    String.valueOf(round(businessLogic.getFeelsLike(location))),
                    String.valueOf(round(businessLogic.getMinTemperature(location))),
                    String.valueOf(round(businessLogic.getMaxTemperature(location))),
                    String.valueOf(businessLogic.getSunriseTime(location)),
                    String.valueOf(businessLogic.getSunsetTime(location)),
                    String.valueOf(businessLogic.getTimestamp(location)),

                    String.valueOf(round(forecast[0])),
                    String.valueOf(round(forecast[1])),
                    String.valueOf(round(forecast[2])),
                    String.valueOf(round(forecast[3])),
                    String.valueOf(round(forecast[4])),

                    String.valueOf(values[0]), String.valueOf(values[1]), String.valueOf(values[2]),
                    String.valueOf(values[3]), String.valueOf(values[4]), String.valueOf(values[5]),
                    String.valueOf(values[6]), String.valueOf(values[7]), String.valueOf(values[8]));
        }

        // file reading

        FileReader filereader = new FileReader("CacheFile.txt");
        BufferedReader reader = new BufferedReader(filereader);

        String loc = reader.readLine();
        String longi = reader.readLine();
        String latitude = reader.readLine();

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

        reader.close();

        // Database Creation
        String data1 = loc + "," + longi + "," + latitude + "," + temp + "," + feel + "," + min + "," + max + "," + rise
                + "," + set + "," + stamp;
        String data2 = loc + "," + day1 + "," + day2 + "," + day3 + "," + day4 + "," + day5;
        String data3 = loc + "," + aqi + "," + CO + "," + NO + "," + NO2 + "," + O3 + "," + SO2 + "," + NH3 + "," + PM25
                + "," + PM10;

        DBTxtManager hello = new DBTxtManager();
        hello.writeToDBTxt(data1, data2, data3);
        DatabaseSQL.main(args);
        ////////////////////////////////////////////////////////////////////////

        // Notification Object
        NotificationManager notify = new NotificationManager();
        String n_weather = notify.GenerateWeatherNotificattions(loc);
        String n_air = notify.generateAirQualityNotification(location);

        GUI G = new GUI(loc, longi, latitude, temp, feel, min, max, rise, set, stamp, day1, day2, day3, day4, day5, aqi,
                CO, NO, NO2, O3, SO2, NH3, PM25, PM10, n_weather, n_air);
    }

    public static void processData(String input) throws IOException {

        // Create a Location object with desired city and country
        location location = new location();

        if (input.contains(",")) {
            // Splitting string into two
            String[] parts = input.split("[,\\s]+");
            // if data is numeric
            if (isNumeric(parts[0]) && isNumeric(parts[1])) {
                // If the first two parts are numeric, assume they are longitude and latitude
                double longitude = Double.parseDouble(parts[0]);
                double latitude = Double.parseDouble(parts[1]);
                location.addManualLocationCountryCity(longitude, latitude);
            } else {
                // Otherwise, assume the first two parts are country and city
                String country = parts[0];
                String city = parts[1];
                location.addManualLocationCoord(country, city);
            }
        } else {
            System.out.println("\n\nThe Format for Searching Is: Country,City or Longitude,Latitude (Plz try Again)\n");
            System.exit(1);
        }

        // Business Logic method
        BusinessLogic businessLogic = new WeatherData();

        // ( Data_Access_Layer Logic)
        ///////////////////////////////////////////////////////////////////////

        // (Initialized Manager)
        CacheManager manager = new CacheManager(location.getCity());

        // (get Data from Cache)
        boolean status = false;
        status = manager.getData(location.getCity());
        // if cant find data store it first
        if (!status) {
            // getting forecast and air pollution data
            double[] forcast = businessLogic.getDayForecast(location);
            double[] values = businessLogic.PollutionValues(location);

            // (store new data in file and update cache)
            manager.storeData(location.getCity(), String.valueOf(location.getLongitude()),
                    String.valueOf(location.getLatitude()),
                    String.valueOf(round(businessLogic.getTemperature(location))),
                    String.valueOf(round(businessLogic.getFeelsLike(location))),
                    String.valueOf(round(businessLogic.getMinTemperature(location))),
                    String.valueOf(round(businessLogic.getMaxTemperature(location))),
                    String.valueOf(businessLogic.getSunriseTime(location)),
                    String.valueOf(businessLogic.getSunsetTime(location)),
                    String.valueOf(businessLogic.getTimestamp(location)),

                    String.valueOf(round(forcast[0])),
                    String.valueOf(round(forcast[1])),
                    String.valueOf(round(forcast[2])),
                    String.valueOf(round(forcast[3])),
                    String.valueOf(round(forcast[4])),

                    String.valueOf(values[0]), String.valueOf(values[1]), String.valueOf(values[2]),
                    String.valueOf(values[3]), String.valueOf(values[4]), String.valueOf(values[5]),
                    String.valueOf(values[6]), String.valueOf(values[7]), String.valueOf(values[8]));
        }
        // file reading
System.out.println(businessLogic.getTimestamp(location));
        FileReader filereader = new FileReader("CacheFile.txt");
        BufferedReader reader = new BufferedReader(filereader);

        String loc = reader.readLine();
        String longi = reader.readLine();
        String latitude = reader.readLine();

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

        reader.close();

        // Database Creation
        String data1 = loc + "," + longi + "," + latitude + "," + temp + "," + feel + "," + min + "," + max + "," + rise + "," + set + "," + stamp;
        String data2 = loc + "," + day1 + "," + day2 + "," + day3 + "," + day4 + "," + day5;
        String data3 = loc + "," + aqi + "," + CO + "," + NO + "," + NO2 + "," + O3 + "," + SO2 + "," + NH3 + "," + PM25 + "," + PM10;

        DBTxtManager hello = new DBTxtManager();
        hello.writeToDBTxt(data1, data2, data3);
        String[] args = new String[0];
        DatabaseSQL.main(args);

        ////////////////////////////////////////////////////////////////////////

        // Notification Object
        NotificationManager notify = new NotificationManager();
        String n_weather = notify.GenerateWeatherNotificattions(loc);
        String n_air = notify.generateAirQualityNotification(location);

        GUI G = new GUI(loc, longi, latitude, temp, feel, min, max, rise, set, stamp, day1, day2, day3, day4, day5, aqi,
                CO, NO, NO2, O3, SO2, NH3, PM25, PM10, n_weather, n_air);
    }

    private static boolean isNumeric(String part) {
        return part.matches("-?\\d+(\\.\\d+)?");
    }
}