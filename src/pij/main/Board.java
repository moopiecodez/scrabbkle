package pij.main;

import java.util.Scanner;

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
            squares += row[i];
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
     * @param row of the Square to be returned.
     * @param column of the Square to be returned.
     * @return the Square at the given coordinates.
     */
    public Square getSquare(Position position) {
        int rowIndex = position.getRowIndex();
        int columnIndex = position.getColumnIndex();
        Square square = this.matrix[rowIndex][columnIndex];

        return square;
    }

    public void placeTile(Position position, Tile tile) {
        Square square = getSquare(position);
        square.setTile(tile);
    }
}
