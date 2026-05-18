package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class BillPaymentGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI mainWindow;
    private JTextField amountField, accountField;
    private JComboBox<String> billTypeCombo;

    public BillPaymentGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;
        this.mainWindow = mainWindow;

        setTitle("Pay Bills");
        setSize(430, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(235, 240, 250));

        //navbar
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
        backBTN.setBorder(BorderFactory.createLineBorder(new Color(80, 110, 180), 1, true));
        backBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(backBTN);

        //header
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 45, 420, 120);
        headerPanel.setBackground(new Color(25, 45, 95));
        add(headerPanel);

        JLabel title = new JLabel("Pay Bills");
        title.setBounds(0, 30, 420, 35);
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(title);

        JLabel subtitle = new JLabel("Pay your utility bills");
        subtitle.setBounds(0, 68, 420, 18);
        subtitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
        subtitle.setForeground(new Color(160, 185, 225));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(subtitle);

        //form
        JPanel card = new JPanel(null);
        card.setBounds(24, 186, 372, 380);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
        add(card);

        JLabel billLBL = new JLabel("Bill Type");
        billLBL.setBounds(20, 22, 250, 18);
        billLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        billLBL.setForeground(new Color(60, 80, 120));
        card.add(billLBL);

        billTypeCombo = new JComboBox<>(new String[]{"Meralco", "Laguna Water", "PLDT", "Maynilad", "Sky Cable"});
        billTypeCombo.setBounds(20, 46, 330, 38);
        billTypeCombo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        billTypeCombo.setBackground(Color.WHITE);
        card.add(billTypeCombo);

        JLabel accountLBL = new JLabel("Account No.");
        accountLBL.setBounds(20, 100, 250, 18);
        accountLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        accountLBL.setForeground(new Color(60, 80, 120));
        card.add(accountLBL);

        accountField = new JTextField("ACC-123456");
        accountField.setBounds(20, 124, 330, 42);
        accountField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        accountField.setHorizontalAlignment(JTextField.CENTER);
        accountField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        card.add(accountField);

        JLabel amountLBL = new JLabel("Enter Amount (PHP)");
        amountLBL.setBounds(20, 182, 250, 18);
        amountLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        amountLBL.setForeground(new Color(60, 80, 120));
        card.add(amountLBL);

        amountField = new JTextField();
        amountField.setBounds(20, 206, 330, 42);
        amountField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        amountField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        card.add(amountField);

        JLabel dateLBL = new JLabel("Date");
        dateLBL.setBounds(20, 264, 250, 18);
        dateLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        dateLBL.setForeground(new Color(60, 80, 120));
        card.add(dateLBL);

        JTextField dateField = new JTextField(LocalDate.now().toString());
        dateField.setBounds(20, 288, 330, 42);
        dateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateField.setHorizontalAlignment(JTextField.CENTER);
        dateField.setEditable(false);
        dateField.setBackground(new Color(235, 240, 250));
        dateField.setForeground(new Color(100, 120, 160));
        dateField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        card.add(dateField);

        //submit
        JButton submitBTN = new JButton("Pay Bill");
        submitBTN.setBounds(24, 590, 372, 48);
        submitBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
        submitBTN.setBackground(new Color(25, 45, 95));
        submitBTN.setForeground(Color.WHITE);
        submitBTN.setFocusPainted(false);
        submitBTN.setBorderPainted(false);
        submitBTN.setOpaque(true);
        submitBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitBTN.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { submitBTN.setBackground(new Color(37, 65, 130)); }
            public void mouseExited (MouseEvent e) { submitBTN.setBackground(new Color(25, 45, 95)); }
        });
        add(submitBTN);

        //action listeners
        backBTN.addActionListener(e -> { mainWindow.setVisible(true); dispose(); });

       submitBTN.addActionListener(e -> {
            try {
            if (accountField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Account number cannot be empty.","Error",JOptionPane.ERROR_MESSAGE);
            accountField.setText("");
            return;
        }
            if (amountField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Amount field cannot be empty.","Error",JOptionPane.ERROR_MESSAGE);
            amountField.setText("");
            return;
        }
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
            JOptionPane.showMessageDialog(this,"Amount must be greater than 0.","Error",JOptionPane.ERROR_MESSAGE);
            amountField.setText("");
            return;
        }
        String biller = billTypeCombo.getSelectedItem()
                + " (ACC: " + accountField.getText().trim() + ")";
        String result = manager.addTransaction(
            "Bills Payment",
            amount,
            dateField.getText(),
            biller
        );
            if (result.equals("SUCCESS")) {
            mainWindow.updateBalance("Debit");
            JOptionPane.showMessageDialog(this,
                "Bill paid successfully!\nNew Balance: PHP "
                + String.format("%.2f", manager.getBalance()));
            mainWindow.setVisible(true);
            dispose();
            } else {
            JOptionPane.showMessageDialog(this,result,"Error",JOptionPane.ERROR_MESSAGE);
            amountField.setText("");
            accountField.setText("");
        }
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Please enter a valid amount.","Error",JOptionPane.ERROR_MESSAGE);
        amountField.setText("");
    }
      });
        setVisible(true);
    }
}