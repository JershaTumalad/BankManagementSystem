package dashboard_transactions;

import dashboard_transactions.TransactionManager;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import project.GUI1Frame;
import project.AccountFiles;
import bankmanagementapp.logInPage;

public class BankAppGUI extends JFrame {

    private JPanel buttonPanel;
    private JLabel header, balanceLBL, balanceAmountLBL;
    private JButton depBTN, witBTN, tranBTN, billsBTN, loadBTN, suppBTN, hisBTN;

    TransactionManager manager;

    public BankAppGUI(String accountNumber) {  // ✅ accepts accountNumber

        manager = new TransactionManager(accountNumber);  // ✅ fixed

        setTitle("Bank Application");
        setSize(400, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(235, 240, 250));

        // ── NAVBAR ──
        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 400, 45);
        navPanel.setBackground(new Color(15, 28, 60));
        add(navPanel);

        JButton homeBtn = new JButton("\uD83C\uDFE0  Home");
        homeBtn.setBounds(10, 9, 80, 27);
        homeBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        homeBtn.setForeground(Color.WHITE);
        homeBtn.setBackground(new Color(37, 65, 130));
        homeBtn.setFocusPainted(false);
        homeBtn.setBorder(BorderFactory.createLineBorder(new Color(80, 110, 180), 1, true));
        homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(homeBtn);
        homeBtn.addActionListener(e -> {
            new bankmanagementapp.logInPage().setVisible(true);
            this.dispose();
        });

        JButton manageBtn = new JButton("\uD83C\uDFDB  Accounts");
        manageBtn.setBounds(225, 9, 155, 27);
        manageBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        manageBtn.setForeground(Color.WHITE);
        manageBtn.setBackground(new Color(37, 65, 130));
        manageBtn.setFocusPainted(false);
        manageBtn.setBorder(BorderFactory.createLineBorder(new Color(80, 110, 180), 1, true));
        manageBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(manageBtn);

        // ── HEADER ──
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 45, 410, 160);
        headerPanel.setBackground(new Color(25, 45, 95));
        add(headerPanel);

        header = new JLabel("Bank Application");
        header.setBounds(0, 28, 390, 30);
        header.setFont(new Font("Tahoma", Font.BOLD, 22));
        header.setForeground(Color.WHITE);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(header);

        balanceLBL = new JLabel("Current Balance");
        balanceLBL.setBounds(0, 72, 380, 18);
        balanceLBL.setFont(new Font("Tahoma", Font.PLAIN, 12));
        balanceLBL.setForeground(new Color(160, 185, 225));
        balanceLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(balanceLBL);

        balanceAmountLBL = new JLabel(String.format("PHP %,.2f", manager.getBalance()));
        balanceAmountLBL.setBounds(0, 92, 380, 38);
        balanceAmountLBL.setFont(new Font("Tahoma", Font.BOLD, 30));
        balanceAmountLBL.setForeground(Color.WHITE);
        balanceAmountLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(balanceAmountLBL);

        // ── SECTION LABEL ──
        JLabel sectionLBL = new JLabel("Quick Actions");
        sectionLBL.setBounds(24, 218, 200, 20);
        sectionLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        sectionLBL.setForeground(new Color(100, 120, 160));
        add(sectionLBL);

        // ── BUTTON PANEL ──
        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(12, 238, 362, 340);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
        add(buttonPanel);

        depBTN   = makeButton("\u2795  Deposit money");
        witBTN   = makeButton("\u2796  Withdraw funds");
        tranBTN  = makeButton("\u27A1  Transfer money");
        billsBTN = makeButton("\uD83D\uDCB3  Pay bills");
        loadBTN  = makeButton("\uD83D\uDCF1  Buy load");
        suppBTN  = makeButton("\uD83C\uDF9A  Support / Help");

        depBTN  .setBounds(5, 8,   350, 45);
        witBTN  .setBounds(5, 63,  350, 45);
        tranBTN .setBounds(5, 118, 350, 45);
        billsBTN.setBounds(5, 173, 350, 45);
        loadBTN .setBounds(5, 228, 350, 45);
        suppBTN .setBounds(5, 283, 350, 45);

        buttonPanel.add(depBTN);
        buttonPanel.add(witBTN);
        buttonPanel.add(tranBTN);
        buttonPanel.add(billsBTN);
        buttonPanel.add(loadBTN);
        buttonPanel.add(suppBTN);

        // ── HISTORY BUTTON ──
        hisBTN = makeButton("\uD83D\uDD50  Transaction History");
        hisBTN.setBounds(18, 600, 350, 45);
        add(hisBTN);

        // ── ACTION LISTENERS ──
        depBTN  .addActionListener(e -> { new DepositGUI(manager, this);     setVisible(false); });
        witBTN  .addActionListener(e -> { new WithdrawalGUI(manager, this);  setVisible(false); });
        tranBTN .addActionListener(e -> { new TransferGUI(manager, this);    setVisible(false); });
        billsBTN.addActionListener(e -> { new BillPaymentGUI(manager, this); setVisible(false); });
        loadBTN .addActionListener(e -> { new BuyLoadGUI(manager, this);     setVisible(false); });
        suppBTN .addActionListener(e -> JOptionPane.showMessageDialog(this,
            "Support & Help\nEmail: support@bankapp.com\nHotline: 1800-BANK-APP"));
        hisBTN  .addActionListener(e -> { new HistoryGUI(manager, this);     setVisible(false); });
        manageBtn.addActionListener(e -> {
            AccountFiles files = new AccountFiles();
            new GUI1Frame(files).setVisible(true);
            setVisible(false);
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
        btn.setIconTextGap(10);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 215, 235), 1),
            BorderFactory.createEmptyBorder(0, 16, 0, 0)
        ));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(225, 232, 250)); }
            public void mouseExited (MouseEvent e) { btn.setBackground(Color.WHITE); }
        });
        return btn;
    }

    public void updateBalance(String type) {
        double bal = manager.getBalance();
        balanceAmountLBL.setText(String.format("PHP %,.2f", bal));
        balanceAmountLBL.setForeground(
            type.equals("Deposit") ? new Color(100, 220, 130) : Color.WHITE
        );
        balanceAmountLBL.revalidate();
        balanceAmountLBL.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankAppGUI("ACC-123456")); // ✅ fixed
    }
}