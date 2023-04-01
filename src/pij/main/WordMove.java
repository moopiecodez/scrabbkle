package pij.main;

public class WordMove extends Move {

    private static final String RACK_LETTERS_ERR =
            "You don't have the tiles.\n";
    private static final String ORIGIN_ERR =
            "Invalid starting position for your word.\n";
    private static final String PLACEMENT_ERR =
            "This move can't be placed here.\n";

    private String letters;
    private Position position;
    private Direction direction;
    private String errorMsg;
    private boolean valid;
    
    public WordMove(String letters, Position position, Direction direction) {
        this.letters = letters;
        this.position = position;
        this.direction = direction;
        this.errorMsg = "";
    }

    public int place(Board board, Rack rack) {
        int letterScore = 0;
        int wordMultiplier = 1;
        Position position = this.position;
        String remainingLetters = this.letters;
        char letter;
        Tile tile;
        Direction direction = this.getDirection();
        //fix as doesn't skip
        while (!remainingLetters.isEmpty()) {
            if (board.isPositionFree(position)) {
                letter = remainingLetters.charAt(0);
                tile = rack.take(letter);
                board.placeTile(position, tile);
            }
            if (remainingLetters.length() == 1) {
                remainingLetters = "";
            } else {
                remainingLetters = remainingLetters.substring(1);
            }
            letterScore += board.getLetterScore(position);
            wordMultiplier *= board.getWordMultiplier(position);
            board.setStandardScoring(position);

            position = position.next(direction);
        }

        return letterScore * wordMultiplier;
    }

    public String getLetters() {
        return this.letters;
    }

    public Position getPosition() {
        return this.position;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean validate(Board board, Rack rack) {
        this.valid = true;
        validLetters(rack);
        validOrigin(board);
        validPlacement(board);
        return this.valid;
    }

    private void validLetters(final Rack rack) {
        boolean error = !rack.hasLetters(letters);
        handleError(error, RACK_LETTERS_ERR);
    }

    private void validOrigin(final Board board) {
        boolean error = !board.hasValidOrigin(this);
        handleError(error, ORIGIN_ERR);
    }

    private void validPlacement(final Board board) {
        boolean error = !board.checkPlacement(this);
        handleError(error, PLACEMENT_ERR);
    }

    public boolean validWord() {
        //TODO letters + tiles on board
        //return dictionary.contains(word);
            System.out.println("All hail King Bob!\n Banana!?");
            return true;
    }
    
    /*
    if (!move.validWord()) {
        this.errorMsg += "Invalid word.\n";
        this.valid = false;
    }
    */

    private void handleError(boolean error, String errorMsg) {
        if (error) {
            this.errorMsg += errorMsg;
            this.valid = false;
        }
    }

    public String toString() {
        String string = String.format(
                "Word: %s at position %s, direction: %s",
                this.letters, this.position, this.direction);
        return string;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

}
