package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import bankmanagementapp.logInPage;

public class BankAppGUI extends JFrame {

    private JPanel buttonPanel;
    private JLabel header, balanceLBL, balanceAmountLBL;
    private JButton depBTN, witBTN, tranBTN, billsBTN, autoPayBTN, loadBTN, suppBTN, hisBTN;

    TransactionManager manager;
    private String accountNumber;

    public BankAppGUI() {

        this.accountNumber = null;
        manager = new TransactionManager();

        setTitle("Bank Application");
        setSize(430, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(235, 240, 250));

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
            new logInPage().setVisible(true);
            this.dispose();
        });

        JButton manageBtn = new JButton("🏛  Accounts");
        manageBtn.setBounds(255, 9, 155, 27);
        manageBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        manageBtn.setForeground(Color.WHITE);
        manageBtn.setBackground(new Color(37, 65, 130));
        manageBtn.setFocusPainted(false);
        manageBtn.setBorder(BorderFactory.createLineBorder(new Color(80, 110, 180), 1, true));
        manageBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(manageBtn);

        // ── HEADER ──
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 45, 430, 155);
        headerPanel.setBackground(new Color(25, 45, 95));
        add(headerPanel);

        header = new JLabel("Bank Application");
        header.setBounds(0, 25, 420, 30);
        header.setFont(new Font("Tahoma", Font.BOLD, 22));
        header.setForeground(Color.WHITE);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(header);

        balanceLBL = new JLabel("Current Balance");
        balanceLBL.setBounds(0, 65, 420, 18);
        balanceLBL.setFont(new Font("Tahoma", Font.PLAIN, 12));
        balanceLBL.setForeground(new Color(160, 185, 225));
        balanceLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(balanceLBL);

        balanceAmountLBL = new JLabel(String.format("PHP %,.2f", manager.getBalance()));
        balanceAmountLBL.setBounds(0, 85, 420, 40);
        balanceAmountLBL.setFont(new Font("Tahoma", Font.BOLD, 30));
        balanceAmountLBL.setForeground(Color.WHITE);
        balanceAmountLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(balanceAmountLBL);

        // ── SECTION LABEL ──
        JLabel sectionLBL = new JLabel("Quick Actions");
        sectionLBL.setBounds(24, 210, 200, 20);
        sectionLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        sectionLBL.setForeground(new Color(100, 120, 160));
        add(sectionLBL);

        // ── BUTTON PANEL ──
        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(12, 232, 395, 344);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
        add(buttonPanel);

        depBTN     = makeButton("➕  Deposit money");
        witBTN     = makeButton("➖  Withdraw funds");
        tranBTN    = makeButton("➡  Transfer money");
        billsBTN   = makeButton("💳  Pay bills");
        autoPayBTN = makeButton("🔄  Auto Payment");   
        loadBTN    = makeButton("📱  Buy load");
        suppBTN    = makeButton("🎚  Support / Help");

        depBTN    .setBounds(5, 5,   383, 42);
        witBTN    .setBounds(5, 52,  383, 42);
        tranBTN   .setBounds(5, 99,  383, 42);
        billsBTN  .setBounds(5, 146, 383, 42);
        autoPayBTN.setBounds(5, 193, 383, 42);
        loadBTN   .setBounds(5, 240, 383, 42);
        suppBTN   .setBounds(5, 287, 383, 42);

        buttonPanel.add(depBTN);
        buttonPanel.add(witBTN);
        buttonPanel.add(tranBTN);
        buttonPanel.add(billsBTN);
        buttonPanel.add(autoPayBTN);
        buttonPanel.add(loadBTN);
        buttonPanel.add(suppBTN);

        JPanel historyPanel = new JPanel(null);
        historyPanel.setBounds(12, 584, 395, 52);
        historyPanel.setBackground(Color.WHITE);
        historyPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
        add(historyPanel);

        hisBTN = makeButton("🕐  Transaction History");
        hisBTN.setBounds(5, 5, 383, 42);
        historyPanel.add(hisBTN);

        // ── ACTION LISTENERS ──
        depBTN    .addActionListener(e -> { new DepositGUI(manager, this);     setVisible(false); });
        witBTN    .addActionListener(e -> { new WithdrawalGUI(manager, this);  setVisible(false); });
        tranBTN   .addActionListener(e -> { new TransferGUI(manager, this);    setVisible(false); });
        billsBTN  .addActionListener(e -> { new BillPaymentGUI(manager, this); setVisible(false); });
        autoPayBTN.addActionListener(e -> { new AutoPaymentGUI(manager, this); setVisible(false); }); // NEW
        loadBTN   .addActionListener(e -> { new BuyLoadGUI(manager, this);     setVisible(false); });
        suppBTN   .addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Support & Help\nEmail: support@bankapp.com\nHotline: 1800-BANK-APP")
        );
        hisBTN    .addActionListener(e -> { new HistoryGUI(manager, this);     setVisible(false); });

        manageBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Account Management module not connected.");
        });

        setVisible(true);
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(25, 45, 95));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 215, 235), 1),
            BorderFactory.createEmptyBorder(0, 16, 0, 0)
        ));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(225, 232, 250));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.WHITE);
            }
        });
        return btn;
    }

    public void updateBalance(String type) {
        double bal = manager.getBalance();
        balanceAmountLBL.setText(String.format("PHP %,.2f", bal));
        balanceAmountLBL.setForeground(
            type.equals("Deposit") ? new Color(100, 220, 130) : Color.WHITE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankAppGUI());
    }
}