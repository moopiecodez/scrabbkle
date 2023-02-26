package pij.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoardFileLoader {
    
    public static ArrayList<String> load(String pathname) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        File file = new File(pathname);
        FileReader reader = new FileReader(file);
        BufferedReader buff = new BufferedReader(reader);
        String line = "";

        while ( (line = buff.readLine()) != null) {
            lines.add(line);
        }
        buff.close();
        return lines;
    }

    public static int parseFirstLine(String line) {
        int size = Integer.parseInt(line);
        if(size < Board.MIN_BOARD_SIZE || size > Board.MAX_BOARD_SIZE) {
            String message =
                    "Invalid board size: " + size + " not in range "
                    + Board.MIN_BOARD_SIZE + "-" + Board.MAX_BOARD_SIZE;
            throw new IllegalArgumentException(message);
        }
        return size;
    }

    public static Square[][] squaresMatrix(int size, ArrayList<String> lines) {
        Square[][] matrix = new Square[size][size];
        
        matrix[0][0] = new Square();
        return matrix;
    }

    public static Square[] squaresRow(int size, String line) {
        Square[] row = new Square[size];
        // TODO will need to be refactored to cover premium squares
        for(int i = 0; i < size; i++) {
            if(line.charAt(i) == '.') {
                row[i] = new Square();
            } else {
                String message = "Incorrect token from line";
                throw new IllegalArgumentException(message);
            }
        }

        return row;
    }
}
