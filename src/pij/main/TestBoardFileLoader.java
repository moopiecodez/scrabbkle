package pij.main;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestBoardFileLoader {

    @Test
    void loadLinesReallySimpleBoard() {
        String fileName = "resources/reallySimpleBoard.txt";
        ArrayList<String> expectedLines = new ArrayList<String>();
        
        expectedLines.add("12");
        expectedLines.add("............");

        ArrayList<String> actualLines = null;
        try {
            actualLines = BoardFileLoader.loadLines(fileName);
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

    @Test
    void createSquaresMatrixFor1By1Board() {
        int size = 1;
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(".");

        Square[][] expectedMatrix = new Square[size][size];
        expectedMatrix[0][0] = new Square();

        Square[][] actualMatrix = BoardFileLoader.squaresMatrix(size, lines);

        assertArrayEquals(expectedMatrix, actualMatrix); 
    }

    @Test
    void createSquaresRowForASimpleLine() {
        String[] tokens  = {".", ".", "."};
        Square[] expectedRow = { new Square(), new Square(), new Square() };
        Square[] actualRow = BoardFileLoader.squaresRow(tokens);
        assertArrayEquals(expectedRow, actualRow);
    }

    @Test
    void createSquareTokensRowForALine() {
        int size = 12;
        String line = "..{2}.{3}.(2)..(3)..";
        String[] expectedRow = {".", ".", "{2}", ".", "{3}", ".", "(2)",
                ".", ".", "(3)", ".", "." };
        String[] actualRow = BoardFileLoader.tokensRow(size, line);
        assertArrayEquals(expectedRow, actualRow);
    }

    @Test
    void createSquaresMatrixFor3By3Board() {
        int size = 3;
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("...");
        lines.add("...");
        lines.add("...");

        Square[][] expectedMatrix = new Square[size][size];
        expectedMatrix[0][0] = new Square();
        expectedMatrix[0][1] = new Square();
        expectedMatrix[0][2] = new Square();
        expectedMatrix[1][0] = new Square();
        expectedMatrix[1][1] = new Square();
        expectedMatrix[1][2] = new Square();
        expectedMatrix[2][0] = new Square();
        expectedMatrix[2][1] = new Square();
        expectedMatrix[2][2] = new Square();
        
        Square[][] actualMatrix = BoardFileLoader.squaresMatrix(size, lines);

        assertArrayEquals(expectedMatrix, actualMatrix); 
    }
    @Test
    void loadSimpleBoard() {
        String fileName = "resources/simpleBoard.txt";
        int expectedSize = 12;
        Square[][] expectedMatrix = new Square[expectedSize][expectedSize];

        for(int i = 0; i < expectedSize; i++) {
            for(int j = 0; j < expectedSize; j++) {
                expectedMatrix[i][j] = new Square();
            }
        }

        Square[][] actualMatrix = null;
        try {
            actualMatrix = BoardFileLoader.load(fileName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertArrayEquals(expectedMatrix, actualMatrix); 
    }
}
