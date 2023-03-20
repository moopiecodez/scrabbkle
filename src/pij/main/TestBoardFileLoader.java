package pij.main;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pij.main.square.Square;
import pij.main.square.StandardSquare;

public class TestBoardFileLoader {

    @Test 
    void parseLine() {
        String expectedLine = ".(33)(4){0}.{-2}..(9).{11}.";
        int size = 12;

        String actualLine = BoardFileLoader.parseLine(size, expectedLine);

        assertEquals(expectedLine, actualLine);
    }

    @Test
    void createSquaresMatrixFor1By1Board() {
        int expectedHeight = 1;
        int expectedWidth = 1;
        Class<StandardSquare> expectedType = StandardSquare.class;

        int size = 1;
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(".");

        Square[][] actualMatrix = BoardFileLoader.squaresMatrix(size, lines);
        int actualHeight = actualMatrix.length;
        int actualWidth = actualMatrix[0].length;

        assertEquals(expectedHeight, actualHeight);
        assertEquals(expectedHeight, actualWidth);
        assertInstanceOf(expectedType, actualMatrix[0][0]);
    }

    @Test
    void createSquaresRowForASimpleLine() {
        int expectedLength =  3;
        Class<StandardSquare> expectedType = StandardSquare.class;

        String[] tokens  = {".", ".", "."};

        Square[] actualRow = BoardFileLoader.squaresRow(tokens);
        int actualLength = actualRow.length;

        assertEquals(expectedLength, actualLength);
        assertInstanceOf(expectedType, actualRow[0]);
        assertInstanceOf(expectedType, actualRow[1]);
        assertInstanceOf(expectedType, actualRow[2]);
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
    void loadSimpleBoard() {
        String fileName = "resources/simpleBoard.txt";
        int expectedSize = 12;
        Square[][] expectedMatrix = new Square[expectedSize][expectedSize];

        for(int i = 0; i < expectedSize; i++) {
            for(int j = 0; j < expectedSize; j++) {
                expectedMatrix[i][j] = new StandardSquare();
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

    /* Saving for later composite test
     *  "    a  b  c  d  e  f  g  h  i  j  k  l  m  n  o \n"
      + " 1 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}\n"
      + " 2  . {2} .  .  . (3) .  .  . (3) .  .  . {2} . \n"
      + " 3  .  . {2} .  .  . (2) . (2) .  .  . {2} .  . \n"
      + " 4 (2) .  . {2} .  .  . (2) .  .  . {2} .  . (2)\n"
      + " 5  .  .  .  . {2} .  .  .  .  . {2} .  .  .  . \n"
      + " 6  . (3) .  .  . (3) .  .  . (3) .  .  . (3) . \n"
      + " 7  .  . (2) .  .  . (2) . (2) .  .  . (2) .  . \n"
      + " 8 {3} .  . (2) .  .  . {2} .  .  . (2) .  . {3}\n"
      + " 9  .  . (2) .  .  . (2) . (2) .  .  . (2) .  . \n"
      + "10  . (3) .  .  . (3) .  .  . (3) .  .  . (3) . \n"
      + "11  .  .  .  . {2} .  .  .  .  . {2} .  .  .  . \n"
      + "12 (2) .  . {2} .  .  . (2) .  .  . {2} .  . (2)\n"
      + "13  .  . {2} .  .  . (2) . (2) .  .  . {2} .  . \n"
      + "14  . {2} .  .  . (3) .  .  . (3) .  .  . {2} . \n"
      + "15 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}\n";
     */
}
