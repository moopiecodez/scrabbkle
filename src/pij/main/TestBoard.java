package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pij.main.square.Square;
import pij.main.square.StandardSquare;

public class TestBoard {

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
    public void squareToString() {
        String squareString =  
                "{3}..(2)...{3}...(2)..{3}"
                +".{2}...(3)...(3)...{2}."
                +"..{2}...(2).(2)...{2}.."
                +"(2)..{2}...(2)...{2}..(2)"
                +"....{2}.....{2}...."
                +".(3)...(3)...(3)...(3)."
                +"..(2)...(2).(2)...(2).."
                +"{3}..(2)...{2}...(2)..{3}"
                +"..(2)...(2).(2)...(2).."
                +".(3)...(3)...(3)...(3)."
                +"....{2}.....{2}...."
                +"(2)..{2}...(2)...{2}..(2)"
                +"..{2}...(2).(2)...{2}.."
                +".{2}...(3)...(3)...{2}."
                +"{3}..(2)...{3}...(2)..{3}";
        String expectedString = 
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

        int size = 15;
   
        Board board = new Board(size, squareString);
        String actualString = board.toString();
        System.out.println(actualString);
        assertEquals(expectedString, actualString);
    }

    @Test
    public void pickSquare() {
        int size = 15;
        Position position = new Position(2, 'b');

        String squareString =
                "{3}..(2)...{3}...(2)..{3}.{2}...(3)...(3)...{2}."
                +"..{2}...(2).(2)...{2}..(2)..{2}...(2)...{2}..(2)"
                +"....{2}.....{2}.....(3)...(3)...(3)...(3)."
                +"..(2)...(2).(2)...(2)..{3}..(2)...{2}...(2)..{3}"
                +"..(2)...(2).(2)...(2)...(3)...(3)...(3)...(3)."
                +"....{2}.....{2}....(2)..{2}...(2)...{2}..(2)"
                +"..{2}...(2).(2)...{2}...{2}...(3)...(3)...{2}."
                +"{3}..(2)...{3}...(2)..{3}";

        Board board = new Board(size, squareString);
        String expectedString = "{2}";

        Square square = board.getSquare(position);
        String actualString = square.toString();
        assertEquals(expectedString, actualString);
    }

    @Test
    public void placeTile() {
        int size = 15;
        Position position = new Position(2, 'b');
        Tile tile = new Tile ('A', 1);

        String squareString =
                "{3}..(2)...{3}...(2)..{3}.{2}...(3)...(3)...{2}."
                +"..{2}...(2).(2)...{2}..(2)..{2}...(2)...{2}..(2)"
                +"....{2}.....{2}.....(3)...(3)...(3)...(3)."
                +"..(2)...(2).(2)...(2)..{3}..(2)...{2}...(2)..{3}"
                +"..(2)...(2).(2)...(2)...(3)...(3)...(3)...(3)."
                +"....{2}.....{2}....(2)..{2}...(2)...{2}..(2)"
                +"..{2}...(2).(2)...{2}...{2}...(3)...(3)...{2}."
                +"{3}..(2)...{3}...(2)..{3}";

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

        Board board = new Board(size, squareString);
        board.placeTile(position, tile);
        String actualString = board.toString();
        assertEquals(expectedString, actualString);
    }

    @Test
    public void findCentreSquareOddBoard() {
        int size = 15;
        String expectedCoordinates = "h8";
        String squareString =
                "{3}..(2)...{3}...(2)..{3}.{2}...(3)...(3)...{2}."
                +"..{2}...(2).(2)...{2}..(2)..{2}...(2)...{2}..(2)"
                +"....{2}.....{2}.....(3)...(3)...(3)...(3)."
                +"..(2)...(2).(2)...(2)..{3}..(2)...{2}...(2)..{3}"
                +"..(2)...(2).(2)...(2)...(3)...(3)...(3)...(3)."
                +"....{2}.....{2}....(2)..{2}...(2)...{2}..(2)"
                +"..{2}...(2).(2)...{2}...{2}...(3)...(3)...{2}."
                +"{3}..(2)...{3}...(2)..{3}";
        
        Board board = new Board(size, squareString);
        Position position = board.centreSquare();
        String actualCoordinates = position.toString();

        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void findCentreSquareEvenBoard() {
        int size = 14;
        String expectedCoordinates = "g7";
        String squareString =
                "{3}..(2)...{3}...(2)..{3}.{2}...(3)...(3)...{2}"
                +"..{2}...(2).(2)...{2}..(2)..{2}...(2)...{2}.."
                +"....{2}.....{2}.....(3)...(3)...(3)...(3)"
                +"..(2)...(2).(2)...(2)..{3}..(2)...{2}...(2).."
                +"..(2)...(2).(2)...(2)...(3)...(3)...(3)...(3)"
                +"....{2}.....{2}....(2)..{2}...(2)...{2}.."
                +"..{2}...(2).(2)...{2}...{2}...(3)...(3)...{2}"
                +"{3}..(2)...{3}...(2)..{3}";
        
        Board board = new Board(size, squareString);
        Position position = board.centreSquare();
        String actualCoordinates = position.toString();
        
        assertEquals(expectedCoordinates, actualCoordinates);
    }

}
