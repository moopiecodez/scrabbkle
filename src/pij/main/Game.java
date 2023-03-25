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

    //think can delete as in getmove human
    //private static final String TILE_MSG = "It's your turn! Your tiles:";
    //will need to be String.format()'d later
    private static final String MOVE_MSG_FMT = "The move is:    Word:"
            + " %s at position %s, direction: %s";
    private static final String SCORE_MSG_FMT = 
             "Human player score:       %d \n"
            +"Computer player score:    %d \n";
    private static final String USER_PLAY_MSG = 
            "Please enter your move with letter sequence, position, and\n"
            + "direction (d for down, r for right) separated by commas.\n"
            + "Entering just two commas passes.";

    public Game(
            Board board, Bag bag,
            Player human, Player computer) {
        this.board = board;
        this.bag = bag;
        this.player1 = human;
        this.player2 = computer;
        this.activePlayer = player1;
    }

    public void play() {
        //game opening
        System.out.println(this.board);
        //System.out.println(TILE_MSG);
        //move to human  turn System.out.println(this.userRack.toString());
        //from user
        activePlayer.getMove();
        //make loop until game ends
       // System.out.println(String.format(SCORE_MSG_FMT, humanScore, computerScore));
        //System.out.println(this.board);
        //if human only
        //System.out.println(this.userRack.toString()));
        // gets user or computer move, validates it and updates score
        //getMove();
        //System.out.println(String.format(MOVE_MSG_FMT, word, position, direction));
    }

}
