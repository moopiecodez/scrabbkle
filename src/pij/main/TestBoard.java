package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestBoard {
    @Test
    public void squareToString() {
        String expectedString = 
                    "    a  b  c \n"
                  + " 1 {3} . (5)\n"
                  + " 2  . {21 . \n"
                  + " 3  .  . (-8\n";
        
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
        
        int size = 3; 
        Square [][] squareMatrix =  new Square [size][size];
            squareMatrix[0][0] = new PremiumWordSquare(3);
            squareMatrix[0][1] = new StandardSquare();
            squareMatrix[0][2] = new PremiumLetterSquare(5);
            squareMatrix[1][0] = new StandardSquare();
            squareMatrix[1][1] = new PremiumWordSquare(21);
            squareMatrix[1][2] = new StandardSquare();
            squareMatrix[2][0] = new StandardSquare();
            squareMatrix[2][1] = new StandardSquare();
            squareMatrix[2][2] = new PremiumLetterSquare(-8);

        Board board = new Board(size, squareMatrix);
        String actualString = board.toString();

        assertEquals(expectedString, actualString);
    }
}
