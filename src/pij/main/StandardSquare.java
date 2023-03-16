package pij.main;

public class StandardSquare extends Square {

    public StandardSquare() {
        LetterScoring letterScoring = new StandardLetterScoring();
        setLetterScoring(letterScoring);
        WordScoring wordScoring = new StandardWordScoring();
        setWordScoring(wordScoring);
    }

    /**
     * The string representation of a StandardSquare is a dot enclosed in two 
     * spaces.
     */
    public String toString() {
        String string;
        if( getTile() != null) {
            string = getTile().toString();
        } else {
            string = " . ";
        }
        return string;
    }

    protected int getMultiplier() {
        throw new UnsupportedOperationException(
                "StandardSquares do not have a multiplier");
    }
}