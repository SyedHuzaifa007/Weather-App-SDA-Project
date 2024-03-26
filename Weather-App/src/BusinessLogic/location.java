package BusinessLogic;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;

public class location {
    private double latitude;
    private double longitude;
    private String city;
    private String country;

    public location() {
    }

    public location(double latitude, double longitude, String city, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    private String getValueFromReverseGeocodeResponse(String urlStr, String key) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        JSONObject address = jsonObject.getJSONObject("address");
        return address.optString(key, null);
    }
    public boolean addManualLocationCountryCity(double longitude, double latitude) {
        try {
            String urlStr = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude;
            this.setCountry(getValueFromReverseGeocodeResponse(urlStr, "country"));
            this.setCity(getValueFromReverseGeocodeResponse(urlStr, "city"));
            if (country != null && city != null) {
           this.setLongitude(longitude);
           this.setLatitude(latitude);
                return true; // Successfully added coordinates with country and city

            } else {
                return false; // Failed to add coordinates
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Failed to add coordinates
        }
    }
    public boolean addManualLocationCoord(String country, String city) {
        try {
            String[] coordinates = getCoordinates(city, country);
            if (coordinates != null) {
                double latitude = Double.parseDouble(coordinates[0]);
                double longitude = Double.parseDouble(coordinates[1]);
                this.setCity(city);
                this.setCountry(country);
                this.setLongitude(longitude);
                this.setLatitude(latitude);
                return true; // Indicate success
            } else {
                return false; // Indicate failure
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Indicate failure
        }
    }

    private String[] getCoordinates(String city, String country) throws IOException {
        String urlStr = "https://nominatim.openstreetmap.org/search?format=json&q=" + city + "," + country;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONArray jsonArray = new JSONArray(response.toString());
        if (jsonArray.length() > 0) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String lat = jsonObject.getString("lat");
            String lon = jsonObject.getString("lon");
            return new String[]{lat, lon};
        } else {
            return null;
        }
    }
    public location getCurrentLocation() {
        try {
            // Get the current IP address
            URL ipApiUrl = new URL("https://api.ipify.org?format=json");
            URLConnection ipApiConnection = ipApiUrl.openConnection();
            BufferedReader ipReader = new BufferedReader(new InputStreamReader(ipApiConnection.getInputStream()));
            String ipResponse = ipReader.readLine();
            ipReader.close();

            // Extract IP address from the response
            String ipAddress = ipResponse.split(":")[1].replaceAll("[^\\d.]", "").trim();

            // Get location data based on the IP address
            URL geoIpUrl = new URL("https://ipapi.co/" + ipAddress + "/json/");
            URLConnection geoIpConnection = geoIpUrl.openConnection();
            BufferedReader geoIpReader = new BufferedReader(new InputStreamReader(geoIpConnection.getInputStream()));
            StringBuilder geoIpResponse = new StringBuilder();
            String line;
            while ((line = geoIpReader.readLine()) != null) {
                geoIpResponse.append(line);
            }
            geoIpReader.close();

            // Extract country, city, latitude, and longitude from the response
            String[] parts = geoIpResponse.toString().split(",");
            for (String part : parts) {
                if (part.contains("\"country_name\"")) {
                    country = part.split(":")[1].replaceAll("\"", "").trim();
                }
                if (part.contains("\"city\"")) {
                    city = part.split(":")[1].replaceAll("\"", "").trim();
                }
                if (part.contains("\"latitude\"")) {
                    latitude = Double.parseDouble(part.split(":")[1]);
                }
                if (part.contains("\"longitude\"")) {
                    longitude = Double.parseDouble(part.split(":")[1]);
                }
            }

               return this;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}

