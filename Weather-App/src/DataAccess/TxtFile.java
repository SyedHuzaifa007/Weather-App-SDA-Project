package DataAccess;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

                LocalDate currentdate = LocalDate.now();
                BufferedReader reader33 = new BufferedReader(new FileReader(loc+".txt"));
                for (int i = 1; i < 27; i++) {
                    reader33.readLine();
                }
                String dateString = reader33.readLine().trim();
                LocalDate storedDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

                if (storedDate.isEqual(currentdate)) {
                } else {
                    deleteFile(loc+".txt");
                    return false;
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

    public void deleteFile(String fileName)
    {
        File fileToDelete = new File(fileName);

        // Check if the file exists
        if (fileToDelete.exists()) {
            // Attempt to delete the file
            fileToDelete.delete();
        }
    }
}
