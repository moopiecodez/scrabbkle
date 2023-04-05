package pij.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * BoardFileLoaders parse text files into strings to create a Board.
 * @author Maurane van der Stoep
 *
 */
public class BoardFileLoader {
    private File file;
    private ArrayList<String> lines;
    private int boardSize;
    private String squareString;

    /**
     * Creates a new BoardFileLoader from a given file name.
     * @param fileName from which the Board is to be created
     */
    public BoardFileLoader(final String fileName) {
        this.file = new File(fileName);
        this.lines = new ArrayList<String>();
    }

    /**
     * Checks file to confirm it is a file that exists and can be read.
     * @return true if file is valid
     */
    public Boolean validateFile() {
        Boolean validFile;
        validFile = this.file.exists();
        validFile &= this.file.isFile();
        validFile &= this.file.canRead();
        return validFile;
    }

    /**
     * Checks file to confirm the contents align to the format of a Board file
     * (e.g. first line is an integer within the valid range).
     * @return true if contents conforms to Board file format
     */
    public Boolean validateContents() {
        Boolean valid;
        valid = parseFirstLine();
        if (valid) {
            valid = correctNumberOfLines();
        }
        if (valid) {
            valid = parseSquareTypeLines();
        }

        return valid;
    }

    /**
     * Checks and confirms a given Board file is a valid file and in the correct
     * format.
     * @return true if valid file
     */
    public boolean validateBoardFile() {
        boolean valid = validateFile();

        if (valid) {
            valid = loadLines();
        }
        if (valid) {
            valid = validateContents();
        }

        return valid;
    }

    private boolean loadLines() {
        boolean success = true;
        FileReader reader = null;

        try {
            reader = new FileReader(this.file);
        } catch (FileNotFoundException exception) {
            success = false;
        }

        if (success) {
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

    private boolean parseFirstLine() {
        boolean success = true;
        int size = 0;
        String line = this.lines.remove(0);

        try {
            size = Integer.parseInt(line);
        } catch (NumberFormatException exception) {
            success = false;
        }

        if (size < Board.MIN_BOARD_SIZE || size > Board.MAX_BOARD_SIZE) {
            success = false;
        }

        this.boardSize = size;

        return success;
    }

    private boolean correctNumberOfLines() {
        return this.lines.size() == this.boardSize;
    }

    /**
     * Checks a line obtained from a Board file to confirm if it is in the
     * correct format (i.e. consists of tokens: "."; "{x}" and "(x)".
     * @param size of each line
     * @param line of tokens to be parsed
     * @return String
     */
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
        if (matcher.matches()) {
            string = matcher.group();
        }
        return string;
    }

    /**
     * Parse lines in board file to confirm valid number and format of square
     * tokens.
     * @return success if valid number and format of tokens.
     */
    private Boolean parseSquareTypeLines() {
        Boolean success = true;

        for (int i = 0; i < boardSize; i++) {
            String line = this.lines.remove(0);
            String parsedLine = parseLine(boardSize, line);
            success &= line.equals(parsedLine);
            this.squareString += parsedLine;
        }

        return success;
    }

    /**
     * Generates a Board from a given size and String representing the Square
     * types and their location on the Board to be created.
     * @return Board
     */
    public Board createBoard() {
        Board board = new Board(this.boardSize, this.squareString);
        return board;
    }
}
