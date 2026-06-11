package bankmanagementapp;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AutoPaymentGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI parent;

    private JTextField tfBiller, tfAmount, tfDescription;
    private JComboBox<String> cbFrequency;
    private JButton btnSubmit, btnBack, btnViewScheduled;

    // Colors
    private final Color mainColor       = new Color(25, 45, 95);
    private final Color bgColor         = new Color(235, 240, 250);
    private final Color cardColor       = Color.WHITE;
    private final Color accentColor     = new Color(138, 173, 255);
    private final Color fieldBg         = new Color(245, 246, 252);
    private final Color labelGray       = new Color(110, 110, 110);

    public AutoPaymentGUI(TransactionManager manager, BankAppGUI parent) {
        this.manager = manager;
        this.parent  = parent;

        setTitle("Auto Payment");
        setSize(430, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(bgColor);

        // ── NAVBAR ──
        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 430, 45);
        navPanel.setBackground(new Color(15, 28, 60));
        add(navPanel);

        JButton homeBtn = new JButton("🏠  Home");
        homeBtn.setBounds(10, 9, 90, 27);
        homeBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        homeBtn.setForeground(Color.WHITE);
        homeBtn.setBackground(new Color(37, 65, 130));
        homeBtn.setFocusPainted(false);
        homeBtn.setBorder(BorderFactory.createLineBorder(new Color(80, 110, 180), 1, true));
        homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(homeBtn);
        homeBtn.addActionListener(e -> {
            new bankmanagementapp.logInPage().setVisible(true);
            dispose();
        });

        // ── HEADER ──
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 45, 430, 120);
        headerPanel.setBackground(mainColor);
        add(headerPanel);

        JLabel miniTitle = new JLabel("SCHEDULED PAYMENTS");
        miniTitle.setForeground(new Color(160, 185, 225));
        miniTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        miniTitle.setBounds(35, 22, 300, 18);
        headerPanel.add(miniTitle);

        JLabel title = new JLabel("Auto Payment");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Tahoma", Font.BOLD, 26));
        title.setBounds(35, 45, 300, 40);
        headerPanel.add(title);

        // ── FORM CARD ──
        JPanel card = new JPanel(null);
        card.setBounds(25, 185, 370, 390);
        card.setBackground(cardColor);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
        add(card);

        // Biller
        addLabel(card, "BILLER NAME", 25, 20);
        tfBiller = addTextField(card, 25, 45);

        // Amount
        addLabel(card, "AMOUNT (PHP)", 25, 105);
        tfAmount = addTextField(card, 25, 130);

        // Frequency
        addLabel(card, "FREQUENCY", 25, 190);
        cbFrequency = new JComboBox<>(new String[]{
            "Daily", "Weekly", "Monthly", "Yearly"
        });
        cbFrequency.setBounds(25, 215, 320, 42);
        cbFrequency.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbFrequency.setBackground(fieldBg);
        cbFrequency.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        card.add(cbFrequency);

        // Description
        addLabel(card, "DESCRIPTION (optional)", 25, 275);
        tfDescription = addTextField(card, 25, 300);

        // ── BUTTONS ──
        btnSubmit = new JButton("Schedule Payment");
        btnSubmit.setBounds(25, 595, 370, 45);
        btnSubmit.setBackground(accentColor);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnSubmit);

        btnViewScheduled = new JButton("View Scheduled Payments");
        btnViewScheduled.setBounds(25, 635, 370, 45);
        btnViewScheduled.setBackground(fieldBg);
        btnViewScheduled.setForeground(mainColor);
        btnViewScheduled.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnViewScheduled.setFocusPainted(false);
        btnViewScheduled.setBorderPainted(false);
        btnViewScheduled.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnViewScheduled);

        btnBack = new JButton("← Back");
        btnBack.setBounds(25, 530, 370, 45);
        btnBack.setBackground(bgColor);
        btnBack.setForeground(new Color(70, 70, 70));
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnBack);

        // ── ACTION LISTENERS ──
        btnBack.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });

        btnSubmit.addActionListener(e -> schedulePayment());
        btnViewScheduled.addActionListener(e -> viewScheduled());

        setVisible(true);
    }

    // ── SCHEDULE PAYMENT ──
    private void schedulePayment() {
        String biller      = tfBiller.getText().trim();
        String amountText  = tfAmount.getText().trim();
        String frequency   = (String) cbFrequency.getSelectedItem();
        String description = tfDescription.getText().trim();

        // Validation
        if (biller.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a biller name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check balance
        if (manager.getBalance() < amount) {
            JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save to database
        try {
            Connection con = bankmanagementapp.DBConnection1.getConnection();

            String sql = "INSERT INTO auto_payment (account_number, biller, amount, frequency, description, status) "
                       + "VALUES (?, ?, ?, ?, ?, 'Successful')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, manager.getAccountNumber());
            ps.setString(2, biller);
            ps.setDouble(3, amount);
            ps.setString(4, frequency);
            ps.setString(5, description.isEmpty() ? null : description);
            ps.executeUpdate();

            // Deduct balance
            manager.deductBalance(amount);
            parent.updateBalance("AutoPay");

            JOptionPane.showMessageDialog(this,
                "Auto payment scheduled!\n" +
                "Biller: " + biller + "\n" +
                "Amount: PHP " + String.format("%,.2f", amount) + "\n" +
                "Frequency: " + frequency,
                "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields
            tfBiller.setText("");
            tfAmount.setText("");
            tfDescription.setText("");
            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── VIEW SCHEDULED PAYMENTS ──
    private void viewScheduled() {
        try {
            Connection con = bankmanagementapp.DBConnection1.getConnection();
            String sql = "SELECT biller, amount, frequency, date, status, description "
                       + "FROM auto_payment WHERE account_number = ? ORDER BY date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, manager.getAccountNumber());
            ResultSet rs = ps.executeQuery();

            String[] columns = {"Biller", "Amount", "Frequency", "Date", "Status"};
            java.util.List<Object[]> rows = new java.util.ArrayList<>();

            while (rs.next()) {
                rows.add(new Object[]{
                    rs.getString("biller"),
                    String.format("PHP %,.2f", rs.getDouble("amount")),
                    rs.getString("frequency"),
                    rs.getTimestamp("date").toString().substring(0, 16),
                    rs.getString("status")
                });
            }

            if (rows.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No scheduled payments found.");
                con.close();
                return;
            }

            Object[][] data = rows.toArray(new Object[0][]);
            JTable table = new JTable(data, columns);
            table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            table.setRowHeight(28);
            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
            table.setEnabled(false);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(600, 300));

            JOptionPane.showMessageDialog(this, scrollPane,
                "Scheduled Payments", JOptionPane.PLAIN_MESSAGE);

            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── HELPERS ──
    private void addLabel(JPanel panel, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 320, 18);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lbl.setForeground(labelGray);
        panel.add(lbl);
    }

    private JTextField addTextField(JPanel panel, int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 320, 42);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBackground(new Color(245, 246, 252));
        tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(tf);
        return tf;
    }
}