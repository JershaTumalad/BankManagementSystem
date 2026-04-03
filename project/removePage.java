package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class removePage extends JFrame implements ActionListener{
    
    private JButton btnRemove, btnBack;
    private JLabel lblTitle, lblaccNo, lblresult;
    private JTextField txtaccNo;
    
    accountFiles files;
    
    removePage(accountFiles files){
        this.files = files;
        
        setSize(360,480);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        lblTitle = new JLabel ("Remove Account");
        lblTitle.setBounds(100, 30, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        add(lblTitle);
        
        lblaccNo = new JLabel ("Account Number");
        lblaccNo.setBounds(30, 79, 147, 50);
        lblaccNo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblaccNo);
        
        txtaccNo = new JTextField();
        txtaccNo.setBounds(147, 90, 180, 30);
        add(txtaccNo);
        
        btnRemove = new JButton ("Remove");
        btnRemove.setBounds(120, 140, 120, 30);
        add(btnRemove);
        
        btnBack = new JButton ("Back");
        btnBack.setBounds(120, 174, 120, 30);
        add(btnBack);
        
        lblresult = new JLabel (" ");
        lblresult.setBounds(30, 224, 300, 30);
        lblresult.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblresult);
        
        btnRemove.addActionListener(this); 
        btnBack.addActionListener(this);    
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnRemove){
            String accNo = txtaccNo.getText().trim();
            boolean removed = files.removeAccount(accNo);
            
            if (removed){
                lblresult.setText("Account " + accNo + " removed.");
            }else{
                lblresult.setText("Account not Found");
            }
            
            
        }
        
            if (e.getSource() == btnBack){
            dispose();
            menuFrame hp = new menuFrame(files);
            hp.setVisible(true);
            
        }
        
    }

}
