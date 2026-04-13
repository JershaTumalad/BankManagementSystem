package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddPage extends JFrame implements ActionListener {
    private JLabel lblTitle, lblName, lblType, lblAccNo, lblIniBal;
    private JTextField txtName, txtAccNo, txtIniBal;
    private JComboBox<String> cmbType;
    private JButton btnAdd, btnBack;
    AccountFiles files;

    public AddPage(AccountFiles files){
        this.files = files;
        setTitle("Add Account");
        setSize(430, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitle = new JLabel("Add New Account");
        lblTitle.setBounds(0, 50, 430, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        lblName = new JLabel("Full Name:");
        lblName.setBounds(50, 150, 150, 30);
        add(lblName);
        txtName = new JTextField();
        txtName.setBounds(180, 150, 200, 30);
        add(txtName);

        lblType = new JLabel("Account Type:");
        lblType.setBounds(50, 210, 150, 30);
        add(lblType);
        cmbType = new JComboBox<>(new String[]{"Savings", "Current"});
        cmbType.setBounds(180, 210, 200, 30);
        add(cmbType);

        lblAccNo = new JLabel("Account Number:");
        lblAccNo.setBounds(50, 270, 150, 30);
        add(lblAccNo);
        txtAccNo = new JTextField();
        txtAccNo.setBounds(180, 270, 200, 30);
        add(txtAccNo);

        lblIniBal = new JLabel("Initial Balance:");
        lblIniBal.setBounds(50, 330, 150, 30);
        add(lblIniBal);
        txtIniBal = new JTextField();
        txtIniBal.setBounds(180, 330, 200, 30);
        add(txtIniBal);

        btnAdd = new JButton("Save Account");
        btnAdd.setBounds(90, 420, 250, 45);
        add(btnAdd);
        btnBack = new JButton("Back to Menu");
        btnBack.setBounds(90, 480, 250, 45);
        add(btnBack);

        btnAdd.addActionListener(this);
        btnBack.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnBack){
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else if(e.getSource() == btnAdd){
            try {
                String name = txtName.getText().trim();
                String accNo = txtAccNo.getText().trim();
                String balStr = txtIniBal.getText().trim();

                if(name.isEmpty() || accNo.isEmpty() || balStr.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
                    return;
                }
                
                double bal = Double.parseDouble(balStr);
                if(bal < 0) {
                    JOptionPane.showMessageDialog(this, "Balance cannot be negative!");
                    return;
                }

                if(files.addAccount(name, cmbType.getSelectedItem().toString(), accNo, bal)){
                    JOptionPane.showMessageDialog(this, "Account added successfully!");
                    dispose();
                    new GUI1Frame(files).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Account Number already exists!");
                }
            } catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Invalid balance input!");
            }
        }
    }
}