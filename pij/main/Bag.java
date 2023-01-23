package pij.main;

import java.util.ArrayList;

public class Bag {
	
	ArrayList<Tile> tiles;
	
	public Bag() {
		int numOfTiles = 100;
		tiles = new ArrayList<Tile>(numOfTiles);
		for(int i = 0; i < numOfTiles; i++) {
			Tile tile = new Tile();
			tiles.add(tile);
		}
	}
	public Tile takeTile() {
		if(tiles.isEmpty()) {
			throw new InsufficientTilesException();
		}
		Tile tile = tiles.remove(0);
		return tile;
	}
	
}