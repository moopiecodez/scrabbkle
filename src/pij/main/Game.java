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

    private static final String END_MSG_FMT =
              "Game Over!\n"
            + "The human player scored %d points.\n"
            + "The computer player scored %d points.\n"
            + "%s";

    private static final String HUMAN_WINS = "The human player wins!";
    private static final String COMPUTER_WINS = "The computer player wins!";
    private static final String DRAW = "It's a draw!";



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
        while (!isEnd()) {
            //game opening
            displayBoard(this.board);
            move = this.activePlayer.chooseMove(this.board, this.dictionary);
            displayMove(move);
            this.activePlayer.playMove(this.board, move);
            displayScore();
            move.updatePassCounter(activePlayer);
            this.activePlayer.replenishRack(this.bag);
            changePlayer(this.activePlayer);
        }
    }

    public void end() {
        int score1 = finalScore(player1);
        int score2 = finalScore(player2);

        displayEndMsg(score1, score2);
    }

    private boolean isEnd() {
        if (this.bag.isEmpty()
                && (player1.rack.size() == 0 || player2.rack.size() == 0)) {
            return true;
        }
        if (this.player1.passCounter >= 2 && this.player2.passCounter >= 2) {
            return true;
        }
        return false;
    }

    private void changePlayer(Player activePlayer) {
        this.activePlayer = activePlayer == this.player1 ?
                this.player2 : this.player1;
    }

    /** Displays the current board. */
    private static void displayBoard(final Board board) {
        System.out.println(board);
    }

    /** Displays the current move. */
    private static void displayMove(final Move move) {
        String msg = String.format(MOVE_MSG_FMT, move);
        System.out.println(msg);
    }

    private void displayScore() {
        int score1 = this.player1.getScore();
        int score2 = this.player2.getScore();
        String msg = String.format(SCORE_MSG_FMT, score1, score2);
        System.out.println(msg);
    }

    private int finalScore(Player player) {
        int penalty = player.rack.getPenaltyScore();
        return player.getScore() - penalty;
    }

    private void displayEndMsg(final int score1, final int score2) {
        String winner = getWinner(score1, score2);
        String msg = String.format(END_MSG_FMT, score1, score2, winner);
        System.out.println(msg);
    }

    private String getWinner(final int score1, final int score2) {
        if (score1 > score2) {
            return HUMAN_WINS;
        }
        if (score1 < score2) {
            return COMPUTER_WINS;
        }
        return DRAW;
    }
}
