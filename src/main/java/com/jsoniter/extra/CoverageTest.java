package com.jsoniter.extra;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CoverageTest {

    public static boolean[] coverageArray = new boolean[20];

    public static void writeToFile() throws IOException, IOException {
        String outputString = "";
        int coveragePositives = 0;

        for (int i = 0; i<coverageArray.length;i++) {
            if (coverageArray[i]) {
                coveragePositives++;
            }
            outputString=outputString+"Statement "+ i+ " is covered: " + coverageArray[i] + "\n";

        }

        outputString=outputString+ "Coverage ratio: " + coveragePositives+"/"+coverageArray.length;

        BufferedWriter writer = new BufferedWriter(new FileWriter("coverage3.txt"));
        writer.write(outputString);
        writer.close();
    }
}
