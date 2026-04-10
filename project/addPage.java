package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class addPage extends JFrame implements ActionListener{
    
    private JButton btnAdd, btnBack;
    private JLabel lblTitle, lblName, lblaccType, lblaccNo, lbliniBal, lblResult;
    private JTextField txtName, txtaccType, txtaccNo, txtiniBal;
    
    accountFiles files;
    
    addPage(accountFiles files){
        this.files = files;
        
        setSize(360,453);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        lblTitle = new JLabel ("Add Account");
        lblTitle.setBounds(120, 30, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        add(lblTitle);
        
        lblName = new JLabel ("Full Name");
        lblName.setBounds(30, 70, 147, 50);
        lblName.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(147, 81, 180, 30);
        add(txtName);
        
        lblaccType = new JLabel ("Account Type");
        lblaccType.setBounds(30, 120, 144, 50);
        lblaccType.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblaccType);
        
        txtaccType = new JTextField();
        txtaccType.setBounds(147, 131, 180, 30);
        add(txtaccType);
        
        lblaccNo = new JLabel ("Account Number");
        lblaccNo.setBounds(30, 170, 144, 50);
        lblaccNo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblaccNo);
        
        txtaccNo = new JTextField();
        txtaccNo.setBounds(147, 181, 180, 30);
        add(txtaccNo);
        
        lbliniBal = new JLabel ("Initial Balance (P)");
        lbliniBal.setBounds(30, 220, 144, 50);
        lbliniBal.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lbliniBal);
        
        txtiniBal = new JTextField();
        txtiniBal.setBounds(147, 231, 180, 30);
        add(txtiniBal);
        
        lblResult = new JLabel (" ");
        lblResult.setBounds(90, 359, 300, 50);
        lblResult.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblResult);
        
        
        btnAdd = new JButton ("Add");
        btnAdd.setBounds(120, 275, 120, 30);
        add(btnAdd);
        
        btnBack = new JButton ("Back");
        btnBack.setBounds(120, 309, 120, 30);
        add(btnBack);
        
        btnAdd.addActionListener(this);    
        btnBack.addActionListener(this);    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd){
            try{
                String accNo = txtaccNo.getText().trim();
                String name = txtName.getText().trim();
                String type = txtaccType.getText().trim();
                double balance = Double.parseDouble(txtiniBal.getText());
            
                files.addAccount(name, type, accNo,balance);
                
                lblResult.setText("               Account added."); 
            }catch (Exception x){
                lblResult.setText("Invalid Input. Please try again.");
            }
        }
        
        if (e.getSource() == btnBack){
            dispose();
            menuFrame hp = new menuFrame(files);
            hp.setVisible(true);
            
        }
        
    }

}
