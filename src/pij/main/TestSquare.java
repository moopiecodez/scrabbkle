package pij.main;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

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
        String token = "(3)";
        Class<PremiumLetterSquare> expectedType = PremiumLetterSquare.class;
        Square actualType = Square.create(token);
        
        assertInstanceOf(expectedType, actualType);
    }

    @Test
    public void createPremiumWordSquare() {
        String token = "{2}";
        Class<PremiumWordSquare> expectedType = PremiumWordSquare.class;
        Square actualType = Square.create(token);
        
        assertInstanceOf(expectedType, actualType);
    }
}