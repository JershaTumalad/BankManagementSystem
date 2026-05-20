package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import AccountManagement.ProjectMain;
import AccountManagement.AccountFiles;
import AccountManagement.GUI1Frame;
import bankmanagementapp.logInPage;

public class BankAppGUI extends JFrame {

    private JLabel balanceAmountLBL;
    private JButton depBTN, witBTN, tranBTN, billsBTN, loadBTN, suppBTN, hisBTN;

    TransactionManager manager = new TransactionManager();

    public static final Color PRIMARY    = new Color(25, 48, 90);
    public static final Color LIGHT_BLUE = new Color(139, 172, 224);
    public static final Color BG         = new Color(200, 210, 230);
    public static final Color TEXT_MUTED = new Color(100, 110, 130);

    public BankAppGUI() {
        setTitle("Bank Account Management");
        setSize(400, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG);

        // header
        JPanel header = new JPanel(null);
        header.setBounds(0, 0, 400, 110);
        header.setBackground(PRIMARY);
        add(header);

        JButton homeBtn = new JButton("Log out");
        homeBtn.setBounds(10, 10, 75, 28);
        homeBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        homeBtn.setForeground(Color.WHITE);
        homeBtn.setBackground(new Color(37, 65, 130));
        homeBtn.setFocusPainted(false);
        homeBtn.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1)); 
        homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.add(homeBtn);
        homeBtn.addActionListener(e -> { new bankmanagementapp.logInPage().setVisible(true); dispose(); });

        JButton manageBtn = new JButton("Accounts");
        manageBtn.setBounds(290, 10, 80, 28);
        manageBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        manageBtn.setForeground(Color.WHITE);
        manageBtn.setBackground(new Color(37, 65, 130));
        manageBtn.setFocusPainted(false);
        manageBtn.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE, 1)); 
        manageBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.add(manageBtn);
        manageBtn.addActionListener(e -> { AccountFiles files = new AccountFiles(); new GUI1Frame(files).setVisible(true); setVisible(false); });

        JLabel appLabel = new JLabel("STATE-BANK APP");
        appLabel.setBounds(0, 48, 400, 16);
        appLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        appLabel.setForeground(LIGHT_BLUE);
        appLabel.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(appLabel);

        JLabel appTitle = new JLabel("Bank Application");
        appTitle.setBounds(0, 65, 400, 32);
        appTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        appTitle.setForeground(Color.WHITE);
        appTitle.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(appTitle);

        // balance panel
        JPanel balPanel = new JPanel(null);
        balPanel.setBounds(14, 120, 355, 65);
        balPanel.setBackground(Color.WHITE);
        balPanel.setBorder(BorderFactory.createLineBorder(new Color(210, 220, 240), 1)); // no rounding
        add(balPanel);

        JLabel balLbl = new JLabel("Current Balance");
        balLbl.setBounds(15, 10, 300, 16);
        balLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        balLbl.setForeground(TEXT_MUTED);
        balPanel.add(balLbl);

        balanceAmountLBL = new JLabel("PHP 0.00");
        balanceAmountLBL.setBounds(15, 28, 300, 28);
        balanceAmountLBL.setFont(new Font("Segoe UI", Font.BOLD, 22));
        balanceAmountLBL.setForeground(PRIMARY);
        balPanel.add(balanceAmountLBL);

        JPanel actionsPanel = new JPanel(null);
        actionsPanel.setBounds(14, 200, 355, 395);
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.setBorder(BorderFactory.createLineBorder(new Color(210, 220, 240), 1)); 
        add(actionsPanel);

        JLabel sectionLbl = new JLabel("QUICK ACTIONS");
        sectionLbl.setBounds(13, 10, 300, 18);
        sectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        sectionLbl.setForeground(TEXT_MUTED);
        actionsPanel.add(sectionLbl);

        depBTN   = makeButton("Deposit money");
        witBTN   = makeButton("Withdraw funds");
        tranBTN  = makeButton("Transfer money");
        billsBTN = makeButton("Pay bills");
        loadBTN  = makeButton("Buy load");
        suppBTN  = makeButton("Support / Help");

        depBTN  .setBounds(10, 38,  333, 48);
        witBTN  .setBounds(10, 98,  333, 48);
        tranBTN .setBounds(10, 158, 333, 48);
        billsBTN.setBounds(10, 218, 333, 48);
        loadBTN .setBounds(10, 278, 333, 48);
        suppBTN .setBounds(10, 338, 333, 48);

        actionsPanel.add(depBTN);
        actionsPanel.add(witBTN);
        actionsPanel.add(tranBTN);
        actionsPanel.add(billsBTN);
        actionsPanel.add(loadBTN);
        actionsPanel.add(suppBTN);

        hisBTN = makeButton("Transaction History");
        hisBTN.setBounds(14, 608, 355, 48);
        add(hisBTN);

        // action listeners
        depBTN  .addActionListener(e -> { new DepositGUI(manager, this);     setVisible(false); });
        witBTN  .addActionListener(e -> { new WithdrawalGUI(manager, this);  setVisible(false); });
        tranBTN .addActionListener(e -> { new TransferGUI(manager, this);    setVisible(false); });
        billsBTN.addActionListener(e -> { new BillPaymentGUI(manager, this); setVisible(false); });
        loadBTN .addActionListener(e -> { new BuyLoadGUI(manager, this);     setVisible(false); });
        suppBTN .addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Support & Help\nEmail: support@bankapp.com\nHotline: 1800-BANK-APP"));
        hisBTN  .addActionListener(e -> { new HistoryGUI(manager, this);     setVisible(false); });

        setVisible(true);
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btn.setBackground(new Color(139, 172, 224));
        btn.setForeground(new Color(25, 48, 90));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setBorder(BorderFactory.createEmptyBorder());
        return btn;
    }

    public void updateBalance(String type) {
        double bal = manager.getBalance();
        balanceAmountLBL.setText(String.format("PHP %.2f", bal));
        balanceAmountLBL.setForeground(
            type.equals("Deposit") ? new Color(30, 160, 90) : PRIMARY
        );
        balanceAmountLBL.revalidate();
        balanceAmountLBL.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankAppGUI::new);
    }
}