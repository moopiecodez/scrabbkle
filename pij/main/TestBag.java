package pij.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test ;

public class TestBag {
	
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
		Bag bag = new Bag();
		int numOfTiles = 100;
		for(int i = 0; i < numOfTiles; i++) {
			bag.takeTile();
		}
		try {
			bag.takeTile();
			fail("expected InsufficientTilesException");
			
		}
       catch (Exception exception) {
            boolean isInsufficientTiles = exception instanceof InsufficientTilesException;
            assertTrue(isInsufficientTiles);
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage);
        }
	}
	
	@Test
	void testTilesAreExpectedTiles() {
		//check tiles are the expected 100 tiles
	}
	}
