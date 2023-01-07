public class Tile {
    private char letter;
    private int score;
    
    public String toString() {
        return "" + letter + score;
    }
    
    /**
     * Get the letter of the tile.
     * @return the letter as a single character.
     */
    public char getLetter() {
        return letter;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        if(score < 1 || score > 10) {
            String message = "Bad score: " + score + " not in range 0-10";
            throw new IllegalArgumentException(message);
        }
        this.score = score;
    }
    
    public void setLetter(char letter) {
        if(letter != ' ' && (letter < 'A' || letter > 'Z')) {
            String message = "Bad letter: '" + letter +
                "' not in range A-Z or ' '";
            throw new IllegalArgumentException(message);
        }
        this.letter = letter;
    }
}

