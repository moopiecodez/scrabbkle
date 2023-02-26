package pij.main;

public class Square {
    private Tile tile;

    public void setTile(Tile tile) {
        if(this.tile != null) {
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
