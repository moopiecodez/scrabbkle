package pij.main;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestMove {

    @Test
    void validInput() {
        String input = "GIT,f8,r";

        boolean valid = Human.validateInput(input);

        assertTrue(valid);
    }

    @Test
    void validPass() {
        String input = ",,";

        boolean valid = Human.validateInput(input);

        assertTrue(valid);
    }

}
