package BAMS;

import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;

public class BankAppGUI extends JFrame{
    private JPanel buttonPanel;
    private JLabel header, billLBL, amountLBL, dateLBL, balanceLBL;
    private JTextField amountFLD, dateFLD;
    private JButton depBTN, witBTN, tranBTN, billsBTN, loadBTN, hisBTN;
    
    TransactionManager manager = new TransactionManager();
    public BankAppGUI(){
        setTitle("Bank Application");
        setSize(390, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        header = new JLabel ("Bank Application");
        header.setBounds(0, 0, 380, 50);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(50, 80, 280, 440);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        add(buttonPanel);
        
        depBTN = new JButton("Deposit");
        depBTN.setBounds(40, 20, 200, 50);
        buttonPanel.add(depBTN);
        
        witBTN = new JButton("Withdrawal");
        witBTN.setBounds(40, 90, 200, 50);
        buttonPanel.add(witBTN);
        
        tranBTN = new JButton("Transfer");
        tranBTN.setBounds(40, 160, 200, 50);
        buttonPanel.add(tranBTN);
        
        billsBTN = new JButton("Bills Payment");
        billsBTN.setBounds(40, 230, 200, 50);
        buttonPanel.add(billsBTN);
        
        loadBTN = new JButton("Buy Load");
        loadBTN.setBounds(40, 300, 200, 50);
        buttonPanel.add(loadBTN);
        
        hisBTN = new JButton("Transaction History");
        hisBTN.setBounds(40, 370, 200, 50);
        buttonPanel.add(hisBTN);
        
        depBTN.addActionListener(e -> {
            new DepositGUI(manager, this);
            setVisible(false);
        });
        
        witBTN.addActionListener(e -> {
            new WithdrawalGUI(manager, this);
            setVisible(false);
        });
        
        tranBTN.addActionListener(e -> {
            new TransferGUI(manager, this);
            setVisible(false);
        });
        
        billsBTN.addActionListener(e -> {
            new BillPaymentGUI(manager, this);
            setVisible(false);
        });
        
        loadBTN.addActionListener(e -> {
            new BuyLoadGUI(manager, this);
            setVisible(false);
        });
        
        hisBTN.addActionListener(e -> {
            new HistoryGUI(manager, this);
            dispose();
        });
        
        setVisible(true);
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(BankAppGUI::new);
    }
}
