package pij.main;

public final class Pass extends Move {

    private static final String PASS_MSG = "a pass.";

    /** It's a pass so do nothing. */
    public void place(Board board, Rack rack) {
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

    public boolean validate(final Board board, final Rack rack) {
        return true;
    }

    public String getErrorMsg() {
        return "";
    }

}
