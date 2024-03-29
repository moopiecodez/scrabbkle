package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test ;

public class TestBag {

    String grumpyduck = "quackhiss!";

    @Test
    void testTakeTile() {
        //check method takeTile takes a tile from bag
        Bag bag = new Bag();
        Object something = bag.takeTile();
        assertTrue(something instanceof Tile);
    }

    @Test
    void testTakeTileThrowsExceptionWhenEmpty() {
        // check we get an exception on 101 goes of takeTile();
        String expectedMessage = "There are no more tiles!";

        int numOfTiles = 100;
        Bag bag = new Bag();

        for(int i = 0; i < numOfTiles; i++) {
            bag.takeTile();
        }

        try {
            bag.takeTile();
            fail("expected InsufficientTilesException");
        }
        catch (InsufficientTilesException exception) {
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        }
    }

    String expectedTiles = " 3 3"
            + "A1A1A1A1A1A1A1A1A1"
            + "B3B3"
            + "C3C3"
            + "D2D2D2D2"
            + "E1E1E1E1E1E1E1E1E1E1E1E1"
            + "F4F4"
            + "G2G2G2"
            + "H4H4"
            + "I1I1I1I1I1I1I1I1I1"
            + "J8"
            + "K5"
            + "L1L1L1L1"
            + "M3M3"
            + "N1N1N1N1N1N1"
            + "O1O1O1O1O1O1O1O1"
            + "P3P3"
            + "Q10"
            + "R1R1R1R1R1R1"
            + "S1S1S1S1"
            + "T1T1T1T1T1T1"
            + "U1U1U1U1"
            + "V4V4"
            + "W4W4"
            + "X8"
            + "Y4Y4"
            + "Z10";

    @Test
    void testTilesAreExpectedTiles() {
        //check tiles are the expected 100 tiles

        class TileComparator implements Comparator<Tile> {
            public int compare(Tile a, Tile b) {
                char x = a.getLetter();
                char y = b.getLetter();
                int comparison = Character.compare(x, y);
                return comparison;
            }
        }

        Comparator<Tile> comparator = new TileComparator();
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        Bag bag = new Bag();

        while(!bag.isEmpty()) {
            Tile tile = bag.takeTile();
            tiles.add(tile);
        }

        tiles.sort(comparator);

        String actualTiles = "";
        for (Tile tile : tiles) {
            actualTiles = actualTiles + tile.toString();
        }
        assertEquals(expectedTiles, actualTiles );
    }
    
    @Test
    void testRandomness() {
        int noOfBags = 10;

        Set<String> bagTilesSet = new HashSet<String>(); //why can I have type interface if I can't make a new one

        for (int i = 0; i < noOfBags ; i++) {
            Bag bag = new Bag();
            String tilesInBag = "";
            while(!bag.isEmpty()) {
                Tile tile = bag.takeTile();
                tilesInBag = tilesInBag + tile.toString();
            }
            boolean added = bagTilesSet.add(tilesInBag);
            assertTrue(added);
        }
    }
}