package pij.main;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pij.main.dictionary.Dictionary;

public class TestComputer {

    
    static final int DEFAULT_SIZE = 15;
    static final String DEFAULT_SQUARE_STRING =
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
    void chooseMove() {
        Rack rack = new Rack();
        Board board = new Board(DEFAULT_SIZE, DEFAULT_SQUARE_STRING);
        Dictionary dictionary = null;
        Player computer = new Computer(rack, dictionary);

        Move move = computer.chooseMove(board);
        System.out.println(move);
        assertTrue(false);
    }

}
