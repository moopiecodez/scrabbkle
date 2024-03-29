package pij.main.square;

import pij.main.Move.Direction;
import pij.main.Tile;

/**
 * Class configuring how individual squares on a board are used in the game of
 * Scrabbkle.
 * 
 * @author Maurane van der Stoep
 *
 */
public abstract class Square {

    private Tile tile;
    private LetterScoring letterScoring;
    private WordScoring wordScoring;
    private final int letterMultiplier;
    private final int wordMultiplier;
    private boolean blockedRight;
    private boolean blockedDown;

    /**
     * Squares have a letterMultiplier and wordMultiplier, both of which are 1
     * in a StandardSquare. Constructor only used by Square subclasses.
     * @param letterMultiplier
     * @param wordMultiplier
     */
    public Square(final int letterMultiplier, final int wordMultiplier) {
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
        this.blockedRight = false;
        this.blockedDown = false;

    }

    /**
     * Helper method to parse multiplier for premium squares from a token.
     * @param token
     * @return multiplier for a premium square.
     */
    private static int parsePremiumValue(final String token) {
        String value = token.substring(1, token.length() - 1);
        int multiplier = Integer.parseInt(value);
        return multiplier;
    }

    /**
     * Factory method to create Squares from the token read from a board file.
     * @param token
     * @return either a StandardSquare, PremiumLetterSquare or PremiumWordSquare
     * depending on the token.
     */
    public static Square create(final String token) {
        Square square = null;
        int multiplier;
        char first = token.charAt(0);

        switch (first) {
            case '.':
                square = new StandardSquare();
                break;
            case '(':
                multiplier = parsePremiumValue(token);
                square = new PremiumLetterSquare(multiplier);
                break;
            case '{':
                multiplier = parsePremiumValue(token);
                square = new PremiumWordSquare(multiplier);
                break;
            default:
                break;
        }

        return square;
    }

    /**
     * Sets a given Tile onto the Square if the Square is empty. An exception
     * is thrown if the Square already holds a Tile.
     * @param tile to be placed.
     */
    public void setTile(final Tile tile) {
        if (!isEmpty()) {
            String message =
                    "Full square, this square already contains a tile.";
            throw new IllegalStateException(message);
        }
        this.tile = tile;
    }

    /**
     * Checks if a square is empty.
     * @return true if square is empty.
     */
    public boolean isEmpty() {
        return this.tile == null;
    }

    /**
     * Checks if a Square is blocked in a given direction.
     * @param direction
     * @return true if blocked
     */
    public boolean isBlocked(Direction direction) {
        boolean blocked = switch(direction) {
            case right -> this.blockedRight;
            case down -> this.blockedDown;
        };
        return blocked;
    }

    /**
     * Sets the blocked status of a Square in a given Direction.
     * @param direction
     */
    public void setBlocked(final Direction direction) {
        switch (direction) {
            case right -> this.blockedRight = true;
            case down -> this.blockedDown = true;
        }
    }

    /**
     * Generates the String representation of a given Square.
     * @return string
     */
    public String toString() {
        String string;
        if (isEmpty()) {
            string = toStringEmpty();
        } else {
            string = this.tile.toString();
        }
        string = String.format("%-3s", string);
        return string;
    }

    /**
     * Creates a String from the given Square details for premium squares.
     * @param multiplier the word or letter multiplier value.
     * @param frontBracket either '{' or '(' depending on the Square type.
     * @param endBracket either '}' or ')' depending on the Square type
     * @return the details of the premium square as a String of no more than 3
     * characters.
     */
    protected static String squareDetailToString(
            final int multiplier,
            final String frontBracket,
            final String endBracket) {

        String string = frontBracket + multiplier;
        if (multiplier >= 0 && multiplier <= 9) {
            string += endBracket;
        }
        return string;
    }

    /**
     * Returns the letter multiplier value.
     * @return letterMultiplier.
     */
    public int getLetterMultiplier() {
        return this.letterMultiplier;
    }

    /**
     * Returns the letter score of a Square by combining the letter value of
     * the Tile on the Square and the premium letter multiplier of the Square.
     * @return letter score.
     */
    public int calculateLetterScore() {
        int tileScore = this.tile.getScore();
        int letterScore =
                this.letterScoring.score(this.letterMultiplier, tileScore);
        return letterScore;
    }

    /**
     * Sets the letterScoring variable. That determines how the letter value is
     * calculated.
     * @param letterScoring
     */
    public void setLetterScoring(LetterScoring letterScoring) {
        this.letterScoring = letterScoring;
    }

    /**
     * Returns the wordMultiplier variable.
     * @return wordMultiplier.
     */
    public int getWordMultiplier() {
        int wordMultiplier = this.wordScoring.multiplier(this.wordMultiplier);
        return wordMultiplier;
    }

    /**
     * Sets the wordScoring variable. That determines how word scoring is
     * calculated.
     * @param wordScoring
     */
    public void setWordScoring(WordScoring wordScoring) {
        this.wordScoring = wordScoring;
    }

    /**
     * Updates the scoring mechanism of a square to be standard.
     */
    public void setStandardScoring() {
        LetterScoring letterScoring = new StandardLetterScoring();
        WordScoring wordScoring = new StandardWordScoring();
        setLetterScoring(letterScoring);
        setWordScoring(wordScoring);
    }

    protected abstract String toStringEmpty();
}
