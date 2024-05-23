package org.guess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class NumberGameGUI extends JFrame {

    private JTextField textGuess;
    private JLabel labelOutput;
    private int randomNumber;

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 1000;

    public NumberGameGUI() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        createGUI();
    }

    private void createGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel label = new JLabel("Enter Your Guess:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(label, constraints);

        textGuess = new JTextField(10);
        constraints.gridx = 1;
        panel.add(textGuess, constraints);

        JButton button = new JButton("Check");
        constraints.gridx = 2;
        panel.add(button, constraints);

        labelOutput = new JLabel("");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        panel.add(labelOutput, constraints);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(panel);
    }

    private void checkGuess() {
        String guessText = textGuess.getText();
        String message = "";
        int guess;

        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException e) {
            message = "Please enter a valid number.";
            labelOutput.setText(message);
            return;
        }

        if (guess == randomNumber) {
            message = "Congratulations, you've guessed correctly.";
        } else if (guess > randomNumber) {
            message = "Sorry, " + guess + " is too high.\nHint: The number is lower.";
        } else {
            message = "Sorry, " + guess + " is too low.\nHint: The number is higher.";
        }

        labelOutput.setText(message);

        if (guess == randomNumber) {
            message = "Would you like to play again? ";
            int option = JOptionPane.showConfirmDialog(this, message, "Play Again", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {

                newGame();
            } else {
                System.exit(0);
            }
        }
    }

    public void newGame() {
        randomNumber = (int) (Math.random() * (MAX_NUMBER - MIN_NUMBER + 1)) + MIN_NUMBER;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGameGUI game = new NumberGameGUI();
                game.newGame();
                game.setVisible(true);
            }
        });
    }
}