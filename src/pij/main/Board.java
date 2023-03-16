package pij.main;

import pij.main.square.Square;

public class Board {

    private Square[][] matrix;
    private int size;

    public Board(int size, Square[][] squareMatrix) {
        this.matrix = squareMatrix;
        this.size = size; 
    }
    public static final int MIN_BOARD_SIZE = 12;
    public static final int MAX_BOARD_SIZE = 26;
    
    /**
     * Turn a row of Squares into a String.
     *  1 {3} . (5)
     */
    private String rowToString(int rowIndex) {
        String string = " " + (rowIndex + 1) + " ";
        Square[] row = this.matrix[rowIndex];
        for(int i = 0; i < this.size; i++) {
            string += row[i];
        }
        string += "\n";
        return string;
    }
    
    public String toString() {
        
        char c;
        String string =  "  ";
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

}
