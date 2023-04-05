package pij.main.square;

/**
 * Type of square that applies a premium to the word score value of words placed
 * on it.
 *
 * @author Maurane van der Stoep
 *
 */
public class PremiumWordSquare extends Square {

    /**
     * PremiumWordSquares require a multiplier in the range -9 to 99.
     * @param wordMultiplier
     */
    public PremiumWordSquare(final int wordMultiplier) {
        super(1, wordMultiplier);
        LetterScoring letterScoring = new StandardLetterScoring();
        setLetterScoring(letterScoring);
        WordScoring wordScoring = new PremiumWordScoring();
        setWordScoring(wordScoring);
    }

    /**
     * The string representation of a PremiumWordSquare is the premium
     * word multiplier value enclosed in two curly brackets.
     */
    protected String toStringEmpty() {
        return squareDetailToString(getWordMultiplier(), "{", "}");
    }

}
