package pij.main.square;

/**
 * Standard letter scoring, applies to Standard Squares and all Squares after
 * a Tile is placed on them.
 *
 * @author Maurane van der Stoep
 *
 */
public class StandardLetterScoring implements LetterScoring {

    /*
     * Returns the letter score as the value of the Tile's letter score.
     */
    public int score(int letterMultiplier, int tileScore) {
        return tileScore;
    }
}