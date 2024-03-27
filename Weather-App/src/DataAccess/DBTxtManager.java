package DataAccess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DBTxtManager {

    public void writeToDBTxt(String data1, String data2, String data3) {
        String fileName = "DBTxt.txt";
        File file = new File(fileName);

        try {
            if (file.exists()) {
                // Delete existing file
                if (file.delete()) {
                } else {
                    return;
                }
            }

            // Create new file
            if (file.createNewFile()) {
            } else {
                return;
            }

            // Write data to file
            FileWriter writer = new FileWriter(file);
            writer.write(data1+"\n");
            writer.write(data2+"\n");
            writer.write(data3+"\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
