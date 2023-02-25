package pij.main;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TestSquare {

    @Test
    public void setTileOnEmptySquare() {
        Tile expectedTile = new Tile('A', 1);
        Square aSquare = new Square();
        
        aSquare.setTile(expectedTile);
        Tile actualTile = aSquare.getTile();
        
        assertEquals(expectedTile, actualTile);
        
        
    }
    
    @Test
    public void setTileOnFullSquare() {
        Tile aTile = new Tile('A', 1);
        Square fullSquare = new Square();
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
}