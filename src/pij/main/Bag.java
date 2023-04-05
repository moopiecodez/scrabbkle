package pij.main;

import java.util.ArrayList;
import java.util.Collections; 

/**
 * Bag to hold the Tiles.
 *
 * @author Maurane van der Stoep
 *
 */
public class Bag {

    private ArrayList<Tile> tiles;

    /**
     * Creates a new bag with the correct number of each type of Tile and
     * shuffles the order of the Tiles.
     */
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

        Collections.shuffle(tiles);
    }

    /**
     * Creates a given number of Tiles with the given letter and value.
     * @param letter on the Tile
     * @param score of the Tile
     * @param multiple number of the same type of Tile created
     */
    private void createTiles(
            final char letter, final int score, final int multiple) {
        for (int i = 0; i < multiple; i++) {
            Tile tile = new Tile(letter, score);
            this.tiles.add(tile);
        }
    }

    /**
     * Take a Tile from the Bag. Throws an exception if the Bag is empty.
     * @return a Tile
     */
    public Tile takeTile() {
        if (tiles.isEmpty()) {
            throw new InsufficientTilesException();
        }
        Tile tile = tiles.remove(0);
        return tile;
    }

    /**
     * Checks if there are any Tiles left in the Bag.
     * @return true if no Tiles left
     */
    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}