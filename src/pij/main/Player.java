package pij.main;

/**
 * Abstract class for players in the Scrabbkle game.
 * @author Maurane van der Stoep
 *
 */
public abstract class Player {

    protected Rack rack;

    public Player(Rack rack) {
        this.rack = rack;
    }

    public abstract void getMove();
}
