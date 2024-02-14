import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// BankAccount class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// ATM class to represent the ATM machine GUI
class ATMGUI extends JFrame {
    private BankAccount userAccount;

    public ATMGUI(BankAccount userAccount) {
        this.userAccount = userAccount;
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckBalance();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExit();
            }
        });

        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);
        panel.add(exitButton);

        add(panel);
    }

    private void handleWithdraw() {
        String amountStr = JOptionPane.showInputDialog("Enter the amount to withdraw:");
        try {
            double withdrawAmount = Double.parseDouble(amountStr);
            if (userAccount.withdraw(withdrawAmount)) {
                JOptionPane.showMessageDialog(this, "Withdrawal successful. Remaining balance: " + userAccount.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Error: Insufficient funds for withdrawal.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    private void handleDeposit() {
        String amountStr = JOptionPane.showInputDialog("Enter the amount to deposit:");
        try {
            double depositAmount = Double.parseDouble(amountStr);
            userAccount.deposit(depositAmount);
            JOptionPane.showMessageDialog(this, "Deposit successful. Updated balance: " + userAccount.getBalance());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    private void handleCheckBalance() {
        JOptionPane.showMessageDialog(this, "Your current balance: " + userAccount.getBalance());
    }

    private void handleExit() {
        JOptionPane.showMessageDialog(this, "Thank you for visiting the ATM!");
        System.exit(0);
    }
}

public class ATMApp {
    public static void main(String[] args) {
        // Initialize a bank account with an initial balance
        BankAccount userAccount = new BankAccount(1000.0);
        // Create an instance of the ATM GUI
        ATMGUI atmGUI = new ATMGUI(userAccount);
        // Set the GUI to be visible
        atmGUI.setVisible(true);
    }
}
