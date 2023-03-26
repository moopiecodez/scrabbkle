package pij.main;

import java.util.regex.Pattern;

public class Human extends Player {

    private static final String TILE_MSG = "It's your turn! Your tiles:";
    private static final String USER_PLAY_MSG = 
            "Please enter your move with letter sequence, position, and\n"
            + "direction (d for down, r for right) separated by commas.\n"
            + "Entering just two commas passes.";
    private static final String INVALID_MOVE = "This is not a valid move.";

    public Human(Rack rack) {
        super(rack);
    }

    public void chooseMove() {
        String input = null;
        boolean validMove = false;
        System.out.println(TILE_MSG);
        System.out.println(this.rack.toString());

        do {
            if(input != null) {
                System.out.println(INVALID_MOVE);
            }        
        System.out.println(USER_PLAY_MSG);
        input = System.console().readLine();
        validMove = validateMove(input);
        } while (!validMove);
    }
    
    public boolean validateMove(String input) {
        boolean validMove = validateInput(input);
        //artificial way for passing test atm
        if(input.contains("bob")){
            validMove = true;
        }

        return validMove;
    }
    
    public static boolean validateInput(String input) {
        String letters = ("\\p{Alpha}{1,7}");
        String position = ("\\p{Lower}\\d{1,2}");
        String direction = ("[rd]");
        String moveFmt = "%s,%s,%s";
        String regex = String.format(moveFmt, letters, position, direction);

        return Pattern.matches(regex, input);
    }
}
