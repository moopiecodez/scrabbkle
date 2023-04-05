package pij.main;

import pij.main.dictionary.Dictionary;

/**
 * Helper class to check if a Move is valid.
 * @author Maurane van der Stoep
 *
 */
public class MoveVerifier {
    private static final String INPUT_ERR =
            "The input is not valid.\n";

    private final Board board;
    private final Rack rack;
    private final String moveString;
    private final Dictionary dictionary;

    private Move move;
    private String errorMsg;
    private boolean valid;

    /**
     * Generates a MoveVerifier for a given Move from its moveString.
     * @param board
     * @param rack
     * @param moveString
     * @param dictionary
     */
    public MoveVerifier(final Board board, final Rack rack,
            final String moveString, final Dictionary dictionary) {

        this.board = board;
        this.rack = rack;
        this.moveString = moveString;
        this.dictionary = dictionary;

        this.move = null;
        this.errorMsg = "";
        this.valid = true;

        validate();
    }

    private void validate() {
        if (Move.validateString(this.moveString)) {
            this.move = Move.fromString(moveString);
            validateMove();
        } else {
            handleError(INPUT_ERR);
        }
    }

    private void validateMove() {
        if (!this.move.validate(this.board, this.rack, this.dictionary)) {
            handleError(this.move.getErrorMsg());
        }
    }

    private void handleError(final String errorMsg) {
        this.errorMsg += errorMsg;
        this.valid = false;
    }

    /**
     * Returns a boolean indicating if a Move is valid.
     * @return true if valid
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * Returns the String of error messages indicating why a Move is not valid.
     * @return string
     */
    public String getErrorMsg() {
        return this.errorMsg;
    }

    /**
     * Returns the Move being validated by the MoveVerifier.
     * @return move
     */
    public Move getMove() {
        return this.move;
    }

}
