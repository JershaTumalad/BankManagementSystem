
package bankmanagementapp;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class accounts extends JFrame{
    
   JLabel email, userId, pass, firstName, lastName, midName, mobNum, address, idType, idNum;
    JTextField tfEmail, tfuserId, tfpass, tfFirstName, tfLastName, tfMidName, tfMobNum, tfAddress, tfIdNum;
    JButton submit, logInPage;
    JComboBox cbAccType;
     
    public accounts(){
         
         
         
        setVisible(true);
        setSize(430, 720);
     
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Account Registration");
        setLayout(null);
        
        
        int labelX = 40;
        int fieldX = 160;
        int fieldWidth = 220;
        int fieldHeight = 30;
        int startY = 40; 
        int spacing = 45;

        firstName = new JLabel("First Name:");
        firstName.setBounds(labelX, startY, 110, 20);
        firstName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(firstName);
        
        
        tfFirstName = new JTextField();
        tfFirstName.setBounds(fieldX, startY, fieldWidth, fieldHeight);
        tfFirstName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfFirstName);

        lastName = new JLabel("Last Name:");
        lastName.setBounds(labelX, startY + spacing, 100, 20);
        lastName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(lastName);
        
        
        tfLastName = new JTextField();
        tfLastName.setBounds(fieldX, startY + spacing, fieldWidth, fieldHeight);
        tfLastName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfLastName);

        midName = new JLabel("Middle Name:");
        midName.setBounds(labelX, startY + (spacing * 2), 100, 20);
        midName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(midName);
        
        
        tfMidName = new JTextField();
        tfMidName.setBounds(fieldX, startY + (spacing * 2), fieldWidth, fieldHeight);
        tfMidName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfMidName);

        mobNum = new JLabel("Mobile No.:");
        mobNum.setBounds(labelX, startY + (spacing * 3), 100, 20);
        mobNum.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(mobNum);
        
        
        tfMobNum = new JTextField();
        tfMobNum.setBounds(fieldX, startY + (spacing * 3), fieldWidth, fieldHeight);
        tfMobNum.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfMobNum);

        address = new JLabel("Address:");
        address.setBounds(labelX, startY + (spacing * 4), 100, 20);
        address.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(address);
        
        
        tfAddress = new JTextField();
        tfAddress.setBounds(fieldX, startY + (spacing * 4), fieldWidth, fieldHeight);
        tfAddress.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfAddress);

        idType = new JLabel("Account Type:");
        idType.setBounds(labelX, startY + (spacing * 5), 100, 20);
        idType.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(idType);
        
      String acc [] = {
        "SAVINGS ACCOUNT",
        "CHECKING ACCOUNT"
      };
      
        cbAccType = new JComboBox<>(acc);
      cbAccType.setBounds(fieldX, startY + (spacing * 5), fieldWidth, fieldHeight);
        cbAccType.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
      add(cbAccType);

        idNum = new JLabel("Birthdate:");
        idNum.setBounds(labelX, startY + (spacing * 6), 100, 20);
        idNum.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(idNum);
        
        
//        tfIdNum = new JTextField();
//        tfIdNum.setBounds(fieldX, startY + (spacing * 6), fieldWidth, fieldHeight);
//        tfIdNum.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
//        add(tfIdNum);


        String[] months = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
        JComboBox<String> cbMonth = new JComboBox<>(months);
        cbMonth.setBounds(fieldX, startY + (spacing * 6), 80, 30);
        add(cbMonth);


        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }
        JComboBox<String> cbDay = new JComboBox<>(days);
        cbDay.setBounds(fieldX + 90, startY + (spacing * 6), 60, 30);
        add(cbDay);


        String[] years = new String[100];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        JComboBox<String> cbYear = new JComboBox<>(years);
        cbYear.setBounds(fieldX + 160, startY + (spacing * 6), 80, 30);
        add(cbYear);



        email = new JLabel("Email:");
        email.setBounds(labelX, startY + (spacing * 7), 100, 20);
        email.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(email);
        
        
        tfEmail = new JTextField();
        tfEmail.setBounds(fieldX, startY + (spacing * 7), fieldWidth, fieldHeight);
        tfEmail.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfEmail);

        userId = new JLabel("User ID:");
        userId.setBounds(labelX, startY + (spacing * 8), 100, 20);
        userId.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(userId);
        
        
        tfuserId = new JTextField();
        tfuserId.setBounds(fieldX, startY + (spacing * 8), fieldWidth, fieldHeight);
        tfuserId.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfuserId);

        pass = new JLabel("Password:");
        pass.setBounds(labelX, startY + (spacing * 9), 100, 20);
        pass.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(pass);
        
        tfpass = new JTextField(); 
        tfpass.setBounds(fieldX, startY + (spacing * 9), fieldWidth, fieldHeight);
        tfpass.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfpass);

        submit = new JButton("CREATE AN ACCOUNT");
        submit.setBounds(100, 580, 230, 35);
        submit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(submit);

        submit.addActionListener(e -> {
        
            
            //for log in to sya to proceed sa dashboard
            
            
            //THIS IS FOR THE EMAIL INFO( FOR retrieving accounts )
            String inputedUserId = tfuserId.getText();
             String inputedPass = tfpass.getText();
        String emailInputed = tfEmail.getText();
        
        if (emailInputed.isEmpty() || inputedUserId.isEmpty() || inputedPass.isEmpty()){
            JOptionPane.showMessageDialog(this,"ERROR!!", "PLEASE FILL IN ALL REQUIRED FIELDS", JOptionPane.ERROR_MESSAGE);
            return;
        }
         
            accCreationPage newAcc = new accCreationPage (inputedUserId,emailInputed, inputedPass);
            accDatabase.acc.add(newAcc);
      
        JOptionPane.showMessageDialog(this, "Account Created Successfully!");
        new frontPage().setVisible(true);
        this.dispose();
        
        });
        
        logInPage = new JButton("BACK TO LOGIN PAGE");
        logInPage.setBounds(100, 630, 230, 35);
        add(logInPage);
        logInPage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        logInPage.addActionListener(e ->{
        
            new logInPage();
            this.dispose();
        
        
        
        });
        
        
        
       
        
    }
}
