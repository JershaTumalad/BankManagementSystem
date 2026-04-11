package project;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class GUI1Frame extends JFrame implements ActionListener {
    private JButton btnAdd, btnRemove, btnSearch, btnView, btnExit;
    private JLabel lblTitle;
    AccountFiles files;

    public GUI1Frame(AccountFiles files){
        this.files = files;
        setTitle("Bank Account Management System");
        setSize(450, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        lblTitle = new JLabel("Account Management");
        lblTitle.setBounds(90, 40, 300, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        add(lblTitle);

        btnAdd = createButton("Add Account", 130, 120);
        add(btnAdd);

        btnRemove = createButton("Remove Account", 130, 190);
        add(btnRemove);

        btnSearch = createButton("Search Account", 130, 260);
        add(btnSearch);

        btnView = createButton("View Accounts", 130, 330);
        add(btnView);

        btnExit = new JButton("Exit");
        btnExit.setBounds(170, 400, 100, 35);
        btnExit.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnExit);

        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnExit.addActionListener(this);
    }

    private JButton createButton(String text, int x, int y){
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 180, 50);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btnAdd){
            dispose();
            new AddPage(files).setVisible(true);
        }
        else if(e.getSource()==btnRemove){
            dispose();
            new RemovePage(files).setVisible(true);
        }
        else if(e.getSource()==btnSearch){
            dispose();
            new SearchPage(files).setVisible(true);
        }
        else if(e.getSource()==btnView){
            dispose();
            new ViewAccountsPage(files).setVisible(true);
        }
        else if(e.getSource()==btnExit){
            System.exit(0);
        }
    }
}