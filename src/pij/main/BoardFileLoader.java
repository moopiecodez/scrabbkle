package pij.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoardFileLoader {
    public static ArrayList<String> load(String pathname) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        File file = new File(pathname);
        FileReader reader = new FileReader(file);
        BufferedReader buff = new BufferedReader(reader);
        String line = "";

        while ( (line = buff.readLine()) != null) {
            lines.add(line);
        }
        buff.close();
        return lines;
    }
}
