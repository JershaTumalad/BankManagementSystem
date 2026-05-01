
package bankmanagementapp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

//email needed


public class forgotPage2 extends JFrame {
    
     ArrayList <accCreationPage> acc;

    
    JLabel Title, desc,email, navTit;
    JTextField tfEmail;
    JButton submit, homepage;
    JPanel navbar;
    
    public forgotPage2(){
        
      this.acc = accDatabase.acc;
      
        setVisible(true);
        setSize(430, 720);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
       
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(200, 210, 230));
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        
        
        
        navbar = new JPanel();
         navbar.setBounds(0, 0, 430, 100);
         navbar.setBackground(theme.Primary); // primary blue
         navbar.setLayout(null);
         add(navbar);
         
        navTit = new JLabel("FORGOT MY ACCOUNT LOGIN");
        navTit.setForeground(Color.WHITE);
        navTit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        navTit.setBounds(50, 25, 300, 30);
        navbar.add(navTit);
        
        desc= new JLabel("Enter your registered email address");
        desc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        add(desc);
        desc.setBounds(50, 160, 400, 30);
        
         email= new JLabel("Email: ");
         email.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(email);
        email.setBounds(60, 230, 100, 30);
        
        tfEmail= new JTextField();
        add(tfEmail);
        tfEmail.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        tfEmail.setBorder(null);
        tfEmail.setBounds(110, 230, 220, 35);
        
         submit= new RoundedButton("Submit");
        submit.setBounds(135, 320, 150, 40);
        submit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
         submit.setBackground(theme.Light_blue);
        add(submit);
        submit.addActionListener(e ->{
        
            String inputEmail = tfEmail.getText();
            boolean found = false;
            
            for(accCreationPage user: acc){
                 
            
            if(user.getEmail().equals(inputEmail)){
                
            found= true;
            break;
            }}
                    
            
            if(found){JOptionPane.showMessageDialog(this, "Email Found!");
                    new logInPage();
                    this.dispose();
            } else{
                    JOptionPane.showMessageDialog(this, "Email not registered!");
                }
        });

            homepage = new RoundedButton ("Back to Homepage");
            homepage.setBounds(110, 450, 200, 40);
            homepage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
             homepage.setBackground(theme.Light_blue);
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
    
