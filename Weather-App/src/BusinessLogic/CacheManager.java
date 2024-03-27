package BusinessLogic;

import DataAccess.TxtFile;

import java.io.*;

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

    public void storeData(String Location,String longi, String lati, String temp, String feel, String min, String max, String sunrise, String sunset, String stamp, String day1, String day2, String day3, String day4, String day5, String aqi, String CO, String NO, String NO2, String O3, String SO2, String NH3, String PM25, String PM10)
    {
        // to store we first update cache file
        String filepath = "CacheFile.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Write data line by line
            writer.write(Location+",");
            writer.write(longi+",");
            writer.write(lati+",");
            writer.write(temp+",");
            writer.write(feel+",");
            writer.write(min+",");
            writer.write(max+",");
            writer.write(sunrise+",");
            writer.write(sunset+",");
            writer.write(stamp+",");

            writer.write(day1+",");
            writer.write(day2+",");
            writer.write(day3+",");
            writer.write(day4+",");
            writer.write(day5+",");

            writer.write(aqi+",");
            writer.write(CO+",");
            writer.write(NO+",");
            writer.write(NO2+",");
            writer.write(O3+",");
            writer.write(SO2+",");
            writer.write(NH3+",");
            writer.write(PM25+",");
            writer.write(PM10);
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
