package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddPage extends JFrame implements ActionListener {

    private JTextField txtLast, txtFirst, txtMiddle, txtAccNo, txtIniBal;
    private JComboBox<String> cmbType;
    private JButton btnAdd, btnBack;
    AccountFiles files;

    Color btnColor = new Color(130, 160, 210);

    public AddPage(AccountFiles files){
        this.files = files;

        setSize(430, 720);
        setLayout(null);
        getContentPane().setBackground(new Color(180, 190, 210)); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel header = new JPanel();
        header.setBackground(new Color(25, 45, 85));
        header.setBounds(0, 0, 430, 80);
        header.setLayout(null);
        add(header);

        JLabel lblTitle = new JLabel("New Account");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(30, 25, 250, 30);
        header.add(lblTitle);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(25, 100, 370, 520);
        card.setLayout(null);
        add(card);

        Font f14 = new Font("Segoe UI", Font.PLAIN, 13);

        JLabel l1 = new JLabel("Last Name");
        l1.setBounds(20, 15, 150, 20);
        l1.setFont(f14);
        card.add(l1);

        txtLast = new JTextField();
        txtLast.setBounds(20, 35, 330, 30);
        card.add(txtLast);

        JLabel l2 = new JLabel("First Name");
        l2.setBounds(20, 70, 150, 20);
        l2.setFont(f14);
        card.add(l2);

        txtFirst = new JTextField();
        txtFirst.setBounds(20, 90, 330, 30);
        card.add(txtFirst);

        JLabel l3 = new JLabel("Middle Name");
        l3.setBounds(20, 125, 150, 20);
        l3.setFont(f14);
        card.add(l3);

        txtMiddle = new JTextField();
        txtMiddle.setBounds(20, 145, 330, 30);
        card.add(txtMiddle);

        JLabel l4 = new JLabel("Account Type");
        l4.setBounds(20, 180, 150, 20);
        l4.setFont(f14);
        card.add(l4);

        cmbType = new JComboBox<>(new String[]{"Savings", "Current"});
        cmbType.setBounds(20, 200, 330, 30);
        card.add(cmbType);

        JLabel l5 = new JLabel("Account Number");
        l5.setBounds(20, 235, 150, 20);
        l5.setFont(f14);
        card.add(l5);

        txtAccNo = new JTextField();
        txtAccNo.setBounds(20, 255, 330, 30);
        card.add(txtAccNo);

        JLabel l6 = new JLabel("Initial Balance");
        l6.setBounds(20, 290, 150, 20);
        l6.setFont(f14);
        card.add(l6);

        txtIniBal = new JTextField();
        txtIniBal.setBounds(20, 310, 330, 30);
        card.add(txtIniBal);

        btnAdd = new JButton("Save");
        btnAdd.setBounds(20, 380, 155, 40);
        btnAdd.setBackground(btnColor);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        card.add(btnAdd);

        btnBack = new JButton("Cancel");
        btnBack.setBounds(195, 380, 155, 40);
        btnBack.setBackground(btnColor); 
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setFocusPainted(false);
        card.add(btnBack);

        btnAdd.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnBack){
            dispose();
            new GUI1Frame(files).setVisible(true);
        }
        else if(e.getSource() == btnAdd){
            try{
                String last = txtLast.getText().trim();
                String first = txtFirst.getText().trim();
                String middle = txtMiddle.getText().trim();
                String accNo = txtAccNo.getText().trim();
                String balText = txtIniBal.getText().trim();

                if(last.isEmpty() || first.isEmpty() || middle.isEmpty()){
                    JOptionPane.showMessageDialog(this, "All name fields are required!");
                    return;
                }

<<<<<<< Updated upstream
                if(!last.matches("[a-zA-Z ]+") ||
                   !first.matches("[a-zA-Z ]+") ||
                   !middle.matches("[a-zA-Z ]+")){
                    JOptionPane.showMessageDialog(this, "Names must contain letters only!");
                    return;
                }

                if(accNo.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Account Number is required!");
                    return;
                }

                if(balText.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Initial Balance is required!");
                    return;
                }

                double balance = Double.parseDouble(balText);

                if(balance < 0){
                    JOptionPane.showMessageDialog(this, "Balance cannot be negative!");
                    return;
                }

                String fullName = last + ", " + first + " " + middle;

                if(files.addAccount(fullName,
                        (String) cmbType.getSelectedItem(),
                        accNo,
                        balance)){

                    JOptionPane.showMessageDialog(this, "Account Saved!");
                    dispose();
                    new GUI1Frame(files).setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "Account Number already exists!");
                }

            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Invalid balance input!");
=======
            double amount = Double.parseDouble(bal);
            String name = last + ", " + first + " " + txtMiddle.getText().trim();
            if (files.addAccount(name, (String) cbType.getSelectedItem(), acc, amount)) {
                JOptionPane.showMessageDialog(this, "Account Created Successfully!");
                dispose();
                new GUI1Frame(files).setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Account Number already exists!");
>>>>>>> Stashed changes
            }
        }
    }
}