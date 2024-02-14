import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI extends JFrame {

    private JTextField[] subjectFields;
    private JLabel totalMarksLabel, averagePercentageLabel, gradeLabel;

    public GradeCalculatorGUI() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Prompt user for the initial number of subjects
        int numOfSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of subjects:"));
        subjectFields = new JTextField[numOfSubjects];

        for (int i = 0; i < numOfSubjects; i++) {
            panel.add(new JLabel("Subject " + (i + 1) + ":"));
            subjectFields[i] = new JTextField();
            panel.add(subjectFields[i]);
        }

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to reset and calculate again?", "Reset", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    reset();
                } else {
                    System.exit(0); // Close the application
                }
            }
        });

        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        panel.add(calculateButton);
        panel.add(resetButton);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(totalMarksLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(averagePercentageLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(gradeLabel);

        add(panel);
    }

    private void calculateResults() {
        int totalMarks = 0;
        int numOfSubjects = subjectFields.length;

        for (int i = 0; i < numOfSubjects; i++) {
            try {
                int marks = Integer.parseInt(subjectFields[i].getText());

                // Check if marks are within the valid range (0 to 100)
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Please enter marks out of 100 for all subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                totalMarks += marks;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        double averagePercentage = (double) totalMarks / numOfSubjects;

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");

        // Assign grades based on average percentage
        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        gradeLabel.setText("Grade: " + grade);
    }

    private void reset() {
        // Prompt user for the new number of subjects
        int newNumOfSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter the new number of subjects:"));

        // Remove old components
        getContentPane().removeAll();

        // Update the number of subjects and initialize components
        subjectFields = new JTextField[newNumOfSubjects];
        initializeComponents();

        // Refresh the UI
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GradeCalculatorGUI calculatorGUI = new GradeCalculatorGUI();
            calculatorGUI.setVisible(true);
        });
    }
}
