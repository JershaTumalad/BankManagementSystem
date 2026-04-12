
package bankmanagementapp;

import javax.swing.*;
import java.awt.event.*;


public class frontPage extends JFrame{
    
    JLabel statement,statemnt1, lbOptions, navTit;
    JButton btnOption1, btnOption2, btnOption3;
    JPanel navbar;
    
      public frontPage(){
          
          setSize(500,500);
          setTitle("Bank Account Management");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLayout(null);
         
          
          
         navbar = new JPanel();
         navbar.setBounds(0, 0, 500, 60);
         navbar.setBackground(new java.awt.Color(0, 102, 204)); // bank blue
         navbar.setLayout(null);
         add(navbar);

        navTit = new JLabel("E-BANK");
        navTit.setForeground(java.awt.Color.WHITE);
        navTit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        navTit.setBounds(20, 15, 200, 30);
        navbar.add(navTit);

      
          statement = new JLabel("WELCOME TO E-BANK MANAGEMENT APP");
          statement.setBounds(50, 70, 400, 40);
          statement.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
          add(statement);
          
          
          statemnt1 = new JLabel("What can I do for you today? ");
          statemnt1.setBounds(50, 110, 300, 30);
          statemnt1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
          add(statemnt1);
          

          
          btnOption1 = new JButton("LOG IN AN ACCOUNT");
          btnOption1.setBounds(100, 180, 300, 45);
          btnOption1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
          add(btnOption1);
          btnOption1.addActionListener( e -> {
          new logInPage();    
          this.dispose();
          
          });
          
            btnOption2 = new JButton("FORGOT USER-ID OR PASSWORD");
          btnOption2.setBounds(100, 240, 300, 45);
           btnOption2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
          add(btnOption2);
          btnOption2.addActionListener( e -> {
          new forgotCredentialPage();
          this.dispose();
          
          });
          
          btnOption3 = new JButton("CREATE AN ACCOUNT");
          btnOption3.setBounds(100, 300, 300, 45);
           btnOption3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
          add(btnOption3);
          btnOption3.addActionListener(e -> {
          new accounts();
          this.dispose();
          
          });
          
          
          
          
          
          
          
      }

  
    
}
