package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI1Frame extends JFrame implements ActionListener {
    private JLabel lblTitle;
    private JButton btnAdd, btnRemove, btnSearch, btnView, btnExit;
    AccountFiles files;

    public GUI1Frame(AccountFiles files){
        this.files = files;
        setTitle("Bank Management System");
        setSize(430, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        lblTitle = new JLabel("Main Menu");
        lblTitle.setBounds(0, 60, 430, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        btnAdd = new JButton("Add Account");
        btnAdd.setBounds(90, 160, 250, 50);
        add(btnAdd);

        btnRemove = new JButton("Remove Account");
        btnRemove.setBounds(90, 230, 250, 50);
        add(btnRemove);

        btnSearch = new JButton("Search Account");
        btnSearch.setBounds(90, 300, 250, 50);
        add(btnSearch);

        btnView = new JButton("View Accounts List");
        btnView.setBounds(90, 370, 250, 50);
        add(btnView);

        btnExit = new JButton("Exit System");
        btnExit.setBounds(140, 550, 150, 40);
        add(btnExit);

        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnExit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnExit){
            int ans = JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if(ans == JOptionPane.YES_OPTION) System.exit(0);
        } else {
            dispose();
            if(e.getSource() == btnAdd) 
                new AddPage(files).setVisible(true);
            else if(e.getSource() == btnRemove) 
                new RemovePage(files).setVisible(true);
            else if(e.getSource() == btnSearch) 
                new SearchPage(files).setVisible(true);
            else if(e.getSource() == btnView) 
                new ViewAccountsPage(files).setVisible(true);
        }
    }
}