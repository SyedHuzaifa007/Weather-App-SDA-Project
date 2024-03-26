package BusinessLogic;

import java.time.LocalTime;

public interface APIHandlerInterface {

    public double getmaxtemperature(location location);
    public double getmintemperature(location location);
    public double feelsliketemperature(location location);
    public double gettemperature(location location);
    public LocalTime getSunrise(location location);
    public LocalTime getSunset(location location);
    public double getForecastForDay(location location, int day);

}
