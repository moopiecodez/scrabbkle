package pij.main.square;

public class PremiumLetterSquare extends Square {

    /**
     * PremiumLetterSquares require a multiplier in the range -9 to 99.
     */
    public PremiumLetterSquare(int multiplier) {
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
    public String toString() {
        String string;
        if(getTile() != null) {
            string = String.format("%-3s", getTile());
        } else {
            string = squareDetailToString(getLetterMultiplier(), "(", ")");
        }
        return string;
    }

}
