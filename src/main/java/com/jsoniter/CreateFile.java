package com.jsoniter;

import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {
    static {
        try {
            FileWriter myWriter = new FileWriter("coverage.txt");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendString(String str) {
        try {
            FileWriter myWriter = new FileWriter("coverage.txt", true);
            myWriter.write(str);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
