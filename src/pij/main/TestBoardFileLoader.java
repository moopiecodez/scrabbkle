package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestBoardFileLoader {
    @Test
    void testLoader() {
        ArrayList<String> expectedLines = new ArrayList<String>();
        
        expectedLines.add("12");
        expectedLines.add("............");
        
        String fileName = "resources/reallySimpleBoard.txt";

        ArrayList<String> actualLines = null;
        try {
            actualLines = BoardFileLoader.load(fileName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals(expectedLines, actualLines);
    }
}
