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
        String line = "";

        int i;
        while ( (i = reader.read()) != -1) {
            char c = (char) i;
            if(c == '\n') {
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
