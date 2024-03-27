package UI;
import BusinessLogic.*;

import java.util.Scanner;

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

    public void run() {
        while (true) {
            System.out.println("Please enter a city/country:");
            String location = scanner.nextLine();
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
        System.out.println("Minimum Temperature: "+ Object_WeatherData.getMinTemperature(Object_Location));
        System.out.println("Maximum Temperature: "+ Object_WeatherData.getMaxTemperature(Object_Location));
        System.out.println("Average Temperature: "+ Object_WeatherData.getTemperature(Object_Location));


    }
    @Override
    public void DisplayWeatherData(String location) {
        WeatherData Object_WeatherData= new WeatherData();
        location Object_Location=new location();
        Object_Location.setCity(location);
        System.out.println("Showing weather details for Time: "+Object_WeatherData.getTimestamp(Object_Location)+" " + location);
        System.out.println("Minimum Temperature: "+ Object_WeatherData.getMinTemperature(Object_Location));
        System.out.println("Maximum Temperature: "+ Object_WeatherData.getMaxTemperature(Object_Location));
        System.out.println("Average Temperature: "+ Object_WeatherData.getTemperature(Object_Location));
        System.out.println("FeelsLike Temperature: "+ Object_WeatherData.getFeelsLike(Object_Location));
        System.out.println("Sunrise Time: "+ Object_WeatherData.getSunriseTime(Object_Location));
        System.out.println("Sunset Time: "+ Object_WeatherData.getSunsetTime(Object_Location));
        System.out.println("Notification: ");
        DisplayNotification(Object_Notify,location);
        // Your code to fetch and display weather details goes here
    }

    @Override
    public void DisplayWeatherForecast(String location) {
        WeatherData Object_WeatherData= new WeatherData();
        location Object_Location=new location();
        Object_Location.setCity(location);
        WeatherForecast Object_Forecast=new WeatherForecast();

        System.out.println("Showing weather forecast for " + location);
        Object_Forecast.getDay5Forecast(Object_Location);
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
        System.out.println("NitrogenMonoxide: "+ Values_PollutionData[1]);
        System.out.println("NitrogenDioxide: "+ Values_PollutionData[2]);
        System.out.println("Ozone: "+ Values_PollutionData[3]);
        System.out.println("SulphurDioxide: "+ Values_PollutionData[4]);
        System.out.println("Ammonia: "+ Values_PollutionData[5]);
        System.out.println("ParticulatePM25: "+ Values_PollutionData[6]);
        System.out.println("ParticulatePM10: "+ Values_PollutionData[7]);
        // Your code to fetch and display air pollution data goes here
    }

    public static void main(String[] args) {
        UITerminalBased ui = new UITerminalBased();
        ui.run();
    }
}
