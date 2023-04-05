package pij.main;

import pij.main.dictionary.Dictionary;

/**
 * Abstract class for players in the Scrabbkle game.
 * @author Maurane van der Stoep
 *
 */
public abstract class Player {

    protected Rack rack;
    protected Dictionary dictionary;
    protected int score;
    protected int passCounter;

    /**
     * Generates a new player with a given rack and dictionary.
     * @param rack
     * @param dictionary
     */
    public Player(Rack rack, Dictionary dictionary) {
        this.rack = rack;
        this.dictionary = dictionary;
        this.score = 0;
    }

    /**
     * The method by which a player chooses its Move. Human players require user
     * input.
     * @param board
     * @return move
     */
    public abstract Move chooseMove(final Board board);

    /**
     * Plays a Move and updates the Player's score accordingly.
     * @param board
     * @param move
     */
    public void playMove(Board board, Move move) {
        this.score += move.place(board, this.rack);
    }

    /**
     * Adds Tiles to the Rack until it is full again, or until the Bag is empty.
     * @param bag
     */
    public void replenishRack(Bag bag) {
        while (rack.size() < Rack.RACK_SIZE) {
            Tile aTile = bag.takeTile();
            rack.add(aTile);
        }
    }

    /**
     * Returns the Player's score.
     * @return int score
     */
    public int getScore() {
        return this.score;
    }

}
