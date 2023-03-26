package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import pij.main.Move.Direction;

public class TestMove {

    @Test
    void validInput() {
        String input = "GIT,f8,r";

        boolean valid = Human.validateInput(input);

        assertTrue(valid);
    }

    @Test
    void validPass() {
        String input = ",,";

        boolean valid = Human.validateInput(input);

        assertTrue(valid);
    }

    @Test
    void simpleMove() {
        String moveString = "STAR,b1,r";
        String expectedLetters = "STAR";
        String expectedPosition = "b1";
        Direction expectedDirection = Direction.RIGHT;

        Move move = Move.fromString(moveString);
        String actualLetters = move.getLetters();
        Position position = move.getPosition();
        String actualPosition = position.toString();
        Direction actualDirection = move.getDirection();
        
        assertEquals(expectedLetters, actualLetters);
        assertEquals(expectedPosition, actualPosition);
        assertEquals(expectedDirection, actualDirection);
    }

}
