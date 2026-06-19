package bankmanagementapp;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;

public class AutoPaymentGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI parent;

    private JTextField tfBiller, tfAmount, tfDescription;
    private JComboBox<String> cbFrequency;
    private JLabel lblNextRunPreview;
    private JButton btnSubmit, btnBack, btnViewScheduled;

    // Colors
    private final Color mainColor   = new Color(25, 45, 95);
    private final Color bgColor     = new Color(235, 240, 250);
    private final Color cardColor   = Color.WHITE;
    private final Color accentColor = new Color(138, 173, 255);
    private final Color fieldBg     = new Color(245, 246, 252);
    private final Color labelGray   = new Color(110, 110, 110);

    public AutoPaymentGUI(TransactionManager manager, BankAppGUI parent) {
        this.manager = manager;
        this.parent  = parent;

        setTitle("Auto Payment");
        setSize(430, 760);
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

        // ── INFO BANNER (explains no immediate deduction) ──
        JPanel infoBanner = new JPanel(null);
        infoBanner.setBounds(25, 178, 370, 38);
        infoBanner.setBackground(new Color(230, 240, 255));
        infoBanner.setBorder(BorderFactory.createLineBorder(new Color(138, 173, 255), 1, true));
        add(infoBanner);

        JLabel infoLbl = new JLabel("ℹ  Amount is deducted on the first due date, not today.");
        infoLbl.setBounds(10, 10, 350, 18);
        infoLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        infoLbl.setForeground(mainColor);
        infoBanner.add(infoLbl);

        // ── FORM CARD ──
        JPanel card = new JPanel(null);
        card.setBounds(25, 228, 370, 430);
        card.setBackground(cardColor);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
        add(card);

        // Biller
        addLabel(card, "BILLER NAME", 25, 20);
        tfBiller = addTextField(card, 25, 42);

        // Amount
        addLabel(card, "AMOUNT (PHP)", 25, 100);
        tfAmount = addTextField(card, 25, 122);

        // Frequency
        addLabel(card, "FREQUENCY", 25, 180);
        cbFrequency = new JComboBox<>(new String[]{"Daily", "Weekly", "Monthly", "Yearly"});
        cbFrequency.setBounds(25, 200, 320, 42);
        cbFrequency.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbFrequency.setBackground(fieldBg);
        card.add(cbFrequency);

        // Next run preview label (updates when frequency changes)
        lblNextRunPreview = new JLabel(getNextRunText());
        lblNextRunPreview.setBounds(25, 250, 320, 18);
        lblNextRunPreview.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblNextRunPreview.setForeground(new Color(80, 130, 80));
        card.add(lblNextRunPreview);

        cbFrequency.addActionListener(e ->
            lblNextRunPreview.setText(getNextRunText()));

        // Description
        addLabel(card, "DESCRIPTION (optional)", 25, 285);
        tfDescription = addTextField(card, 25, 307);

        // ── BUTTONS ──
        btnBack = new JButton("← Back");
        btnBack.setBounds(25, 573, 370, 45);
        btnBack.setBackground(bgColor);
        btnBack.setForeground(new Color(70, 70, 70));
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnBack);

        btnSubmit = new JButton("Schedule Payment");
        btnSubmit.setBounds(25, 630, 370, 45);
        btnSubmit.setBackground(accentColor);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnSubmit);

        btnViewScheduled = new JButton("View Scheduled Payments");
        btnViewScheduled.setBounds(25, 680, 370, 45);
        btnViewScheduled.setBackground(fieldBg);
        btnViewScheduled.setForeground(mainColor);
        btnViewScheduled.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnViewScheduled.setFocusPainted(false);
        btnViewScheduled.setBorderPainted(false);
        btnViewScheduled.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnViewScheduled);

        // ── ACTION LISTENERS ──
        btnBack.addActionListener(e -> { parent.setVisible(true); dispose(); });
        btnSubmit.addActionListener(e -> scheduleAutoPayment());
        btnViewScheduled.addActionListener(e -> viewScheduled());

        setVisible(true);
    }

    // ── SCHEDULE PAYMENT (no immediate deduction) ──
    private void scheduleAutoPayment() {
        String biller      = tfBiller.getText().trim();
        String amountText  = tfAmount.getText().trim();
        String frequency   = (String) cbFrequency.getSelectedItem();
        String description = tfDescription.getText().trim();

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

        // Note: we do NOT check balance here — balance check happens on the due date
        String result = manager.scheduleAutoPayment(biller, amount, frequency, description);

        if (result.startsWith("SUCCESS")) {
            // Extract the first run date returned by the method
            String firstRunDate = result.contains(":") ? result.split(":")[1] : "next cycle";

            JOptionPane.showMessageDialog(this,
                "Auto payment scheduled!\n\n" +
                "Biller    : " + biller + "\n" +
                "Amount    : PHP " + String.format("%,.2f", amount) + "\n" +
                "Frequency : " + frequency + "\n" +
                "First deduction on: " + firstRunDate + "\n\n" +
                "Your balance will not be affected today.",
                "Scheduled", JOptionPane.INFORMATION_MESSAGE);

            tfBiller.setText("");
            tfAmount.setText("");
            tfDescription.setText("");
        } else {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── VIEW SCHEDULED PAYMENTS ──
    private void viewScheduled() {
        try {
            Connection con = bankmanagementapp.DBConnection1.getConnection();
            // Show all (active and inactive) so user can see history
            String sql = "SELECT biller, amount, frequency, date, next_run_date, status, description, is_active "
                       + "FROM auto_payment WHERE account_number = ? ORDER BY date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, manager.getAccountNumber());
            ResultSet rs = ps.executeQuery();

            String[] columns = {"Biller", "Amount", "Frequency", "Next Run", "Status"};
            java.util.List<Object[]> rows = new java.util.ArrayList<>();

            while (rs.next()) {
                boolean active = rs.getInt("is_active") == 1;
                String nextRun = rs.getDate("next_run_date") != null
                    ? rs.getDate("next_run_date").toString() : "—";
                rows.add(new Object[]{
                    rs.getString("biller"),
                    String.format("PHP %,.2f", rs.getDouble("amount")),
                    rs.getString("frequency"),
                    active ? nextRun : "Cancelled",
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
            scrollPane.setPreferredSize(new Dimension(620, 300));

            JOptionPane.showMessageDialog(this, scrollPane,
                "Scheduled Payments", JOptionPane.PLAIN_MESSAGE);

            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Returns preview text for the next run date ──
    private String getNextRunText() {
        String freq = (String) cbFrequency.getSelectedItem();
        if (freq == null) freq = "Monthly";
        LocalDate next = TransactionManager.calculateNextRun(AppClock.today(), freq);
        return "→ First deduction on: " + next.toString();
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