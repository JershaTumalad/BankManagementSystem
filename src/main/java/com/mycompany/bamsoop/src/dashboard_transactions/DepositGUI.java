package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class DepositGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI mainWindow;
    private JTextField amountField;

    public DepositGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;
        this.mainWindow = mainWindow;

        setTitle("Deposit Money");
        setSize(430, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final Color PRIMARY    = new Color(25, 48, 90);
        final Color LIGHT_BLUE = new Color(139, 172, 224);
        final Color BG         = new Color(200, 210, 230);
        final Color TEXT_MUTED = new Color(100, 110, 130);

        getContentPane().setBackground(BG);

        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 430, 90);
        navPanel.setBackground(PRIMARY);
        add(navPanel);

        JLabel bankName = new JLabel("STATE BANK · MONEY IN");
        bankName.setBounds(0, 18, 430, 16);
        bankName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        bankName.setForeground(LIGHT_BLUE);
        bankName.setHorizontalAlignment(SwingConstants.CENTER);
        navPanel.add(bankName);

        JLabel navTitle = new JLabel("Deposit Money");
        navTitle.setBounds(0, 36, 430, 36);
        navTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        navTitle.setForeground(Color.WHITE);
        navTitle.setHorizontalAlignment(SwingConstants.CENTER);
        navPanel.add(navTitle);

        JPanel balCard = new JPanel(null);
        balCard.setBounds(20, 108, 380, 80);
        balCard.setBackground(PRIMARY);
        balCard.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
        add(balCard);

        JLabel balLbl = new JLabel("CURRENT BALANCE");
        balLbl.setBounds(15, 12, 350, 16);
        balLbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        balLbl.setForeground(LIGHT_BLUE);
        balCard.add(balLbl);

        JLabel balAmount = new JLabel(String.format("PHP %,.2f", manager.getBalance()));
        balAmount.setBounds(15, 30, 350, 36);
        balAmount.setFont(new Font("Segoe UI", Font.BOLD, 26));
        balAmount.setForeground(Color.WHITE);
        balCard.add(balAmount);

        JPanel card = new JPanel(null);
        card.setBounds(20, 208, 380, 165);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
        add(card);

        JLabel depositDetailsLbl = new JLabel("DEPOSIT DETAILS");
        depositDetailsLbl.setBounds(20, 14, 200, 18);
        depositDetailsLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        depositDetailsLbl.setForeground(TEXT_MUTED);
        card.add(depositDetailsLbl);

        JLabel amountLBL = new JLabel("AMOUNT");
        amountLBL.setBounds(20, 40, 200, 18);
        amountLBL.setFont(new Font("Segoe UI", Font.BOLD, 13));
        amountLBL.setForeground(PRIMARY);
        card.add(amountLBL);

        amountField = new JTextField();
        amountField.setBounds(20, 64, 340, 42);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        amountField.setForeground(TEXT_MUTED);
        amountField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_BLUE, 1),
            BorderFactory.createEmptyBorder(0, 12, 0, 12)
        ));
        amountField.setText("Enter amount (PHP)");
        amountField.setForeground(LIGHT_BLUE);
        amountField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (amountField.getText().equals("Enter amount (PHP)")) {
                    amountField.setText("");
                    amountField.setForeground(PRIMARY);
                }
            }
            public void focusLost(FocusEvent e) {
                if (amountField.getText().isEmpty()) {
                    amountField.setText("Enter amount (PHP)");
                    amountField.setForeground(LIGHT_BLUE);
                }
            }
        });
        card.add(amountField);

        JLabel hintLbl = new JLabel("Max PHP 500,000 per transaction");
        hintLbl.setBounds(20, 112, 340, 14);
        hintLbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        hintLbl.setForeground(TEXT_MUTED);
        card.add(hintLbl);

        JButton submitBTN = new JButton("Confirm deposit");
        submitBTN.setBounds(20, 393, 380, 50);
        submitBTN.setFont(new Font("Segoe UI", Font.BOLD, 15));
        submitBTN.setBackground(PRIMARY);
        submitBTN.setForeground(Color.WHITE);
        submitBTN.setFocusPainted(false);
        submitBTN.setBorderPainted(false);
        submitBTN.setOpaque(true);
        submitBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(submitBTN);

        JButton backDashBTN = new JButton("Back to dashboard");
        backDashBTN.setBounds(20, 455, 380, 50);
        backDashBTN.setFont(new Font("Segoe UI", Font.BOLD, 15));
        backDashBTN.setBackground(Color.WHITE);
        backDashBTN.setForeground(PRIMARY);
        backDashBTN.setFocusPainted(false);
        backDashBTN.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
        backDashBTN.setOpaque(true);
        backDashBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(backDashBTN);

        backDashBTN.addActionListener(e -> { mainWindow.setVisible(true); dispose(); });

        submitBTN.addActionListener(e -> {
            try {
                String inputText = amountField.getText().trim();

                if (inputText.isEmpty() || inputText.equals("Enter amount (PHP)")) {
                    JOptionPane.showMessageDialog(this, "Amount field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double amount = Double.parseDouble(inputText);

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    amountField.setText("");
                    return;
                }
                if (amount > 500000) {
                    JOptionPane.showMessageDialog(this, "Maximum deposit limit is PHP 500,000 per transaction.", "Error", JOptionPane.ERROR_MESSAGE);
                    amountField.setText("");
                    return;
                }

                String date = LocalDate.now().toString();
                String result = manager.addTransaction("Deposit", amount, date, "");

                if (result.equals("SUCCESS")) {
                    balAmount.setText(String.format("PHP %,.2f", manager.getBalance()));
                    mainWindow.updateBalance("Deposit");
                    JOptionPane.showMessageDialog(this,
                        "Deposit successful!\nNew Balance: PHP " + String.format("%,.2f", manager.getBalance()));
                    mainWindow.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
                    amountField.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                amountField.setText("");
            }
        });

        setVisible(true);
    }
}