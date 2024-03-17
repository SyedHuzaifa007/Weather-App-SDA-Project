package BusinessLogic;

public class Main {
    public static void main(String[] args) {
        APIhandler apiHandler = new APIhandler(); // Replace "API_KEY_HERE" with your actual API key

        // Create a Location object with desired city and country
        location location = new location(); // Replace with actual city name and country code
        location.setCountry("Pakistan");
        location.setCity("Lahore");
        // Call getCurrentData method to fetch weather data
        WeatherData weatherData = apiHandler.getCurrentData(null);

        System.out.println(apiHandler.getSunsetSunrise(location));


        location Location = new location();
        AirPollutionData airPollutionData = new AirPollutionData(location);

        // Print air pollution data
        airPollutionData.printAirPollutionData();

    }
}
