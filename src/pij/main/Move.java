package pij.main;

import java.util.regex.Pattern;

public abstract class Move {
    public enum Direction { right, down }

    /**
     * Takes a string representation of a move and checks it is syntactically
     * valid as a move
     *
     * (e.g. GIT,f8,d or ,, for a pass).
     *
     * @param input string representation of the move.
     * @return boolean true if valid syntax for a move.
     */
    public static boolean validateString(final String input) {
        String letters = ("\\p{Alpha}{1,7}");
        String position = ("\\p{Lower}\\d{1,2}");
        String direction = ("[rd]");
        String moveFmt = "%s,%s,%s|,,";
        String regex = String.format(moveFmt, letters, position, direction);

        return Pattern.matches(regex, input);
    }

    public static Move fromString(final String string) {
        Move move;

        switch (string) {
            case ",,":
                move = new Pass();
                break;
            default:
                String[] parts = string.split(",");
                String letters = parts[0];
                Position position = Position.fromString(parts[1]);
                Direction direction;
                switch (parts[2]) {
                    case "r":
                        direction = Direction.right;
                        break;
                    case "d":
                        direction = Direction.down;
                        break;
                    default:
                        direction = null;
                }
                move = new WordMove(letters, position, direction);
        }
        return move;
    }

    public abstract String getLetters();

    public abstract Position getPosition();

    public abstract Direction getDirection();

    public abstract boolean validate(final Board board, final Rack rack);

    public abstract String getErrorMsg();
    
    public abstract void place(Board board, Rack rack);

}
