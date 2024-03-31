package BusinessLogic;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;
import java.time.*;
import java.util.Scanner;
import java.util.TimeZone;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.format.DateTimeFormatter;

public class WeatherInfo extends APIhandler{
    private double temperature;
    private double feelsLike;
    private double minTemperature;
    private double maxTemperature;
    private LocalTime sunriseTime;
    private LocalTime sunsetTime;
    private LocalTime timestamp;


    public WeatherInfo() {
        this.temperature = 0.0;
        this.feelsLike = 0.0;
        this.minTemperature = 0.0;
        this.maxTemperature = 0.0;
        this.sunriseTime = null;
        this.sunsetTime = null;
        this.timestamp = null;
    }
    public WeatherInfo(double temp)
    {
        temperature=temp;
    }
    public WeatherInfo(double temperature, double feelsLike, double minTemperature, double maxTemperature,
                       LocalTime sunriseTime, LocalTime sunsetTime, LocalTime timestamp) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.timestamp = timestamp;
    }
    public double getmaxtemperature(location location) {
        try {
            String response;
            if (location == null) {
                // If location is not provided, use current location
                location loc = new location();
                try {
                    loc.getCurrentLocation();
                    response = getResponse(loc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1; // Handle error gracefully
                }
            } else {
                response = getResponse(location);
            }

            if (response != null) {
                // Extract max temperature from the response
                String maxTemperatureStr = response.split("\"temp_max\":")[1].split(",")[0];
                return Double.parseDouble(maxTemperatureStr) - 273.15;
            } else {
                return -1; // Handle error gracefully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error gracefully
        }
    }
    public LocalTime getTimestamp(location location) {
        try {
            // Fetch current timestamp from an external service
            URL url = new URL("https://worldtimeapi.org/api/ip");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response to get the current timestamp
            String json = response.toString();
            String timestampStr = json.split("\"datetime\":\"")[1].split("\"")[0];

            // Convert timestamp string to LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
            LocalTime timeStamp = LocalTime.parse(timestampStr, formatter);
            timestamp=timeStamp;
            return timestamp;

        } catch (Exception e) {
            e.printStackTrace();
            // Returning null may not be ideal, consider handling errors more gracefully
            return null;
        }
    }
    public double getmintemperature(location location) {
        try {
            String response;
            if (location == null) {
                // If location is not provided, use current location
                location loc = new location();
                try {
                    loc.getCurrentLocation();
                    response = getResponse(loc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1; // Handle error gracefully
                }
            } else {
                response = getResponse(location);
            }

            if (response != null) {
                // Extract min temperature from the response
                String minTemperatureStr = response.split("\"temp_min\":")[1].split(",")[0];
                return Double.parseDouble(minTemperatureStr) - 273.15;
            } else {
                return -1; // Handle error gracefully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error gracefully
        }
    }
    public double feelsliketemperature(location location) {
        try {
            String response;
            if (location == null) {
                // If location is not provided, use current location
                location loc = new location();
                try {
                    loc.getCurrentLocation();
                    response = getResponse(loc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1; // Handle error gracefully
                }
            } else {
                response = getResponse(location);
            }

            if (response != null) {
                // Extract feels like temperature from the response
                String feelslikeStr = response.split("\"feels_like\":")[1].split(",")[0];
                return Double.parseDouble(feelslikeStr) - 273.15;
            } else {
                return -1; // Handle error gracefully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error gracefully
        }
    }
    public double gettemperature(location location) {
        try {
            String response;
            if (location == null) {
                // If location is not provided, use current location
                location loc = new location();
                try {
                    loc.getCurrentLocation();
                    response = getResponse(loc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1; // Handle error gracefully
                }
            } else {
                response = getResponse(location);
            }

            if (response != null) {
                // Extract temperature from the response
                String TemperatureStr = response.split("\"temp\":")[1].split(",")[0];
                return Double.parseDouble(TemperatureStr) - 273.15;
            } else {
                return -1; // Handle error gracefully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error gracefully
        }
    }

    public static String calculateSunriseOrSunset(double latitude, double longitude, Calendar date, boolean sunrise, boolean twilight) {
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1;
        int year = date.get(Calendar.YEAR);

        double zenith;

        if (twilight) {
            zenith = 99;
        } else {
            zenith = 90.8333333333;
        }

        // Calculate the day of the year.
        int N1 = (int) Math.floor(275.0 * month / 9.0);
        int N2 = (int) Math.floor((month + 9.0) / 12.0);
        int N3 = 1 + (int) Math.floor((year - 4.0 * Math.floor(year / 4.0) + 2.0) / 3.0);
        int N = N1 - (N2 * N3) + day - 30;

        // Convert the longitude to hour value and calculate an approximate time.
        double lngHour = longitude / 15.0;
        double t;
        if (sunrise) {
            t = N + ((6.0 - lngHour) / 24.0);
        } else {
            t = N + ((18.0 - lngHour) / 24.0);
        }

        // Calculate the sun's mean anomaly.
        double M = (0.9856 * t) - 3.289;

        // Calculate the sun's true longitude.
        double L = M + (1.916 * sinD(M)) + (0.020 * sinD(2 * M)) + 282.634;
        while (L >= 360) {
            L -= 360.0;
        }
        while (L < 0) {
            L += 360.0;
        }

        // Calculate the sun's right ascension.
        double RA = atanD(0.91764 * tanD(L));
        while (RA >= 360) {
            RA -= 360.0;
        }
        while (RA < 0) {
            RA += 360.0;
        }

        // Right ascension value needs to be in the same quadrant as L.
        double Lquadrant = Math.floor(L / 90.0) * 90.0;
        double RAquadrant = Math.floor(RA / 90.0) * 90.0;
        RA = RA + (Lquadrant - RAquadrant);

        // Right ascension value needs to be converted into hours.
        RA /= 15.0;

        // Calculate the sun's declination.
        double sinDec = 0.39782 * sinD(L);
        double cosDec = cosD(asinD(sinDec));

        // Calculate the sun's local hour angle.
        double cosH = (cosD(zenith) - (sinDec * sinD(latitude))) / (cosDec * cosD(latitude));

        if (sunrise) {
            if (cosH > 1) return "NaN";
        } else {
            if (cosH < -1) return "NaN";
        }

        // Finish calculating H and convert into hours.
        double H;
        if (sunrise) {
            H = 360.0 - acosD(cosH);
        } else {
            H = acosD(cosH);
        }
        H /= 15.0;

        // Calculate local mean time of rising.
        double T = H + RA - (0.06571 * t) - 6.622;

        // Adjust back to UTC.
        double UT = T - lngHour;

        // Calculate the local time zone offset in hours.
        TimeZone timeZone = TimeZone.getDefault();
        int localTimeZoneOffset = timeZone.getOffset(date.getTimeInMillis()) / (60 * 60 * 1000);

        // Convert UTC to local time by adding the time zone offset.
        double localTime = UT + localTimeZoneOffset;

        // Ensure local time is within 24-hour range.
        while (localTime >= 24) {
            localTime -= 24.0;
        }
        while (localTime < 0) {
            localTime += 24.0;
        }

        // Format local time in HH:mm format
        int hours = (int) localTime;
        int minutes = (int) ((localTime - hours) * 60);
        return String.format("%02d:%02d", hours, minutes);
    }

    // Helper function to convert degrees to radians.
    private static double toRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }

    // Helper function to convert radians to degrees.
    private static double toDegrees(double radians) {
        return radians * 180.0 / Math.PI;
    }

    // Helper function to calculate sine of angle in degrees.
    private static double sinD(double degrees) {
        return Math.sin(toRadians(degrees));
    }

    // Helper function to calculate cosine of angle in degrees.
    private static double cosD(double degrees) {
        return Math.cos(toRadians(degrees));
    }

    // Helper function to calculate arc sine in degrees.
    private static double asinD(double value) {
        return toDegrees(Math.asin(value));
    }

    // Helper function to calculate arc cosine in degrees.
    private static double acosD(double value) {
        return toDegrees(Math.acos(value));
    }

    // Helper function to calculate arc tangent in degrees.
    private static double atanD(double value) {
        return toDegrees(Math.atan(value));
    }

    // Helper function to calculate tangent of angle in degrees.
    private static double tanD(double degrees) {
        return Math.tan(toRadians(degrees));
    }

    public  String getsunset(location location)  {
        // Example usage:
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 2024);
        date.set(Calendar.MONTH, Calendar.MARCH);
        date.set(Calendar.DAY_OF_MONTH, 31);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        String sunsetTime = calculateSunriseOrSunset(latitude, longitude, date, false, false);
        return sunsetTime;

    }

    public  String getsunrise(location location)  {
        // Example usage:
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 2024);
        date.set(Calendar.MONTH, Calendar.MARCH);
        date.set(Calendar.DAY_OF_MONTH, 31);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        String sunriseTime = calculateSunriseOrSunset(latitude, longitude, date, true, false);
        return sunriseTime;

    }

}

