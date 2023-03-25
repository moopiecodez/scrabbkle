package pij.main;

/**
 * Initialisation of a Scrabbkle game. This class is a collection of static
 * helper methods to create all necessary elements of the game.
 * Creates the board from a board definition file.
 * Creates the bag of tiles.
 * Creates the racks to hold the players' tiles.
 * Loads the dictionary of words from a file.
 * Creates the human and computer players.
 * @author Maurane van der Stoep
 *
 */
public class Initialisation {

    private static final String INTRO_MSG = 
            "Would you like to _l_oad a board or use the _d_efault board?\n" +
            "Please enter your choice (l/d):";
    private static final String DEFAULT_BOARD_FILE = "resources/defaultBoard.txt";
    private static final String INVALID_FILE_MSG = "This is not a valid file.";
    private static final String REQUEST_FILENAME_MSG =
            "Please enter the file name of the board:";

    public static Game setupGame() {
        Board board = null;
        String input = null;
        do {
            if (input != null) {
                System.out.printf("Sorry, '%s' is not a valid choice.\n", input);
            }
            System.out.println(INTRO_MSG);
            input = System.console().readLine();
        } while(!(input.equals("l") || input.equals("d")));

        char choice = input.charAt(0);

        BoardFileLoader boardLoader = null;
        switch(choice) {
            case 'l':
                boardLoader = userBoardFileLoader();
                break;
            case 'd':
                boardLoader = defaultBoardFileLoader();
                break;
        }
        board = boardLoader.createBoard();
        Game game = new Game(board);
        return game;
    }

    /**
     * Asks user to provide a file name and uses this to create a
     * BoardFileLoader object, which is then used to validate the file
     * according to the Scrabbkle board file format. The process will repeat
     * until a valid file is provided.
     * 
     * @return A BoardFileLoader based on a file provided by user.
     */
    private static BoardFileLoader userBoardFileLoader() {
        BoardFileLoader loader;
        String fileName = null;
        boolean validFile = false;

        do {
            if (fileName != null) {
                System.out.print(INVALID_FILE_MSG + " ");
            }
            fileName = getFileNameFromUser();
            loader = new BoardFileLoader(fileName);
            validFile = loader.validateBoardFile();
        } while (!validFile);

        return loader;
    }

    /**
     * Creates a BoardFileLoader using the default board file and validates it
     * to ensure loader variables updated according to file details.
     * @return a BoardFileLoader based on the default board file.
     */
    private static BoardFileLoader defaultBoardFileLoader() {
        BoardFileLoader loader = new BoardFileLoader(DEFAULT_BOARD_FILE);
        loader.validateBoardFile();
        return loader;
    }

    private static String getFileNameFromUser() {
        String fileName;
        System.out.println(REQUEST_FILENAME_MSG);
        fileName = System.console().readLine();
        return fileName;
    }
}
