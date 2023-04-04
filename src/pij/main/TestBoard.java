package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pij.main.Move.Direction;
import pij.main.square.Square;
import pij.main.square.StandardSquare;

public class TestBoard {

    static final int defaultSize = 15;
    static final String defaultSquareString =
                     "{3}..(2)...{3}...(2)..{3}"
                    + ".{2}...(3)...(3)...{2}."
                    + "..{2}...(2).(2)...{2}.."
                    + "(2)..{2}...(2)...{2}..(2)"
                    + "....{2}.....{2}...."
                    + ".(3)...(3)...(3)...(3)."
                    + "..(2)...(2).(2)...(2).."
                    + "{3}..(2)...{2}...(2)..{3}"
                    + "..(2)...(2).(2)...(2).."
                    + ".(3)...(3)...(3)...(3)."
                    + "....{2}.....{2}...."
                    + "(2)..{2}...(2)...{2}..(2)"
                    + "..{2}...(2).(2)...{2}.."
                    + ".{2}...(3)...(3)...{2}."
                    + "{3}..(2)...{3}...(2)..{3}";

    static final String defaultExpectedString =
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

    Board defaultBoard() {
        Board board = new Board(defaultSize, defaultSquareString);
        return board;
    }

    Rack catRack() {
        Rack rack = new Rack();
        Tile tileC = new Tile('C', 3);
        Tile tileA = new Tile('A', 1);
        Tile tileT = new Tile('T', 1);
        Tile tileS = new Tile('S', 1);
        rack.add(tileS);
        rack.add(tileC);
        rack.add(tileA);
        rack.add(tileT);

        return rack;
    }

    @Test
    void createSquaresMatrixFor3By3Board() {
        int size = 3;
        String squareString = ".........";

        Square[][] expectedMatrix = new Square[size][size];
        expectedMatrix[0][0] = new StandardSquare();
        expectedMatrix[0][1] = new StandardSquare();
        expectedMatrix[0][2] = new StandardSquare();
        expectedMatrix[1][0] = new StandardSquare();
        expectedMatrix[1][1] = new StandardSquare();
        expectedMatrix[1][2] = new StandardSquare();
        expectedMatrix[2][0] = new StandardSquare();
        expectedMatrix[2][1] = new StandardSquare();
        expectedMatrix[2][2] = new StandardSquare();

        Square[][] actualMatrix = Board.squaresMatrix(size, squareString);
        int matrixHeight = actualMatrix.length;
        int matrixWidth = actualMatrix[0].length;

        assertEquals(size, matrixHeight);
        assertEquals(size, matrixWidth);

    }

    @Test
    void squareToString() {
        Board board = defaultBoard();
        String actualString = board.toString();
        assertEquals(defaultExpectedString, actualString);
    }

    @Test
    void pickSquare() {
        Position position = new Position(2, 'b');

        Board board = defaultBoard();
        String expectedString = "{2}";

        Square square = board.getSquare(position);
        String actualString = square.toString();
        assertEquals(expectedString, actualString);
    }

    @Test
    void placeTile() {
        Position position = new Position(2, 'b');
        Tile tile = new Tile ('A', 1);

        String expectedString =
                "    a  b  c  d  e  f  g  h  i  j  k  l  m  n  o \n"
              + " 1 {3} .  . (2) .  .  . {3} .  .  . (2) .  . {3}\n"
              + " 2  . A1  .  .  . (3) .  .  . (3) .  .  . {2} . \n"
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

        Board board = defaultBoard();
        board.placeTile(position, tile);
        String actualString = board.toString();
        assertEquals(expectedString, actualString);
    }

    @Test
    void findCentreSquareOddBoard() {
        String expectedCoordinates = "h8";

        Board board = defaultBoard();
        Position position = board.centreSquare();
        String actualCoordinates = position.toString();

        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    void findCentreSquareEvenBoard() {
        int size = 14;
        String expectedCoordinates = "g7";
        String squareString =
                "{3}..(2)...{3}...(2)..{3}.{2}...(3)...(3)...{2}"
                + "..{2}...(2).(2)...{2}..(2)..{2}...(2)...{2}.."
                + "....{2}.....{2}.....(3)...(3)...(3)...(3)"
                + "..(2)...(2).(2)...(2)..{3}..(2)...{2}...(2).."
                + "..(2)...(2).(2)...(2)...(3)...(3)...(3)...(3)"
                + "....{2}.....{2}....(2)..{2}...(2)...{2}.."
                + "..{2}...(2).(2)...{2}...{2}...(3)...(3)...{2}"
                + "{3}..(2)...{3}...(2)..{3}";

        Board board = new Board(size, squareString);
        Position position = board.centreSquare();
        String actualCoordinates = position.toString();

        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    void validVerticalOrigins() {
        Board board = defaultBoard();
        ArrayList<Position> verticalOrigins = new ArrayList<Position>();
        String expectedVerticalString = "[h8, h7, h6, h5, h4, h3, h2]";

        verticalOrigins = board.getOrigins(Direction.down);

        String actualVerticalString = verticalOrigins.toString();

        assertEquals(expectedVerticalString, actualVerticalString);
    }

    @Test
    void validHorizontalOrigins() {
        Board board = defaultBoard();
        ArrayList<Position> horizontalOrigins = new ArrayList<Position>();
        String expectedHorizontalString = "[h8, g8, f8, e8, d8, c8, b8]";

        horizontalOrigins = board.getOrigins(Direction.right);

        String actualHorizontalString = horizontalOrigins.toString();

        assertEquals(expectedHorizontalString, actualHorizontalString);
    }

    @Test
    void findOriginsOnPlacement () {
        Board board = defaultBoard();
        Rack rack = catRack();
        ArrayList<Position> horizontalOrigins = new ArrayList<Position>();

        String expectedHorizontalString = "[f8, e8, d8, c8, b8, a8]";

        Move move = Move.fromString("CAT,f8,r");
        move.place(board, rack);
        horizontalOrigins = board.getOrigins(Direction.right);

        String actualHorizontalString = horizontalOrigins.toString();

        assertEquals(expectedHorizontalString, actualHorizontalString);
    }

    @Test
    void getWordCleanBoard() {
        Board board = defaultBoard();
        Move move = Move.fromString("CAT,h8,r");
        String expectedWord = "CAT";

        String actualWord = board.getWord(move);

        assertEquals(expectedWord, actualWord);
    }

    @Test
    void getWordBoardWithLetterAtEnd() {
        Board board = defaultBoard();
        Tile tileA = new Tile('S', 1);
        Position positionA = Position.fromString("h8");
        Move move = Move.fromString("CAT,e8,r");
        String expectedWord = "CATS";

        board.placeTile(positionA, tileA);

        String actualWord = board.getWord(move);

        assertEquals(expectedWord, actualWord);
    }

    @Test
    void getWordBoardWithLetterAtStart() {
        Board board = defaultBoard();
        Tile tileA = new Tile('C', 3);
        Position positionA = Position.fromString("h8");
        Move move = Move.fromString("ATS,h8,d");
        String expectedWord = "CATS";

        board.placeTile(positionA, tileA);

        String actualWord = board.getWord(move);

        assertEquals(expectedWord, actualWord);
    }

    @Test
    void blockedInitial() {
        //i,j
        //a1
        //0,0
        //b1
        //0,1
        //c1
        //0,2
        int[][] hBlocks = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] vBlocks = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Board board = defaultBoard();

        for (int i = 0; i < hBlocks.length; i++) {
            for (int j = 0; j < hBlocks.length; j++) {
                Position position = Position.fromIndices(i, j);
                boolean expectedHorizontal =  hBlocks[i][j] != 0;
                boolean expectedVertical =  vBlocks[i][j] != 0;
                boolean actualHorizontal =
                        board.isBlocked(position, Direction.right);
                boolean actualVertical =
                        board.isBlocked(position, Direction.down);
                assertEquals(expectedHorizontal, actualHorizontal);
                assertEquals(expectedVertical, actualVertical);
            }
        }
    }

    @Test
    void blockedPlacement() {
        int[][] hBlocks = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] vBlocks = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Board board = defaultBoard();
        Rack rack = catRack();
        Move move = Move.fromString("CAT,g8,r");
        move.place(board, rack);

        for (int i = 0; i < hBlocks.length; i++) {
            for (int j = 0; j < hBlocks.length; j++) {
                Position position = Position.fromIndices(i, j);
                boolean expectedHorizontal =  hBlocks[i][j] != 0;
                boolean expectedVertical =  vBlocks[i][j] != 0;
                boolean actualHorizontal =
                        board.isBlocked(position, Direction.right);
                boolean actualVertical =
                        board.isBlocked(position, Direction.down);
                assertEquals(expectedHorizontal, actualHorizontal);
                assertEquals(expectedVertical, actualVertical);
            }
        }
    }
    @Test
    void startPositionsInitial() {

        int[][] hStarts = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] vStarts = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Board board = defaultBoard();
        String letters = "CATSDRE";

        for (int i = 0; i < hStarts.length; i++) {
            for (int j = 0; j < hStarts.length; j++) {
                Position position = Position.fromIndices(i, j);
                Move moveRight = new WordMove(
                        letters, position, Direction.right);
                Move moveDown = new WordMove(
                        letters, position, Direction.down);

                boolean expectedHorizontal =  hStarts[i][j] != 0;
                boolean expectedVertical =  vStarts[i][j] != 0;
                boolean actualHorizontal =
                        board.validStart(moveRight);
                boolean actualVertical =
                        board.validStart(moveDown);

                if (expectedVertical != actualVertical) {
                    System.out.println(position);
                    System.out.print("\nvertical");
                    System.out.print(expectedVertical);
                    System.out.println(actualVertical);
                }
                if (expectedHorizontal != actualHorizontal) {
                    System.out.println(position);
                    System.out.println("\nhorizontal");
                    System.out.print(expectedHorizontal);
                    System.out.println(actualHorizontal);
                }

                assertEquals(expectedHorizontal, actualHorizontal);
                assertEquals(expectedVertical, actualVertical);
            }
        }
    }

    @Test
    void startPositionsAfterFirstWord() {
        //CAT
        int[][] hStarts = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] vStarts = {
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Board board = defaultBoard();
        Rack rack = catRack();
        Move move = Move.fromString("CAT,g8,r");
        move.place(board, rack);
        String letters = "ABCDEFG";

        for (int i = 0; i < hStarts.length; i++) {
            for (int j = 0; j < hStarts.length; j++) {
                Position position = Position.fromIndices(i, j);
                Move moveRight = new WordMove(
                        letters, position, Direction.right);
                Move moveDown = new WordMove(
                        letters, position, Direction.down);

                boolean expectedHorizontal =  hStarts[i][j] != 0;
                boolean expectedVertical =  vStarts[i][j] != 0;
                boolean actualHorizontal =
                        board.validStart(moveRight);
                boolean actualVertical =
                        board.validStart(moveDown);

                if (expectedVertical != actualVertical) {
                    System.out.println(position);
                    System.out.print(i);
                    System.out.print(j);
                    System.out.print("\nvertical");
                    System.out.print(expectedVertical);
                    System.out.println(actualVertical);
                }
                if (expectedHorizontal != actualHorizontal) {
                    System.out.println(position);
                    System.out.println("\nhorizontal");
                    System.out.print(expectedHorizontal);
                    System.out.println(actualHorizontal);
                }

                assertEquals(expectedHorizontal, actualHorizontal);
                assertEquals(expectedVertical, actualVertical);
            }
        }
    }

    @Test
    void startPositionsAfterSecondWord() {
        /*
                B
                O
               CAT
                R
                D
        */

        int[][] hStarts = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] vStarts = {
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        String letters = "ABCDEFG";

        Board board = defaultBoard();
        Rack rack = catRack();
        Move move = Move.fromString("CAT,g8,r");
        move.place(board, rack);

        rack.add(new Tile('B', 3));
        rack.add(new Tile('O', 1));
        rack.add(new Tile('R', 1));
        rack.add(new Tile('D', 2));
        move = Move.fromString("BORD,h6,d");
        move.place(board, rack);

        for (int i = 0; i < hStarts.length; i++) {
            for (int j = 0; j < hStarts.length; j++) {
                Position position = Position.fromIndices(i, j);
                Move moveRight = new WordMove(
                        letters, position, Direction.right);
                Move moveDown = new WordMove(
                        letters, position, Direction.down);

                boolean expectedHorizontal =  hStarts[i][j] != 0;
                boolean expectedVertical =  vStarts[i][j] != 0;
                boolean actualHorizontal =
                        board.validStart(moveRight);
                boolean actualVertical =
                        board.validStart(moveDown);

                if (expectedVertical != actualVertical) {
                    System.out.println(position);
                    System.out.print("\nvertical");
                    System.out.print(expectedVertical);
                    System.out.println(actualVertical);
                }
                if (expectedHorizontal != actualHorizontal) {
                    System.out.println(position);
                    System.out.println("\nhorizontal");
                    System.out.print(expectedHorizontal);
                    System.out.println(actualHorizontal);
                }
                assertEquals(expectedHorizontal, actualHorizontal);
                assertEquals(expectedVertical, actualVertical);
            }
        }
    }

    @Test
    void startPositionsAfterSecondLongerWord() {
        /*      
                B
                O
               CATS
                R
                D
        */
         
        int[][] hStarts = {
        //   A  B  C  D  E  F  G  H  I  J  K  L  M  N  O
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] vStarts = {
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        String letters = "ABCDEFG";

        Board board = defaultBoard();
        Rack rack = catRack();
        Move move = Move.fromString("CATS,g8,r");
        move.place(board, rack);

        rack.add(new Tile('B', 3));
        rack.add(new Tile('O', 1));
        rack.add(new Tile('R', 1));
        rack.add(new Tile('D', 2));
        move = Move.fromString("BORD,h6,d");
        move.place(board, rack);

        for (int i = 0; i < hStarts.length; i++) {
            for (int j = 0; j < hStarts.length; j++) {
                Position position = Position.fromIndices(i, j);
                Move moveRight = new WordMove(
                        letters, position, Direction.right);
                Move moveDown = new WordMove(
                        letters, position, Direction.down);

                boolean expectedHorizontal =  hStarts[i][j] != 0;
                boolean expectedVertical =  vStarts[i][j] != 0;
                boolean actualHorizontal =
                        board.validStart(moveRight);
                boolean actualVertical =
                        board.validStart(moveDown);

                if (expectedVertical != actualVertical) {
                    System.out.println(position);
                    System.out.print("\nvertical");
                    System.out.print(expectedVertical);
                    System.out.println(actualVertical);
                }
                if (expectedHorizontal != actualHorizontal) {
                    System.out.println(position);
                    System.out.println("\nhorizontal");
                    System.out.print(expectedHorizontal);
                    System.out.println(actualHorizontal);
                }
                assertEquals(expectedHorizontal, actualHorizontal);
                assertEquals(expectedVertical, actualVertical);
            }
        }
    }

    @Test
    void startPositionsBob() {
        /*      
                B
                O
               CATS
                R
                D
        */

        Board board = defaultBoard();
        Rack rack = catRack();
        Move move = Move.fromString("CATS,g8,r");
        move.place(board, rack);

        rack.add(new Tile('B', 3));
        rack.add(new Tile('O', 1));
        rack.add(new Tile('R', 1));
        rack.add(new Tile('D', 2));
        move = Move.fromString("BORD,h6,d");
        move.place(board, rack);

        boolean expectedBob = true;
        Position b = Position.fromString("f6");
        Position fail = Position.fromString("e6");
        Move bob = new WordMove("BO", b, Direction.right);
        Move failBob = new WordMove("BO", fail, Direction.right);
        boolean actualBob = board.validStart(bob);
        boolean expectedFailBob = board.validStart(failBob);

        assertEquals(expectedBob, actualBob);
        assertFalse(expectedBob == expectedFailBob);

        }
}
