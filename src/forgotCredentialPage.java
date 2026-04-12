
package bankmanagementapp;

import javax.swing.*;
import java.awt.event.*;



public class forgotCredentialPage extends JFrame {
    
    
            JLabel forgotMes;
            JButton btnOpt1,btnOpt2,btnOpt3,homepage;
    
    public forgotCredentialPage(){
        
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Forgot User-ID / Password");
        
        
        forgotMes = new JLabel ("Recover your account: ");
        forgotMes.setBounds(50, 60, 400, 30);
        forgotMes.setFont(new java.awt.Font("Segoe UI",java.awt.Font.BOLD, 14));
        add(forgotMes);
        
        btnOpt1 = new JButton ("I forgot my USER-ID");//for user id itowww
        btnOpt1.setBounds(100, 130, 300, 40);
        btnOpt1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(btnOpt1);
        btnOpt1.addActionListener(e ->{
        new forgotPage2();
        this.dispose();
        });
        
          btnOpt2 = new JButton ("I forgot my password"); //for forgot pass nmn here
        btnOpt2.setBounds(100, 190, 300, 40);
        btnOpt2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
       add(btnOpt2);
       btnOpt2.addActionListener(e ->{
        new forgotPage1();
        this.dispose();
        });
       
        btnOpt3 = new JButton ("I forgot my USER-ID & password");
        btnOpt3.setBounds(100, 250, 300, 40);
        btnOpt3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(btnOpt3);  
        btnOpt3.addActionListener(e ->{
        new forgotPage2();
        this.dispose();
        });
        
        
        homepage = new JButton ("Back to Homepage");
            homepage.setBounds(150, 360, 200, 40);
            homepage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            add(homepage); 
            homepage.addActionListener( e-> {
            
            new frontPage().setVisible(true);
            this.dispose();
            
            
            });
        
        
        
    }
    
    
}
