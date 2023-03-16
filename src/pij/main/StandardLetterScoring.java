package pij.main;

public class StandardLetterScoring implements LetterScoring {

    public int score(Square square) {
        int letterScore = square.getTile().getScore();
        return letterScore;
    }
}