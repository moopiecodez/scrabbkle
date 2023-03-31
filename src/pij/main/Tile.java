package pij.main;

/**
 * Representation of a letter tile in the game of Scrabbkle.
 * Tiles have both a letter and a score value and can occupy one square on the
 * board.
 *
 * @author Maurane van der Stoep
 */
public class Tile {
    /**
     * The letter value of the tile.
     */
    private char letter;
    /**
     * The score value of the tile.
     */
    private int score;

    /**
     * Tile must have a letter and a score value.
     * @param letter of the Tile.
     * @param score of the Tile.
     */
    public Tile(final char letter, final int score) {
        this.setLetter(letter);
        this.setScore(score);
    }

    /**
     * Converts a Tile into a String, stating its letter and score.
     * @return the letter and score of the Tile as a String.
     */
    public String toString() {
        return "" + letter + score;
    }

    /**
     * Get the letter of the Tile.
     * @return the letter as a single character.
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Get the score of the Tile.
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score of a Tile. Throws an exception if the score is not in the
     * range of 0-10.
     * @param score of the Tile, 0-10.
     */
    private void setScore(final int score) {
        final int maxScore = 10;
        final int minScore = 1;
        if (score < minScore || score > maxScore) {
            String message = "Bad score: " + score + " not in range 0-10";
            throw new IllegalArgumentException(message);
        }
        this.score = score;
    }

    /**
     * Sets the letter of a Tile, throws an exception if the letter isn't in
     * the range A-Z or ' ' for a wild card.
     * @param letter of the Tile A-Z or ' '.
     */
    private void setLetter(final char letter) {
        if (letter != ' ' && (letter < 'A' || letter > 'Z')) {
            String message = "Bad letter: '" + letter
                    + "' not in range A-Z or ' '";
            throw new IllegalArgumentException(message);
        }
        this.letter = letter;
    }

    public void setWildCard(final char letter) {
        if (this.letter == ' ') {
            this.letter = letter;
        }
    }

}
