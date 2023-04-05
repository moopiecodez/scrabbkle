package pij.main;

import java.util.Arrays;

import pij.main.Move.Direction;
import pij.main.dictionary.Dictionary;

/**
 * Computer player class to play as oponent against the human user.
 *
 * @author Maurane van der Stoep
 *
 */
public class Computer extends Player {

    public Computer(Rack rack, Dictionary dictionary) {
        super(rack, dictionary);
    }

    /**
     * Generates and returns the Move generated by the Computer player.
     * @param board
     * @return Move of Computer player
     */
    public Move chooseMove(final Board board) {

        for (int i = 0; i < board.getSize(); i++) {
            getMoveForArray(i, Direction.right, board);
            getMoveForArray(i, Direction.down, board);
        }

        Move move = Move.fromString(",,");
        return move;
    }

    /**
     * In a given array of the Board (row or column) generates a Move.
     * @param array
     * @param direction
     * @param board
     * @return move
     */
    private Move getMoveForArray(
            final int array, final Direction direction, final Board board) {
        Move move = null;
        String rackLetters = this.rack.getLetters();
        //String fmt = "array: index: %d, direction: %s";
        //System.out.println(String.format(fmt, array, direction));

        for (int i = 0; i < board.getSize(); i++) {
            Position position = switch (direction) {
                case right -> Position.fromIndices(array, i);
                case down -> Position.fromIndices(i, array);
            };
            Move testMove = new WordMove(rackLetters, position, direction);
            boolean validStart = board.checkStart(testMove);
            if (validStart) {
                System.out.println("Maybe I go, " + direction
                        + " here...: " + position);
                System.out.println("My letters are: " + rackLetters);
                System.out.println("Move:   " + testMove.toString());
            }
        }

        return move;
    }

    /**
     * Generates all the combinations from a given string.
     * @param limit
     * @param source
     * @param combi
     * @param depth
     * @param start
     */
    private static void combinations(
            int limit, char[] source, char[] combi,
            int depth, int start) {
        if (depth == limit) {
            String letters = Arrays.toString(combi);
            int length = combi.length;
            char[] permu = new char[length];
            boolean[] visited = new boolean[length];
            for (int i = 0; i < length; i++) {
                visited[i] = false;
            }
            permutations(length, combi, permu, visited, -1);
            return;
        }
        for (int i = start; i < source.length; i++) {
            combi[depth] = source[i];
            start++;
            depth++;
            combinations(limit, source, combi, depth, start);
            depth--;
        }
    }

    /**
     * Generate the powerSet permutations of a given String.
     * @param string
     */
    public static void powerSetPermutations(String string) {
        char[] source = string.toCharArray();
        for (int i = 1; i <= source.length; i++) {
            char[] combi = new char[i];
            combinations(i, source, combi, 0, 0);
        }
    }

    public static void permutations(int length, char[] source, char[] permu,
            boolean[] visited, int depth) {
        if (depth + 1 >= length) {
            String letters = new String(permu);
            System.out.println(letters);
            return;
        }
        for (int i = 0; i < source.length; i++) {
            if (!visited[i]) {
                depth++;
                permu[depth] = source[i];
                visited[i] = true;
                permutations(length, source, permu, visited, depth);
                visited[i] = false;
                depth--;
            }
        }
    }
}
