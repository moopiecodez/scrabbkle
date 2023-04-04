package pij.main;

import pij.main.dictionary.Dictionary;

/**
 * Abstract class for players in the Scrabbkle game.
 * @author Maurane van der Stoep
 *
 */
public abstract class Player {

    protected Rack rack;
    protected int score;
    protected int passCounter;

    public Player(Rack rack) {
        this.rack = rack;
        this.score = 0;
    }

    public abstract Move chooseMove(
            final Board board, final Dictionary dictionary);

    public void playMove(Board board, Move move) {
        this.score += move.place(board, this.rack);
    }

    public void replenishRack(Bag bag) {
        while (rack.size() < Rack.RACK_SIZE) {
            Tile aTile = bag.takeTile();
            rack.add(aTile);
        }
    }

    public int getScore() {
        return this.score;
    }
}
