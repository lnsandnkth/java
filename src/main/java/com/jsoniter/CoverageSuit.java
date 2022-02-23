package com.jsoniter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CoverageSuit {
    public static boolean[] stmt_covered = new boolean[24];

    public static void print() {
        String s = "";
        int i = 0;
        float count = 0;
        for (boolean b : stmt_covered) {
            s += "stmt " + i + " is covered: " + b + "\n";
            ++i;
            if(b){
                ++count;
            }
        }
        s += count + " statements covered of 23 statements: " + count/24 + "% coverage" + "\n";

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("coverage"));
            writer.write(s);
            writer.close();
        }catch (IOException e){

        }

    }
}
