package BAMS;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class BankAppGUI extends JFrame {

    private JPanel buttonPanel;
    private JLabel header, balanceLBL, balanceAmountLBL;
    private JButton depBTN, witBTN, tranBTN, billsBTN, loadBTN, hisBTN;
    private JButton homeBTN, manageAccBTN;

    TransactionManager manager = new TransactionManager();

    public BankAppGUI() {
        setTitle("Bank Application");
        setSize(400, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 250));

        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 0, 390, 140); 
        headerPanel.setBackground(new Color(30, 50, 100));
        add(headerPanel);

        homeBTN = new JButton("Home");
        homeBTN.setBounds(8, 8, 70, 26);
        homeBTN.setFont(new Font("Tahoma", Font.PLAIN, 11));
        homeBTN.setBackground(new Color(30, 50, 100));
        homeBTN.setForeground(Color.WHITE);
        homeBTN.setFocusPainted(false);
        homeBTN.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230), 1, true));
        homeBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        headerPanel.add(homeBTN);

        manageAccBTN = new JButton("Manage Accounts");
        manageAccBTN.setBounds(255, 8, 125, 26);
        manageAccBTN.setFont(new Font("Tahoma", Font.PLAIN, 11));
        manageAccBTN.setBackground(new Color(30, 50, 100));
        manageAccBTN.setForeground(Color.WHITE);
        manageAccBTN.setFocusPainted(false);
        manageAccBTN.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230), 1, true));
        manageAccBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        headerPanel.add(manageAccBTN);

        header = new JLabel("Bank Application");
        header.setBounds(0, 45, 390, 35);
        header.setFont(new Font("Tahoma", Font.BOLD, 22));
        header.setForeground(Color.WHITE);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(header);

        balanceLBL = new JLabel("Current Balance");
        balanceLBL.setBounds(0, 82, 390, 18);
        balanceLBL.setFont(new Font("Tahoma", Font.PLAIN, 12));
        balanceLBL.setForeground(new Color(180, 200, 230));
        balanceLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(balanceLBL);

        balanceAmountLBL = new JLabel("PHP 0.00");
        balanceAmountLBL.setBounds(0, 100, 390, 28);
        balanceAmountLBL.setFont(new Font("Tahoma", Font.BOLD, 24));
        balanceAmountLBL.setForeground(Color.WHITE);
        balanceAmountLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(balanceAmountLBL);

        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(40, 155, 305, 460); 
        buttonPanel.setBackground(new Color(245, 245, 250));
        add(buttonPanel);

        depBTN=makeButton("Deposit");
        witBTN=makeButton("Withdrawal");
        tranBTN=makeButton("Transfer");
        billsBTN=makeButton("Bills Payment");
        loadBTN=makeButton("Buy Load");
        hisBTN=makeButton("Transaction History");

        int startY = 10;
        int gap    = 68;
        depBTN.setBounds(0, startY,305, 52);
        witBTN.setBounds(0, startY + gap,305, 52);
        tranBTN.setBounds(0, startY + gap * 2,305, 52);
        billsBTN.setBounds(0, startY + gap * 3,305, 52);
        loadBTN.setBounds(0, startY + gap * 4,305, 52);
        hisBTN.setBounds(0, startY + gap * 5,305, 52);

        buttonPanel.add(depBTN);
        buttonPanel.add(witBTN);
        buttonPanel.add(tranBTN);
        buttonPanel.add(billsBTN);
        buttonPanel.add(loadBTN);
        buttonPanel.add(hisBTN);

  
        depBTN.addActionListener(e -> { new DepositGUI(manager, this); setVisible(false); });
        witBTN.addActionListener(e -> { new WithdrawalGUI(manager, this); setVisible(false); });
        tranBTN.addActionListener(e -> { new TransferGUI(manager, this); setVisible(false); });
        billsBTN.addActionListener(e -> { new BillPaymentGUI(manager, this); setVisible(false); });
        loadBTN.addActionListener(e -> { new BuyLoadGUI(manager, this); setVisible(false); });
        hisBTN.addActionListener(e -> { new HistoryGUI(manager, this); dispose(); });

        homeBTN.addActionListener(e -> {new bankmanagementapp.frontPage();
        dispose();
        });

        manageAccBTN.addActionListener(e -> {project.AccountFiles files = new project.AccountFiles();
            new project.GUI1Frame(files).setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(30, 50, 100));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 230), 1, true),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        return btn;
    }

    public void updateBalance(double balance) {
        balanceAmountLBL.setText(String.format("PHP %.2f", balance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankAppGUI::new);
    }
}