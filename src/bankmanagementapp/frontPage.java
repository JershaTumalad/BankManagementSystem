package bankmanagementapp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class frontPage extends JFrame {

    JLabel statement, statemnt1, navTit;
    JButton btnOption1, btnOption2, btnOption3;
    JPanel navbar;

    public frontPage() {
        setSize(430, 720);
        setLocationRelativeTo(null);
        setTitle("Bank Account Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(200, 210, 230));
        setContentPane(mainPanel);

        JPanel c = new JPanel();
        c.setBackground(Color.WHITE);
        c.setBounds(30, 120, 360, 450);
        c.setLayout(null);
        c.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        mainPanel.add(c);

        navbar = new JPanel();
        navbar.setBounds(0, 0, 430, 80);
        navbar.setBackground(new Color(25, 42, 86));
        navbar.setLayout(null);
        mainPanel.add(navbar);

        navTit = new JLabel("WELCOME TO E-BANK APP");
        navTit.setForeground(Color.WHITE);
        navTit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        navTit.setBounds(20, 25, 380, 30);
        navbar.add(navTit);

        statement = new JLabel("E-BANK APP");
        statement.setHorizontalAlignment(JLabel.CENTER);
        statement.setBounds(15, 50, 320, 30);
        statement.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        c.add(statement);

        statemnt1 = new JLabel("What can I do for you today?");
        statemnt1.setBounds(20, 110, 300, 30);
        statemnt1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        c.add(statemnt1);

        btnOption1 = new RoundedButton("LOG IN AN ACCOUNT");
        btnOption1.setBounds(30, 180, 300, 40);
        btnOption1.setBackground(new Color(0x8bace0));
        btnOption1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        c.add(btnOption1);
        btnOption1.addActionListener(e -> {
            new logInPage();
            setVisible(false);
        });

        btnOption2 = new RoundedButton("FORGOT USER-ID OR PASSWORD");
        btnOption2.setBounds(30, 230, 300, 40);
        btnOption2.setBackground(new Color(0x8bace0));
        btnOption2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        c.add(btnOption2);
        btnOption2.addActionListener(e -> {
            new forgotCredentialPage();   
            setVisible(false);
        });

        btnOption3 = new RoundedButton("CREATE AN ACCOUNT");
        btnOption3.setBounds(30, 280, 300, 40);
        btnOption3.setBackground(new Color(0x8bace0));
        btnOption3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        c.add(btnOption3);
        btnOption3.addActionListener(e -> {
            new accounts();
            setVisible(false);
        });

        setVisible(true);
    }
}