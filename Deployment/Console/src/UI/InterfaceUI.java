import BusinessLogic.NotificationManager;

public interface InterfaceUI {
    public void DisplayWeatherData(String location);
    public void DisplayBasicWeatherData(String location);
    public void showAirPollutionData(String location);
    public void DisplayWeatherForecast(String location);
    public void DisplayNotification(NotificationManager Object_Notify, String Location);
}
