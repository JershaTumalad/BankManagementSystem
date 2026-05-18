
package bankmanagementapp;

import java.awt.*;
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
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(200, 210, 230));
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        
        
        forgotMes = new JLabel ("ACCOUNT RECOVERY");
        forgotMes.setBounds(50, 110, 400, 30);
        forgotMes.setFont(new java.awt.Font("Segoe UI",java.awt.Font.BOLD, 18));
        add(forgotMes);
        
        btnOpt1 = new RoundedButton ("I forgot my USER-ID");//for user id itowww
        btnOpt1.setBounds(60, 180, 300, 40);
        btnOpt1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        btnOpt1.setBackground(theme.Light_blue);
        add(btnOpt1);
        btnOpt1.addActionListener(e ->{
        new forgotPage2();
        this.dispose();
        });
        
         btnOpt2 = new RoundedButton ("I forgot my password"); //for forgot pass nmn here
        btnOpt2.setBounds(60, 240, 300, 40);
        btnOpt2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        btnOpt2.setBackground(theme.Light_blue);
       add(btnOpt2);
       btnOpt2.addActionListener(e ->{
        new forgotPage1();
        this.dispose();
        });
       
        btnOpt3 = new RoundedButton ("I forgot my USER-ID & password");
        btnOpt3.setBounds(60, 300, 300, 40);
        btnOpt3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        btnOpt3.setBackground(theme.Light_blue);
        add(btnOpt3);  
        btnOpt3.addActionListener(e ->{
        new forgotPage2();
        this.dispose();
        });
        
        
        homepage = new JButton ("Back to Homepage");
            homepage.setBounds(110, 410, 200, 40);
            homepage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            homepage.setBackground(theme.Light_blue);
            homepage.setBorderPainted(false); //removes the outline
            homepage.setBorder(null);
            add(homepage); 
            homepage.addActionListener( e-> {
            
            new frontPage().setVisible(true);
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
