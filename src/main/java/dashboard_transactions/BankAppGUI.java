package dashboard_transactions;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
//import project.GUI1Frame;    
//import project.AccountFiles; 
import AccountManagement.AccountFiles;
import AccountManagement.dashboardAccMng;
import bankmanagementapp.logInPage;

public class BankAppGUI extends JFrame {

    private JPanel buttonPanel;
    private JLabel header, balanceLBL, balanceAmountLBL;
    private JButton depBTN, witBTN, tranBTN, billsBTN, autoPayBTN, loadBTN, suppBTN, hisBTN;

   TransactionManager manager;
private int accountNumber;

public BankAppGUI(int accountNumber) {
    this.accountNumber = accountNumber;
    this.manager = new TransactionManager(accountNumber);
        setTitle("Bank Application");
        setSize(400, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(235, 240, 250));

        //navbar 
        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 400, 45);
        navPanel.setBackground(new Color(15, 28, 60));
        add(navPanel);
        

        JButton homeBtn = new JButton("<- Home");
        homeBtn.setBounds(10, 9, 65, 27);
        homeBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        homeBtn.setForeground(Color.WHITE);
        homeBtn.setBackground(new Color(37, 65, 130));
        homeBtn.setFocusPainted(false);
        homeBtn.setBorder(BorderFactory.createLineBorder(new Color(80, 110, 180), 1, true));
        homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(homeBtn);
        
        homeBtn.addActionListener(e -> {
        new bankmanagementapp.logInPage().setVisible(true); this.dispose();});

        JButton manageBtn = new JButton("≡ Accounts");
        manageBtn.setBounds(230, 9, 145, 27);
        manageBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        manageBtn.setForeground(Color.WHITE);
        manageBtn.setBackground(new Color(37, 65, 130));
        manageBtn.setFocusPainted(false);
        manageBtn.setBorder(BorderFactory.createLineBorder(new Color(80, 110, 180), 1, true));
        manageBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(manageBtn);

        //header
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

        balanceAmountLBL = new JLabel("PHP 0.00");
        balanceAmountLBL.setBounds(0, 92, 380, 38);
        balanceAmountLBL.setFont(new Font("Tahoma", Font.BOLD, 30));
        balanceAmountLBL.setForeground(Color.WHITE);
        balanceAmountLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(balanceAmountLBL);

        //section panel
        JLabel sectionLBL = new JLabel("Quick Actions");
        sectionLBL.setBounds(24, 218, 200, 20);
        sectionLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        sectionLBL.setForeground(new Color(100, 120, 160));
        add(sectionLBL);

        //button
        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(12, 238, 362, 340);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
        add(buttonPanel);

        depBTN   = makeButton("[+] Deposit money");
        witBTN   = makeButton("[-]  Withdraw funds");
        tranBTN  = makeButton("[->]  Transfer money");
        billsBTN = makeButton("[$]  Pay bills");
        loadBTN  = makeButton("[#]  Buy load");
        autoPayBTN = makeButton("[R]  Auto Payment");
buttonPanel.add(autoPayBTN);
        suppBTN  = makeButton("[?]  Support / Help");

        buttonPanel.add(depBTN);
        buttonPanel.add(witBTN);
        buttonPanel.add(tranBTN);
        buttonPanel.add(billsBTN);
        buttonPanel.add(loadBTN);
        buttonPanel.add(suppBTN);
depBTN    .setBounds(5, 8,   350, 42);
witBTN    .setBounds(5, 55,  350, 42);
tranBTN   .setBounds(5, 102, 350, 42);
billsBTN  .setBounds(5, 149, 350, 42);
autoPayBTN.setBounds(5, 196, 350, 42);
loadBTN   .setBounds(5, 243, 350, 42);
suppBTN   .setBounds(5, 290, 350, 42);

        //trnasaction history
        hisBTN = makeButton("[=]  Transaction History");
        hisBTN.setBounds(18, 600, 350, 45);
        add(hisBTN);

        //aaction listeners 
        depBTN  .addActionListener(e -> { new DepositGUI(manager, this);     setVisible(false); });
        witBTN  .addActionListener(e -> { new WithdrawalGUI(manager, this);  setVisible(false); });
        tranBTN .addActionListener(e -> { new TransferGUI(manager, this);    setVisible(false); });
        billsBTN.addActionListener(e -> { new BillPaymentGUI(manager, this); setVisible(false); });
        loadBTN .addActionListener(e -> { new BuyLoadGUI(manager, this);     setVisible(false); });
        autoPayBTN.addActionListener(e -> { new AutoPaymentGUI(manager, this); setVisible(false); });
        suppBTN .addActionListener(e -> { JOptionPane.showMessageDialog(this, "Support & Help\nEmail: support@bankapp.com\nHotline: 1800-BANK-APP"); });
        hisBTN  .addActionListener(e -> { new HistoryGUI(manager, this);     setVisible(false); });
      manageBtn.addActionListener(e -> {
    AccountFiles files = new AccountFiles();
    new dashboardAccMng(files, accountNumber).setVisible(true);
    setVisible(false);
});

       updateBalance("None");
setVisible(true);
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(25, 45, 95));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(225, 232, 250)); }
            public void mouseExited (MouseEvent e) { btn.setBackground(Color.WHITE); }
        });
        return btn;
    }

    public void updateBalance(String type) {
        double bal = manager.getBalance();
        balanceAmountLBL.setText(String.format("PHP %.2f", bal));
        balanceAmountLBL.setForeground(
            type.equals("Deposit") ? new Color(100, 220, 130) : Color.WHITE
        );
        balanceAmountLBL.revalidate();
        balanceAmountLBL.repaint();
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(BankAppGUI::new);
//    }
    
   
}