package pij.main;

import java.util.ArrayList;
import java.util.Scanner;

import pij.main.Move.Direction;
import pij.main.square.Square;

public class Board {

    private Square[][] matrix;
    private int size;
    public static final int MIN_BOARD_SIZE = 12;
    public static final int MAX_BOARD_SIZE = 26;
    private ArrayList<Position> horizontalOrigins = new ArrayList<Position>();
    private ArrayList<Position> verticalOrigins = new ArrayList<Position>();

    /**
     * Boards must have a size within the range 12 -26.
     * @param size of the Board, must be between 12-26.
     * @param squareString of tokens determining Squares to be added to the
     * Board.
     */
    public Board(int size, String squareString) {
        if(size < Board.MIN_BOARD_SIZE || size > Board.MAX_BOARD_SIZE) {
            String message =
                    "Invalid board size: " + size + " not in range "
                    + Board.MIN_BOARD_SIZE + "-" + Board.MAX_BOARD_SIZE;
            throw new IllegalArgumentException(message);
        }
        this.size = size;
        this.matrix = squaresMatrix(size, squareString);
        Position centreSquare = centreSquare();
        findOrigins(centreSquare, Direction.right);
        findOrigins(centreSquare, Direction.down);
    }

    /**
     * Creates a matrix of squares based on the string extracted from the board
     * file.
     * @param size of the board
     * @param squareString string of tokens representing squares
     * @return a matrix of squares (standard, premiumletter and premiumword)
     */
    public static Square[][] squaresMatrix(int size, String squareString) {
        Square[][] matrix = new Square[size][size];
        Scanner scanner = new Scanner(squareString);
        String standard = "\\.";
        //multiplier in range -9 - 99
        String multiplier = "(-\\d|\\d{1,2})";
        String premiumLetter = String.format("\\(%s\\)", multiplier);
        String premiumWord = String.format("\\{%s\\}", multiplier);
        String regex = String.format("%s|%s|%s",
                standard, premiumLetter, premiumWord);

        for(int i = 0; i < size; i ++) {
            for(int j = 0; j < size; j++) {
                String token = scanner.findInLine(regex);
                matrix[i][j] = Square.create(token);
            }
        }
        scanner.close();

        return matrix;
    }

    /**
     * Turn a row of Squares into a String.
     *  1 {3} . (5)
     */
    private String rowToString(int rowIndex) {
        int rowNumber = rowIndex + 1;
        String squares = "";
        Square[] row = this.matrix[rowIndex];
        for(int i = 0; i < this.size; i++) {
            squares += String.format("%s", row[i]);
        }
        String string = String.format("%2d %s\n", rowNumber, squares);
        return string;
    }

    /**
     * Converts the Board into a String with alpha column headers and
     * numerical row references.
     */
    public String toString() {
        char c;
        String string = "  ";
        for(int i = 0; i < size; i ++) {
            c = (char) ('a' + i);
            string += "  " + c;
        }
        string += " \n";

        for(int i = 0; i < size; i++) {
            string += rowToString(i);
        }

        return string;
    }

    /**
     * Finds the coordinates of the centre Square on the Board. If it is an
     * even sized Board, there are technically 4 options for Squares closest to
     * the centre. The centre Square is the top left of these Squares.
     * @return string of coordinates of the centre Square.
     */
    public Position centreSquare() {
        int index = this.size / 2;
        if(this.size % 2 == 0) {
            //even sized Board
            index--;
        }
        Position position = Position.fromIndices(index, index);

        return position;
    }

    /**
     * Returns the Square at the given coordinates on the Board.
     * @param position of the Square to be returned.
     * @return the Square at the given coordinates.
     */
    public Square getSquare(Position position) {
        int rowIndex = position.getRowIndex();
        int columnIndex = position.getColumnIndex();

        Square square = this.matrix[rowIndex][columnIndex];

        return square;
    }

    public boolean positionExists(Position position) {
        int rowIndex = position.getRowIndex();
        int columnIndex = position.getColumnIndex();
        boolean success = (rowIndex >= 0 && columnIndex >= 0
                && rowIndex < this.size && columnIndex < this.size);

        return success;
    }

    public boolean isPositionFree(Position position) {
        Square square = getSquare(position);
        return square.isEmpty();
    }

    public int getLetterScore(final Position position) {
        Square square = getSquare(position);
        return square.calculateLetterScore();
    }

    public int getWordMultiplier(final Position position) {
        Square square = getSquare(position);
        return square.getWordMultiplier();
    }

    public void placeTile(Position position, Tile tile) {
        Square square = getSquare(position);
        square.setTile(tile);
        setBlockedSquares(position);
    }

    public void setStandardScoring(final Position position) {
        Square square = getSquare(position);
        square.setStandardScoring();
    }

    public ArrayList<Position> getOrigins(Direction direction) {
        ArrayList<Position> origins = switch(direction) {
            case right -> this.horizontalOrigins;
            case down -> this.verticalOrigins;
        };
        return origins;
    }

    private void findOrigins(Position position, Direction direction) {
        int columnDelta = 0;
        int rowDelta = 0;
        int rowIndex = position.getRowIndex();
        int columnIndex = position.getColumnIndex();
        ArrayList<Position> origins = getOrigins(direction);
        int i = switch (direction) {
            case right -> {
                columnDelta = -1;
                yield columnIndex;
            }
            case down -> {
                rowDelta = -1;
                yield rowIndex;
            }
        };

        int j = 0;
        while (i >= 0
                && j < Rack.RACK_SIZE
                && !isBlocked(position, direction)) {
            origins.add(position);
            columnIndex += columnDelta;
            rowIndex += rowDelta;
            position = Position.fromIndices(rowIndex, columnIndex);
            i--;
            j++;
        }
    }

    public boolean hasValidOrigin(Move move) {
        Position position = move.getPosition();
        Direction direction = move.getDirection();
        ArrayList<Position> origins = getOrigins(direction);
        boolean validStartingPosition = origins.contains(position);
        //block check only to apply to empty squares
        boolean positionFreeInDirection = !isBlocked(position, direction);

        return validStartingPosition && positionFreeInDirection;
    }

    /**
     * For a given move check if the starting position is within 7 squares of
     * the centre tile either horizontally or vertically. If it is return true.
     * @param move
     * @return true if the starting position is within 7 squares of centre
     * square
     */
    public boolean validStart(final Move move) {
        Position position = move.getPosition();
        Direction direction = move.getDirection();
        int numOfLetters = move.getLetters().length();

        final int i = position.getRowIndex();
        final int j = position.getColumnIndex();

        Position centre = centreSquare();
        if (isPositionFree(centre)) {
            int centreRow = centre.getRowIndex();
            int centreColumn = centre.getColumnIndex();

            return switch (direction) {
                case right -> checkRowStart(centreRow, i,
                            centreColumn, j, numOfLetters);
                case down -> checkRowStart(centreColumn, j,
                            centreRow, i, numOfLetters);
            };
        }

        Position previous = switch (direction) {
            case right -> Position.fromIndices(i, j - 1);
            case down -> Position.fromIndices(i - 1, j);
        };

        // previous square must be empty or off the board
        if (positionExists(previous) && !(isPositionFree(previous))) {
            return false;
        }

        // need position to exist and previous to be free/off board
        //start on square with tile
        if (!isPositionFree(position)) {
            Position next = position.next(direction);
            return checkNextFullTile(next, direction);
        }

        return checkNextFreeTile(numOfLetters, position, direction);
    }

    private static boolean checkRowStart(
            final int centreFixed, final int actualFixed,
            final int centreVar, final int actualVar, final int numOfLetters) {
        return (actualFixed == centreFixed)
                && (actualVar > 0)
                && (actualVar > (centreVar - Rack.RACK_SIZE))
                && (actualVar > (centreVar - numOfLetters))
                && (actualVar <= centreVar);
    }

    private boolean checkNextFreeTile(final int remainingLetters,
            final Position position, final Direction direction) {
        if (!positionExists(position)) {        // off board
            return false;
        }
        if (!isPositionFree(position)) {        // hit tile
            return true;
        }
        if (isBlocked(position, direction)) {   // hit block
            return false;
        }
        if (remainingLetters == 0) {            // run out of tiles
            return false;
        }
        return checkNextFreeTile(remainingLetters - 1, position.next(direction),
                direction);
    }

    private boolean checkNextFullTile(
            final Position position, final Direction direction) {
        if (!positionExists(position)) {
            return false;
        }
        if (isPositionFree(position)) {
            return !isBlocked(position, direction);
        }

        Position next = position.next(direction);
        return checkNextFullTile(next, direction);
    }

    public boolean isBlocked(Position position, Direction direction) {
        Square square = getSquare(position);
        return square.isBlocked(direction);
    }

    private void setBlockedSquares(final Position position) {
        final int i = position.getRowIndex();
        final int j = position.getColumnIndex();

        setBlockedSquare(i, j - 1, Direction.down);
        setBlockedSquare(i, j + 1, Direction.down);
        setBlockedSquare(i - 1, j, Direction.right);
        setBlockedSquare(i + 1, j, Direction.right);
    }

    private void setBlockedSquare(
            final int rowIndex, final int columnIndex,
            final Direction direction) {
        Position position = Position.fromIndices(rowIndex, columnIndex);
        Square square = getSquare(position);
        square.setBlocked(direction);
    }

    public boolean checkPlacement(final Move move) {
        Position position = move.getPosition();
        String remainingLetters = move.getLetters();
        Direction direction = move.getDirection();

        while (!remainingLetters.isEmpty()) {
            if (!positionExists(position)) {
                return false;
            }

            if (isPositionFree(position)) {
                if (isBlocked(position, direction)) {
                    return false;
                } else {
                    if (remainingLetters.length() > 1) {
                        remainingLetters = remainingLetters.substring(1);
                    } else {
                        remainingLetters = "";
                    }
                }
            }
            position = position.next(direction);
        }
        return true;
    }

/**
 * Gets a word by combining the letters in a Move with letters on the Board,
 * starting from the origin provided in the Move until there are no more letters
 * and an empty square is reached.
 * @param move to be played
 * @return the word generated by the move on the current Board as a string
 */
    public String getWord(Move move) {
        String word = "";
        char letter = ' ';
        Position position = move.getPosition();
        String remainingLetters = move.getLetters();
        Direction direction = move.getDirection();

        while (!remainingLetters.isEmpty() || !isPositionFree(position)) {
            if (isPositionFree(position) && !remainingLetters.isEmpty()) {
                letter = remainingLetters.charAt(0);

                if (remainingLetters.length() > 1) {
                    remainingLetters = remainingLetters.substring(1);
                } else {
                    remainingLetters = "";
                }

            } else if (!isPositionFree(position)) {
                Square square = getSquare(position);
                letter = (square.toString()).charAt(0);
            }
            word += letter;
            position = position.next(direction);
        }
        return word;
    }
}

