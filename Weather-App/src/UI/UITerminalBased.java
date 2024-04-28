package UI;
import BusinessLogic.*;
import DataAccess.DBTxtManager;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
        System.out.println("6. To Change Location");
    }
    private  ArrayList<String> storedataTXT(String location,String location1, CacheManager manager) throws IOException {
        location Object_Location=new location();
        Object_Location.setCity(location);
        Object_Location.addManualLocationCoord(location1, location);
        manager = new CacheManager(Object_Location);
        manager.getData(Object_Location.getCity());
        ArrayList<String> data = manager.readCacheFile();
        ArrayList<String> db = manager.readCacheDB();
        return db;
    }
    private void storeDataDB(ArrayList<String> db,String[]args,DBTxtManager hello) throws Exception {

        hello.writeToDBTxt(db.get(0), db.get(1), db.get(2));
        DatabaseSQL.main(args);

    }
    public void run(String[]args,CacheManager manager,DBTxtManager hello) throws Exception {
        while (true) {
            System.out.println("Please enter a Country");
            String location1 = scanner.nextLine();
            System.out.println("Please enter a city");
            String location =scanner.nextLine();
            ArrayList<String>db=storedataTXT(location,location1,manager);
            storeDataDB(db,args,hello);
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
                case 6:
                    continue;
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
        String text= Object_Notify.GenerateWeatherNotificattions(Location);
         System.out.println(text);
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
        String text= Object_Notify.generateAirQualityNotification(Object_Location);
        System.out.println(text);
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
    }
}
