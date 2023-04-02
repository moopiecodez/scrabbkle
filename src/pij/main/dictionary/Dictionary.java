package pij.main.dictionary;

import java.util.HashSet;

/**
 * Dictionary to hold legal words for the Scrabbkle game.
 * @author Maurane van der Stoep
 *
 */
public class Dictionary {

    private HashSet<String> words;

    /** Creates an empty dictionary. */
    public Dictionary() {
        this.words = new HashSet<String>();
    }

    /**
     * Checks if the dictionary contains a given word.
     * @param input word to be checked
     * @return true if the word is in the dictionary
     */
    public boolean contains(final String input) {
        String word = input.toLowerCase();
        boolean result = this.words.contains(word);
        //convert word to lower case
        return result;
    }

    /**
     * Adds a word to the dictionary if it is not already present. If dictionary
     * already contains the word, the call leaves the dictionary unchanged and
     * returns false.
     * @param word to be added
     * @return true if word successfully added to dictionary
     */
    public boolean add(final String word) {
        return this.words.add(word);
    }

}
