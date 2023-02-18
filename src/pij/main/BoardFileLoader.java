package pij.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BoardFileLoader {
    public static String load(String pathname) throws IOException {
        File file = new File(pathname);
        FileReader reader = new FileReader(file);
        String string = "";

        int i;
        while ( (i = reader.read()) != -1) {
            string += (char) i;
        }
        return string;
    }
}
