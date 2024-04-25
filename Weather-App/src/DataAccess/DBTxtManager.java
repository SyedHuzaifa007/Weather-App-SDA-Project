package DataAccess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DBTxtManager {

    public void writeToDBTxt(String data1, String data2, String data3) {
        String fileName = "DBTxt.txt";
        File file = new File(fileName);

        try {
            // Create a FileWriter in append mode (this will not delete the existing file)
            FileWriter writer = new FileWriter(file, false);

            // Write data to the file
            writer.write(data1 + "\n");
            writer.write(data2 + "\n");
            writer.write(data3);

            // Close the writer
            writer.close();
        } catch (IOException e) {
            // Print an error message and stack trace if an IOException occurs
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
