class Tile {
    char letter;
    int score;
    
    String showTile() {
        return "" + letter + score;
    }
    
    char getLetter() {
        return letter;
    }
    
    int getScore() {
        return score;
    }
}

