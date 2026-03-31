import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;

public class BankAppGUI extends JFrame{
    TransactionManager manager = new TransactionManager();
    public BankAppGUI(){
        setTitle("Bank Application");
        setSize(390, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel header = new JLabel ("Bank Application");
        header.setBounds(0, 0, 380, 50);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(50, 80, 280, 380);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        add(buttonPanel);
        
        JButton depBTN = new JButton("Deposit");
        depBTN.setBounds(40, 20, 200, 50);
        buttonPanel.add(depBTN);
        
        JButton witBTN = new JButton("Withdrawal");
        witBTN.setBounds(40, 90, 200, 50);
        buttonPanel.add(witBTN);
        
        JButton tranBTN = new JButton("Transfer");
        tranBTN.setBounds(40, 160, 200, 50);
        buttonPanel.add(tranBTN);
        
        JButton billsBTN = new JButton("Bills Payment");
            billsBTN.setBounds(40, 230, 200, 50);
        buttonPanel.add(billsBTN);
        
        JButton loadBTN = new JButton("Buy Load");
        loadBTN.setBounds(40, 300, 200, 50);
        buttonPanel.add(loadBTN);
        
        depBTN.addActionListener(e -> {
            new DepositGUI(manager, this);
            dispose();
        });
        
        witBTN.addActionListener(e -> {
            new WithdrawalGUI(manager, this);
            dispose();
        });
        
        tranBTN.addActionListener(e -> {
            new TransferGUI(manager, this);
            dispose();
        });
        
        billsBTN.addActionListener(e -> {
            new BillPaymentGUI(manager, this);
            dispose();
        });
        
        loadBTN.addActionListener(e -> {
            new BuyLoadGUI(manager, this);
            dispose();
        });
        
        setVisible(true);
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(BankAppGUI::new);
    }
}
