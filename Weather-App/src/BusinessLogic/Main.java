package BusinessLogic;
import DataAccess.DBTxtManager;
import DataAccess.DatabaseSQL;
import UI.GUI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.round;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.JFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception
    {
//-----------------------------------GUI Object------------------------------------------//
        GUI G = new GUI();
        G.createLoadingFrame();
//-----------------------------------Location Object-------------------------------------//
        location location = new location();
        location.getCurrentLocation();
//-----------------------------------Cache Manager Object--------------------------------//
        CacheManager manager = new CacheManager(location);
        manager.getData(location.getCity());

        ArrayList<String> data = manager.readCacheFile();
        ArrayList<String> db = manager.readCacheDB();
//-----------------------------------Database Object-------------------------------------//
        DBTxtManager hello = new DBTxtManager();
        hello.writeToDBTxt(db.get(0), db.get(1), db.get(2));
        DatabaseSQL.main(args);
//-----------------------------------GUI called------------------------------------------//
        G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25));
    }


    public static void processData(String input) throws Exception
    {
        GUI G = new GUI();
        G.createLoadingFrame();

        location location = new location();

        if (input.contains(",")) {
            // Splitting string into two
            String[] parts = input.split("[,\\s]+");
            // if data is numeric
            if (isNumeric(parts[0]) && isNumeric(parts[1])) {
                // If the first two parts are numeric, assume they are longitude and latitude
                double longitude = Double.parseDouble(parts[0]);
                double latitude = Double.parseDouble(parts[1]);
                location.addManualLocationCountryCity(longitude, latitude);
            } else {
                // Otherwise, assume the first two parts are country and city
                String country = parts[0];
                String city = parts[1];
                location.addManualLocationCoord(country, city);
            }
        }
        else
        {
            G.disposeLoadingFrame();
            String errorMessage = "The Format for Searching Is: Country,City or Longitude,Latitude (Plz try Again)";
            String formattedMessage = "<html><body style='width: 250px;'>" + errorMessage + "</body></html>";
            JOptionPane.showMessageDialog(null, formattedMessage, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        CacheManager manager = new CacheManager(location);
        manager.getData(location.getCity());

        ArrayList<String> data = new ArrayList<>();
        data = manager.readCacheFile();

        ArrayList<String> db = new ArrayList<>();
        db = manager.readCacheDB();

        DBTxtManager hello = new DBTxtManager();
        hello.writeToDBTxt(db.get(0), db.get(1), db.get(2));
        String[] arg = new String[0];
        DatabaseSQL.main(arg);

        G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25));
    }

    private static boolean isNumeric(String part) {
        return part.matches("-?\\d+(\\.\\d+)?");
    }
}