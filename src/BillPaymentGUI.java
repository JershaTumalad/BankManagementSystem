package BAMS;

import javax.swing.*;
import java.awt.*;
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
        setSize(400, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 250));

        JPanel header = new JPanel(null);
        header.setBounds(0, 0, 400, 60);
        header.setBackground(new Color(30, 50, 100));
        add(header);

        JButton backBTN = new JButton("← Back");
        backBTN.setBounds(8, 12, 80, 30);
        styleHeaderButton(backBTN);
        header.add(backBTN);

        JLabel title = new JLabel("Pay Bills");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(100, 15, 200, 30);
        header.add(title);

        addLabel("Bill Type:", 40, 85);
        billTypeCombo = new JComboBox<>(new String[]{"Meralco", "Laguna Water", "PLDT", "Bisyo", "Credit Card"});
        billTypeCombo.setBounds(40, 108, 310, 38);
        billTypeCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(billTypeCombo);

        addLabel("Account No.:", 40, 158);
        accountField = new JTextField("ACC-123456");
        accountField.setBounds(40, 181, 310, 38);
        accountField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(accountField);

        addLabel("Enter Amount (PHP):", 40, 231);
        amountField = new JTextField();
        amountField.setBounds(40, 254, 310, 40);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        add(amountField);

        addLabel("Date:", 40, 306);
        JTextField dateField = new JTextField(LocalDate.now().toString());
        dateField.setBounds(40, 329, 310, 38);
        dateField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateField.setEditable(false);
        dateField.setBackground(new Color(220, 220, 230));
        add(dateField);

        JButton submitBTN = new JButton("Pay Bill");
        submitBTN.setBounds(40, 388, 310, 45);
        styleActionButton(submitBTN);
        add(submitBTN);

        backBTN.addActionListener(e -> { mainWindow.setVisible(true); dispose(); });

        submitBTN.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                String biller = billTypeCombo.getSelectedItem() + " (ACC: " + accountField.getText().trim() + ")";
                String result = manager.addTransaction("Bills Payment", amount, dateField.getText(), biller);
                if (result.equals("SUCCESS")) {
                    mainWindow.updateBalance("Debit");
                    JOptionPane.showMessageDialog(this, "Bill paid successfully!\nNew Balance: PHP " + String.format("%.2f", manager.getBalance()));
                    mainWindow.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 310, 20);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        lbl.setForeground(new Color(60, 60, 90));
        add(lbl);
    }

    private void styleHeaderButton(JButton btn) {
        btn.setBackground(new Color(50, 80, 160));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleActionButton(JButton btn) {
        btn.setBackground(new Color(30, 50, 100));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(new Color(50, 80, 160)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(new Color(30, 50, 100)); }
        });
    }
}