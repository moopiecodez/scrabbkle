package pij.main;

import pij.main.square.Square;

/**
 * Helper class with scoring mechanisms for Scrabbkle.
 *
 * @author Maurane van der Stoep
 *
 */
public class Score {

    /**
     * Calculates and returns the letter score of a given word based on the
     * squares it is played on.
     * @param word
     * @return int letterscore
     */
    public static int letterScoring(final Square[] word) {
        int wordLetterScore = 0;
        for (int i = 0; i < word.length; i++) {
            wordLetterScore += word[i].calculateLetterScore();
        }
        return wordLetterScore;
    }

    /**
     * Calculates and returns the wordmultiplier of a given word based on the
     * squares it is played on.
     * @param word
     * @return int wordmultiplier
     */
    public static int fullWordMultiplier(final Square[] word) {
        int fullWordMultiplier = 1;
        for (int i = 0; i < word.length; i++) {
            fullWordMultiplier =
                    fullWordMultiplier * word[i].getWordMultiplier();
        }
        return fullWordMultiplier;
    }

    /**
     * Calculates and returns the full score of a given word based on the
     * squares it is played on.
     * @param word
     * @return int score
     */
    public static int wordScoring(final Square[] word) {
        int wordScore = letterScoring(word) * fullWordMultiplier(word);
        return wordScore;
    }
}
