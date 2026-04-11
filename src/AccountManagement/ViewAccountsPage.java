package project;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class ViewAccountsPage extends JFrame implements ActionListener {
    private JButton btnBack, btnExit;
    AccountFiles files;

    public ViewAccountsPage(AccountFiles files){
        this.files = files;
        setTitle("View Accounts");
        setSize(550, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblTitle = new JLabel("View Accounts"); 
        lblTitle.setBounds(200, 30, 250, 30); 
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24)); 
        add(lblTitle);

        String[] columnNames = {"Account No", "Name", "Type", "Balance"};
        JTable table = new JTable(files.getAccountsData(), columnNames);
        table.setFont(new Font("Arial", Font.PLAIN, 14)); 
        table.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(table); 
        scrollPane.setBounds(30, 80, 480, 400); 
        add(scrollPane);

        btnBack = new JButton("Back");
        btnBack.setBounds(150, 500, 100, 40); 
        add(btnBack);

        btnExit = new JButton("Exit");
        btnExit.setBounds(300, 500, 100, 40); 
        add(btnExit);
        
        btnBack.addActionListener(this); 
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btnBack){ 
            dispose(); 
            new GUI1Frame(files).setVisible(true); 
        } else if(e.getSource()==btnExit){ 
            System.exit(0); 
        }
    }
}