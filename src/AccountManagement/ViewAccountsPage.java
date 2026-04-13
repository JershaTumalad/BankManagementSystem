package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewAccountsPage extends JFrame implements ActionListener {
    private JButton btnBack;
    AccountFiles files;

    public ViewAccountsPage(AccountFiles files){
        this.files = files;
        setTitle("View Accounts");
        setSize(430, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblTitle = new JLabel("All Accounts");
        lblTitle.setBounds(0, 30, 430, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        String[] columnNames = {"Acc No", "Name", "Type", "Balance"};
        JTable table = new JTable(files.getAccountsData(), columnNames);
        table.setEnabled(false);
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(25, 80, 380, 500);
        add(sp);

        btnBack = new JButton("Back to Menu");
        btnBack.setBounds(90, 600, 250, 45);
        btnBack.addActionListener(this);
        add(btnBack);
    }

    public void actionPerformed(ActionEvent e){
        dispose();
        new GUI1Frame(files).setVisible(true);
    }
}