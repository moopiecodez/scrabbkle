package pij.main;

public class PremiumLetterScoring implements LetterScoring {

    public int score(Square square) {
        int tileScore = square.getTile().getScore();
        int multiplier = square.getMultiplier();
        int letterScore = tileScore * multiplier;
        return letterScore;
    }
}