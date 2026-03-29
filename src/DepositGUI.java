import javax.swing.*;
import java.awt.*;

public class DepositGUI extends JFrame{
    private TransactionManager manager;
    private JLabel billLBL, amountLBL, dateLBL;
    private JTextField amountFLD, dateFLD;
    private JTextArea receiptArea;
    private JButton subBTN;
    
    public DepositGUI(TransactionManager manager){
        this.manager = manager;
        setTitle("Deposit");
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
        
        receiptArea = new JTextArea();
        receiptArea.setFont(new Font("Arial", Font.BOLD, 14));
        receiptArea.setBounds(20, 320, 340, 200);
        receiptArea.setEditable(false);
        add(receiptArea);
        
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
                String result = manager.addTransaction("Deposit", amount, date, "");
                if(result.equals("SUCCESS")){
                    JOptionPane.showMessageDialog(this, "Deposit is succesful.");
                    receiptArea.setText(
                        "Transaction Successful!\n" +
                        "-----------------------------\n" +
                        "\nTransaction Type : Deposit\n" +
                        "Amount                 : + PHP " + String.format("%.2f", amount) + "\n" +
                        "Date                      : " + date + "\n" +
                        "Status                   : Successful\n" +
                        "-----------------------------"
                    );
                    amountFLD.setText("");
                    dateFLD.setText("");
            }else{
                  JOptionPane.showMessageDialog(this, result);
                }
            }catch(NumberFormatException ex){
             JOptionPane.showMessageDialog(this, "Invalid amount. Enter a number.");
            }
        });
        
        setVisible(true);
    }
}
