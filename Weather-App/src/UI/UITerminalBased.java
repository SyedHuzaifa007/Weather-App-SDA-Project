package UI;
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
            System.out.println("2. Show Weather Forecast");
            System.out.println("3. Show Hourly Forecast");
            System.out.println("4. Show 5 Day Forecast");
            System.out.println("5. Show Air Pollution Data");
            System.out.println("6. Exit");

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
                    showHourlyForecast(location);
                    break;
                case 4:
                    showFiveDayForecast(location);
                    break;
                case 5:
                    showAirPollutionData(location);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void showWeatherDetails(String location) {
        System.out.println("Showing weather details for " + location);
        // Your code to fetch and display weather details goes here
    }

    private void showWeatherForecast(String location) {
        System.out.println("Showing weather forecast for " + location);
    }

    private void showHourlyForecast(String location) {
        System.out.println("Showing hourly forecast for " + location);
    }

    private void showFiveDayForecast(String location) {
        System.out.println("Showing 5 day forecast for " + location);
        // Your code to fetch and display 5 day forecast goes here
    }

    private void showAirPollutionData(String location) {
        System.out.println("Showing air pollution data for " + location);
        // Your code to fetch and display air pollution data goes here
    }

    public static void main(String[] args) {
        UITerminalBased ui = new UITerminalBased();
        ui.run();
    }
}
