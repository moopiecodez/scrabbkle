package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class TestBoardFileLoader {
    @Test
    void testLoader() {
        String expectedString = "12\n" +
                            "............" /*+ 
                            "............\n" + 
                            "............\n" +
                            "............\n" +
                            "............\n" +
                            "............\n" +
                            "............\n" +
                            "............\n" +
                            "............\n" +
                            "............\n" +
                            "............\n" +
                            "............\n" */;
        String fileName = "resources/reallySimpleBoard.txt";

        String actualString = null;
        try {
            actualString = BoardFileLoader.load(fileName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals(expectedString, actualString);
    }
}
