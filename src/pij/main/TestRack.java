package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestRack {
    @Test
    void emptyRackToString() {
        String expectedString = "";
        Rack rack = new Rack();
        String actualString = rack.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void fullRackToString() {
        String expectedString = "[T1], [I1], [U1], [M3], [G2], [R1], [L1]";
        Rack rack = new Rack();
        rack.add(new Tile('T', 1));
        rack.add(new Tile('I', 1));
        rack.add(new Tile('U', 1));
        rack.add(new Tile('M', 3));
        rack.add(new Tile('G', 2));
        rack.add(new Tile('R', 1));
        rack.add(new Tile('L', 1));

        String actualString = rack.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void takeFromRackWithTile() {
        String expectedTile = "R1";
        String expectedRack = "[R1]";
        Rack rack = new Rack();
        rack.add(new Tile('R', 1));
        rack.add(new Tile('R', 1));
        char letter = 'R';
        
        Tile tile = rack.take(letter);
        String actualTile = tile.toString();
        String actualRack = rack.toString();
        
        assertEquals(expectedTile, actualTile);
        assertEquals(expectedRack, actualRack);
    }

    @Test
    void takeFromRackWithoutTile() {
        Tile expectedTile = null;
        String expectedRack = "[R1]";
        Rack rack = new Rack();
        rack.add(new Tile('R', 1));
        char letter = 'A';
        
        Tile tile = rack.take(letter);
        Tile actualTile = tile;
        String actualRack = rack.toString();
        
        assertEquals(expectedTile, actualTile);
        assertEquals(expectedRack, actualRack);
    }

    @Test
    void rackSize() {
        int expectedSize = 3;
        Rack rack = new Rack();
        rack.add(new Tile('T', 1));
        rack.add(new Tile('I', 1));
        rack.add(new Tile('U', 1));
        int actualSize = rack.size();
        
        assertEquals(expectedSize, actualSize);
    }
}
