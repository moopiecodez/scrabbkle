package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
    void addToFullRack() {
        String expectedMessage = "The rack is full, no more tiles can be added";
        Rack rack = new Rack();
        for (int i = 0; i < Rack.RACK_SIZE; i++) {
            rack.add(new Tile('T', 1));
        }

        try {
            rack.add(new Tile('A', 1));
            fail("expected IllegalStateException");
        } catch (IllegalStateException exception){
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        }
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
    void takeWildCardFromRack() {
        String expectedTile = "b3";
        Rack rack = new Rack();
        rack.add(new Tile(' ', 3));
        char letter = 'b';

        Tile tile = rack.take(letter);
        String actualTile = tile.toString();

        assertEquals(expectedTile, actualTile);
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

    @Test
    void hasLetters() {
        String letters = "ASTR";
        Rack rack = new Rack();
        Tile tileA = new Tile('A', 1);
        Tile tileB = new Tile('A', 1);
        Tile tileC = new Tile('S', 1);
        Tile tileD = new Tile('T', 1);
        Tile tileE = new Tile('R', 1);
        Tile tileF = new Tile(' ', 3);
        Tile tileG = new Tile('D', 2);

        rack.add(tileA);
        rack.add(tileB);
        rack.add(tileC);
        rack.add(tileD);
        rack.add(tileE);
        rack.add(tileF);
        rack.add(tileG);

        assertTrue(rack.hasLetters(letters));
    }

    @Test
    void hasWildTile() {
        String letters = "ASaR";
        Rack rack = new Rack();
        Tile tileA = new Tile('A', 1);
        Tile tileB = new Tile('A', 1);
        Tile tileC = new Tile('S', 1);
        Tile tileD = new Tile('T', 1);
        Tile tileE = new Tile('R', 1);
        Tile tileF = new Tile(' ', 3);
        Tile tileG = new Tile('D', 2);

        rack.add(tileA);
        rack.add(tileB);
        rack.add(tileC);
        rack.add(tileD);
        rack.add(tileE);
        rack.add(tileF);
        rack.add(tileG);

        assertTrue(rack.hasLetters(letters));
    }

    @Test
    void penaltyScore() {
        Rack rack = new Rack();
        rack.add(new Tile('M', 3));
        rack.add(new Tile('G', 2));
        rack.add(new Tile('R', 1));
        rack.add(new Tile('L', 1));

        int expectedPenalty = 7;
        int actualPenalty = rack.getPenaltyScore();

        assertEquals(expectedPenalty, actualPenalty);
    }
}
