package pij.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoardFileLoader {
    
    public static ArrayList<String> loadLines(String pathname) throws IOException {
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
        Square[][] matrix = new Square[size][];

        for(int i = 0; i < size; i++) {
            String line = lines.get(i);
            String[] tokens = tokensRow(size, line);
            Square[] row = squaresRow(tokens);
            matrix[i] = row;
        }

        return matrix;
    }

    public static String[] tokensRow(int size, String line) {
        String[] row = new String[size];
        String string = "";
        int j = 0;
        for( int i = 0; i< line.length(); i++) {
            char c = line.charAt(i);
            string += c;
            if( c == '.' || c == ')'|| c == '}') {
                row[j] = string;
                string = "";
                j++;
            }
        }
        //add illegalArgumentException "incorrect token from line."
        return row;
    }

    public static Square[] squaresRow(String[] tokens) {
        int size = tokens.length;
        Square[] row = new Square[size];
        for(int i = 0; i < size; i++) {
            String token = tokens[i];
            row[i] = Square.createSquare(token);
        }

        return row;
    }

    public static Square[][] load(String fileName) throws IOException {
        ArrayList<String> lines = loadLines(fileName);
        String firstLine = lines.remove(0);
        int size = parseFirstLine(firstLine);
        Square[][] squareMatrix = squaresMatrix(size, lines);
        return squareMatrix;
    }

}
