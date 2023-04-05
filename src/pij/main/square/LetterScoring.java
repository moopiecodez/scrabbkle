package pij.main.square;

/**
 * Letter scoring mechanism based on strategy pattern to enable updating of
 * scoring mechanism during the game.
 *
 * @author Maurane van der Stoep
 *
 */
public interface LetterScoring {

    public int score(int letterMultiplier, int tileScore);
}