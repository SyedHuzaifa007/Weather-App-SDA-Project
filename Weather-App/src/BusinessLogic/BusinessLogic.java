package BusinessLogic;
import java.time.LocalTime;
public interface BusinessLogic {
    public double getTemperature(location location);
    public double getFeelsLike(location location);
    public double getMinTemperature(location location);
    public double getMaxTemperature(location location);
    public LocalTime getSunriseTime(location location);
    public LocalTime getSunsetTime(location location);
    public LocalTime getTimestamp(location location);
}
