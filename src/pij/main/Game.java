package pij.main;

import pij.main.dictionary.Dictionary;

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
    private Dictionary dictionary;

    private static final String MOVE_MSG_FMT = "The move is:\t%s\n";
    private static final String SCORE_MSG_FMT = 
              "Human player score:       %d \n"
            + "Computer player score:    %d \n";

    public Game(
            final Board board, final Dictionary dictionary, final Bag bag,
            final Player human, final Player computer) {
        this.board = board;
        this.bag = bag;
        this.dictionary = dictionary;
        this.player1 = human;
        this.player2 = computer;
        this.activePlayer = player1;
    }

    public void play() {
        Move move;
        while (true) {
            //game opening
            displayBoard(this.board);
            move = activePlayer.chooseMove(this.board, this.dictionary);
            displayMove(move);
            activePlayer.playMove(this.board, move);
            displayScore();
            activePlayer.replenishRack(this.bag);
        }
    }
        //make loop until game ends, switch active player,
        //validate words
        // gets computer move
        //updates score

    /** Displays the current board. */
    public static void displayBoard(final Board board) {
        System.out.println(board);
    }

    /** Displays the current move. */
    public static void displayMove(final Move move) {
        String msg = String.format(MOVE_MSG_FMT, move);
        System.out.println(msg);
    }

    public void displayScore() {
        int score1 = this.player1.getScore();
        int score2 = this.player2.getScore();
        String msg = String.format(SCORE_MSG_FMT, score1, score2);
        System.out.println(msg);
    }
}
