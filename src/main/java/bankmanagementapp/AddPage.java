package bankmanagementapp;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class AddPage extends JFrame implements ActionListener{

    private JTextField txtLastName, txtFirstName, txtMiddleName, txtAccNo, txtIniBal;
    private JLabel lblLName, lblFName, lblMName, lblAccType, lblAccNo, lblIniBal, lblDes, lblStateBank;
    private JPanel headerPanel, personalPanel, accountPanel;
    private JComboBox<String> cmbType;
    private JButton btnSave, btnBack;
    private int userId;
    AccountFiles files;

    //Customized Colors
    Color mainColor = new Color(25, 48, 90);
    Color buttonColor = new Color (244, 246, 252);
    Color mainButtonColor = new Color(139, 172, 224);
    Color txtFieldColor = new Color(245, 245, 245);
    Color backgroundColor = new Color(200, 210, 230);

    private String generateAccountNo(){
        
        Random random = new Random();
        long accountNo = random.nextLong(1000000000L,10000000000L);
        
        return String.valueOf(accountNo);
    }

    public AddPage(AccountFiles files, int userId){
        this.files = files;
        this.userId = userId;

        
         // Basic window configuration
        setTitle("Bank Account Management");
        setSize(430, 720);
        setLayout(null);
        getContentPane().setBackground(backgroundColor); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Header Panel with Bank Name and Page Name
        headerPanel = new JPanel();
        headerPanel.setBackground(mainColor);
        headerPanel.setBounds(0, 0, 430, 90); 
        headerPanel.setLayout(null);
        add(headerPanel);
        
        lblStateBank = new JLabel("STATE Bank");
        lblStateBank.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblStateBank.setForeground(new Color(170, 190, 220)); 
        lblStateBank.setBounds(30, 20, 350, 25);
        headerPanel.add(lblStateBank);

        JLabel lblTitle = new JLabel("Add Account");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(30, 48, 250, 30);
        headerPanel.add(lblTitle);

        //Personal Information Panel
        personalPanel = new JPanel();
        personalPanel.setBackground(Color.WHITE);
        personalPanel.setBounds(25, 110, 370, 201);
        personalPanel.setLayout(null);
        add(personalPanel);
        
        //Account Details Panel
        accountPanel = new JPanel();
        accountPanel.setBackground(Color.WHITE);
        accountPanel.setBounds(25, 330, 370, 282);
        accountPanel.setLayout(null);
        add(accountPanel);

        lblDes = new JLabel ("PERSONAL INFORMATION");
        lblDes.setBounds(20, 10, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDes.setForeground(Color.DARK_GRAY);
        personalPanel.add(lblDes);
        
        lblLName = new JLabel("LAST NAME");
        lblLName.setBounds(20, 45, 120, 20);
        lblLName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblLName.setForeground(Color.GRAY);
        personalPanel.add(lblLName);

        txtLastName = new JTextField();
        txtLastName.setBackground(txtFieldColor);
        txtLastName.setBounds(20, 75, 160, 30);
        personalPanel.add(txtLastName);

        lblFName = new JLabel("FIRST NAME");
        lblFName.setBounds(190, 45, 120, 20);
        lblFName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFName.setForeground(Color.GRAY);
        personalPanel.add(lblFName);

        txtFirstName = new JTextField();
        txtFirstName.setBackground(txtFieldColor);
        txtFirstName.setBounds(190, 75, 160, 30);
        personalPanel.add(txtFirstName);

        lblMName = new JLabel("MIDDLE NAME ");
        lblMName.setBounds(20, 125, 300, 20);
        lblMName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMName.setForeground(Color.GRAY);
        personalPanel.add(lblMName);

        txtMiddleName = new JTextField();
        txtMiddleName.setBackground(txtFieldColor);
        txtMiddleName.setBounds(20, 155, 330, 30);
        personalPanel.add(txtMiddleName);

        lblDes = new JLabel ("ACCOUNT DETAILS");
        lblDes.setBounds(20, 10, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDes.setForeground(Color.DARK_GRAY);
        accountPanel.add(lblDes);
        
        lblAccType = new JLabel("ACCOUNT TYPE");
        lblAccType.setBounds(20, 45, 150, 20);
        lblAccType.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAccType.setForeground(Color.GRAY);
        accountPanel.add(lblAccType);

        
        // Dropdown for choosing Savings or Current account type
       cmbType = new JComboBox<>(new String[]{"SAVINGS ACCOUNT", "CHECKING ACCOUNT"});
        cmbType.setBounds(20, 75, 330, 30);
        accountPanel.add(cmbType);

        lblAccNo = new JLabel("ACCOUNT NUMBER");
        lblAccNo.setBounds(20, 125, 150, 20);
        lblAccNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAccNo.setForeground(Color.GRAY);
        accountPanel.add(lblAccNo);

        
        // Auto-generated account number — read-only, user cannot edit it
        txtAccNo = new JTextField();
        txtAccNo.setBackground(txtFieldColor);
        txtAccNo.setBounds(20, 155, 330, 30);
        txtAccNo.setText(generateAccountNo());
        txtAccNo.setEditable(false);
        accountPanel.add(txtAccNo);

        lblIniBal = new JLabel("INITIAL BALANCE");
        lblIniBal.setBounds(20, 205, 150, 20);
        lblIniBal.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblIniBal.setForeground(Color.GRAY);
        accountPanel.add(lblIniBal);

        txtIniBal = new JTextField();
        txtIniBal.setBackground(txtFieldColor);
        txtIniBal.setBounds(20, 235, 330, 30);
        accountPanel.add(txtIniBal);
        
        //Save Account Button
        btnSave = new JButton("SAVE ACCOUNT");
        btnSave.setBorderPainted(false);
        btnSave.setBackground(mainColor);
        btnSave.setForeground(buttonColor);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSave.setBounds(192, 625, 192, 45);
        add(btnSave);
        
        //Back button going to Account Management Page
        btnBack = new JButton("BACK");
        btnBack.setBorderPainted(false);
        btnBack.setBackground(mainButtonColor);
        btnBack.setForeground(mainColor);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setBounds(25, 625, 140, 45);
        add(btnBack);
        
        btnSave.addActionListener(this);
        btnBack.addActionListener(this);
        
    }

    
     //  Handles button clicks.
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnBack){
            dispose();
            new dashboardAccMng(files, userId).setVisible(true);
        }
        else if(e.getSource() == btnSave){
            try{
                String last = txtLastName.getText().trim().toUpperCase();
                String first = txtFirstName.getText().trim().toUpperCase();
                String middle = txtMiddleName.getText().trim().toUpperCase();
                String accNo = txtAccNo.getText().trim();
                String balText = txtIniBal.getText().trim();

                if(last.isEmpty() || first.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Last Name and First Name are required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(!last.matches("[a-zA-Z ]+") ||
                   !first.matches("[a-zA-Z ]+") || !middle.matches("[a-zA-Z ]+") ){
                    JOptionPane.showMessageDialog(this, "Invalid Input! Names must contain letters only.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(accNo.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Account Number is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!accNo.matches("\\d+")){
                    JOptionPane.showMessageDialog(this, "Invalid Input! Account Number must contain numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(balText.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Initial Balance is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double balance = Double.parseDouble(balText);

                if(balance < 0){
                    JOptionPane.showMessageDialog(this, "Invalid Input! Balance cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String fullName = last + ", " + first + " " + middle;

                if(files.addAccount(userId, fullName,
                        (String) cmbType.getSelectedItem(),
                        accNo,
                        balance)){

                    JOptionPane.showMessageDialog(this, "Account Saved.");
                    dispose();
                    new dashboardAccMng(files, userId).setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "Account Number already exists.");
                }

            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Invalid balance input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

}