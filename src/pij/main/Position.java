package pij.main;

public class Position {
    private final int row;
    private final char column;

    public static Position fromIndices(int rowIndex, int columnIndex) {
        int row = rowIndex + 1;
        char column = (char)('a' + columnIndex);

        return new Position(row, column);
    }

    public static Position fromString(String string) {
        char column = string.charAt(0);
        String rowDetails = string.substring(1);
        int row = Integer.parseInt(rowDetails);

        return new Position(row, column);
    }

    public Position(int row, char column) {
        this.row = row;
        this.column = column;
    }

    public String toString() {
        String string = String.format("%c%d", this.column, this.row);

        return string;
    }

    public int getRow() {
        return this.row;
    }

    public char getColumn() {
        return this.column;
    }

    public int getRowIndex() {
        return this.row - 1;
    }

    public int getColumnIndex() {
        return (int)(this.column - 'a');
    }
}
