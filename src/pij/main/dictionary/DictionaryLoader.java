package pij.main.dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** DictionaryLoader to load a dictionary from a file. */
public class DictionaryLoader {

    /**
     * Reads a given wordlist file and creates a dictionary from it.
     * @param wordlist filename
     * @return dictionary containing the words in the wordlist
     */
    public static Dictionary dictionaryFromFile(String wordlist) {
        Dictionary dictionary = new Dictionary();
        FileReader reader = null;

        try {
            reader = new FileReader(wordlist);
        } catch (FileNotFoundException exception) {
            System.out.println("Error processing wordlist: " + exception);
        }

        BufferedReader buff = new BufferedReader(reader);
        String word = "";

        try {
            while ((word = buff.readLine()) != null) {
                dictionary.add(word);
            }
            buff.close();
        } catch (IOException exception) {
            System.out.println("Error processing wordlist: " + exception);
        }

        return dictionary;
    }
}
