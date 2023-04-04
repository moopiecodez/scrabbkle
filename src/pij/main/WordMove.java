package pij.main;

import pij.main.dictionary.Dictionary;

public class WordMove extends Move {

    private static final String RACK_LETTERS_ERR =
            "You don't have the tiles.\n";
    private static final String ORIGIN_ERR =
            "Invalid starting position for your word.\n";
    private static final String PLACEMENT_ERR =
            "This move can't be placed here.\n";
    private static final String WORD_ERR =
            "The word is not valid.\n";

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
        int bonus;
        Position position = this.position;
        String remainingLetters = this.letters;
        char letter;
        Tile tile;
        Direction direction = this.getDirection();
        while (!remainingLetters.isEmpty() || !board.isPositionFree(position)) {
            if (board.isPositionFree(position)) {
                letter = remainingLetters.charAt(0);
                tile = rack.take(letter);
                board.placeTile(position, tile);

                if (remainingLetters.length() == 1) {
                    remainingLetters = "";
                } else {
                    remainingLetters = remainingLetters.substring(1);
                }
            }
            letterScore += board.getLetterScore(position);
            wordMultiplier *= board.getWordMultiplier(position);
            //happens even on full squares
            board.setStandardScoring(position);

            position = position.next(direction);
        }
        bonus = this.letters.length() == Rack.RACK_SIZE ? 70 : 0;

        return (letterScore * wordMultiplier) + bonus;
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

    public boolean validate(
            final Board board, final Rack rack, final Dictionary dictionary) {
        this.valid = true;
        validLetters(rack);
        validStart(board);
        validPlacement(board);
        if (this.valid) {
            validWord(dictionary, board);
        }

        return this.valid;
    }

    private void validLetters(final Rack rack) {
        if (!rack.hasLetters(this.letters)) {
            handleError(RACK_LETTERS_ERR);
        }
    }

    private void validStart(final Board board) {
        if (!board.checkStart(this)) {
            handleError(ORIGIN_ERR);
        }
    }

    private void validPlacement(final Board board) {
        if (!board.checkPlacement(this)) {
            handleError(PLACEMENT_ERR);
        }
    }

    public void validWord(final Dictionary dictionary, final Board board) {
        String word = board.getWord(this);
        if (!dictionary.contains(word)) {
            handleError(WORD_ERR);
        }
    }

    private void handleError( String errorMsg) {
        this.errorMsg += errorMsg;
        this.valid = false;
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

    public void updatePassCounter(final Player player) {
        player.passCounter = 0;
    }

}
