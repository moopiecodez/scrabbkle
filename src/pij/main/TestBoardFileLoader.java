package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestBoardFileLoader {

    @Test
    void testLoader() {
        String fileName = "resources/reallySimpleBoard.txt";
        ArrayList<String> expectedLines = new ArrayList<String>();
        
        expectedLines.add("12");
        expectedLines.add("............");
        

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
    
    @Test
    void parseFirstLineValid() {
        int expectedInt = 12;
        String line = "12";

        int actualInt = BoardFileLoader.parseFirstLine(line);

        assertEquals(expectedInt, actualInt);
    }

    @Test
    void parseFirstLineInvalid() {
        String line = "27";
        String expectedMessage = "Invalid board size: 27 not in range 12-26";

        try {
            BoardFileLoader.parseFirstLine(line);
            fail("expected IllegalArgumentException");
        }
        catch (IllegalArgumentException exception) {
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        }
    }
}
