package pij.main;

import org.junit.jupiter.api.Test;

public class TestRack {
	@Test
	void testSetTile() {
		//TODO check method setTile calls takeTile and assigns it to empty slot on rack;
	}
	
	@Test
	void testStateTile() {
		//TODO check method StateTile returns the tiles currently on rack;
		//TODO decide if output to user of tiles on their rack is a side effect or separate method;
	}
	
	@Test
	void testTakeTile() {
		//TODO test method by which Tiles can be taken from Rack to go on board - query if that has to be in Rack or if Turn/Move can do that?
		//Presumably method to Return the Tiles being picked has to be in the class holding that data and then another class calls that method?
	}

}
