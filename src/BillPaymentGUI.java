import javax.swing.*;
import java.awt.*;

public class BillPaymentGUI extends JFrame{
    private TransactionManager manager;
    private JRadioButton meralco, pldt, globe, maynilad, skycable;
    private JLabel billLBL, amountLBL, dateLBL;
    private JTextField amountFLD, dateFLD;
    private JButton subBTN, backBTN;
    ButtonGroup group;
 
    public BillPaymentGUI (TransactionManager manager, BankAppGUI mainWindow){
        this.manager = manager;
        setTitle("Bills Payment");
        setSize(390, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        
        billLBL = new JLabel("Choose Biller: ");
        billLBL.setLayout(null);
        billLBL.setFont(new Font("Arial", Font.BOLD, 14));
        billLBL.setBounds(20, 70, 340, 30);
        add(billLBL);
        
        
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
        
        backBTN= new JButton("Back");
        backBTN.setLayout(null);
        backBTN.setBounds(20, 40, 80, 30);
        add(backBTN);
        
        backBTN.addActionListener(e -> {
            mainWindow.setVisible(true); 
            dispose();                   
        });
        
        
        meralco = new JRadioButton("Meralco");
        pldt = new JRadioButton("Pldt");
        globe = new JRadioButton("Globe");
        maynilad = new JRadioButton("Maynilad");
        skycable = new JRadioButton("Sky Cable");
        
        meralco.setBounds(20, 90, 340, 30);
        pldt.setBounds(20, 120, 340, 30);
        globe.setBounds(20, 150, 340, 30);
        maynilad.setBounds(20, 180, 340, 30);
        skycable.setBounds(20, 210, 340, 30);
        
        group = new ButtonGroup();
        group.add(meralco);
        group.add(pldt);
        group.add(globe);
        group.add(maynilad);
        group.add(skycable);
        
        add(meralco); 
        add(pldt); 
        add(globe); 
        add(maynilad); 
        add(skycable);
        
        
        
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
                manager.addTransaction("Bills Payment", amount, date, "");
                JOptionPane.showMessageDialog(this, "Paid.");
                    amountFLD.setText("");
                    dateFLD.setText("");
                    meralco.setSelected(false);
                    pldt.setSelected(false);
                    globe.setSelected(false);
                    maynilad.setSelected(false);
                    skycable.setSelected(false);
                    meralco.setSelected(true); 
            }catch(NumberFormatException ex){
             JOptionPane.showMessageDialog(this, "Invalid amount. Enter a number.");
            }
        });
        
        setVisible(true);
    }
}