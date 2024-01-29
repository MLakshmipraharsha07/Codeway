import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int targetNumber;
    private int attempts;
    private int maxAttempts;
    public NumberGuessingGameGUI() {
        super("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        initializeGame();

        JButton guessButton = new JButton("Submit Guess");
        JTextField guessField = new JTextField(10);
        JLabel messageLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(guessField.getText());
                    checkGuess(userGuess, messageLabel);
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
            JOptionPane.showMessageDialog(this,
                    "Congratulations! You guessed the correct number in " + attempts + " attempts.");
            initializeGame();
            messageLabel.setText("");
        } else if (userGuess < targetNumber) {
            messageLabel.setText("Too low! Try again.");
        } else {
            messageLabel.setText("Too high! Try again.");
        }

        if (attempts == maxAttempts) {
            JOptionPane.showMessageDialog(this,
                    "Sorry! You've reached the maximum number of attempts. The correct number was: " + targetNumber);
            initializeGame();
            messageLabel.setText("");
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
