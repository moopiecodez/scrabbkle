package pij.main.square;

public class PremiumWordSquare extends Square {

    /**
     * PremiumWordSquares require a multiplier in the range -9 to 99.
     */
    public PremiumWordSquare(int wordMultiplier) {
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
