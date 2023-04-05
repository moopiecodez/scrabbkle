package pij.main.square;

/**
 * Scoring of tiles placed on premium word squares.
 *
 * @author Maurane van der Stoep
 *
 */
public class PremiumWordScoring implements WordScoring {

    /**
     * Returns the wordMultiplier of a tile on a premium word square to be
     * applied to the whole word score.
     * @param wordMultiplier
     * @return int wordMultiplier
     */
    public int multiplier(final int wordMultiplier) {
        return wordMultiplier;
    }
}