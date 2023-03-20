package pij.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pij.main.square.Square;

public class BoardFileLoader {
    private File file;
    private ArrayList<String> lines;
    private int boardSize;
    private String squareString;

    public BoardFileLoader(String fileName) {
        this.file = new File(fileName);
        this.lines = new ArrayList<String>();
    }

    public Boolean validateFile() {
        Boolean validFile;
        validFile = this.file.exists();
        validFile &= this.file.isFile();
        validFile &= this.file.canRead();
        return validFile;
    }

    public Boolean validateContents() {
        Boolean valid;
        valid = parseFirstLine();
        if(valid) {
            valid = correctNumberOfLines();
        }
        if(valid) {
            valid = parseSquareTypeLines();
        }
        
        return valid;
    }

    public Boolean validateBoardFile() {
        Boolean valid = validateFile();

        if(valid) {
            valid = loadLines();
        }
        if (valid) {
            valid = validateContents();
        }

        return valid;
    }

    private Boolean loadLines() {
        Boolean success = true;
        FileReader reader = null;
        
        try {
            reader = new FileReader(this.file);
        } catch (FileNotFoundException exception) {
            success = false;
        }

        if(success) {
            BufferedReader buff = new BufferedReader(reader);
            String line = "";

            try {
                while ((line = buff.readLine()) != null) {
                    this.lines.add(line);
                }
                buff.close();
            } catch (IOException exception) {
                success = false;
            }
        }

        return success;
    }

    private Boolean parseFirstLine() {
        boolean success = true;
        int size = 0;
        String line = this.lines.remove(0);

        try {
            size = Integer.parseInt(line);
        } catch(NumberFormatException exception) {
            success = false;
        }

        if(size < Board.MIN_BOARD_SIZE || size > Board.MAX_BOARD_SIZE) {
            success = false;
        }

        this.boardSize = size;

        return success;
    }

    private Boolean correctNumberOfLines() {
        return this.lines.size() == this.boardSize;
    }

    public static String parseLine(int size, String line) {
        String string = "";
        String standard = "\\.";
        String multiplier = "(-\\d|\\d{1,2})";
        String premiumLetter = String.format("\\(%s\\)", multiplier);
        String premiumWord = String.format("\\{%s\\}", multiplier);
        String regex = String.format("(%s|%s|%s){%d}",
                standard, premiumLetter, premiumWord, size);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if(matcher.matches()) {
            string = matcher.group();
        }
        return string;
    }

    private Boolean parseSquareTypeLines() {
        Boolean success = true;

        for(int i = 0; i < boardSize; i++) {
            String line = this.lines.remove(0);
            String parsedLine = parseLine(boardSize, line);
            success &= line.equals(parsedLine);
            this.squareString += parsedLine;
        }

        return success;
    }

    public Board createBoard() {
        Board board = new Board(this.boardSize, this.squareString);
        return board;
    }
}
