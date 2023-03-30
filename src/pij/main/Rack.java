package pij.main;

import java.util.ArrayList;

public class Rack {

    /* declaring RACK_SIZE as a public constant because
     * everyone can know racks hold 7 tiles. 
     */
    public static final int RACK_SIZE = 7; 
    private ArrayList<Tile> tiles = new ArrayList<Tile>(RACK_SIZE);

    /**
     * Adds a Tile to the Rack. Throws an exception if the Rack is full.
     * @param aTile to be added.
     */
    public void add(Tile aTile) {
        if(tiles.size() < RACK_SIZE) {
            tiles.add(aTile);
        }
        else {
            String message = "The rack is full, no more tiles can be added";
            throw new IllegalStateException(message);
        }
    }
    
    public int size() {
        int size = tiles.size();
        return size;
    }
    

    /**
     * Returns a String of the Tiles on the Rack.
     */
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

    /**
     * Takes a Tile with the given letter from the Rack if there is at least
     * one Tile on the Rack with that letter, otherwise returns null. If
     * multiple Tiles have that letter, only one is taken.
     * @param letter indicating which Tile to take.
     * @return a Tile with the given letter.
     */
    public Tile take(char letter) {
        for (Tile tile : tiles) {
            if (Character.isLowerCase(letter)) {
                char wildCardLetter = letter;
                letter = ' ';
                if (tile.getLetter() == letter) {
                    tile.setWildCard(letter, wildCardLetter);
                    tiles.remove(tile);
                    return tile;
                }

            } else if (tile.getLetter() == letter) {
                tiles.remove(tile);

                return tile;
            }
        }
       return null;
    }

    public boolean hasLetters(String letters) {
        boolean success = false;
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