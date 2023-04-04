package pij.main;

import pij.main.dictionary.Dictionary;

public class Computer extends Player {

    public Computer(Rack rack) {
        super(rack);
    }

    /**
     * Returns the move generated by the Computer player.
     */
    public Move chooseMove(final Board board, final Dictionary dictionary) {
        Move move = Move.fromString(",,");
        return move;
    }

}
