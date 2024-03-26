package UI;
import BusinessLogic.AirPollutionData;
import BusinessLogic.WeatherData;
import BusinessLogic.WeatherForecast;
import BusinessLogic.location;

import java.util.Scanner;

public class UITerminalBased {
    private Scanner scanner;

    public UITerminalBased() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Please enter a city/country:");
            String location = scanner.nextLine();

            System.out.println("Choose an option:");
            System.out.println("1. Show Weather Details");
            System.out.println("2. Show 5 Day Forecast");
            System.out.println("3 Show Air Pollution Data");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    showWeatherDetails(location);
                    break;
                case 2:
                    showWeatherForecast(location);
                    break;
                case 3:
                    showAirPollutionData(location);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void showWeatherDetails(String location) {
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
        // Your code to fetch and display weather details goes here
    }

    private void showWeatherForecast(String location) {
        WeatherData Object_WeatherData= new WeatherData();
        location Object_Location=new location();
        Object_Location.setCity(location);
        WeatherForecast Object_Forecast=new WeatherForecast();

        System.out.println("Showing weather forecast for " + location);
        Object_Forecast.forecastData(Object_Location);
    }
    private void showAirPollutionData(String location) {
        location Object_Location=new location();
        Object_Location.setCity(location);
        AirPollutionData Object_AirPollution=new AirPollutionData(Object_Location);
        System.out.println("Showing air pollution data for " + location);
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
