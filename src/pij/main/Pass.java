package pij.main;

public final class Pass extends Move {

    private static final String PASS_MSG = "a pass.";

    /** It's a pass so do nothing. */
    public void place(Board board, Rack rack) {
    }

    /** Letters of move relevant for a pass. */
    public String getLetters() {
        throw new UnsupportedOperationException();
    }

    /** Position of move relevant for a pass. */
    public Position getPosition() {
        throw new UnsupportedOperationException();
    }

    /** Direction of move relevant for a pass. */
    public Direction getDirection() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return PASS_MSG;
    }

    public boolean validLetters(Rack rack) {
        return true;
    }

    public boolean validOrigin(Board board) {
        return true;
    }

    public boolean wordFits(Board board) {
        return true;
    }

    public boolean validWord() {
        return true;
    }

}
