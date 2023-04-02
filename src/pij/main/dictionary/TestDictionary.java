package pij.main.dictionary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
/**
 * Tests for the Dictionary class.
 * @author Maurane van der Stoep
 *
 */
public class TestDictionary {

    Dictionary threeWords() {
        Dictionary dictionary = new Dictionary();
        dictionary.add("owl");
        dictionary.add("banana");
        dictionary.add("unicorn");

        return dictionary;
    }


    @Test
    void addWordSuccessful() {
        String word = "owl";
        Dictionary dictionary = new Dictionary();

        boolean result = dictionary.add(word);

        assertTrue(result);
    }

    @Test
    void addSamewordTwiceFails() {
        String word = "owl";
        Dictionary dictionary = new Dictionary();

        dictionary.add(word);
        boolean result = dictionary.add(word);

        assertFalse(result);
    }

    @Test
    void containsWord() {
        String checkedWord = "owl";
        Dictionary dictionary = threeWords();


        assertTrue(dictionary.contains(checkedWord));
    }

    @Test
    void wordNotThere() {
        String checkedWord = "fish";
        Dictionary dictionary = threeWords();

        assertFalse(dictionary.contains(checkedWord));
    }
}
