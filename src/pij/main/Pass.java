package pij.main;

import pij.main.dictionary.Dictionary;

public final class Pass extends Move {

    private static final String PASS_MSG = "a pass.";

    /** It's a pass so do nothing. */
    public int place(Board board, Rack rack) {
        return 0;
    }

    /** Letters of move relevant for a pass. */
    public String getLetters() {
        return "";
    }

    /** Position of move relevant for a pass. */
    public Position getPosition() {
        return null;
    }

    /** Direction of move relevant for a pass. */
    public Direction getDirection() {
        return null;
    }

    public String toString() {
        return PASS_MSG;
    }

    public boolean validate(
            final Board board, final Rack rack, final Dictionary dictionary) {
        return true;
    }

    public String getErrorMsg() {
        return "";
    }

}
