import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTile {
    
    @Test
    void tileGetScore() {
        int expectedScore = 1;
        
        Tile tile = new Tile();
        tile.setScore(1);

        int actualScore = tile.getScore();
        
        assertEquals(expectedScore, actualScore);
    }
    
    @Test
    void tileBadScore() {
        String expectedMessage = "Bad score: -1 not in range 0-10";
        
        Tile tile = new Tile();
        
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> tile.setScore(-1)
        );
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        
    }

    @Test
    void tileGetLetter() {
        char expectedLetter = 'S';
        
        Tile tile = new Tile();
        tile.setLetter('S');
        
        char actualLetter = tile.getLetter();
        
        assertEquals(expectedLetter, actualLetter);
    }
    
    @Test
    void tileToString() {
        String expectedString = "S1";
        
        Tile tile = new Tile();
        tile.setLetter('S');
        tile.setScore(1);
        
        String actualString = tile.toString();
        
        assertEquals(expectedString, actualString);
    }
}

//        System.out.println(s.showTile());
