package org.guess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class NumberGameTest {

    private NumberGame game;

    @BeforeEach
    public void setUp() {
        game = new NumberGame();
    }

    @Test
    public void testNumberGenerator() {
        for (int i = 0; i < 10; i++) {
            int number = game.numberGenerator();
            assertTrue(number >= NumberGame.MIN_NUMBER && number <= NumberGame.MAX_NUMBER,
                    "Generated number should be within the range");
        }
    }

    @Test
    public void testIsGuessValid() {
        assertTrue(game.isGuessValid("5"), "Valid guess");
        assertFalse(game.isGuessValid("abc"), "Invalid guess: non-numeric");
        assertFalse(game.isGuessValid(" "), "Invalid guess: empty string");
        assertFalse(game.isGuessValid("1001"), "Invalid guess: out of range");
    }

    @Test
    public void testCheckGuess() {

        Random random = new Random();
        int randomNumber = random.nextInt(NumberGame.MIN_NUMBER, NumberGame.MAX_NUMBER + 1);

        InputStream originalIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("5\n".getBytes());
        System.setIn(in);

        assertTrue(game.checkGuess(randomNumber, randomNumber), "Correct guess should return true");

        int higherGuess = randomNumber + 1;
        if (higherGuess <= NumberGame.MAX_NUMBER) {
            assertFalse(game.checkGuess(higherGuess, randomNumber), "Higher guess should return false");
        }

        int lowerGuess = randomNumber - 1;
        if (lowerGuess >= NumberGame.MIN_NUMBER) {
            assertFalse(game.checkGuess(lowerGuess, randomNumber), "Lower guess should return false");
        }

        System.setIn(originalIn);
    }

    @Test
    public void testGetGuess() {
        InputStream originalIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("500\n".getBytes());
        System.setIn(in);

        String guess = game.getGuess();
        assertEquals("5", guess, "The guessed input should be returned");

        System.setIn(originalIn);
    }
}
