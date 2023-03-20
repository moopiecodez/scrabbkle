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
}
