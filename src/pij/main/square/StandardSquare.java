package pij.main.square;

public class StandardSquare extends Square {

    public StandardSquare() {
        super(1, 1);
        LetterScoring letterScoring = new StandardLetterScoring();
        setLetterScoring(letterScoring);
        WordScoring wordScoring = new StandardWordScoring();
        setWordScoring(wordScoring);
    }

    /**
     * The string representation of a StandardSquare is a dot enclosed in two 
     * spaces.
     */
    protected String toStringEmpty() {
        String string = " . ";
        return string;
    }

}