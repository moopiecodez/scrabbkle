package pij.main;

public abstract class Square {
    private Tile tile;

    public static Square create(String token) {
        Square square = null;
        char first = token.charAt(0);

        switch(first) {
            case '.':
                square = new StandardSquare();
                break;
            case '(':
                square = new PremiumLetterSquare();
                break;
            case '{':
                square = new PremiumWordSquare();
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
}
