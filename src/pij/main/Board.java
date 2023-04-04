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

    }

    /**
     * Creates a matrix of squares based on the string extracted from the board
     * file.
     * @param size of the board
     * @param squareString string of tokens representing squares
     * @return a matrix of squares (standard, premiumletter and premiumword)
     */
    public static Square[][] squaresMatrix(
            final int size, final String squareString) {
        Square[][] matrix = new Square[size][size];
        Scanner scanner = new Scanner(squareString);
        String standard = "\\.";
        //multiplier in range -9 - 99
        String multiplier = "(-\\d|\\d{1,2})";
        String premiumLetter = String.format("\\(%s\\)", multiplier);
        String premiumWord = String.format("\\{%s\\}", multiplier);
        String regex = String.format("%s|%s|%s",
                standard, premiumLetter, premiumWord);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
     *  @param rowIndex of the row to be converted
     *  @return a string representation of the row
     */
    private String rowToString(final int rowIndex) {
        int rowNumber = rowIndex + 1;
        String squares = "";
        Square[] row = this.matrix[rowIndex];
        for (int i = 0; i < this.size; i++) {
            squares += String.format("%s", row[i]);
        }
        String string = String.format("%2d %s\n", rowNumber, squares);
        return string;
    }

    /**
     * Converts the Board into a String with alpha column headers and
     * numerical row references.
     * @return a string representation of the board
     */
    public String toString() {
        char c;
        String string = "  ";
        for (int i = 0; i < size; i++) {
            c = (char) ('a' + i);
            string += "  " + c;
        }
        string += " \n";

        for (int i = 0; i < size; i++) {
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
        if (this.size % 2 == 0) {
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
    public Square getSquare(final Position position) {
        int rowIndex = position.getRowIndex();
        int columnIndex = position.getColumnIndex();

        Square square = this.matrix[rowIndex][columnIndex];

        return square;
    }

    /**
     * Confirms if a given position is on the board.
     * @param position to be checked
     * @return true if the position is within the limits of the board
     */
    public boolean positionExists(final Position position) {
        int rowIndex = position.getRowIndex();
        int columnIndex = position.getColumnIndex();
        boolean success = (rowIndex >= 0 && columnIndex >= 0
                && rowIndex < this.size && columnIndex < this.size);

        return success;
    }

    /**
     * Checks whether a given position already has a tile on it or is free.
     * @param position to be checked
     * @return true if the position is free
     */
    public boolean isPositionFree(final Position position) {
        Square square = getSquare(position);
        return square.isEmpty();
    }

    /**
     * Calculates and returns the letter score of a given position.
     * @param position
     * @return letter score of the given position
     */
    public int getLetterScore(final Position position) {
        Square square = getSquare(position);
        return square.calculateLetterScore();
    }

    /**
     * Returns the word multiplier value of a given position.
     * @param position
     * @return word multiplier of the given position
     */
    public int getWordMultiplier(final Position position) {
        Square square = getSquare(position);
        return square.getWordMultiplier();
    }

    /**
     * Places a tile on the square at the given position.
     * @param position of the square
     * @param tile to be placed on the square
     */
    public void placeTile(final Position position, final Tile tile) {
        Square square = getSquare(position);
        square.setTile(tile);
        setBlockedSquares(position);
    }

    /**
     * Sets the scoring mechanism of a square to standard.
     * @param position of the square
     */
    public void setStandardScoring(final Position position) {
        Square square = getSquare(position);
        square.setStandardScoring();
    }


    /**
     * For a given move returns a boolean depending on whether the starting
     * position is valid. On the first move a valid starting position must
     * include the centre square. Subsequent moves must overlap with a previous
     * word without passing squares blocked in the direction of the move.
     * @param move to be checked
     * @return true if the starting position is valid
     *      */
    public boolean checkStart(final Move move) {
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

        if (positionExists(previous) && !(isPositionFree(previous))) {
            return false;
        }

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

    /**
     * Checks is a given position is blocked in a given direction.
     * @param position to be checked
     * @param direction to be checked
     * @return true if the square at the given position is blocked in the given
     * direction
     */
    public boolean isBlocked(
            final Position position, final Direction direction) {
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
        if (positionExists(position)) {
            Square square = getSquare(position);
            square.setBlocked(direction);
        }
    }

    /**
     * Check a given move can successfully be placed from the intended position.
     * @param move to be checked
     * @return true if the move fits at the location indicated by its starting
     * position
     */
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
                }
                if (remainingLetters.length() > 1) {
                    remainingLetters = remainingLetters.substring(1);
                } else {
                    remainingLetters = "";
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
    public String getWord(final Move move) {
        String word = "";
        char letter = ' ';
        Position position = move.getPosition();
        String remainingLetters = move.getLetters();
        Direction direction = move.getDirection();

        while (!remainingLetters.isEmpty()
                || (positionExists(position) && !isPositionFree(position))) {
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

    /**
     * Returns the length of the sides of the Board.
     * @return size of the Board
     */
    public int getSize() {
        return this.size;
    }
}

