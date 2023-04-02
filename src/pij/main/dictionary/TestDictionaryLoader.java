package pij.main.dictionary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests DictionaryLoader loads wordlist from file to Dictionary. */
public class TestDictionaryLoader {

    static final String SIMPLE_WORDLIST = "resources/simpleWordList.txt";
    static final String SCRABBKLE_WORDLIST = "resources/wordlist.txt";

    @Test
    void dictionaryFromFile() {
        Dictionary dictionary =
                DictionaryLoader.dictionaryFromFile(SIMPLE_WORDLIST);

        assertTrue(dictionary.contains("owl"));
        assertTrue(dictionary.contains("BANANA"));
        assertTrue(dictionary.contains("unicorn"));
    }

    @Test
    void dictionaryFromWordlist() {
        Dictionary dictionary =
                DictionaryLoader.dictionaryFromFile(SCRABBKLE_WORDLIST);

        assertTrue(dictionary.contains("owl"));
        assertTrue(dictionary.contains("banana"));
        assertTrue(dictionary.contains("unicorn"));
        assertTrue(dictionary.contains("zebra"));
        assertFalse(dictionary.contains("allHailKingBob"));
    }
}
