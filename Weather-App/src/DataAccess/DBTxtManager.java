package DataAccess;


import DataAccess.TxtFile;

import java.io.*;

public class DBTxtManager
{
    TxtFile files;
    private String Location;
    public DBTxtManager(String loc)
    {
        Location = loc;
        files = new TxtFile(loc);
    }
    boolean getData(String loc)
    {
        // reading file
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("DBTxt.txt");
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
                    fileReader = new FileReader("DBTxt.txt");
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
        String filepath = "DBTxt.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Write data line by line
            writer.write(Location+","+longi+","+lati+","+temp+","+feel+","+min+","+max+","+sunrise+","+sunset+","+stamp);

            writer.write(Location+","+day1+","+day2+","+day3+","+day4+","+day5);

            writer.write(Location+","+aqi+","+CO+","+NO+","+NO2+","+O3+","+SO2+","+NH3+","+PM25+","+PM10);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // read the updated cache file
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("DBTxt.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(fileReader);


        // lastly we also create the new location file using TxtFile class
        String newFile = Location + ".txt";
        files.createFile(newFile);
    }
}

