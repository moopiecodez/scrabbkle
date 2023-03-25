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
    void parseLineValid() {
        String line = ".(33)(4){0}.{-2}..(9).{11}.";
        String expectedLine = line;
        int size = 12;

        String actualLine = BoardFileLoader.parseLine(size, line);

        assertEquals(expectedLine, actualLine);
    }

    @Test 
    void parseLineInvalidTooLong() {
        String line = ".(33)(4){0}.{-2}..(9).{11}..";
        String expectedLine = "";

        int size = 12;

        String actualLine = BoardFileLoader.parseLine(size, line);

        assertEquals(expectedLine, actualLine);
    }

    @Test
    void loadSimpleBoard() {
        String fileName = "resources/simpleBoard.txt";
        String expectedBoardString = 
           "    a  b  c  d  e  f  g  h  i  j  k  l \n" + 
           " 1  .  .  .  .  .  .  .  .  .  .  .  . \n" + 
           " 2  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           " 3  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           " 4  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           " 5  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           " 6  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           " 7  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           " 8  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           " 9  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           "10  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           "11  .  .  .  .  .  .  .  .  .  .  .  . \n" +
           "12  .  .  .  .  .  .  .  .  .  .  .  . \n";
        
        BoardFileLoader loader = new BoardFileLoader(fileName);
        loader.validateBoardFile();
        Board board = loader.createBoard();
        String actualBoardString = board.toString();
        
        assertEquals(expectedBoardString, actualBoardString); 
    }

    @Test
    void defaultBoard() {
        String fileName = "resources/defaultBoard.txt";
        String expectedBoardString = 
        "    a  b  c  d  e  f  g  h  i  j  k  l  m  n  o \n"
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

        BoardFileLoader loader = new BoardFileLoader(fileName);
        loader.validateBoardFile();
        Board board = loader.createBoard();
        String actualBoardString = board.toString();
        
        assertEquals(expectedBoardString, actualBoardString); 
    }
}
