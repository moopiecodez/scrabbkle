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
        String errorMsg = "";
        boolean valid = false;
        displayStartOfUserTurnMsg();

        do {
            String moveString = getMoveStringFromUser();

            valid = Move.validateString(moveString);

            if (valid) {
                move = Move.fromString(moveString);
                valid &= move.validLetters(rack);
                if (!valid) {
                    errorMsg += "You don't have the tiles.\n";
                }
                valid &= move.validOrigin(board);
                if (!valid) {
                    errorMsg += "Invalid starting position for your word.\n";
                }
                valid &= move.wordFits(board); 
                if (!valid){
                    errorMsg += "Word doesn't fit.\n";
                }
                valid &= move.validWord();
                if (!valid) {
                    errorMsg += "Invalid word.\n";
                }

              //validMove &= validateWord();
                //check wordfits
                //validateletters
                //errorMSg = blob
            }
            if (!valid) {
                System.out.println(INVALID_MOVE);
                System.out.println(errorMsg);
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
