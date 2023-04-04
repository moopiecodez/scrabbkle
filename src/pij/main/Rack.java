package pij.main;

import java.util.ArrayList;

public class Rack {

    /**
     * Declaring RACK_SIZE as a public constant to be accessed by other methods.
     */
    public static final int RACK_SIZE = 7;
    private ArrayList<Tile> tiles = new ArrayList<Tile>(RACK_SIZE);

    /**
     * Adds a Tile to the Rack. Throws an exception if the Rack is full.
     * @param aTile to be added.
     */
    public void add(final Tile aTile) {
        if (this.tiles.size() < RACK_SIZE) {
            this.tiles.add(aTile);
        } else {
            String message = "The rack is full, no more tiles can be added";
            throw new IllegalStateException(message);
        }
    }

    /**
     * Helper method that returns the number of Tiles on a Rack.
     * @return number of Tiles on the Rack
     */
    public int size() {
        int size = this.tiles.size();
        return size;
    }


    /**
     * Converts the Rack and the Tiles it holds to a String.
     * @return String of the Tiles on the Rack
     */
    public String toString() {
        String string = "";
        int n = tiles.size();
        for (int i = 0; i < n; i++) {
            string = string + "[" + tiles.get(i) + "]";
            if (i < n - 1) {
                string = string + ", ";
            }
        }
        return string;
    }

    /**
     * Calculates and returns the sum of the values of the unplayed tiles on the
     * rack.
     * @return sum of the values of the tiles on the rack
     */
    public int getPenaltyScore() {
        int n = this.tiles.size();
        int score = 0;
        for (int i = 0; i < n; i++) {
            Tile tile = this.tiles.get(i);
            score += tile.getScore();
        }
        return score;
    }

    /**
     * Linear search of Rack for a Tile matching the letter passed to it.
     * Returns a Tile removing it from Rack as soon as a correct Tile is found,
     * to ensure if multiple Tiles have that letter, only one is taken.
     * @param letter indicating which Tile to take, lower case for wildcards.
     * @return a Tile with the given letter.
     */
    public Tile take(final char letter) {
        char matchLetter = Character.isLowerCase(letter) ? ' ' : letter;

        for (Tile tile : tiles) {
            if (tile.getLetter() == matchLetter) {
                tiles.remove(tile);
                if (matchLetter == ' ') {
                    tile.setWildCard(letter);
                }
                return tile;
            }
        }
        return null;
    }

/**
 * Checks if the given letters are present on the Rack. Breaks out of loop if
 * any letter is missing from the Rack.
 * @param letters to be checked.
 * @return whether or not tiles on the Rack.
 */
    public boolean hasLetters(String letters) {
        boolean success = true;
        ArrayList<Tile> found = new ArrayList<Tile>();
        for(int i = 0; i < letters.length(); i++) {
            Character letter = letters.charAt(i);
            letter = Character.isLowerCase(letter) ? ' ' : letter;
            Tile tile = take(letter);
            success = tile != null; 

            if(!success) {
                break;
            }
            found.add(tile);
        }
        for(Tile tile : found) {
            add(tile);
        }

        return success;
    }
}