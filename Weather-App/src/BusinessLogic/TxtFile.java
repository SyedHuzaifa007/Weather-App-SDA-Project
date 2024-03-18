package BusinessLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TxtFile {
    private String location;

    public TxtFile(String loc) {
        location = loc;
    }

    public boolean search(String loc)
    {
        try
        {
            // Construct the path of the file to search
            Path filePath = Paths.get(loc+".txt");

            // Checking if the file exists
            if (Files.exists(filePath))
            {
                // Read file
                StringBuilder fileContent = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile())))
                {
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        fileContent.append(line).append("\n");
                    }
                }

                // Replacing contents of CacheFile.txt with contents of found file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("CacheFile.txt")))
                {
                    writer.write(fileContent.toString());
                }

                return true; // File found and contents replaced
            }
            else
            {
                return false; // File not found
            }
        } catch (IOException e)
        {
            throw new RuntimeException("Error occurred while searching and replacing file contents: " + e.getMessage());
        }
    }

    public void createFile(String newFile)
    {
        try
        {
            // Read contents of CacheFile.txt
            StringBuilder cacheFileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("CacheFile.txt")))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    cacheFileContent.append(line).append("\n");
                }
            }

            // Write contents to the new create file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile)))
            {
                writer.write(cacheFileContent.toString());
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error occurred while creating file and copying contents: " + e.getMessage());
        }
    }
}
