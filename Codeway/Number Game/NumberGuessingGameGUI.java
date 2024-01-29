import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NumberGuessingGameGUI extends JFrame {
    private int targetNumber;
    private int attempts;
    private int maxAttempts;
    private JTextField guessField;

    public NumberGuessingGameGUI() {
        super("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        initializeGame();

        JButton guessButton = new JButton("Submit Guess");
        guessField = new JTextField(10);
        JLabel messageLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(guessField.getText());
                    checkGuess(userGuess, messageLabel);
                    guessField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(NumberGuessingGameGUI.this, "Invalid input. Please enter a number.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter your guess: "));
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(messageLabel);

        add(panel);
    }

    private void initializeGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        maxAttempts = 10;
        attempts = 0;
    }

    private void checkGuess(int userGuess, JLabel messageLabel) {
        attempts++;

        if (userGuess == targetNumber) {
            int result = JOptionPane.showConfirmDialog(this,
                    "Congratulations! You guessed the correct number in " + attempts + " attempts.\n" +
                            "Do you want to play again?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                targetNumber = new Random().nextInt(100) + 1;
                attempts = 0;
                messageLabel.setText("");
            } else {
                System.exit(0);
            }
        } else if (userGuess < targetNumber) {
            messageLabel.setText("Too low! Try again.");
        } else {
            messageLabel.setText("Too high! Try again.");
        }

        if (attempts == maxAttempts) {
            int result = JOptionPane.showConfirmDialog(this,
                    "Sorry! You've reached the maximum number of attempts. The correct number was: " + targetNumber
                            + "\n" +
                            "Do you want to play again?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                targetNumber = new Random().nextInt(100) + 1;
                attempts = 0;
                messageLabel.setText("");
            } else {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}