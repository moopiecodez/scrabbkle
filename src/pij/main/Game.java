package pij.main;

/**
 * The classic word game Scrabbkle.
 * @author Maurane van der Stoep
 *
 */
public class Game {

    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public void play() {
        System.out.println(this.board);
        System.out.println("It's your turn! Your tiles:");
        System.out.println("[T1], [I1], [U1], [M3], [G2], [R1], [L1]");
    }
}
