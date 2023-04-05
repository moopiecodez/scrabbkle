package pij.main;

/**
 * Class from which a game of Scrabbkle can be launched.
 * @author Maurane van der Stoep
 *
 */
public class Main {

    /**
     * Launches the Scrabbkle game.
     * @param args
     */
    public static void main(String[] args) {
        Game game = Initialisation.setupGame();
        game.play();
        game.end();
    }

}
