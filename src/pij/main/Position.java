package pij.main;

public class Position {
    private final int row;
    private final char column;
    
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
