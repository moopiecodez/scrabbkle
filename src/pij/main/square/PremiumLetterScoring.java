package pij.main.square;

/**
 * Scoring of tiles placed on premium letter squares.
 *
 * @author Maurane van der Stoep
 *
 */
public class PremiumLetterScoring implements LetterScoring {

    /**
     * Calculates and returns the score of a Tile on a premium letter square.
     * @param letterMultiplier
     * @param tileScore
     * @return int score
     */
    public int score(final int letterMultiplier, final int tileScore) {
         int letterScore = tileScore * letterMultiplier;
        return letterScore;
    }
}