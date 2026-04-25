package BAMS;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class DepositGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI mainWindow;
    private JTextField amountField;

    public DepositGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;
        this.mainWindow = mainWindow;

        setTitle("Deposit Money");
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

        JLabel title = new JLabel("Deposit Money");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(100, 15, 200, 30);
        header.add(title);

        addLabel("Enter Amount (PHP):", 40, 90);
        amountField = new JTextField();
        amountField.setBounds(40, 120, 310, 40);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        add(amountField);

        addLabel("Date:", 40, 175);
        JTextField dateField = new JTextField(LocalDate.now().toString());
        dateField.setBounds(40, 205, 310, 40);
        dateField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateField.setEditable(false);
        dateField.setBackground(new Color(220, 220, 230));
        add(dateField);

        JButton submitBTN = new JButton("Deposit");
        submitBTN.setBounds(40, 270, 310, 45);
        styleActionButton(submitBTN);
        add(submitBTN);

        backBTN.addActionListener(e -> { mainWindow.setVisible(true); dispose(); });

        submitBTN.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                String result = manager.addTransaction("Deposit", amount, dateField.getText(), "");
                if (result.equals("SUCCESS")) {
                    mainWindow.updateBalance("Deposit");
                    JOptionPane.showMessageDialog(this, "Deposit successful!\nNew Balance: PHP " + String.format("%.2f", manager.getBalance()));
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