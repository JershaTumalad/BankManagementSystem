import java.awt.*;
import javax.swing.*;

public class WithdrawalGUI extends JFrame{
    private TransactionManager manager;
    private JLabel billLBL, amountLBL, dateLBL, balanceLBL;
    private JTextField amountFLD, dateFLD;
    private JButton subBTN, backBTN;
    
    public WithdrawalGUI(TransactionManager manager, BankAppGUI mainWindow){
        this.manager = manager;
        setTitle("Withdraw");
        setSize(390, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        
        amountLBL = new JLabel("Amount: ");
        amountLBL.setLayout(null);
        amountLBL.setFont(new Font("Arial", Font.BOLD, 14));
        amountLBL.setBounds(20, 100, 340, 30);
        add(amountLBL);
        
        amountFLD = new JTextField();
        amountFLD.setBounds(20, 130, 340, 30);
        add(amountFLD);
        
        dateLBL = new JLabel("Date: ");
        dateLBL.setLayout(null);
        dateLBL.setFont(new Font("Arial", Font.BOLD, 14));
        dateLBL.setBounds(20, 180, 340, 30);
        add(dateLBL);
        
        dateFLD = new JTextField();
        dateFLD.setBounds(20, 210, 340, 30);
        add(dateFLD);
        
        subBTN = new JButton("Submit");
        subBTN.setLayout(null);
        subBTN.setBounds(95, 270, 200, 40);
        add(subBTN);
        
        backBTN= new JButton("Back");
        backBTN.setLayout(null);
        backBTN.setBounds(20, 30, 80, 40);
        add(backBTN);
        
        backBTN.addActionListener(e -> {
            mainWindow.setVisible(true); 
            dispose();                   
        });
        
        balanceLBL = new JLabel("Balance: PHP " + String.format("%.2f", manager.getBalance()));
        balanceLBL.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLBL.setBounds(20, 550, 340, 30);
        add(balanceLBL);
        
        
        
        subBTN.addActionListener(e -> {
            String date = dateFLD.getText();
            if(!Transaction.isValidDate(date)){
                JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.");
                return;
            }
            
            try{
                double amount = Double.parseDouble(amountFLD.getText());
                if(amount <= 0 ){
                    JOptionPane.showMessageDialog(this, "Amount must be greater than 0.");
                    return;
                }
                manager.addTransaction("Withdrawal", amount, date, "");
                JOptionPane.showMessageDialog(this, "Withdrawal successful.");
                balanceLBL.setText("Balance: PHP " + String.format("%.2f", manager.getBalance()));
                    amountFLD.setText("");
                    dateFLD.setText("");
            }catch(NumberFormatException ex){
             JOptionPane.showMessageDialog(this, "Invalid amount. Enter a number.");
            }
        });
        
        setVisible(true);
    }
}
