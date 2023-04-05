package pij.main;

import pij.main.dictionary.Dictionary;

/**
 * Class representing when a player passes their turn in Scrabbkle.
 * @author Maurane van der Stoep
 *
 */
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

    /** String indicating the Move is a pass. */
    public String toString() {
        return PASS_MSG;
    }

    /** A pass is always valid provided it is input correctly. */
    public boolean validate(
            final Board board, final Rack rack, final Dictionary dictionary) {
        return true;
    }

    /** A Pass will not have an error message, the only possible error is if
     * the String to input it is incorrectly formatted.
     */
    public String getErrorMsg() {
        return "";
    }

    /** Updates the passCounter, incrementing it by 1 for a Player that plays a
     * pass is played.
     */
    public void updatePassCounter(final Player player) {
        player.passCounter++;
    }

}
