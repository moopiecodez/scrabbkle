package pij.main;

import java.io.Console;
import java.util.regex.Pattern;

public class Human extends Player {

    private static final String TILE_MSG = "It's your turn! Your tiles:";
    private static final String USER_PLAY_MSG = 
            "Please enter your move with letter sequence, position, and\n"
            + "direction (d for down, r for right) separated by commas.\n"
            + "Entering just two commas passes.\n";
    private static final String INVALID_MOVE = "This is not a valid move.";

    public Human(Rack rack) {
        super(rack);
    }

    public Move chooseMove(Board board) {
        Move move = null;
        boolean valid = false;
        displayStartOfUserTurnMsg();

        do {
            String moveString = getMoveStringFromUser();

            valid = Move.validateString(moveString);

            if (valid) {
                move = Move.fromString(moveString);
                //valid = move.validate(this.rack, board);
            }
            if (!valid) {
                System.out.println(INVALID_MOVE);
            }

        } while (!valid);

        return move;
    }


    public void displayStartOfUserTurnMsg() {
        System.out.println(TILE_MSG);
        System.out.println(this.rack.toString() + "\n");
    }

    public String getMoveStringFromUser() {
        System.out.println(USER_PLAY_MSG);
        Console console = System.console();
        String moveString = console.readLine(); 

        return moveString;
    }

}
