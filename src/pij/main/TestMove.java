package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pij.main.square.PremiumLetterSquare;
import pij.main.square.PremiumWordSquare;
import pij.main.square.Square;
import pij.main.square.StandardSquare;

public class TestMove {

    //Note this test just goes through qn array of squares scoring.
    //How those squares are selected isn't part of scoring
    @Test
    public void wordLetterScoring() {
        int expectedWordLetterScore = 21;

        Tile tileA = new Tile('Z', 10);
        Tile tileB = new Tile('I', 1);
        Tile tileC = new Tile('P', 3);
        Square [] word = new Square [3];
            word[0] = new StandardSquare();
            word[1] = new PremiumLetterSquare(2);
            word[2] = new PremiumLetterSquare(3);
        word[0].setTile(tileA);
        word[1].setTile(tileB);
        word[2].setTile(tileC);

        int actualWordLetterScore = Move.letterScoring(word);

        assertEquals(expectedWordLetterScore, actualWordLetterScore);
    }

    @Test
    public void fullWordMultiplier() {
        int expectedFullWordMultiplier = 6;

        Tile tileA = new Tile('Z', 10);
        Tile tileB = new Tile('I', 1);
        Tile tileC = new Tile('P', 3);
        Square[] word = new Square[3];
            word[0] = new StandardSquare();
            word[1] = new PremiumWordSquare(2);
            word[2] = new PremiumWordSquare(3);
        word[0].setTile(tileA);
        word[1].setTile(tileB);
        word[2].setTile(tileC);

        int actualFullWordMultiplier = Move.fullWordMultiplier(word);

        assertEquals(expectedFullWordMultiplier, actualFullWordMultiplier);
    }

    @Test
    public void wordScore() {
        int expectedFullWordScore = 45;

        Tile tileA = new Tile('Z', 10);
        Tile tileB = new Tile('I', 1);
        Tile tileC = new Tile('P', 3);
        Square [] word = new Square [3];
            word[0] = new StandardSquare();
            word[1] = new PremiumLetterSquare(2);
            word[2] = new PremiumWordSquare(3);
        word[0].setTile(tileA);
        word[1].setTile(tileB);
        word[2].setTile(tileC);

        int actualFullWordScore = Move.wordScoring(word);

        assertEquals(expectedFullWordScore, actualFullWordScore);
    }

    public void horizontalOriginFirstMove() {
        int size = 15;
        String expectedCoordinates = "";
        
        String squareString =
                "{3}..(2)...{3}...(2)..{3}.{2}...(3)...(3)...{2}."
                +"..{2}...(2).(2)...{2}..(2)..{2}...(2)...{2}..(2)"
                +"....{2}.....{2}.....(3)...(3)...(3)...(3)."
                +"..(2)...(2).(2)...(2)..{3}..(2)...{2}...(2)..{3}"
                +"..(2)...(2).(2)...(2)...(3)...(3)...(3)...(3)."
                +"....{2}.....{2}....(2)..{2}...(2)...{2}..(2)"
                +"..{2}...(2).(2)...{2}...{2}...(3)...(3)...{2}."
                +"{3}..(2)...{3}...(2)..{3}";
        
        String boardString = 
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
        board.placeTile(row, column, tile);
    }
}
