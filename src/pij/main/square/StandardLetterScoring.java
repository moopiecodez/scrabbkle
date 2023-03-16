package pij.main.square;

public class StandardLetterScoring implements LetterScoring {

    public int score(int letterMultiplier, int tileScore) {
        return tileScore;
    }
}