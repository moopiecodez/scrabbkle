package pij.main.square;

/**
 * Standard square which does not apply a premium to the score of Tiles placed
 * on it.
 *
 * @author Maurane van der Stoep
 *
 */
public class StandardSquare extends Square {

    /**
     * Generates a standard square.
     */
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