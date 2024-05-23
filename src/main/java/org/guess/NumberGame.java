package org.guess;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 10;
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        NumberGame game = new NumberGame();
        game.playGame();
    }

    public void playGame(){
        boolean isCorrectGuess = false;
        int randomNumber = numberGenerator();
        String playAgain = "";

        do {

            while (!isCorrectGuess){
                int guess = Integer.parseInt(getGuess());
                isCorrectGuess = checkGuess(guess, randomNumber);
            }

            System.out.println("Would you like to play again? ");
            playAgain = input.next();

        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for playing! Goodbye.");
        input.close();

    }

    boolean checkGuess(int guess, int randomNumber) {
        if (guess == randomNumber) {
            System.out.println("\nCongratulations, you've guessed correctly.");
            return true;
        } else if (guess > randomNumber) {
            System.out.println("\nSorry, too high. Hint: The number is lower.");
        } else {
            System.out.println("\nSorry, too low. Hint: The number is higher.");
        }
        return false;
    }

    public int numberGenerator(){
        Random random = new Random();
        int num = random.nextInt(MIN_NUMBER, MAX_NUMBER + 1);
        return num;
    }

    public String getGuess() {
        String guess;
        boolean isValidGuess;

        do {
            System.out.println("Please Enter Your Guess: ");
            guess = input.next();
            isValidGuess = isGuessValid(guess);

            if (!isValidGuess) {
                System.out.println("\nInvalid guess. Please try again.");
            }
        } while (!isValidGuess);

        return guess;
    }

    boolean isGuessValid(String guess) {
        return !guess.trim().isEmpty() && guess.matches("\\d+");
    }

}