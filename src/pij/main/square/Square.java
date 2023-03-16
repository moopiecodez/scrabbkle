package pij.main.square;

import pij.main.Tile;

public abstract class Square {

    private Tile tile;
    private LetterScoring letterScoring;
    private WordScoring wordScoring;
    private final int letterMultiplier;
    private final int wordMultiplier;

    public Square(int letterMultiplier, int wordMultiplier) {
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
    }

    private static int parsePremiumValue(String token) {
        String value = token.substring(1, token.length() - 1);
        int multiplier = Integer.parseInt(value);
        return multiplier;
    }

    public static Square create(String token) {
        Square square = null;
        int multiplier;
        char first = token.charAt(0);

        switch(first) {
            case '.':
                square = new StandardSquare();
                break;
            case '(':
                multiplier = parsePremiumValue(token);
                square = new PremiumLetterSquare(multiplier);
                break;
            case '{':
                multiplier = parsePremiumValue(token);
                square = new PremiumWordSquare(multiplier);
                break;
        }

        return square;
    }

    public void setTile(Tile tile) {
        if (this.tile != null) {
            String message =
                    "Full square, this square already contains a tile.";
            throw new IllegalStateException(message);
        }
        this.tile = tile;
    }

    public Tile getTile() {
        return this.tile;
    }

    public boolean equals(Object obj) {
        Class<?> thisClass = this.getClass();
        boolean isSame = thisClass.isInstance(obj);
        return isSame;
    }

    protected static String squareDetailToString(
            int multiplier, String frontBracket, String endBracket) {

        String string = frontBracket + multiplier;;
        if(multiplier >= 0 && multiplier <= 9) {
            string += endBracket;
        }
        return string;
    }

    public int getLetterMultiplier() {
        return this.letterMultiplier;
    }

    public int calculateLetterScore() {
        int tileScore = this.tile.getScore();
        int letterScore = 
                this.letterScoring.score(this.letterMultiplier, tileScore);
        return letterScore;
    }

    public void setLetterScoring(LetterScoring letterScoring) {
        this.letterScoring = letterScoring;
    }

    public int getWordMultiplier() {
        int wordMultiplier = this.wordScoring.multiplier(this.wordMultiplier);
        return wordMultiplier;
    }

    public void setWordScoring(WordScoring wordScoring) {
        this.wordScoring = wordScoring;
    }
}