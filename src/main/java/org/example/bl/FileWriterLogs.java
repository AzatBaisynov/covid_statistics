package org.example.bl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterLogs {
    public static void writeToLog(String str){
        File myFile = new File("/covidlogs/text.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(myFile, true));
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
