package pij.main;

/**
 * The classic word game Scrabbkle.

 * @author Maurane van der Stoep
 *
 */
public class Game {

    private Board board;
    private Bag bag;
    private Player player1;
    private Player player2;
    private Player activePlayer;

    private static final String MOVE_MSG_FMT = "The move is:\t%s";
    private static final String SCORE_MSG_FMT = 
              "Human player score:       %d \n"
            + "Computer player score:    %d \n";

    public Game(
            Board board, Bag bag, Player human, Player computer) {
        this.board = board;
        this.bag = bag;
        this.player1 = human;
        this.player2 = computer;
        this.activePlayer = player1;
    }

    public void play() {
        Move move;
        //game opening
        displayBoard(this.board);
        move = activePlayer.chooseMove(this.board);
        displayMove(move);
        activePlayer.playMove(this.board, move);
        displayBoard(this.board);
        }
        //make loop until game ends, switch active player, print score and board
       // System.out.println(String.format(SCORE_MSG_FMT, humanScore, computerScore));
        //System.out.println(this.board);
        //if human only
        // gets user or computer move, validates it and updates score
        //getMove();
    //}

    /** Displays the current board. */
    public static void displayBoard(final Board board) {
        System.out.println(board);
    }

    /** Displays the current move. */
    public static void displayMove(final Move move) {
        String msg = String.format(MOVE_MSG_FMT, move);
        System.out.println(msg);
    }

    public static void displayScore() {
        //TODO implement displayscore method
    }
}
