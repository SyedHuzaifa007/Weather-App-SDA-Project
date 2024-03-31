package BusinessLogic;
import java.time.LocalTime;
public interface BusinessLogic {
    public double getTemperature(location location);
    public double getFeelsLike(location location);
    public double getMinTemperature(location location);
    public double getMaxTemperature(location location);
    public String getSunriseTime(location location);
    public String getSunsetTime(location location);
    public LocalTime getTimestamp(location location);
    public double[] PollutionValues(location location) ;
    public double[] getDayForecast(location location);



}
