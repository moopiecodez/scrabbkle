package pij.main;

import java.util.ArrayList;

public class Bag {

	private ArrayList<Tile> tiles;

	public Bag() {
		int numOfTiles = 100;
		tiles = new ArrayList<Tile>(numOfTiles);
		for(int i = 0; i < numOfTiles; i++) {
			Tile tile = new Tile();
			if (i % 2 == 0) {
				tile.setLetter('A');
				tile.setScore(1);
			}
			else {
				tile.setLetter('B');
				tile.setScore(3);
			}

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

	public boolean isEmpty() {
		return tiles.isEmpty();
	}
}