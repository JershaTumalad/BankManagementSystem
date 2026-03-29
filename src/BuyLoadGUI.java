import javax.swing.*;
import java.awt.*;

public class BuyLoadGUI extends JFrame{
    private TransactionManager manager;
    private JRadioButton globe, smart, dito, tm, tnt;
    private JLabel loadLBL, amountLBL, dateLBL;
    private JTextField amountFLD, dateFLD;
    private JButton subBTN;
    ButtonGroup group;
 
    public BuyLoadGUI (TransactionManager manager){
        this.manager = manager;
        setTitle("Buy Load");
        setSize(390, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel loadLBL = new JLabel("Choose Telco: ");
        loadLBL.setLayout(null);
        loadLBL.setFont(new Font("Arial", Font.BOLD, 14));
        loadLBL.setBounds(20, 50, 340, 30);
        add(loadLBL);
        
        globe = new JRadioButton("Globe");
        smart = new JRadioButton("Smart");
        dito = new JRadioButton("DITO");
        tm = new JRadioButton("TM");
        tnt = new JRadioButton("TNT");
        
        globe.setBounds(20, 90, 340, 30);
        smart.setBounds(20, 120, 340, 30);
        dito.setBounds(20, 150, 340, 30);
        tm.setBounds(20, 180, 340, 30);
        tnt.setBounds(20, 210, 340, 30);
        
        group = new ButtonGroup();
        group.add(globe);
        group.add(smart);
        group.add(dito);
        group.add(tm);
        group.add(tnt);
        
        add(globe);
        add(smart);
        add(dito);
        add(tm);
        add(tnt);
        
        
        amountLBL = new JLabel("Amount: ");
        amountLBL.setLayout(null);
        amountLBL.setFont(new Font("Arial", Font.BOLD, 14));
        amountLBL.setBounds(20, 260, 340, 30);
        add(amountLBL);
        
        amountFLD = new JTextField();
        amountFLD.setBounds(20, 290, 340, 30);
        add(amountFLD);
        
        dateLBL = new JLabel("Date: ");
        dateLBL.setLayout(null);
        dateLBL.setFont(new Font("Arial", Font.BOLD, 14));
        dateLBL.setBounds(20, 340, 340, 30);
        add(dateLBL);
        
        dateFLD = new JTextField();
        dateFLD.setBounds(20, 370, 340, 30);
        add(dateFLD);
        
        subBTN = new JButton("Submit");
        subBTN.setLayout(null);
        subBTN.setBounds(95, 430, 200, 40);
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
                manager.addTransaction("Buy Load", amount, date, "");
                JOptionPane.showMessageDialog(this, "Load purchase");
                    amountFLD.setText("");
                    dateFLD.setText("");
                    globe.setSelected(false);
                    smart.setSelected(false);
                    dito.setSelected(false);
                    tm.setSelected(false);
                    tnt.setSelected(false);
                    globe.setSelected(true);
            }catch(NumberFormatException ex){
             JOptionPane.showMessageDialog(this, "Invalid amount. Enter a number.");
            }
        });
        
        setVisible(true);
    }
}