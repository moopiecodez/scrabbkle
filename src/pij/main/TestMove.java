package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import pij.main.Move.Direction;

public class TestMove {

    static final int defaultSize = 15;
    static final String defaultSquareString =
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

    Board defaultBoard() {
        Board board = new Board(defaultSize, defaultSquareString);
        return board;
    }

    Rack catRack() {
        Rack rack = new Rack();
        Tile tileC = new Tile('C', 3);
        Tile tileA = new Tile('A', 1);
        Tile tileT = new Tile('T', 1);
        Tile tileS = new Tile('S', 1);
        rack.add(tileS);
        rack.add(tileC);
        rack.add(tileA);
        rack.add(tileT);

        return rack;
    }

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

        Board board = defaultBoard();

        assertTrue(board.positionExists(position));
    }

    @Test
    void checkSquareEmpty() {
        Board board = defaultBoard();
        Position position = Position.fromString("a3");

        assertTrue(board.isPositionFree(position));
    }

    @Test
    void placeMoveOnEmptyBoard() {
        Board board = defaultBoard();
        Rack rack = catRack();

        String expectedWord = "CAT";
        int expectedScore = 10;

        Move move = Move.fromString("CAT,f8,r");


        String actualWord = board.getWord(move);
        int actualScore = move.place(board, rack);

        assertEquals(expectedWord, actualWord);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    void placeMoveOnBoardWithTileAtEnd() {
        Board board = defaultBoard();
        Rack rack = catRack();
        Tile tileA = new Tile('S',1);
        Position positionA = Position.fromString("h8");

        Move move = Move.fromString("CAT,e8,r");

        String expectedWord = "CATS";
        int expectedScore = 6;

        board.placeTile(positionA, tileA);
        board.setStandardScoring(positionA);

        String actualWord = board.getWord(move);
        int actualScore = move.place(board, rack);

        assertEquals(expectedWord, actualWord);
        assertEquals(expectedScore, actualScore);

    }

    @Test
    void placeMoveOnBoardWithTileAtStart() {
        Board board = defaultBoard();
        Rack rack = catRack();
        Tile tileA = new Tile('C',3);
        Position positionA = Position.fromString("h8");

        Move move = Move.fromString("ATS,h8,r");

        String expectedWord = "CATS";
        int expectedScore = 6;

        board.placeTile(positionA, tileA);
        board.setStandardScoring(positionA);

        String actualWord = board.getWord(move);
        int actualScore = move.place(board, rack);

        assertEquals(expectedWord, actualWord);
        assertEquals(expectedScore, actualScore);

    }

    @Test
    void placeMoveOnBoardWithTileBetweenLetters() {
        Board board = defaultBoard();
        Rack rack = catRack();
        Tile tileA = new Tile('A',1);
        Position positionA = Position.fromString("h8");

        Move move = Move.fromString("CTS,g8,r");

        String expectedWord = "CATS";
        int expectedScore = 6;

        board.placeTile(positionA, tileA);
        board.setStandardScoring(positionA);

        String actualWord = board.getWord(move);
        int actualScore = move.place(board, rack);

        assertEquals(expectedWord, actualWord);
        assertEquals(expectedScore, actualScore);

    }

    @Test
    void bonusScore() {
        Board board = defaultBoard();
        Move move = Move.fromString("ABCDEFG,e8,r");
        Rack rack = new Rack();
        rack.add(new Tile('A', 1));
        rack.add(new Tile('B', 3));
        rack.add(new Tile('C', 3));
        rack.add(new Tile('D', 2));
        rack.add(new Tile('E', 1));
        rack.add(new Tile('F', 4));
        rack.add(new Tile('G', 2));

        int expectedScore = 102;
        int actualScore = move.place(board, rack);

        assertEquals(expectedScore, actualScore);
    }
}
