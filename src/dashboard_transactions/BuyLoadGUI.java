package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class BuyLoadGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI mainWindow;
    private JTextField phoneField;
    private JComboBox<String> telcoCombo;
    private JButton selectedAmountBtn = null;

    private static final int[] AMOUNTS = {20, 50, 100, 150, 200, 300};

    public BuyLoadGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;
        this.mainWindow = mainWindow;

        final Color PRIMARY    = new Color(25, 48, 90);
        final Color LIGHT_BLUE = new Color(139, 172, 224);
        final Color BG         = new Color(200, 210, 230);
        final Color TEXT_MUTED = new Color(100, 110, 130);

        setTitle("Buy Load");
        setSize(430, 760);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(BG);

        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 430, 90);
        navPanel.setBackground(PRIMARY);
        add(navPanel);

        JLabel bankName = new JLabel("STATE BANK · BUY LOAD");
        bankName.setBounds(0, 18, 430, 16);
        bankName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        bankName.setForeground(LIGHT_BLUE);
        bankName.setHorizontalAlignment(SwingConstants.CENTER);
        navPanel.add(bankName);

        JLabel navTitle = new JLabel("Buy Load");
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
        card.setBounds(20, 205, 380, 375);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
        add(card);

        JLabel telcoLBL = new JLabel("TELCO");
        telcoLBL.setBounds(20, 18, 250, 18);
        telcoLBL.setFont(new Font("Segoe UI", Font.BOLD, 13));
        telcoLBL.setForeground(TEXT_MUTED);
        card.add(telcoLBL);

        telcoCombo = new JComboBox<>(new String[]{"Globe", "Smart", "TNT", "Sun", "DITO"});
        telcoCombo.setBounds(20, 40, 340, 38);
        telcoCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        telcoCombo.setBackground(Color.WHITE);
        card.add(telcoCombo);

        JLabel phoneLBL = new JLabel("PHONE NUMBER");
        phoneLBL.setBounds(20, 90, 250, 18);
        phoneLBL.setFont(new Font("Segoe UI", Font.BOLD, 13));
        phoneLBL.setForeground(TEXT_MUTED);
        card.add(phoneLBL);

        phoneField = new JTextField("0917XXXXXXX");
        phoneField.setBounds(20, 112, 340, 42);
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneField.setHorizontalAlignment(JTextField.CENTER);
        phoneField.setForeground(LIGHT_BLUE);
        phoneField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_BLUE, 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        card.add(phoneField);

        JLabel amountLBL = new JLabel("SELECT AMOUNT (PHP)");
        amountLBL.setBounds(20, 168, 250, 18);
        amountLBL.setFont(new Font("Segoe UI", Font.BOLD, 13));
        amountLBL.setForeground(TEXT_MUTED);
        card.add(amountLBL);

        JButton[] amountBtns = new JButton[AMOUNTS.length];
        int btnW = 100, btnH = 40, startX = 20, startY = 192, gapX = 10, gapY = 10;

        for (int i = 0; i < AMOUNTS.length; i++) {
            JButton btn = new JButton("₱" + AMOUNTS[i]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            btn.setBackground(BG);
            btn.setForeground(PRIMARY);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
            btn.setOpaque(true);

            int col = i % 3;
            int row = i / 3;
            btn.setBounds(startX + col * (btnW + gapX), startY + row * (btnH + gapY), btnW, btnH);

            btn.addActionListener(e -> {
                if (selectedAmountBtn != null) {
                    selectedAmountBtn.setBackground(BG);
                    selectedAmountBtn.setForeground(PRIMARY);
                    selectedAmountBtn.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1));
                }
                btn.setBackground(PRIMARY);
                btn.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createLineBorder(PRIMARY, 1));
                selectedAmountBtn = btn;
            });

            amountBtns[i] = btn;
            card.add(btn);
        }

        JLabel dateLBL = new JLabel("DATE");
        dateLBL.setBounds(20, 300, 250, 18);
        dateLBL.setFont(new Font("Segoe UI", Font.BOLD, 13));
        dateLBL.setForeground(TEXT_MUTED);
        card.add(dateLBL);

        JTextField dateField = new JTextField(LocalDate.now().toString());
        dateField.setBounds(20, 322, 340, 42);
        dateField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateField.setHorizontalAlignment(JTextField.CENTER);
        dateField.setEditable(false);
        dateField.setBackground(BG);
        dateField.setForeground(TEXT_MUTED);
        dateField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_BLUE, 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        card.add(dateField);

        JButton submitBTN = new JButton("Buy Load");
        submitBTN.setBounds(20, 585, 380, 48);
        submitBTN.setFont(new Font("Segoe UI", Font.BOLD, 15));
        submitBTN.setBackground(PRIMARY);
        submitBTN.setForeground(Color.WHITE);
        submitBTN.setFocusPainted(false);
        submitBTN.setBorderPainted(false);
        submitBTN.setOpaque(true);
        submitBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(submitBTN);

        JButton backDashBTN = new JButton("Back to dashboard");
        backDashBTN.setBounds(20, 643, 380, 48);
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
            if (selectedAmountBtn == null) {
                JOptionPane.showMessageDialog(this, "Please select a load amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String phone = phoneField.getText().trim();

            if (phone.isEmpty() || phone.equals("0917XXXXXXX")) {
                JOptionPane.showMessageDialog(this, "Please enter a phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone.matches("^09[0-9]{9}$")) {
                JOptionPane.showMessageDialog(this, "Invalid phone number.\nMust be 11 digits and start with 09 (e.g. 09171234567)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount = Double.parseDouble(selectedAmountBtn.getText().replace("₱", ""));
            String telco = telcoCombo.getSelectedItem() + " (" + phone + ")";
            String result = manager.addTransaction("BuyLoad", amount, dateField.getText(), telco);

            if (result.equals("SUCCESS")) {
                mainWindow.updateBalance("Debit");
                JOptionPane.showMessageDialog(this,
                    "Load sent successfully!\nNew Balance: PHP " + String.format("%.2f", manager.getBalance()));
                mainWindow.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}