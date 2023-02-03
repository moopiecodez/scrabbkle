package pij.main;

import java.util.ArrayList;
import java.util.Collections; 

public class Bag {

    private ArrayList<Tile> tiles;

    public Bag() {
        int numOfTiles = 100;
        tiles = new ArrayList<Tile>(numOfTiles);
        createTiles('A', 1, 9);
        createTiles('B', 3, 2);
        createTiles('C', 3, 2);
        createTiles('D', 2, 4);
        createTiles('E', 1, 12);
        createTiles('F', 4, 2);
        createTiles('G', 2, 3);
        createTiles('H', 4, 2);
        createTiles('I', 1, 9);
        createTiles('J', 8, 1);
        createTiles('K', 5, 1);
        createTiles('L', 1, 4);
        createTiles('M', 3, 2);
        createTiles('N', 1, 6);
        createTiles('O', 1, 8);
        createTiles('P', 3, 2);
        createTiles('Q', 10, 1);
        createTiles('R', 1, 6);
        createTiles('S', 1, 4);
        createTiles('T', 1, 6);
        createTiles('U', 1, 4);
        createTiles('V', 4, 2);
        createTiles('W', 4, 2);
        createTiles('X', 8, 1);
        createTiles('Y', 4, 2);
        createTiles('Z', 10, 1);
        createTiles(' ', 3, 2);
        
        Collections.shuffle(tiles); //what is Collections here?
    }
    
    private void createTiles(char letter, int score, int multiple) {
        for (int i = 0; i < multiple; i++) {
            Tile tile = new Tile(letter, score);
            this.tiles.add(tile);
        }
    }

    public Tile takeTile() {
        if(tiles.isEmpty()) {
            throw new InsufficientTilesException();
        }
        Tile tile = tiles.remove(0);
        return tile;
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}