
package bankmanagementapp;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

//email needed


public class forgotPage2 extends JFrame {
    
     ArrayList <accCreationPage> acc;

    
    JLabel Title, desc,email;
    JTextField tfEmail;
    JButton submit, homepage;
    
    public forgotPage2(){
        
      this.acc = accDatabase.acc;
      
        setVisible(true);
        setSize(430, 720);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
       
        
        Title= new JLabel("FORGOT MY ACCOUNT LOGIN");
        add(Title);
        Title.setFont(new java.awt.Font("Segoe UI",java.awt.Font.BOLD, 20));
        Title.setBounds(30, 100, 320, 40);
        
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
        tfEmail.setBounds(110, 230, 220, 35);
        
         submit= new JButton("Submit");
        add(submit);
        submit.setBounds(135, 320, 150, 40);
        submit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        submit.addActionListener(e ->{
        
            String inputEmail = tfEmail.getText();
            boolean found = false;
            
            for(accCreationPage user: acc){
                 
            
            if(user.getEmail().equals(inputEmail)){
                
            found= true;
            break;
            }}
                    
            
            if(found){JOptionPane.showMessageDialog(this, "Email Found!");
                    new dashboard();
                    this.dispose();
            } else{
                    JOptionPane.showMessageDialog(this, "Email not registered!");
                }
        });

            homepage = new JButton ("Back to Homepage");
            homepage.setBounds(110, 450, 200, 40);
            add(homepage); 
            homepage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            homepage.addActionListener( e-> {
            
            new frontPage().setVisible(true);
            this.dispose();
            
            
            });

    

        }
                }
    
