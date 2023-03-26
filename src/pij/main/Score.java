package pij.main;

import pij.main.square.Square;

public class Score {

    public static int letterScoring(Square[] word) {
        int wordLetterScore = 0;
        for(int i = 0; i < word.length; i++) {
            wordLetterScore += word[i].calculateLetterScore();
        }
        return wordLetterScore;
    }

    public static int fullWordMultiplier(Square[] word) {
        int fullWordMultiplier = 1;
        for (int i = 0; i < word.length; i++) {
            fullWordMultiplier = fullWordMultiplier * word[i].getWordMultiplier();
        }
        return fullWordMultiplier;
    }

    public static int wordScoring(Square[] word) {
        int wordScore = letterScoring(word) * fullWordMultiplier(word);
        return wordScore;    
    }
}
