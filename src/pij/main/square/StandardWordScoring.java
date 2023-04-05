package pij.main.square;

/**
 * Standard word scoring, applies to Standard Squares and all Squares after
 * a Tile is placed on them.
 *
 * @author Maurane van der Stoep
 *
 */
public class StandardWordScoring implements WordScoring {

    /**
     * Returns wordMultiplier of 1 for standard word scoring.
     */
    public int multiplier(int wordMultiplier) {
        return 1;
    }
}