
package bankmanagementapp;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class logInPage extends JFrame{
    
    JLabel tit1, userName,pass;
    JTextField tfUsername, tfPassword;
    JButton btnLog, btnForgot, btnBack;
    
        public logInPage(){
        
        setVisible(true);
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("WELCOME TO LOGIN PAGE");
        


        
        
        tit1 = new JLabel("Welcome Back!");
        tit1.setBounds(50, 40, 200, 40);
        tit1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 25));
        add(tit1);
        
        userName = new JLabel("User ID: ");
        userName.setBounds(90, 120, 100, 30);
        userName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(userName);
        
         pass = new JLabel("Password: ");
        pass.setBounds(90, 170, 100, 30);
      pass.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(pass);
        
        tfUsername = new JTextField();
        tfUsername.setBounds(190, 120, 200, 35);
         tfUsername.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfUsername);
        
         tfPassword = new JTextField();
        tfPassword.setBounds(190, 170, 200, 35);
        tfPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfPassword);
        
        btnLog = new JButton("LOG IN");
        btnLog.setBounds(175, 240, 150, 40);
         btnLog.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(btnLog);

  
        
        
        btnLog.addActionListener( e -> {
                 
                boolean found= false;

                    String id = tfUsername.getText();
                    String password = tfPassword.getText();
                for (accCreationPage user : accDatabase.acc){
                    if (user.getUserId().equals(id) && user.getPassword().equals(password)){
                        found = true;
                       
                        break;
                        
                    }
                   
                }
                 if(found){
                         new dashboard();
                         this.dispose();;
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Incorrect UserId or Password");
                    }
        
        
        
        });
        
        
        btnBack= new JButton("HOMEPAGE");
        btnBack.setBounds(175, 290, 150, 40);
         btnBack.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(btnBack);
        btnBack.addActionListener(e -> {
          new frontPage().setVisible(true);
        this.dispose();
        
        });
        
        btnForgot = new JButton("Forgot User-Id/Password");
        btnForgot.setBounds(140, 350, 220, 25);
        btnForgot.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(btnForgot);
        btnForgot.addActionListener(e -> {
        new forgotCredentialPage().setVisible(true);
        this.dispose();
        
        });
        
    }
}
