package pij.main;

/**
 * Abstract class for players in the Scrabbkle game.
 * @author Maurane van der Stoep
 *
 */
public abstract class Player {

    protected Rack rack;
    protected int score;

    public Player(Rack rack) {
        this.rack = rack;
        this.score = 0;
    }

    public abstract Move chooseMove(Board board);

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
