package UI;
import BusinessLogic.*;
import DataAccess.DBTxtManager;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.FileReader;
import static java.lang.Math.round;
import DataAccess.DatabaseSQL;
public class UITerminalBased implements InterfaceUI {
    private Scanner scanner;
    private NotificationManager Object_Notify;
    public UITerminalBased() {
        scanner = new Scanner(System.in);
    }
    private void displaymenu(){

        System.out.println("Choose an option:");
        System.out.println("1. Show Basic Weather Data");
        System.out.println("2. Show Weather Details");
        System.out.println("3. Show 5 Day Forecast");
        System.out.println("4. Show Air Pollution Data");
        System.out.println("5. Exit");

    }
    private void storedataTXT(String location,String location1){
        location Object_Location=new location();
        Object_Location.setCity(location);
        BusinessLogic businessLogic = new WeatherData();

        // ( Data_Access_Layer Logic)
        ///////////////////////////////////////////////////////////////////////

        // (Initialized Manager)
        CacheManager manager = new CacheManager(location);

        // (get Data from Cache)
        boolean status = false;
        status = manager.getData(location);
        // if cant find data store it first
        if (!status) {
            Object_Location.addManualLocationCoord(location1, location);
            // getting forecast and air pollution data
            double[] forecast = businessLogic.getDayForecast(Object_Location);
            double[] values = businessLogic.PollutionValues(Object_Location);

            // (store new data in file and update cache)
            LocalDate currentDate = LocalDate.now();
            String current = String.valueOf(currentDate);

            manager.storeData(Object_Location.getCity(),
                    String.valueOf(Object_Location.getLongitude()),
                    String.valueOf(Object_Location.getLatitude()),
                    String.valueOf(round(businessLogic.getTemperature(Object_Location))),
                    String.valueOf(round(businessLogic.getFeelsLike(Object_Location))),
                    String.valueOf(round(businessLogic.getMinTemperature(Object_Location))),
                    String.valueOf(round(businessLogic.getMaxTemperature(Object_Location))),
                    String.valueOf(businessLogic.getSunriseTime(Object_Location)),
                    String.valueOf(businessLogic.getSunsetTime(Object_Location)),
                    String.valueOf(businessLogic.getTimestamp(Object_Location)),

                    String.valueOf(round(forecast[0])),
                    String.valueOf(round(forecast[1])),
                    String.valueOf(round(forecast[2])),
                    String.valueOf(round(forecast[3])),
                    String.valueOf(round(forecast[4])),

                    String.valueOf(values[0]), String.valueOf(values[1]), String.valueOf(values[2]),
                    String.valueOf(values[3]), String.valueOf(values[4]), String.valueOf(values[5]),
                    String.valueOf(values[6]), String.valueOf(values[7]), String.valueOf(values[8]),

                    current
            );
        }
    }
    private void storeDataDB() throws Exception {
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
       String[] arg = new String[0];
       DatabaseSQL.main(arg);

    }
    public void run() throws Exception {
        while (true) {
            System.out.println("Please enter a Country");
            String location1 = scanner.nextLine();
            System.out.println("Please enter a city");
            String location =scanner.nextLine();
            storedataTXT(location,location1);
            storeDataDB();
            displaymenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    DisplayBasicWeatherData(location);
                    break;
                case 2:
                    DisplayWeatherData(location);
                    break;
                case 3:
                    DisplayWeatherForecast(location);
                    break;
                case 4:
                    showAirPollutionData(location);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    @Override
    public void DisplayNotification(NotificationManager Object_Notify, String Location)
    {
        DBTxtManager obj=new DBTxtManager();
        location Object_Location=new location();
        Object_Location.setCity(Location);
        Object_Notify=new NotificationManager();
        Object_Notify.GenerateWeatherNotificattions(Location);
    }
    @Override
    public void DisplayBasicWeatherData(String location)
    {
        WeatherData Object_WeatherData= new WeatherData();
        location Object_Location=new location();
        Object_Location.setCity(location);

        System.out.println("Showing weather details for: " + location);
        System.out.println("Minimum Temperature: "+ round(Object_WeatherData.getMinTemperature(Object_Location)));
        System.out.println("Maximum Temperature: "+ round(Object_WeatherData.getMaxTemperature(Object_Location)));
        System.out.println("Average Temperature: "+ round(Object_WeatherData.getTemperature(Object_Location)));

    }
    @Override
    public void DisplayWeatherData(String location) {
        WeatherData Object_WeatherData= new WeatherData();
        location Object_Location=new location();
        Object_Location.setCity(location);

        System.out.println("Showing weather details for Time: "+Object_WeatherData.getTimestamp(Object_Location)+ " "+ location);
        System.out.println("Minimum Temperature: "+ round(Object_WeatherData.getMinTemperature(Object_Location)));
        System.out.println("Maximum Temperature: "+ round(Object_WeatherData.getMaxTemperature(Object_Location)));

        System.out.println("Average Temperature: "+ round(Object_WeatherData.getTemperature(Object_Location)));
        System.out.println("FeelsLike Temperature: "+ round(Object_WeatherData.getFeelsLike(Object_Location)));
        System.out.println("Sunrise Time: "+ Object_WeatherData.getSunriseTime(Object_Location));

        System.out.println("Sunset Time: "+ Object_WeatherData.getSunsetTime(Object_Location));
        System.out.println("Notification: ");
        DisplayNotification(Object_Notify,location);
    }

    @Override
    public void DisplayWeatherForecast(String location) {
        WeatherData Object_WeatherData= new WeatherData();
        location Object_Location=new location();
        Object_Location.setCity(location);
        WeatherForecast Object_Forecast=new WeatherForecast();

        System.out.println("Showing weather forecast for " + location);
        System.out.println("First Day: "+ round(Object_Forecast.getDay1Forecast(Object_Location))+ " Celsius ");
        System.out.println("Second Day: "+ round(Object_Forecast.getDay2Forecast(Object_Location))+ " Celsius");
        System.out.println("Third Day: "+ round(Object_Forecast.getDay3Forecast(Object_Location))+ " Celsius");
        System.out.println("Fourth Day: "+ round(Object_Forecast.getDay4Forecast(Object_Location))+ " Celsius");
        System.out.println("Fifth Day: "+ round(Object_Forecast.getDay5Forecast(Object_Location))+ " Celsius");
    }
    @Override
    public void showAirPollutionData(String location) {
        location Object_Location=new location();
        Object_Location.setCity(location);
        AirPollutionData Object_AirPollution=new AirPollutionData(Object_Location);
        System.out.println("Showing air pollution data for " + location);
        Object_Notify=new NotificationManager();
        Object_Notify.generateAirQualityNotification(Object_Location);
        double Values_PollutionData[]=Object_AirPollution.PollutionValues();
        System.out.println("CarbonMonoxide: "+ Values_PollutionData[0]);
        System.out.println("NitrogenMonoxide: "+Values_PollutionData[1]);
        System.out.println("NitrogenDioxide: "+ Values_PollutionData[2]);
        System.out.println("Ozone: "+ Values_PollutionData[3]);
        System.out.println("SulphurDioxide: "+ Values_PollutionData[4]);
        System.out.println("Ammonia: "+ Values_PollutionData[5]);
        System.out.println("ParticulatePM25: "+ Values_PollutionData[6]);
        System.out.println("ParticulatePM10: "+ Values_PollutionData[7]);
        // Your code to fetch and display air pollution data goes here
    }

    public static void main(String[] args) throws Exception {
        UITerminalBased ui = new UITerminalBased();
        ui.run();
    }
}
