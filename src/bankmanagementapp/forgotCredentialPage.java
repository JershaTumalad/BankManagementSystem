
package bankmanagementapp;

import javax.swing.*;
import java.awt.event.*;



public class forgotCredentialPage extends JFrame {
    
    
            JLabel forgotMes;
            JButton btnOpt1,btnOpt2,btnOpt3,homepage;
    
    public forgotCredentialPage(){
        
        setSize(430, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Forgot User-ID / Password");
        
        
        forgotMes = new JLabel ("Recover Your Account ");
        forgotMes.setBounds(50, 110, 400, 30);
        forgotMes.setFont(new java.awt.Font("Segoe UI",java.awt.Font.BOLD, 18));
        add(forgotMes);
        
        btnOpt1 = new JButton ("I forgot my USER-ID");//for user id itowww
        btnOpt1.setBounds(60, 180, 300, 40);
        btnOpt1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(btnOpt1);
        btnOpt1.addActionListener(e ->{
        new forgotPage2();
        this.dispose();
        });
        
          btnOpt2 = new JButton ("I forgot my password"); //for forgot pass nmn here
        btnOpt2.setBounds(60, 240, 300, 40);
        btnOpt2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
       add(btnOpt2);
       btnOpt2.addActionListener(e ->{
        new forgotPage1();
        this.dispose();
        });
       
        btnOpt3 = new JButton ("I forgot my USER-ID & password");
        btnOpt3.setBounds(60, 300, 300, 40);
        btnOpt3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(btnOpt3);  
        btnOpt3.addActionListener(e ->{
        new forgotPage2();
        this.dispose();
        });
        
        
        homepage = new JButton ("Back to Homepage");
            homepage.setBounds(110, 410, 200, 40);
            homepage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            add(homepage); 
            homepage.addActionListener( e-> {
            
            new frontPage().setVisible(true);
            this.dispose();
            
            
            });
        
        
        
    }
    
    
}
