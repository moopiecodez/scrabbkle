package pij.main;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test ;

public class TestBag {
	
	@Test
	void testTakeTile() {
		//check method takeTile takes a tile from bag
		Bag bag = new Bag();
		Object something = bag.takeTile();
		assertTrue(something instanceof Tile);
	}
}
