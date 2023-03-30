package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import pij.main.Move.Direction;

public class TestMove {

    int size = 15;
    String squareString =
            "{3}..(2)...{3}...(2)..{3}"
          + ".{2}...(3)...(3)...{2}."
          + "..{2}...(2).(2)...{2}.."
          + "(2)..{2}...(2)...{2}..(2)"
          + "....{2}.....{2}...."
          + ".(3)...(3)...(3)...(3)."
          + "..(2)...(2).(2)...(2).."
          + "{3}..(2)...{2}...(2)..{3}"
          + "..(2)...(2).(2)...(2).."
          + ".(3)...(3)...(3)...(3)."
          + "....{2}.....{2}...."
          + "(2)..{2}...(2)...{2}..(2)"
          + "..{2}...(2).(2)...{2}.."
          + ".{2}...(3)...(3)...{2}."
          + "{3}..(2)...{3}...(2)..{3}";

    @Test
    void validInput() {
        String input = "GIT,f8,r";

        boolean valid = Move.validateString(input);

        assertTrue(valid);
    }

    @Test
    void validPass() {
        String input = ",,";

        boolean valid = Move.validateString(input);

        assertTrue(valid);
    }

    @Test
    void simpleMove() {
        String moveString = "STAR,b1,r";
        String expectedLetters = "STAR";
        String expectedPosition = "b1";
        Direction expectedDirection = Direction.right;

        Move move = Move.fromString(moveString);
        String actualLetters = move.getLetters();
        Position position = move.getPosition();
        String actualPosition = position.toString();
        Direction actualDirection = move.getDirection();

        assertEquals(expectedLetters, actualLetters);
        assertEquals(expectedPosition, actualPosition);
        assertEquals(expectedDirection, actualDirection);
    }

    @Test
    void validatePosition() {
        Position position = Position.fromString("a3");

        Board board = new Board(size, squareString);

        assertTrue(board.positionExists(position));
    }

    @Test
    void checkSquareEmpty() {
        Board board = new Board(size, squareString);
        Position position = Position.fromString("a3");
        
        assertTrue(board.isPositionFree(position));
    }
}
