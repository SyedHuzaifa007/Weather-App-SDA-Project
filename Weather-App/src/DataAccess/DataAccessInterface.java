package DataAccess;

import BusinessLogic.location;

import java.time.LocalTime;

public interface DataAccessInterface {
    public void insertWeatherData();
    public void insertWeatherForecast();
    public void insertAirPollutionData();
    public void getdata();
}
