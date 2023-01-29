package pij.main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        try {
            tile.setScore(-1);
            fail("expected IllegalArgumentException");
        }
        catch (Exception exception) {
            boolean isIllegalArgument = exception instanceof IllegalArgumentException;
            assertTrue(isIllegalArgument);
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        }
    }

    @Test
    void tileBadLetter() {
        String expectedMessage = "Bad letter: '*' not in range A-Z or ' '";
        Tile tile = new Tile();

        try {
            tile.setLetter('*');
            fail("expected IllegalAgumentException");
        }
        catch (Exception exception) {
            boolean isIllegalArgument = exception instanceof IllegalArgumentException;
            assertTrue(isIllegalArgument);
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        }
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
