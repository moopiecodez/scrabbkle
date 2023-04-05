package pij.main;

import pij.main.Move.Direction;

/**
 * Positions on the Board.
 * @author Maurane van der Stoep
 *
 */
public class Position {
    private final int row;
    private final char column;

    /** 
     * Generates a new Position based on int indices referring to array indices.
     * @param rowIndex
     * @param columnIndex
     * @return position
     */
    public static Position fromIndices(
            final int rowIndex, final int columnIndex) {
        int row = rowIndex + 1;
        char column = (char) ('a' + columnIndex);

        return new Position(row, column);
    }

    /**
     * Generates a new Position from a string representation (e.g. "a1").
     * @param string
     * @return position
     */
    public static Position fromString(final String string) {
        char column = string.charAt(0);
        String rowDetails = string.substring(1);
        int row = Integer.parseInt(rowDetails);

        return new Position(row, column);
    }

    /**
     * Generates a new Position from row and column values.
     * @param row as an int
     * @param column as a char
     */
    public Position(final int row, final char column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Represents a position as a String.
     * @return string
     */
    public String toString() {
        String string = String.format("%c%d", this.column, this.row);

        return string;
    }

    /**
     * Overrides equals method to enable comparison of Positions.
     * @param Object
     * @return true if two Positions equal
     */
    public boolean equals(final Object object) {
        Position other = (Position) object;
        boolean sameRow = this.row == other.row;
        boolean sameColumn = this.column == other.column;

        return sameRow && sameColumn;
    }

    /**
     * Returns a Position's row.
     * @return int
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns a Position's column.
     * @return char
     */
    public char getColumn() {
        return this.column;
    }

    /**
     * Returns a Positions row index value.
     * @return int
     */
    public int getRowIndex() {
        return this.row - 1;
    }

    /**
     * Returns a Position's column index value.
     * @return int
     */
    public int getColumnIndex() {
        return (int) (this.column - 'a');
    }

    /**
     * For a given Position returns the next Position in a given direction.
     * @param direction
     * @return Position
     */
    public Position next(final Direction direction) {
        int row = this.row;
        char column = this.column;
        return switch (direction) {
            case down -> new Position(++row, column);
            case right -> new Position(row, ++column);
        };
    }

}
