package pij.main;

public class PremiumLetterScoring implements LetterScoring {

    public int score(int letterMultiplier, int tileScore) {
         int letterScore = tileScore * letterMultiplier;
        return letterScore;
    }
}