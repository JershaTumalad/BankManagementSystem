
package bankmanagementapp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import BAMS.BankAppGUI;        
import BAMS.accCreationPage;   
import BAMS.accDatabase;      

public class logInPage extends JFrame{
    
    JLabel tit1, userName,pass,navTit;
    JTextField tfUsername, tfPassword;
    JButton btnLog, btnForgot, btnBack;
    
        public logInPage(){
        
        setVisible(true);
        setSize(430, 720);
            setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("WELCOME TO LOGIN PAGE");
        
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(200, 210, 230));
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        
        
         JPanel navbar = new JPanel();
         navbar.setBounds(0, 0, 430, 100);
         navbar.setBackground(theme.Primary); // primary blue
         navbar.setLayout(null);
         add(navbar);

        navTit = new JLabel(" Welcome Back!");
        navTit.setForeground(Color.WHITE);
        navTit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 30));
        navTit.setBounds(50,30, 300, 30);
        navbar.add(navTit);
        
       
        
        userName = new JLabel("User ID: ");
        userName.setBounds(70, 150, 100, 30);
        userName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(userName);
        
         pass = new JLabel("Password: ");
        pass.setBounds(70, 200, 100, 30);
      pass.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(pass);
        
        tfUsername = new JTextField();
        tfUsername.setBounds(170, 150, 180, 35);
         tfUsername.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
         tfUsername.setBorder(null);
        add(tfUsername);
        
         tfPassword = new JTextField();
        tfPassword.setBounds(170, 200, 180, 35);
        tfPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        tfPassword.setBorder(null);
        add(tfPassword);
        
        btnLog = new RoundedButton("LOG IN");
        btnLog.setBounds(140, 290, 150, 40);
         btnLog.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
         btnLog.setBackground(theme.Light_blue);
        add(btnLog);

  
        
        
        btnLog.addActionListener( e -> {
            boolean found = false;
            String id = tfUsername.getText();
            String password = tfPassword.getText();

            for (accCreationPage user : accDatabase.acc){
                if (user.getUserId().equals(id) && user.getPassword().equals(password)){
                    found = true;
                    break;
                }
            }

            if(found){
                new BAMS.BankAppGUI();  
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect user_ID or Password");
            }
        });
        
        
        btnBack= new RoundedButton("HOMEPAGE");
        btnBack.setBounds(140, 340, 150, 40);
         btnBack.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
         btnBack.setBackground(theme.Light_blue);
        add(btnBack);
        btnBack.addActionListener(e -> {
          new frontPage().setVisible(true);
        this.dispose();
        
        });
        
        btnForgot = new RoundedButton("Forgot User_ID/Password");
        btnForgot.setBounds(105, 450, 220, 25);
        btnForgot.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        btnForgot.setBackground(theme.Light_blue);
        add(btnForgot);
        btnForgot.addActionListener(e -> {
        new forgotCredentialPage().setVisible(true);
        this.dispose();
        
        });
        
    }
        
         public class theme {
          public static final Color Primary = new Color (25,42,86);
        public static final Color Light_blue = new Color(0x8bace0);
          public static final Color bg = new Color (230, 235, 245);
          public static final Color text_d = new Color (40,40,40);
         
          
          
      }
}
