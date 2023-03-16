package pij.main.square;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pij.main.Tile;

public class TestSquare {

    @Test
    public void setTileOnEmptySquare() {
        Tile expectedTile = new Tile('A', 1);
        Square aSquare = Square.create(".");
        
        aSquare.setTile(expectedTile);
        Tile actualTile = aSquare.getTile();
        
        assertEquals(expectedTile, actualTile);
        
        
    }
    
    @Test
    public void setTileOnFullSquare() {
        Tile aTile = new Tile('A', 1);
        Square fullSquare = Square.create(".");
        String expectedMessage = 
                "Full square, this square already contains a tile.";
       
        fullSquare.setTile(aTile);
        
        try {
            fullSquare.setTile(aTile);
            fail("expected IllegalStateException");
        } catch(IllegalStateException exception){
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        }
    }

    @Test
    public void createStandardSquare() {
        String token = ".";
        Class<StandardSquare> expectedType = StandardSquare.class;
        Square actualType = Square.create(token);
        
        assertInstanceOf(expectedType, actualType);
    }

    @Test
    public void createPremiumLetterSquare() {
        Class<PremiumLetterSquare> expectedType = PremiumLetterSquare.class;
        int expectedMultiplier = 3;

        String token = "(3)";

        Square square = Square.create(token);
        int actualMultiplier = square.getLetterMultiplier();

        assertInstanceOf(expectedType, square);
        assertEquals(expectedMultiplier, actualMultiplier);
    }

    @Test
    public void createPremiumWordSquare() {
        Class<PremiumWordSquare> expectedType = PremiumWordSquare.class;
        int expectedMultiplier = 2;

        String token = "{2}";

        Square square = Square.create(token);
        int actualMultiplier = square.getWordMultiplier();
        
        assertInstanceOf(expectedType, square);
        assertEquals(expectedMultiplier, actualMultiplier);
    }

    @Test
    public void getSquareLetterScoreStandard() {
        int expectedLetterScore = 3;
        Square square = new StandardSquare();
        Tile tile = new Tile('P', 3);
        square.setTile(tile);        

        int actualLetterScore = square.calculateLetterScore();

        assertEquals(expectedLetterScore, actualLetterScore);
    }

    @Test
    public void getSquareLetterScorePremium() {
        int expectedLetterScore = 12;
        Square square = new PremiumLetterSquare(4);
        Tile tile = new Tile('P', 3);
        square.setTile(tile);

        int actualLetterScore = square.calculateLetterScore();

        assertEquals(expectedLetterScore, actualLetterScore);
    }

    @Test
    public void getSquareWordMultiplierStandard() {
        int expectedWordMultiplier = 1;
        Square square = new StandardSquare();
        Tile tile = new Tile('P', 3);
        square.setTile(tile);

        int actualWordMultiplier = square.getWordMultiplier();

        assertEquals(expectedWordMultiplier, actualWordMultiplier);
    }

    @Test
    public void getSquareWordMultiplierPremium() {
        int expectedWordMultiplier = 2;
        Square square = new PremiumWordSquare(2);
        Tile tile = new Tile('P', 3);
        square.setTile(tile);

        int actualWordMultiplier = square.getWordMultiplier();

        assertEquals(expectedWordMultiplier, actualWordMultiplier);
    }
}