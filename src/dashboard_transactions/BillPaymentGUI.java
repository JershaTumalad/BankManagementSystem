package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class BillPaymentGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI mainWindow;

    private JTextField amountField, accountField;
    private JComboBox<String> billTypeCombo;
    private JLabel balanceLBL;

    public BillPaymentGUI(TransactionManager manager,
                          BankAppGUI mainWindow) {

        this.manager = manager;
        this.mainWindow = mainWindow;

        setTitle("Pay Bills");
        setSize(430, 750);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(
                new Color(212, 219, 235));

        // ================= NAVBAR =================
        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 430, 32);
        navPanel.setBackground(
                new Color(18, 45, 105));
        add(navPanel);

        // ================= HEADER =================
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 32, 430, 90);
        headerPanel.setBackground(
                new Color(18, 45, 105));
        add(headerPanel);

        JLabel bankLBL =
                new JLabel("STATE BANK • BILL PAYMENT");
        bankLBL.setBounds(20, 8, 360, 18);
        bankLBL.setHorizontalAlignment(SwingConstants.CENTER);
        bankLBL.setForeground(
                new Color(170, 190, 240));
        bankLBL.setFont(
                new Font("Tahoma", Font.BOLD, 11));
        headerPanel.add(bankLBL);

        JLabel title =
                new JLabel("Pay bills");
        title.setBounds(20, 38, 350, 38);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(
                new Font("Tahoma", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        // ================= BALANCE PANEL =================
        JPanel balancePanel = new JPanel(null);
        balancePanel.setBounds(8, 128, 404, 100);
        balancePanel.setBackground(
                new Color(18, 45, 105));
        add(balancePanel);

        JLabel currentBalanceLBL =
                new JLabel("CURRENT BALANCE");
        currentBalanceLBL.setBounds(28, 12, 250, 18);
        currentBalanceLBL.setForeground(
                new Color(170, 190, 240));
        currentBalanceLBL.setFont(
                new Font("Tahoma", Font.BOLD, 13));
        balancePanel.add(currentBalanceLBL);

        balanceLBL =
                new JLabel("PHP " + String.format("%,.2f", manager.getBalance()));
        balanceLBL.setBounds(28, 38, 350, 38);
        balanceLBL.setForeground(Color.WHITE);
        balanceLBL.setFont(
                new Font("Tahoma", Font.BOLD, 30));
        balancePanel.add(balanceLBL);

        // ================= FORM PANEL =================
        JPanel card = new JPanel(null);
        card.setBounds(8, 238, 404, 460);
        card.setBackground(Color.WHITE);
        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 225, 235), 1));
        add(card);

        JLabel detailsLBL =
                new JLabel("BILL PAYMENT DETAILS");
        detailsLBL.setBounds(0, 16, 404, 22);
        detailsLBL.setHorizontalAlignment(
                SwingConstants.CENTER);
        detailsLBL.setForeground(
                new Color(166, 187, 241));
        detailsLBL.setFont(
                new Font("Tahoma", Font.BOLD, 14));
        card.add(detailsLBL);

        // ================= BILL TYPE =================
        JLabel billLBL =
                new JLabel("BILL TYPE");
        billLBL.setBounds(28, 50, 250, 18);
        billLBL.setForeground(
                new Color(60, 80, 120));
        billLBL.setFont(
                new Font("Tahoma", Font.BOLD, 13));
        card.add(billLBL);

        billTypeCombo = new JComboBox<>(
                new String[]{
                        "Meralco",
                        "Laguna Water",
                        "PLDT",
                        "Maynilad",
                        "Sky Cable"
                });
        billTypeCombo.setBounds(28, 72, 348, 42);
        billTypeCombo.setFont(
                new Font("Tahoma", Font.PLAIN, 14));
        billTypeCombo.setBackground(Color.WHITE);
        billTypeCombo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220, 225, 235), 1),
                        BorderFactory.createEmptyBorder(0, 10, 0, 10)
                ));
        card.add(billTypeCombo);

        // ================= ACCOUNT FIELD =================
        JLabel accountLBL =
                new JLabel("ACCOUNT NUMBER");
        accountLBL.setBounds(28, 128, 250, 18);
        accountLBL.setForeground(
                new Color(60, 80, 120));
        accountLBL.setFont(
                new Font("Tahoma", Font.BOLD, 13));
        card.add(accountLBL);

        accountField = new JTextField("ACC-123456");
        accountField.setBounds(28, 150, 348, 42);
        accountField.setFont(
                new Font("Tahoma", Font.PLAIN, 15));
        accountField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 235), 1),
                        BorderFactory.createEmptyBorder(
                                0, 15, 0, 15)
                ));
        card.add(accountField);

        // ================= AMOUNT FIELD =================
        JLabel amountLBL =
                new JLabel("AMOUNT TO PAY");
        amountLBL.setBounds(28, 206, 250, 18);
        amountLBL.setForeground(
                new Color(60, 80, 120));
        amountLBL.setFont(
                new Font("Tahoma", Font.BOLD, 13));
        card.add(amountLBL);

        amountField = new JTextField();
        amountField.setBounds(28, 228, 348, 42);
        amountField.setFont(
                new Font("Tahoma", Font.PLAIN, 18));
        amountField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 235), 1),
                        BorderFactory.createEmptyBorder(
                                0, 15, 0, 15)
                ));
        card.add(amountField);

        JLabel noteLBL =
                new JLabel("Payments are processed instantly");
        noteLBL.setBounds(28, 276, 320, 18);
        noteLBL.setForeground(
                new Color(130, 130, 130));
        noteLBL.setFont(
                new Font("Tahoma", Font.BOLD, 12));
        card.add(noteLBL);

        // ================= CONFIRM BUTTON =================
        JButton payBTN =
                new JButton("Confirm payment");
        payBTN.setBounds(28, 306, 348, 42);
        payBTN.setFont(
                new Font("Tahoma", Font.BOLD, 15));
        payBTN.setBackground(
                new Color(18, 45, 105));
        payBTN.setForeground(Color.WHITE);
        payBTN.setFocusPainted(false);
        payBTN.setBorderPainted(false);
        payBTN.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        card.add(payBTN);

        // ================= DASHBOARD BUTTON =================
        JButton dashboardBTN =
                new JButton("Back to dashboard");
        dashboardBTN.setBounds(28, 358, 348, 40);
        dashboardBTN.setFont(
                new Font("Tahoma", Font.BOLD, 14));
        dashboardBTN.setBackground(Color.WHITE);
        dashboardBTN.setForeground(
                new Color(18, 45, 105));
        dashboardBTN.setBorder(
                BorderFactory.createLineBorder(
                        new Color(135, 170, 255), 2));
        dashboardBTN.setFocusPainted(false);
        dashboardBTN.setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        card.add(dashboardBTN);

        // ================= ACTION LISTENERS =================
        dashboardBTN.addActionListener(e -> {
            mainWindow.setVisible(true);
            dispose();
        });

        payBTN.addActionListener(e -> {
            try {
                if (accountField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Account number cannot be empty.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    accountField.setText("");
                    return;
                }

                if (amountField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Amount field cannot be empty.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    amountField.setText("");
                    return;
                }

                double amount =
                        Double.parseDouble(
                                amountField.getText().trim());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Amount must be greater than 0.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    amountField.setText("");
                    return;
                }

                String biller =
                        billTypeCombo.getSelectedItem()
                                + " (ACC: "
                                + accountField.getText().trim()
                                + ")";

                String result = manager.addTransaction(
                        "Bills Payment",
                        amount,
                        "",
                        biller
                );

                if (result.equals("SUCCESS")) {
                    mainWindow.updateBalance("Debit");
                    balanceLBL.setText("PHP " + String.format("%,.2f", manager.getBalance()));
                    JOptionPane.showMessageDialog(
                            this,
                            "Bill paid successfully!\nNew Balance: PHP "
                                    + String.format("%.2f",
                                    manager.getBalance()));
                    mainWindow.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            result,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    amountField.setText("");
                    accountField.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid amount.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                amountField.setText("");
            }
        });

        setVisible(true);
    }
}