import java.time.LocalDateTime;

public class WeatherData {
    private double temperature;
    private String feelsLike;
    private double minTemperature;
    private double maxTemperature;
    private LocalDateTime sunriseTime;
    private LocalDateTime sunsetTime;
    private LocalDateTime timestamp;
    private APIhandler1 api;

    public WeatherData() {
        this.temperature = 0.0;
        this.feelsLike = "";
        this.minTemperature = 0.0;
        this.maxTemperature = 0.0;
        this.sunriseTime = null;
        this.sunsetTime = null;
        this.timestamp = null;
    }

    public WeatherData(double temperature, String feelsLike, double minTemperature, double maxTemperature,
                       LocalDateTime sunriseTime, LocalDateTime sunsetTime, LocalDateTime timestamp) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.timestamp = timestamp;
    }

    // Setter methods
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setSunriseTime(LocalDateTime sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public void setSunsetTime(LocalDateTime sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Getter methods
    public double getTemperature() {
        return temperature;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public LocalDateTime getSunriseTime() {
        return sunriseTime;
    }

    public LocalDateTime getSunsetTime() {
        return sunsetTime;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //public void getCurrentData(){
    //api.getCurrentData();
    //}

}
