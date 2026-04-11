package project;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class AddPage extends JFrame implements ActionListener {
    private JLabel lblTitle, lblName, lblAccNo, lblIniBal, lblType;
    private JTextField txtName, txtAccNo, txtIniBal;
    private JComboBox<String> cmbType;
    private JButton btnAdd, btnBack, btnExit;
    AccountFiles files;

    public AddPage(AccountFiles files){
        this.files = files;
        setTitle("Add Account");
        setSize(450, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        lblTitle = new JLabel("Add Account");
        lblTitle.setBounds(150, 40, 200, 30); 
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24)); 
        add(lblTitle);

        lblName = new JLabel("Full Name:"); 
        lblName.setBounds(50, 110, 120, 30); lblName.setFont(new Font("Arial", Font.PLAIN, 14)); 
        add(lblName);
        
        txtName = new JTextField(); 
        txtName.setBounds(180, 110, 200, 30); 
        add(txtName);

        lblType = new JLabel("Account Type:"); 
        lblType.setBounds(50, 160, 120, 30); 
        lblType.setFont(new Font("Arial", Font.PLAIN, 14)); 
        add(lblType);
        
        cmbType = new JComboBox<>(new String[]{"Savings","Current"}); 
        cmbType.setBounds(180, 160, 200, 30); 
        add(cmbType);

        lblAccNo = new JLabel("Account Number:");
        lblAccNo.setBounds(50, 210, 120, 30); 
        lblAccNo.setFont(new Font("Arial", Font.PLAIN, 14)); 
        add(lblAccNo);
        
        txtAccNo = new JTextField(); 
        txtAccNo.setBounds(180, 210, 200, 30); 
        add(txtAccNo);

        lblIniBal = new JLabel("Initial Balance:"); 
        lblIniBal.setBounds(50, 260, 120, 30); 
        lblIniBal.setFont(new Font("Arial", Font.PLAIN, 14)); 
        add(lblIniBal);
        
        txtIniBal = new JTextField(); 
        txtIniBal.setBounds(180, 260, 200, 30);
        add(txtIniBal);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(90, 330, 100, 40);
        btnAdd.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnAdd);

        btnBack = new JButton("Back");
        btnBack.setBounds(230, 330, 100, 40);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnBack);

        btnExit = new JButton("Exit");
        btnExit.setBounds(160, 390, 100, 35);
        btnExit.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnExit);

        btnAdd.addActionListener(this); 
        btnBack.addActionListener(this); 
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btnAdd){
            try{
                String name = txtName.getText().trim();
                String accNo = txtAccNo.getText().trim();
                String type = cmbType.getSelectedItem().toString();
                double balance = Double.parseDouble(txtIniBal.getText());

                if(balance < 0){ JOptionPane.showMessageDialog(this,"Balance cannot be negative!","Error",JOptionPane.ERROR_MESSAGE); return; }

                if(!files.addAccount(name,type,accNo,balance)){
                    JOptionPane.showMessageDialog(this,"Account already exists!","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,"Account successfully added!","Success",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else if(e.getSource()==btnBack) { 
            dispose(); 
            new GUI1Frame(files).setVisible(true); 
        } else if(e.getSource()==btnExit){ 
            System.exit(0); 
        }
    }
}