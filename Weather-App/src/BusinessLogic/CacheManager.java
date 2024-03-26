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
               // storing data from file to variables
               String Loct = loc;
               String temperature = reader.readLine();
               String feelsLike = reader.readLine();
               String minTemperature = reader.readLine();
               String maxTemperature = reader.readLine();
               String sunriseTime = reader.readLine();
               String sunsetTime = reader.readLine();
               String TimeStamp = reader.readLine();

               // printing those variables individually
               System.out.println("Location: " + Loct);
               System.out.println("Temperature: " + temperature);
               System.out.println("Feels Like: " + feelsLike);
               System.out.println("Min Temperature: " + minTemperature);
               System.out.println("Max Temperature: " + maxTemperature);
               System.out.println("Sunrise Time: " + sunriseTime);
               System.out.println("Sunset Time: " + sunsetTime);
               System.out.println("TimeStamp: " + TimeStamp);
               try
               {
                   reader.close(); // Close the reader when done
               }
               catch (IOException ex)
               {
                   throw new RuntimeException(ex);
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

               // if the file was found we print the data from updated cache File
               if(status)
               {
                   String Loct = loc;
                   String temperature = reader.readLine();
                   String feelsLike = reader.readLine();
                   String minTemperature = reader.readLine();
                   String maxTemperature = reader.readLine();
                   String sunriseTime = reader.readLine();
                   String sunsetTime = reader.readLine();
                   String TimeStamp = reader.readLine();

                   System.out.println("Location: " + Loct);
                   System.out.println("Temperature: " + temperature);
                   System.out.println("Feels Like: " + feelsLike);
                   System.out.println("Min Temperature: " + minTemperature);
                   System.out.println("Max Temperature: " + maxTemperature);
                   System.out.println("Sunrise Time: " + sunriseTime);
                   System.out.println("Sunset Time: " + sunsetTime);
                   System.out.println("TimeStamp: " + TimeStamp);
                   try
                   {
                       reader.close(); // Close the reader when done
                   }
                   catch (IOException ex)
                   {
                       throw new RuntimeException(ex);
                   }
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

    public void storeData(String Location, String temp, String feel, String min, String max, String sunrise, String sunset, String stamp)
    {
        // to store we first update cahe file
        String filepath = "CacheFile.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Write data line by line
            writer.write(Location+"\n");
            writer.write(temp+"\n");
            writer.write(feel+"\n");
            writer.write(min+"\n");
            writer.write(max+"\n");
            writer.write(sunrise+"\n");
            writer.write(sunset+"\n");
            writer.write(stamp);
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

        // print that updated cache file
        try {
            String data = reader.readLine();
            String Loct = Location;
            String temperature = reader.readLine();
            String feelsLike = reader.readLine();
            String minTemperature = reader.readLine();
            String maxTemperature = reader.readLine();
            String sunriseTime = reader.readLine();
            String sunsetTime = reader.readLine();
            String TimeStamp = reader.readLine();

            System.out.println("Location: " + Loct);
            System.out.println("Temperature: " + temperature);
            System.out.println("Feels Like: " + feelsLike);
            System.out.println("Min Temperature: " + minTemperature);
            System.out.println("Max Temperature: " + maxTemperature);
            System.out.println("Sunrise Time: " + sunriseTime);
            System.out.println("Sunset Time: " + sunsetTime);
            System.out.println("TimeStamp: " + TimeStamp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try
        {
            reader.close(); // Close the reader when done
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }

        // lastly we also create the new location file using TxtFile class
        String newFile = Location + ".txt";
        files.createFile(newFile);
    }
}
