package pij.main;

import java.util.ArrayList;

public class Rack {

    private static final int RACK_SIZE = 7;
    private ArrayList<Tile> tiles = new ArrayList<Tile>(RACK_SIZE);

    public void add(Tile aTile) {
        tiles.add(aTile);
    }
    
    public int size() {   //why do I need this?
        int size = tiles.size();
        return size;
    }
    

    public String toString() {
        String string = "";
        int n = tiles.size();
        for(int i = 0; i < n; i++) {
            string = string + "[" + tiles.get(i) + "]";
            if(i< n-1) {
                string = string + ", ";
            }
        }
        return string;
    }

    public Tile take(char letter) {
        for(Tile tile : tiles) {
            if(tile.getLetter() == letter) {
                tiles.remove(tile);
                return tile;
            }
        }
       return null;
    }
}