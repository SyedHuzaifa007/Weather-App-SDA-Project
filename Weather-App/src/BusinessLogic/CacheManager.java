package BusinessLogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;

import static java.nio.file.Files.delete;

public class CacheManager
{
    // TxtFiles files;
    private String Location;
    public CacheManager(String loc)
    {
        Location = loc;
    }
    boolean getData(String loc)
    {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("CacheFile.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(fileReader);

        try {
            String data = reader.readLine();
           if (data.equals(loc))
           {
               String Loct = loc;
               String temperature = reader.readLine();
               String feelsLike = reader.readLine();
               String minTemperature = reader.readLine();
               String maxTemperature = reader.readLine();
               String sunriseTime = reader.readLine();
               String sunsetTime = reader.readLine();

               System.out.println("Location: " + Loct);
               System.out.println("Temperature: " + temperature);
               System.out.println("Feels Like: " + feelsLike);
               System.out.println("Min Temperature: " + minTemperature);
               System.out.println("Max Temperature: " + maxTemperature);
               System.out.println("Sunrise Time: " + sunriseTime);
               System.out.println("Sunset Time: " + sunsetTime);
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
           else
           {
               // will create the TxtFile class later
               boolean status = false;
               // status  = file.search(loc);

               if(status)
               {
                   String Loct = loc;
                   String temperature = reader.readLine();
                   String feelsLike = reader.readLine();
                   String minTemperature = reader.readLine();
                   String maxTemperature = reader.readLine();
                   String sunriseTime = reader.readLine();
                   String sunsetTime = reader.readLine();

                   System.out.println("Location: " + Loct);
                   System.out.println("Temperature: " + temperature);
                   System.out.println("Feels Like: " + feelsLike);
                   System.out.println("Min Temperature: " + minTemperature);
                   System.out.println("Max Temperature: " + maxTemperature);
                   System.out.println("Sunrise Time: " + sunriseTime);
                   System.out.println("Sunset Time: " + sunsetTime);
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
               else
               {
                   try
                   {
                       reader.close(); // Close the reader when done
                   }
                   catch (IOException ex)
                   {
                       throw new RuntimeException(ex);
                   }
                   delete(Path.of("CacheFile.txt"));
                   System.out.println("Deleted");
                   return false;
               }
           }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void storeData(String Location, BusinessLogic weather)
    {
        // create cache file
        // copy weather data and location in it
        // print that data
        // file.create(Location,weather);
    }
}
