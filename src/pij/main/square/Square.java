package pij.main.square;

import pij.main.Tile;

public abstract class Square {

    private Tile tile;
    private LetterScoring letterScoring;
    private WordScoring wordScoring;
    private final int letterMultiplier;
    private final int wordMultiplier;

    /**
     * Squares have a letterMultiplier and wordMultiplier, both of which are 1
     * in a StandardSquare. Constructor only used by Square subclasses.
     * @param letterMultiplier
     * @param wordMultiplier
     */
    public Square(int letterMultiplier, int wordMultiplier) {
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
    }

    private static int parsePremiumValue(String token) {
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
    public static Square create(String token) {
        Square square = null;
        int multiplier;
        char first = token.charAt(0);

        switch(first) {
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
        }

        return square;
    }

    /**
     * Sets a given Tile onto the Square if the Square is empty. An exception
     * is thrown if the Square already holds a Tile.
     * @param tile to be placed.
     */
    public void setTile(Tile tile) {
        if (this.tile != null) {
            String message =
                    "Full square, this square already contains a tile.";
            throw new IllegalStateException(message);
        }
        this.tile = tile;
    }

    /**
     * Returns the Tile on a Square.
     * @return Tile.
     */
    public Tile getTile() {
        return this.tile;
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
            int multiplier, String frontBracket, String endBracket) {

        String string = frontBracket + multiplier;;
        if(multiplier >= 0 && multiplier <= 9) {
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
     * @param letterScoring.
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
     * @param wordScoring.
     */
    public void setWordScoring(WordScoring wordScoring) {
        this.wordScoring = wordScoring;
    }
}