package bankmanagementapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class TransferGUI extends JFrame {
private TransactionManager manager;
private BankAppGUI mainWindow;
private JTextField amountField, recipientField;

public TransferGUI(TransactionManager manager, BankAppGUI mainWindow) {

    this.manager = manager;
    this.mainWindow = mainWindow;

    setTitle("Transfer Money");
    setSize(430, 720);
    setLayout(null);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setBackground(new Color(235, 240, 250));

    JPanel navPanel = new JPanel(null);
    navPanel.setBounds(0, 0, 420, 45);
    navPanel.setBackground(new Color(15, 28, 60));
    add(navPanel);

    JButton backBTN = new JButton("← Back");
    backBTN.setBounds(10, 9, 70, 27);
    backBTN.setFont(new Font("Tahoma", Font.PLAIN, 12));
    backBTN.setForeground(Color.WHITE);
    backBTN.setBackground(new Color(37, 65, 130));
    backBTN.setFocusPainted(false);
    navPanel.add(backBTN);

    JPanel headerPanel = new JPanel(null);
    headerPanel.setBounds(0, 45, 420, 120);
    headerPanel.setBackground(new Color(25, 45, 95));
    add(headerPanel);

    JLabel title = new JLabel("Transfer Money");
    title.setBounds(0, 30, 420, 35);
    title.setFont(new Font("Tahoma", Font.BOLD, 22));
    title.setForeground(Color.WHITE);
    title.setHorizontalAlignment(SwingConstants.CENTER);
    headerPanel.add(title);

    JLabel subtitle = new JLabel("Send money to another account");
    subtitle.setBounds(0, 68, 420, 18);
    subtitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
    subtitle.setForeground(new Color(160, 185, 225));
    subtitle.setHorizontalAlignment(SwingConstants.CENTER);
    headerPanel.add(subtitle);

    JPanel card = new JPanel(null);
    card.setBounds(24, 186, 372, 300);
    card.setBackground(Color.WHITE);
    add(card);

    JLabel amountLBL = new JLabel("Enter Amount (PHP)");
    amountLBL.setBounds(20, 22, 250, 18);
    amountLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
    card.add(amountLBL);

    amountField = new JTextField();
    amountField.setBounds(20, 46, 330, 42);
    amountField.setHorizontalAlignment(JTextField.CENTER);
    card.add(amountField);

    JLabel recipientLBL = new JLabel("Recipient Account Number");
    recipientLBL.setBounds(20, 108, 250, 18);
    recipientLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
    card.add(recipientLBL);

    recipientField = new JTextField();
    recipientField.setBounds(20, 132, 330, 42);
    recipientField.setHorizontalAlignment(JTextField.CENTER);
    card.add(recipientField);

    JLabel dateLBL = new JLabel("Date");
    dateLBL.setBounds(20, 194, 250, 18);
    card.add(dateLBL);

    JTextField dateField = new JTextField(LocalDate.now().toString());
    dateField.setBounds(20, 218, 330, 42);
    dateField.setEditable(false);
    card.add(dateField);

    JButton submitBTN = new JButton("Transfer");
    submitBTN.setBounds(24, 510, 372, 48);
    submitBTN.setBackground(new Color(25, 45, 95));
    submitBTN.setForeground(Color.WHITE);
    add(submitBTN);

    backBTN.addActionListener(e -> {
        mainWindow.setVisible(true);
        dispose();
    });

    submitBTN.addActionListener(e -> {

        try {

            if (amountField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Amount field cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount = Double.parseDouble(amountField.getText().trim());

            if (amount <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid amount.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String recipient = recipientField.getText().trim();

            if (recipient.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter recipient account number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

          String result = manager.addTransaction(
    "Transfer",
    amount,
    dateField.getText(),
    recipient
);

            if (result.equals("SUCCESS")) {

                mainWindow.updateBalance("Debit");

                JOptionPane.showMessageDialog(
                        this,
                        "Transfer Successful!\n\n"
                        + "Recipient: " + recipient
                        + "\nAmount: PHP "
                        + String.format("%,.2f", amount)
                        + "\nRemaining Balance: PHP "
                        + String.format("%,.2f", manager.getBalance()),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                mainWindow.setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Transfer Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid amount.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    });

    setVisible(true);
}

}
