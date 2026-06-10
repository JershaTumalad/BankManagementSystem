package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class TransferGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI mainWindow;
    private JTextField amountField, recipientField;
    private JLabel balanceLBL;

    public TransferGUI(TransactionManager manager,
                       BankAppGUI mainWindow) {

        this.manager = manager;
        this.mainWindow = mainWindow;

        setTitle("Transfer Money");
        setSize(430, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(212, 219, 235));

        // NAVBAR 
        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 430, 35);
        navPanel.setBackground(new Color(18, 45, 105));
        add(navPanel);

        // HEADER 
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 35, 430, 95);
        headerPanel.setBackground(new Color(18, 45, 105));
        add(headerPanel);
        JLabel bankLBL = new JLabel("STATE BANK • SEND MONEY");
        bankLBL.setBounds(25, 8, 350, 18);
        bankLBL.setHorizontalAlignment(SwingConstants.CENTER);
        bankLBL.setForeground(new Color(170, 190, 240));
        bankLBL.setFont(new Font("Tahoma", Font.BOLD, 11));
        headerPanel.add(bankLBL);

        JLabel title = new JLabel("Transfer money");
        title.setBounds(25, 38, 350, 35);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        // BALANCE PANEL
        JPanel balancePanel = new JPanel(null);
        balancePanel.setBounds(20, 150, 372, 105);
        balancePanel.setBackground(new Color(18, 45, 105));
        add(balancePanel);
        JLabel currentBalanceLBL = new JLabel("CURRENT BALANCE");
        currentBalanceLBL.setBounds(25, 15, 250, 20);
        currentBalanceLBL.setForeground(new Color(170, 190, 240));
        currentBalanceLBL.setFont(new Font("Tahoma", Font.BOLD, 15));
        balancePanel.add(currentBalanceLBL);
        balanceLBL = new JLabel("PHP " + String.format("%,.2f", manager.getBalance()));
        balanceLBL.setBounds(25, 38, 320, 35);
        balanceLBL.setForeground(Color.WHITE);
        balanceLBL.setFont(new Font("Tahoma", Font.BOLD, 31));
        balancePanel.add(balanceLBL);

        // FORM PANEL
        JPanel card = new JPanel(null);
        card.setBounds(20, 275, 372, 375);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 235), 1));
        add(card);

        JLabel detailsLBL = new JLabel("TRANSFER DETAILS");
        detailsLBL.setBounds(0, 20, 372, 20);
        detailsLBL.setHorizontalAlignment(SwingConstants.CENTER);
        detailsLBL.setForeground(new Color(166, 187, 241));
        detailsLBL.setFont(new Font("Tahoma", Font.BOLD, 15));
        card.add(detailsLBL);

        JLabel recipientLBL = new JLabel("RECIPIENT ACCOUNT");
        recipientLBL.setBounds(25, 58, 250, 18);
        recipientLBL.setForeground(new Color(60, 80, 120));
        recipientLBL.setFont(new Font("Tahoma", Font.BOLD, 13));
        card.add(recipientLBL);
        recipientField = new JTextField("••••1234");
        recipientField.setBounds(25, 85, 320, 42);
        recipientField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        recipientField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220, 225, 235), 1),BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));
        card.add(recipientField);

        JLabel amountLBL = new JLabel("AMOUNT TO TRANSFER");
        amountLBL.setBounds(25, 145, 250, 18);
        amountLBL.setForeground(new Color(60, 80, 120));
        amountLBL.setFont(new Font("Tahoma", Font.BOLD, 13));
        card.add(amountLBL);
        amountField = new JTextField();
        amountField.setBounds(25, 172, 320, 42);
        amountField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        amountField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220, 225, 235), 1),BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));
        card.add(amountField);
        
        JLabel noteLBL = new JLabel("Funds transferred instantly");
        noteLBL.setBounds(25, 225, 250, 18);
        noteLBL.setForeground(new Color(130, 130, 130));
        noteLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        card.add(noteLBL);
        JSeparator separator = new JSeparator();
        separator.setBounds(25, 250, 320, 1);
        card.add(separator);

        JButton transferBTN = new JButton("Confirm transfer");
        transferBTN.setBounds(25, 268, 320, 45);
        transferBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
        transferBTN.setBackground(new Color(18, 45, 105));
        transferBTN.setForeground(Color.WHITE);
        transferBTN.setFocusPainted(false);
        transferBTN.setBorderPainted(false);
        transferBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(transferBTN);

        JButton dashboardBTN =new JButton("Back to dashboard");
        dashboardBTN.setBounds(25, 318, 320, 40);
        dashboardBTN.setFont(new Font("Tahoma", Font.BOLD, 14));
        dashboardBTN.setBackground(Color.WHITE);
        dashboardBTN.setForeground(new Color(18, 45, 105));
        dashboardBTN.setBorder(BorderFactory.createLineBorder(new Color(135, 170, 255), 2));
        dashboardBTN.setFocusPainted(false);
        dashboardBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(dashboardBTN);

        // ACTION LISTENERS 
        dashboardBTN.addActionListener(e -> {
            mainWindow.setVisible(true);
            dispose();
        });

        transferBTN.addActionListener(e -> {
            try { if (amountField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Amount field cannot be empty.","Error",JOptionPane.ERROR_MESSAGE);
                    amountField.setText("");
                    return;
                }
                double amount = Double.parseDouble(
                                amountField.getText().trim());

                if (amount <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a valid amount.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                    amountField.setText("");
                    return;
                }

                String recipient =
                        recipientField.getText().trim();

                if (recipient.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter recipient account.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                    return;
                }

                String result = manager.addTransaction("Transfer", amount, java.time.LocalDate.now().toString(), recipient);

                if (result.equals("SUCCESS")) {

                    mainWindow.updateBalance("Debit");
                    balanceLBL.setText("PHP " + String.format("%,.2f", manager.getBalance()));

                    JOptionPane.showMessageDialog(
                            this,
                            "Transfer successful!\nNew Balance: PHP "
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