package pij.main.square;

/**
 * Word scoring mechanism based on strategy pattern to enable updating of
 * scoring mechanism during the game.
 *
 * @author Maurane van der Stoep
 *
 */
public interface WordScoring {
    public int multiplier(int wordMultiplier);
}
