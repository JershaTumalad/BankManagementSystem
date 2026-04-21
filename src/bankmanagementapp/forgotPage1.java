
package bankmanagementapp;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;



//user id needed



public class forgotPage1 extends JFrame {
    
    ArrayList <accCreationPage> acc;
    
   
    
    JLabel Title, desc,Userid;
    JTextField tfUserid;
    JButton submit,homepage;
    
    public forgotPage1(){
        
        this.acc = accDatabase.acc;
        
        
        setVisible(true);
        setSize(430, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        Title= new JLabel("FORGOT MY PASSWORD");
        Title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        add(Title);
        Title.setBounds(30, 100, 300, 40);
        
        desc= new JLabel("Enter your registered USER_ID ");
        desc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        add(desc);
        desc.setBounds(50, 160, 400, 30);
        
         Userid= new JLabel("User-ID: ");
         Userid.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(Userid);
        Userid.setBounds(60, 230, 100, 30);
        
        tfUserid= new JTextField();
          tfUserid.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(tfUserid);
        tfUserid.setBounds(120, 230, 220, 35);
        
         submit= new JButton("Submit");
           submit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(submit);
        submit.setBounds(135, 320, 150, 40);
        submit.addActionListener(e ->{
        
            String inputUserId = tfUserid.getText();
            boolean found = false;
            
            for(accCreationPage user: acc){
                 
            
            if(user.getUserId().equals(inputUserId)){
                
            found= true;
            break;
            }}
                    
            
            if(found){JOptionPane.showMessageDialog(this, "USER- ID Found!");
                    new dashboard();
                    this.dispose();
            } else{
                    JOptionPane.showMessageDialog(this, "USER_ID not registered!");
                }
            
        
        
        
        
        });
        
        homepage = new JButton ("Back to Homepage");
            homepage.setBounds(110, 450, 200, 40);
            homepage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            add(homepage); 
            homepage.addActionListener( e-> {
            
            new frontPage().setVisible(true);
            this.dispose();
            
            
            });
        

    
 


  }
    

}

