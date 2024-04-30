package BusinessLogic;

import DataAccess.TxtFile;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Math.round;

public class CacheManager
{
    TxtFile files;
    private location location;

    public CacheManager(location loc)
    {
        location = loc;
        files = new TxtFile(loc.getCity());
    }

   public void getData(String loc)
    {
        // reading file
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("CacheFile.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(fileReader);

        try {
            String data = reader.readLine();

            // if cache file has required location data

            if (data.equals(loc))
            {
                LocalDate currentdate = LocalDate.now();
                BufferedReader reader33 = new BufferedReader(new FileReader("CacheFile.txt"));
                for (int i = 1; i < 27; i++) {
                    reader33.readLine();
                }
                String dateString = reader33.readLine();
                LocalDate storedDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

                if (storedDate.isEqual(currentdate)) {
                } else {
                    files.deleteFile(loc+".txt");
                    storing();
                    return;
                }

                return;
            }
            // if cache does not have the required location data
            else
            {
                // we search the location file by name using TxtFile class
                boolean status = false;
                status  = files.search(loc);

                // if the required location file is found
                // we update the cache file read the updated Cache file again
                fileReader = null;
                try {
                    fileReader = new FileReader("CacheFile.txt");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                reader = new BufferedReader(fileReader);

                if(status)
                {
                    return;
                }
                // if the file was not found we return false
                else
                {
                    storing();
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void storeData(String Location,String longi, String lati, String temp, String feel, String min, String max, String sunrise, String sunset, String stamp, String day1, String day2, String day3, String day4, String day5, String aqi, String CO, String NO, String NO2, String O3, String SO2, String NH3, String PM25, String PM10,String n_weather,String n_air,String date)
    {
        // to store we first update cache file
        String filepath = "CacheFile.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Write data line by line
            writer.write(Location);
            writer.newLine();
            writer.write(longi);
            writer.newLine();
            writer.write(lati);
            writer.newLine();
            writer.write(temp);
            writer.newLine();
            writer.write(feel);
            writer.newLine();
            writer.write(min);
            writer.newLine();
            writer.write(max);
            writer.newLine();
            writer.write(sunrise);
            writer.newLine();
            writer.write(sunset);
            writer.newLine();
            writer.write(stamp);
            writer.newLine();

            writer.write(day1);
            writer.newLine();
            writer.write(day2);
            writer.newLine();
            writer.write(day3);
            writer.newLine();
            writer.write(day4);
            writer.newLine();
            writer.write(day5);
            writer.newLine();

            writer.write(aqi);
            writer.newLine();
            writer.write(CO);
            writer.newLine();
            writer.write(NO);
            writer.newLine();
            writer.write(NO2);
            writer.newLine();
            writer.write(O3);
            writer.newLine();
            writer.write(SO2);
            writer.newLine();
            writer.write(NH3);
            writer.newLine();
            writer.write(PM25);
            writer.newLine();
            writer.write(PM10);
            writer.newLine();

            writer.write(n_weather);
            writer.newLine();
            writer.write(n_air);
            writer.newLine();

            writer.write(date);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // read the updated cache file
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("CacheFile.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(fileReader);


        // lastly we also create the new location file using TxtFile class
        String newFile = Location + ".txt";
        files.createFile(newFile);
    }

    public void storing()
    {
        // Business Logic method
        BusinessLogic businessLogic = new WeatherData();
        // getting forecast and air pollution data
        double[] forecast = businessLogic.getDayForecast(location);
        double[] values = businessLogic.PollutionValues(location);

        // (store new data in file and update cache)
        LocalDate currentDate = LocalDate.now();
        String current = String.valueOf(currentDate);

        storeData(location.getCity(),
                String.valueOf(location.getLongitude()),
                String.valueOf(location.getLatitude()),
                String.valueOf(round(businessLogic.getTemperature(location))),
                String.valueOf(round(businessLogic.getFeelsLike(location))),
                String.valueOf(round(businessLogic.getMinTemperature(location))),
                String.valueOf(round(businessLogic.getMaxTemperature(location))),
                String.valueOf(businessLogic.getSunriseTime(location)),
                String.valueOf(businessLogic.getSunsetTime(location)),
                String.valueOf(businessLogic.getTimestamp(location)),

                String.valueOf(round(forecast[0])),
                String.valueOf(round(forecast[1])),
                String.valueOf(round(forecast[2])),
                String.valueOf(round(forecast[3])),
                String.valueOf(round(forecast[4])),

                String.valueOf(values[0]), String.valueOf(values[1]), String.valueOf(values[2]),
                String.valueOf(values[3]), String.valueOf(values[4]), String.valueOf(values[5]),
                String.valueOf(values[6]), String.valueOf(values[7]), String.valueOf(values[8]),

                businessLogic.GenerateWeatherNotificattions(location.getCity()),businessLogic.generateAirQualityNotification(location),

                current
        );
    }

    public ArrayList<String> readCacheFile() throws IOException {
        // file reading
        FileReader filereader = new FileReader("CacheFile.txt");
        BufferedReader reader = new BufferedReader(filereader);

        ArrayList<String> data = new ArrayList<>();

        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());

        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());

        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());

        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());
        data.add(reader.readLine());

        data.add(reader.readLine());
        data.add(reader.readLine());

        reader.close();

        return data;
    }

    public ArrayList<String> readCacheDB() throws IOException
    {
        ArrayList<String> data = new ArrayList<>();
        data = readCacheFile();

        ArrayList<String> data2 = new ArrayList<>();

        // Database Creation
        data2.add(data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + data.get(8) + "," + data.get(9));
        data2.add(data.get(0) + "," + data.get(10) + "," + data.get(11) + "," + data.get(12) + "," + data.get(13) + "," + data.get(14));
        data2.add(data.get(0) + "," + data.get(15) + "," + data.get(16) + "," + data.get(17) + "," + data.get(18) + "," + data.get(19) + "," + data.get(20) + "," + data.get(21) + "," + data.get(22) + "," + data.get(23));

        return data2;
    }
}