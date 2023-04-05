package pij.main.square;

import pij.main.Tile;

/**
 * Type of square that applies a premium to the letter score value of the Tile
 * placed on it.
 *
 * @author Maurane van der Stoep
 *
 */
public class PremiumLetterSquare extends Square {

    /**
     * PremiumLetterSquares require a multiplier in the range -9 to 99.
     * @param multiplier
     */
    public PremiumLetterSquare(final int multiplier) {
        super(multiplier, 1);
        LetterScoring letterScoring = new PremiumLetterScoring();
        setLetterScoring(letterScoring);
        WordScoring wordScoring = new StandardWordScoring();
        setWordScoring(wordScoring);
    }

    /**
     * The string representation of a PremiumLetterSquare is the premium
     * letter multiplier value enclosed in two round brackets.
     */
    protected String toStringEmpty() {
        return squareDetailToString(getLetterMultiplier(), "(", ")");
    }

}
