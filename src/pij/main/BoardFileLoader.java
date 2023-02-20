package pij.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class BoardFileLoader {
    public static ArrayList<String> load(String pathname) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        File file = new File(pathname);
        FileReader reader = new FileReader(file);
        String string = "";

        int i;
        while ( (i = reader.read()) != -1) {
            char c = (char) i;
            string += c;
        }

        int max = string.length();
        String line = "";
        for(i = 0; i < max; i++) {
            char c = string.charAt(i);
            if(c == 10) {
                lines.add(line);
                line = "";
            }
            else {
                line = line + c;
            }
        }
        lines.add(line);
        return lines;
    }
}
