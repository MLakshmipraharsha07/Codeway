import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame {
    private JLabel questionLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private JLabel timerLabel; // Added timer label
    private Timer timer;
    private int timeLeft;
    private int currentQuestionIndex;
    private int score;
    private String[] questions = {
            "1. What does the keyword final signify in Java?",
            "2. Which of the following is a valid declaration of a Java main method?",
            "3. Which of the following is a marker interface in Java?",
            "4. What does the break statement do in Java?",
            "5. What does the super keyword refer to in Java?",
            "6. Which of the following statements is true about Java exceptions?",
            "7. Which of the following is not a valid primitive data type in Java?",
            "8. Which keyword is used to indicate that a method does not return any value in Java?",
            "9. Which keyword is used to prevent method overriding in Java?",
            "10. What is the result of the expression true && false in Java?"
    };
    private String[][] options = {
            { "The variable can be modified at runtime", "The class cannot be inherited",
                    "The method cannot be overridden", "The variable cannot be reassigned after initialization" },
            { "public static void main(String[] args)", "public static int main(String[] args)",
                    " public void main(String[] args)",
                    "static void main(String[] args)" },
            { "Serializable", "Cloneable", " Comparable", " Runnable" },
            { "Exits the loop or switch statement", "Continues with the next iteration of the loop",
                    "Jumps to a specific label in the code", "None of the above" },
            { "It refers to the superclass of the current object", "It refers to the subclass of the current object",
                    "It refers to the parent class", " It refers to the child class" },
            { "All exceptions are checked exceptions", "RuntimeExceptions are checked exceptions",
                    "Checked exceptions must be caught or declared", "Error is a checked exception" },
            { "int", "float", "string", "char" },
            { "void", "null", "return", "none" },
            { "static", "final", "abstract", "override" },
            { "true", "false", "Compile-time error", "Runtime error" }
    };
    private int[] answers = { 3, 0, 1, 0, 2, 2, 2, 0, 1, 1 }; // Index of correct answer for each question

    public QuizApp() {
        setTitle("Quiz Application");
        setSize(500, 450); // Increased height to accommodate timer
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1)); // Increased rows for timer label

        questionLabel = new JLabel();
        add(questionLabel);

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        add(option1);
        add(option2);
        add(option3);
        add(option4);

        submitButton = new JButton("Submit");
        add(submitButton);

        timerLabel = new JLabel(); // Initialized timer label
        add(timerLabel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                if (timeLeft <= 0) {
                    timer.stop();
                    showNextQuestion();
                } else {
                    timerLabel.setText("Time Left: " + timeLeft + " seconds");
                }
            }
        });

        startQuiz();
    }

    private void startQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);
            option1.setText(options[currentQuestionIndex][0]);
            option2.setText(options[currentQuestionIndex][1]);
            option3.setText(options[currentQuestionIndex][2]);
            option4.setText(options[currentQuestionIndex][3]);

            optionsGroup.clearSelection();
            timeLeft = 20; // Set timer for each question to 10 seconds
            timerLabel.setText("Time Left: " + timeLeft + " seconds");
            timer.start();
            currentQuestionIndex++;
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        timer.stop();
        int selectedOption = -1;
        if (option1.isSelected())
            selectedOption = 0;
        else if (option2.isSelected())
            selectedOption = 1;
        else if (option3.isSelected())
            selectedOption = 2;
        else if (option4.isSelected())
            selectedOption = 3;

        if (selectedOption == answers[currentQuestionIndex - 1]) {
            score++;
        }

        showNextQuestion();
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Over!\nYour Score: " + score + "/" + questions.length);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApp().setVisible(true);
            }
        });
    }
}
