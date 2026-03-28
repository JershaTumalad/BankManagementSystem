import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;

public class BankAppGUI extends JFrame{
    TransactionManager manager = new TransactionManager();
    public BankAppGUI(){
        setTitle("Bank Application");
        setSize(900, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel header = new JLabel ("Bank Application");
        header.setBounds(0, 0, 900, 50);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header);
//        
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(null);
//        formPanel.setBounds(0, 50, 300, 500);
//        formPanel.setBackground(Color.lightGray);
//        add(formPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(300, 150, 300, 300);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        add(buttonPanel);
        
        JButton depBTN = new JButton("Deposit");
        depBTN.setBounds(50, 20, 200, 50);
        buttonPanel.add(depBTN);
        
        JButton witBTN = new JButton("Withdrawal");
        witBTN.setBounds(50, 90, 200, 50);
        buttonPanel.add(witBTN);
        
        JButton tranBTN = new JButton("Transfer");
        tranBTN.setBounds(50, 160, 200, 50);
        buttonPanel.add(tranBTN);
        
        JButton billsBTN = new JButton("Bills Payment");
        billsBTN.setBounds(50, 230, 200, 50);
        buttonPanel.add(billsBTN);
        
        depBTN.addActionListener(e -> {
            new DepositGUI(manager);
            dispose();
        });
        
        witBTN.addActionListener(e -> {
            new WithdrawalGUI(manager);
            dispose();
        });
        
        setVisible(true);
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(BankAppGUI::new);
    }
}
