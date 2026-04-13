package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchPage extends JFrame implements ActionListener {
    private JTextField txtAccNo;
    private JButton btnSearch, btnBack;
    AccountFiles files;

    public SearchPage(AccountFiles files){
        this.files = files;
        setTitle("Search Account");
        setSize(430, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblTitle = new JLabel("Search Account");
        lblTitle.setBounds(0, 60, 430, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        JLabel lblPrompt = new JLabel("Enter Account Number:");
        lblPrompt.setBounds(50, 160, 200, 30);
        add(lblPrompt);

        txtAccNo = new JTextField();
        txtAccNo.setBounds(50, 200, 330, 40);
        add(txtAccNo);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(90, 280, 250, 50);
        add(btnSearch);

        btnBack = new JButton("Back");
        btnBack.setBounds(90, 350, 250, 50);
        add(btnBack);

        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBack) {
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else if(e.getSource() == btnSearch) {
            String accNo = txtAccNo.getText().trim();
            
            if(!accNo.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this, "Input Number Only!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Account acc = files.searchAccount(accNo);
            if(acc != null) {
                String formattedBalance = String.format("P %,.2f", acc.getBalance());
                JOptionPane.showMessageDialog(this, "Name: " + acc.getName() + "\nBalance: " + formattedBalance);
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!");
            }
        }
    }
}