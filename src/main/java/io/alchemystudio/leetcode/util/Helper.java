package io.alchemystudio.leetcode.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Helper {

    public static int[] ReadLargeIntArray(String filename) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            StringBuffer sb = null;
            while (in.ready()) {
                sb = (new StringBuffer(in.readLine()));
            }
            in.close();
            if (sb != null) {
                return Arrays.stream(sb.toString().split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
            }
        } catch (IOException ex) {
//            ex.printStackTrace();
        }
        return null;
    }

}
