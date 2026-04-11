package project;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class SearchPage extends JFrame implements ActionListener {
    private JTextField txtAccNo;
    private JButton btnSearch, btnBack, btnExit;
    AccountFiles files;

    public SearchPage(AccountFiles files){
        this.files = files;
        setTitle("Search Account");
        setSize(450, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblTitle = new JLabel("Search Account");
        lblTitle.setBounds(130, 40, 250, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle);

        JLabel lblAccNo = new JLabel("Account Number:");
        lblAccNo.setBounds(50, 120, 120, 30);
        lblAccNo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblAccNo);

        txtAccNo = new JTextField();
        txtAccNo.setBounds(180, 120, 200, 30);
        add(txtAccNo);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(90, 200, 100, 40);
        btnSearch.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnSearch);

        btnBack = new JButton("Back");
        btnBack.setBounds(230, 200, 100, 40);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnBack);

        btnExit = new JButton("Exit");
        btnExit.setBounds(160, 260, 100, 35);
        btnExit.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnExit);

        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btnSearch){
            String accNo = txtAccNo.getText().trim();
            Account acc = files.searchAccount(accNo);
            if(acc != null){
                JOptionPane.showMessageDialog(this, "Found: " + acc.getName() + "\nBalance: " + acc.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(e.getSource()==btnBack){
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else if(e.getSource()==btnExit){
            System.exit(0);
        }
    }
}