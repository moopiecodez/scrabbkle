package pij.main;

public class MoveVerifier {
    private static final String INPUT_ERR = 
            "The input is not valid.";

    private boolean valid;
    private final Board board;
    private final Rack rack;
    private final String moveString;
    private Move move;
    private String errorMsg;

    public MoveVerifier(final Board board, final Rack rack,
            final String moveString) {

        this.board = board;
        this.rack = rack;
        this.moveString = moveString;

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
        if (!this.move.validate(this.board, this.rack)) {
            handleError(this.move.getErrorMsg());
        }
    }

    private void handleError(final String errorMsg) {
        this.errorMsg += errorMsg + "\n";
        this.valid = false;
    }

    public boolean isValid() {
        return this.valid;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public Move getMove() {
        return this.move;
    }

}
