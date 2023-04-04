package pij.main;

import java.io.Console;
import java.util.regex.Pattern;

import pij.main.dictionary.Dictionary;

public class Human extends Player {

    private static final String TILE_MSG = "It's your turn! Your tiles:";
    private static final String USER_PLAY_MSG = 
            "Please enter your move with letter sequence, position, and\n"
            + "direction (d for down, r for right) separated by commas.\n"
            + "Entering just two commas passes.\n";
    private static final String INVALID_MOVE = "This is not a valid move.";

    public Human(Rack rack) {
        super(rack);
    }

    /**
     * Asks the user to type in a move repeatedly until a valid move is
     * provided.
     *
     * @param board current board that the move will be played on
     * @param dictionary of valid words
     * @return the validated move
     */
    public Move chooseMove(final Board board, final Dictionary dictionary) {
        Move move = null;

        displayStartOfUserTurnMsg();

        while (move == null) {
            String string = getMoveStringFromUser();
            MoveVerifier verifier = new MoveVerifier(
                    board, this.rack, string, dictionary);
            if (verifier.isValid()) {
                move = verifier.getMove();
            } else {
                String errorMsg = verifier.getErrorMsg();
                displayErrors(errorMsg);
            }
        }

        return move;
    }

    public void displayStartOfUserTurnMsg() {
        System.out.println(TILE_MSG);
        System.out.println(this.rack.toString() + "\n");
    }

    public void displayErrors(final String errorMsg) {
        System.out.println(INVALID_MOVE);
        System.out.println(errorMsg);
    }

    public String getMoveStringFromUser() {
        System.out.println(USER_PLAY_MSG);
        Console console = System.console();
        String moveString = console.readLine();

        return moveString;
    }

}
