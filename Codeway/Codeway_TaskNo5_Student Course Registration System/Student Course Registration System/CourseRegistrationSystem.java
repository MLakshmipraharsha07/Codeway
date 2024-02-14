import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    List<Student> studentsRegistered;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        studentsRegistered = new ArrayList<>();
    }

    public int getRemainingSlots() {
        return capacity - studentsRegistered.size();
    }

    @Override
    public String toString() {
        return code + " - " + title + " (" + getRemainingSlots() + " slots available)";
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        registeredCourses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}

public class CourseRegistrationSystem {
    private JFrame frame;
    private JTextArea courseTextArea;

    private List<Student> students;
    private List<Course> courses;

    public CourseRegistrationSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();

        frame = new JFrame("Course Registration System");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel studentLabel = new JLabel("Students:");
        studentLabel.setBounds(20, 20, 80, 25);
        panel.add(studentLabel);

        JComboBox<Student> studentComboBox = new JComboBox<>();
        studentComboBox.setBounds(120, 20, 200, 25);
        panel.add(studentComboBox);

        JLabel courseLabel = new JLabel("Courses:");
        courseLabel.setBounds(20, 50, 80, 25);
        panel.add(courseLabel);

        JComboBox<Course> courseComboBox = new JComboBox<>();
        courseComboBox.setBounds(120, 50, 350, 25);
        panel.add(courseComboBox);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(500, 20, 100, 25);
        panel.add(registerButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(500, 50, 100, 25);
        panel.add(removeButton);

        courseTextArea = new JTextArea();
        courseTextArea.setBounds(20, 100, 450, 200);
        courseTextArea.setEditable(false);
        panel.add(courseTextArea);

        // Add some sample data
        students.add(new Student("1", "John Doe"));
        students.add(new Student("2", "Jane Smith"));
        students.add(new Student("3", "Alice Johnson"));
        students.add(new Student("4", "Bob Davis"));
        students.add(new Student("5", "Charlie Brown"));
        courses.add(new Course("CSE101", "Introduction to Programming", "Learn basics of programming", 30));
        courses.add(new Course("MAT201", "Calculus", "Advanced calculus topics", 25));
        courses.add(new Course("CSE301", "Introduction to Python", "Dive into python  language  and its  applications", 20));
        courses.add(new Course("CSE401", "Introduction to Operating Systems", "Understand OS  concepts  and  design principle", 15));

        for (Student student : students) {
            studentComboBox.addItem(student);
        }

        for (Course course : courses) {
            courseComboBox.addItem(course);
        }

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student selectedStudent = (Student) studentComboBox.getSelectedItem();
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();

                if (selectedCourse.getRemainingSlots() > 0) {
                    selectedCourse.studentsRegistered.add(selectedStudent);
                    courseTextArea.setText(displayCourseDetails());
                } else {
                    JOptionPane.showMessageDialog(frame, "Course is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student selectedStudent = (Student) studentComboBox.getSelectedItem();
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();

                selectedCourse.studentsRegistered.remove(selectedStudent);
                courseTextArea.setText(displayCourseDetails());
            }
        });
    }

    private String displayCourseDetails() {
        StringBuilder sb = new StringBuilder();
        for (Course course : courses) {
            sb.append(course.title).append(" (").append(course.code).append("): ").append(course.studentsRegistered.size()).append(" students registered\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new CourseRegistrationSystem();
    }
}
