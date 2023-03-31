package pij.main;

import java.util.ArrayList;

import pij.main.Move.Direction;

public class WordMove extends Move {

    private String letters;
    private Position position;
    private Direction direction;
    private String word;
    
    public WordMove(String letters, Position position, Direction direction) {
        this.letters = letters;
        this.position = position;
        this.direction = direction;
    }

    public void place(Board board, Rack rack) {
        Position currentPosition = this.position;
        String remainingLetters = this.letters;
        char letter;
        Tile tile;
        Direction direction = this.getDirection();
        while (!remainingLetters.isEmpty()) {
            if (board.isPositionFree(currentPosition)) {
                letter = remainingLetters.charAt(0);
                tile = rack.take(letter);
                board.placeTile(currentPosition, tile);
            }
            if (remainingLetters.length() == 1) {
                remainingLetters = "";
            } else {
                remainingLetters = remainingLetters.substring(1);
            }
            currentPosition = currentPosition.next(direction);
        }
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

    public boolean validLetters(Rack rack) {
        return rack.hasLetters(letters);
    }

    public boolean validOrigin(Board board) {
        ArrayList<Position> origins = board.getOrigins(direction);
        return origins.contains(position) &&
                !board.getSquare(position).isblocked(direction);
    }

    public boolean wordFits(Board board) {
        word = "";
        char letter;
        Position currentPosition = position;
        String remainingLetters = letters;

        while (!remainingLetters.isEmpty()) {
            if (!board.isPositionFree(currentPosition)) {
                word += board.getSquare(currentPosition).toString().charAt(0);
            } else {
                word += remainingLetters.charAt(0);
            }
            if (remainingLetters.length() == 1) {
                remainingLetters = "";
            } else {
                remainingLetters = remainingLetters.substring(1);
            }
            currentPosition = currentPosition.next(this.direction);
        }
        do {
            word += board.getSquare(currentPosition).toString().charAt(0);
            currentPosition = currentPosition.next(this.direction);
        } while (!board.isPositionFree(currentPosition));

        System.out.println(word);
        //needs to check previous position from current
        //ispositionfree will error if hit end of board

        return board.positionExists(currentPosition);
    }

    public boolean validWord() {
        //TODO letters + tiles on board
        //return dictionary.contains(word);
        return true;
    }

    public String toString() {
        String string = String.format(
                "Word: %s at position %s, direction: %s",
                this.letters, this.position, this.direction);
        return string;
    }

}
