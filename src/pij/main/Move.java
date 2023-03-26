package pij.main;

public class Move {
    public static enum Direction { RIGHT, DOWN }

    private String letters;
    private Position position;
    private Direction direction;


    public static Move fromString(String string) {
        String[] parts = string.split(",");
        String letters = parts[0];
        Position position = Position.fromString(parts[1]);
        Direction direction = null;
        switch(parts[2]) {
            case "r":
                direction = Direction.RIGHT;
                break;
            case "d":
                direction = Direction.DOWN;
                break;
        }
        return new Move(letters, position, direction);
    }

    public Move(String letters, Position position, Direction direction) {
        this.letters = letters;
        this.position = position;
        this.direction = direction;
    }

    public String getLetters() {
        return this.letters;
    }

    public Position getPosition() {
        return this.position;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
