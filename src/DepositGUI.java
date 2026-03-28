import javax.swing.*;
import java.awt.*;

public class DepositGUI extends JFrame{
    private TransactionManager manager;
    
    public DepositGUI(TransactionManager manager){
        this.manager = manager;
        setTitle("Deposit");
        setSize(900, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel amountLBL = new JLabel("Amount: ");
        amountLBL.setLayout(null);
        amountLBL.setFont(new Font("Arial", Font.BOLD, 14));
        amountLBL.setBounds(50, 100, 150, 30);
        add(amountLBL);
        
        JTextField amountFLD = new JTextField();
        amountFLD.setBounds(110, 100, 200, 30);
        add(amountFLD);
        
        JLabel dateLBL = new JLabel("Date: ");
        dateLBL.setLayout(null);
        dateLBL.setFont(new Font("Arial", Font.BOLD, 14));
        dateLBL.setBounds(50, 150, 150, 30);
        add(dateLBL);
        
        JTextField dateFLD = new JTextField();
        dateFLD.setBounds(110, 150, 200, 30);
        add(dateFLD);
        
        JButton subBTN = new JButton("Submit");
        subBTN.setLayout(null);
        subBTN.setBounds(110, 200, 200, 30);
        add(subBTN);
        
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
                manager.addTransaction("Deposit", amount, date, "");
                JOptionPane.showMessageDialog(this, "Deposit successful.");
            }catch(NumberFormatException ex){
             JOptionPane.showMessageDialog(this, "Invalid amount. Enter a number.");
            }
        });
        
        setVisible(true);
    }
}
