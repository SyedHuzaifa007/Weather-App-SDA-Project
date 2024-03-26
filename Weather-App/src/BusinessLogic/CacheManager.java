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

    public void storeData(String Location,String longi, String lati, String temp, String feel, String min, String max, String sunrise, String sunset, String stamp, String day1, String day2, String day3, String day4, String day5)
    {
        // to store we first update cache file
        String filepath = "CacheFile.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Write data line by line
            writer.write(Location+"\n");
            writer.write(longi+"\n");
            writer.write(lati+"\n");
            writer.write(temp+"\n");
            writer.write(feel+"\n");
            writer.write(min+"\n");
            writer.write(max+"\n");
            writer.write(sunrise+"\n");
            writer.write(sunset+"\n");
            writer.write(stamp+"\n");

            writer.write(day1+"\n");
            writer.write(day2+"\n");
            writer.write(day3+"\n");
            writer.write(day4+"\n");
            writer.write(day5+"\n");
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
