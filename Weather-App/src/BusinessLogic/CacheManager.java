package BusinessLogic;

import DataAccess.TxtFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CacheManager
{
    TxtFile files;
    private String Location;
    public CacheManager(String loc)
    {
        Location = loc;
        files = new TxtFile(loc);
    }
    boolean getData(String loc)
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
                for (int i = 1; i < 25; i++) {
                    reader33.readLine();
                }
                String dateString = reader33.readLine();
                LocalDate storedDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

                if (storedDate.isEqual(currentdate)) {
                } else {
                    files.deleteFile(loc+".txt");
                    return false;
                }

                return true;
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
                    return true;
                }
                // if the file was not found we return false
                else
                {
                    return false;
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void storeData(String Location,String longi, String lati, String temp, String feel, String min, String max, String sunrise, String sunset, String stamp, String day1, String day2, String day3, String day4, String day5, String aqi, String CO, String NO, String NO2, String O3, String SO2, String NH3, String PM25, String PM10, String date)
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
}