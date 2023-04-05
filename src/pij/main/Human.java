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

    /**
     * Creates a Human player with the given Rack and Dictionary.
     * @param rack
     * @param dictionary
     */
    public Human(final Rack rack, final Dictionary dictionary) {
        super(rack, dictionary);
    }

    /**
     * Asks the user to type in a move repeatedly until a valid move is
     * provided.
     *
     * @param board current board that the move will be played on
     * @return the validated move
     */
    public Move chooseMove(final Board board) {
        Move move = null;

        displayStartOfUserTurnMsg();

        while (move == null) {
            String string = getMoveStringFromUser();
            MoveVerifier verifier = new MoveVerifier(
                    board, this.rack, string, this.dictionary);
            if (verifier.isValid()) {
                move = verifier.getMove();
            } else {
                String errorMsg = verifier.getErrorMsg();
                displayErrors(errorMsg);
            }
        }

        return move;
    }

    /**
     * Displays a message to the user that it is their turn and what Tiles are
     * on their Rack.
     */
    public void displayStartOfUserTurnMsg() {
        System.out.println(TILE_MSG);
        System.out.println(this.rack.toString() + "\n");
    }

    /**
     * Displays the reasons why a user's Move was invalid.
     * @param errorMsg
     */
    public void displayErrors(final String errorMsg) {
        System.out.println(INVALID_MOVE);
        System.out.println(errorMsg);
    }

    /**
     * Displays text asking the user to input their move in a particular format.
     * @return string input by the user
     */
    public String getMoveStringFromUser() {
        System.out.println(USER_PLAY_MSG);
        Console console = System.console();
        String moveString = console.readLine();

        return moveString;
    }

}
