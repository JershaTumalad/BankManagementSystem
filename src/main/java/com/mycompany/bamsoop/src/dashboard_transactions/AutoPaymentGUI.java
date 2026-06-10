package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AutoPaymentGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI mainWindow;
    private JComboBox<String> billerCombo;
    private JTextField amountField;
    private JComboBox<String> frequencyCombo;
    private JTextField startDateField;

    public AutoPaymentGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;
        this.mainWindow = mainWindow;

        setTitle("Setup Auto Payment");
        setSize(430, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final Color PRIMARY    = new Color(25, 48, 90);
        final Color LIGHT_BLUE = new Color(139, 172, 224);
        final Color BG         = new Color(200, 210, 230);
        final Color TEXT_MUTED = new Color(100, 110, 130);

        getContentPane().setBackground(BG);

        // ── HEADER PANEL ──
        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 430, 90);
        navPanel.setBackground(PRIMARY);
        add(navPanel);

        JLabel bankName = new JLabel("STATE BANK · MONEY OUT");
        bankName.setBounds(0, 18, 430, 16);
        bankName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bankName.setForeground(LIGHT_BLUE);
        bankName.setHorizontalAlignment(SwingConstants.CENTER);
        navPanel.add(bankName);

        JLabel navTitle = new JLabel("Setup Auto Payment");
        navTitle.setBounds(0, 36, 430, 40);
        navTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        navTitle.setForeground(Color.WHITE);
        navTitle.setHorizontalAlignment(SwingConstants.CENTER);
        navPanel.add(navTitle);

        // ── BALANCE CARD ──
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

        // ── PAYMENT DETAILS CARD ──
        JPanel card = new JPanel(null);
        card.setBounds(20, 205, 380, 290);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(210, 220, 240), 1));
        add(card);

        JLabel paymentDetailsLbl = new JLabel("PAYMENT DETAILS");
        paymentDetailsLbl.setBounds(20, 14, 300, 18);
        paymentDetailsLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        paymentDetailsLbl.setForeground(TEXT_MUTED);
        card.add(paymentDetailsLbl);

        JLabel payToLbl = new JLabel("PAY TO");
        payToLbl.setBounds(20, 38, 300, 16);
        payToLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        payToLbl.setForeground(PRIMARY);
        card.add(payToLbl);

        String[] billers = {
            "Select Biller (e.g., Meralco)", "Meralco", "PLDT", "Globe",
            "Manila Water", "Netflix", "Converge", "Sky Cable"
        };
        billerCombo = new JComboBox<>(billers);
        billerCombo.setBounds(20, 58, 340, 36);
        billerCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        billerCombo.setBackground(Color.WHITE);
        billerCombo.setForeground(PRIMARY);
        card.add(billerCombo);

        JLabel amountLBL = new JLabel("AMOUNT");
        amountLBL.setBounds(20, 106, 300, 16);
        amountLBL.setFont(new Font("Segoe UI", Font.BOLD, 11));
        amountLBL.setForeground(PRIMARY);
        card.add(amountLBL);

        amountField = new JTextField();
        amountField.setBounds(20, 126, 340, 36);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        amountField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_BLUE, 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
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

        JLabel freqLbl = new JLabel("FREQUENCY");
        freqLbl.setBounds(20, 176, 160, 16);
        freqLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        freqLbl.setForeground(PRIMARY);
        card.add(freqLbl);

        String[] frequencies = {"Monthly", "Bi-weekly", "Annually"};
        frequencyCombo = new JComboBox<>(frequencies);
        frequencyCombo.setBounds(20, 196, 155, 36);
        frequencyCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        frequencyCombo.setBackground(Color.WHITE);
        frequencyCombo.setForeground(PRIMARY);
        card.add(frequencyCombo);

        JLabel startDateLbl = new JLabel("START DATE");
        startDateLbl.setBounds(195, 176, 160, 16);
        startDateLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        startDateLbl.setForeground(PRIMARY);
        card.add(startDateLbl);

        startDateField = new JTextField();
        startDateField.setBounds(195, 196, 165, 36);
        startDateField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        startDateField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_BLUE, 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        startDateField.setText("YYYY-MM-DD");
        startDateField.setForeground(LIGHT_BLUE);
        startDateField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (startDateField.getText().equals("YYYY-MM-DD")) {
                    startDateField.setText("");
                    startDateField.setForeground(PRIMARY);
                }
            }
            public void focusLost(FocusEvent e) {
                if (startDateField.getText().isEmpty()) {
                    startDateField.setText("YYYY-MM-DD");
                    startDateField.setForeground(LIGHT_BLUE);
                }
            }
        });
        card.add(startDateField);

        JLabel hintLbl = new JLabel("Specify the payee, amount, frequency, and start date.");
        hintLbl.setBounds(20, 248, 340, 14);
        hintLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        hintLbl.setForeground(TEXT_MUTED);
        card.add(hintLbl);

        JButton submitBTN = new JButton("Confirm Setup");
        submitBTN.setBounds(20, 515, 380, 52);
        submitBTN.setFont(new Font("Segoe UI", Font.BOLD, 15));
        submitBTN.setBackground(PRIMARY);
        submitBTN.setForeground(Color.WHITE);
        submitBTN.setFocusPainted(false);
        submitBTN.setBorderPainted(false);
        submitBTN.setOpaque(true);
        submitBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(submitBTN);

        JButton backDashBTN = new JButton("Back to dashboard");
        backDashBTN.setBounds(20, 580, 380, 52);
        backDashBTN.setFont(new Font("Segoe UI", Font.BOLD, 15));
        backDashBTN.setBackground(Color.WHITE);
        backDashBTN.setForeground(PRIMARY);
        backDashBTN.setFocusPainted(false);
        backDashBTN.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
        backDashBTN.setOpaque(true);
        backDashBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(backDashBTN);

        // ── ACTION LISTENERS ──
        backDashBTN.addActionListener(e -> {
            mainWindow.setVisible(true);
            dispose();
        });

        submitBTN.addActionListener(e -> {
            try {
                // Validation 1: Biller
                if (billerCombo.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Please select a valid biller.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String selectedBiller = (String) billerCombo.getSelectedItem();

                // Validation 2: Amount
                String amountText = amountField.getText().trim();
                if (amountText.isEmpty() || amountText.equals("Enter amount (PHP)")) {
                    JOptionPane.showMessageDialog(this, "Amount field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double amount = Double.parseDouble(amountText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validation 3: Date
                String dateText = startDateField.getText().trim();
                if (dateText.isEmpty() || dateText.equals("YYYY-MM-DD")) {
                    JOptionPane.showMessageDialog(this, "Please enter a start date.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    LocalDate.parse(dateText);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String frequency = (String) frequencyCombo.getSelectedItem();
                String description = "To: " + selectedBiller + " (" + frequency + ")";

                String result = manager.addTransaction("Auto Payment", amount, dateText, description);

                if (result.equals("SUCCESS")) {
                    balAmount.setText(String.format("PHP %,.2f", manager.getBalance()));
                    mainWindow.updateBalance("Auto Payment");
                    JOptionPane.showMessageDialog(this,
                        "Auto Payment scheduled successfully!\n" +
                        "Biller: "     + selectedBiller + "\n" +
                        "Amount: PHP " + String.format("%,.2f", amount) + "\n" +
                        "Frequency: "  + frequency + "\n" +
                        "Start Date: " + dateText);
                    mainWindow.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}