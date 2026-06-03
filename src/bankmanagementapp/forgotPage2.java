package bankmanagementapp;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import dashboard_transactions.BankAppGUI;

public class forgotPage2 extends JFrame {

    ArrayList<accCreationPage> acc;
    String userId;

    JLabel lblMiniTitle, lblTitle, lblDesc, lblEmail;
    JTextField tfEmail;
    JButton btnSubmit, btnBack;

    public forgotPage2(String userId) {

        this.acc = accDatabase.acc;
        this.userId = userId;

        setTitle("Forgot Account Login");
        setSize(430, 720);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel bg = new JPanel();
        bg.setBackground(new Color(220, 226, 241));
        bg.setLayout(null);
        setContentPane(bg);

        JPanel navbar = new JPanel();
        navbar.setBounds(0, 0, 430, 120);
        navbar.setBackground(new Color(20, 45, 95));
        navbar.setLayout(null);
        bg.add(navbar);

        lblMiniTitle = new JLabel("ACCOUNT RECOVERY");
        lblMiniTitle.setForeground(new Color(190, 200, 230));
        lblMiniTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMiniTitle.setBounds(35, 25, 250, 20);
        navbar.add(lblMiniTitle);

        lblTitle = new JLabel("Forgot Login");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setBounds(35, 45, 320, 40);
        navbar.add(lblTitle);

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(35, 170, 340, 270);
        bg.add(card);

        lblDesc = new JLabel("Enter your registered email address");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(100, 100, 100));
        lblDesc.setBounds(30, 30, 260, 20);
        card.add(lblDesc);

        lblEmail = new JLabel("EMAIL ADDRESS");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEmail.setForeground(new Color(110, 110, 110));
        lblEmail.setBounds(30, 75, 150, 20);
        card.add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(30, 100, 280, 42);
        tfEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfEmail.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tfEmail.setBackground(new Color(245, 246, 252));
        card.add(tfEmail);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(30, 180, 280, 42);
        btnSubmit.setBackground(new Color(138, 173, 255));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        card.add(btnSubmit);

        btnSubmit.addActionListener(e -> {

            String inputEmail = tfEmail.getText().trim();
            boolean found = false;

            if (inputEmail.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please enter your email address.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                return;

            }

            for (accCreationPage user : acc) {

                if ((userId.isEmpty() || user.getUserId().equals(userId)) && user.getEmail().equals(inputEmail))  {

                    found = true;
                    break;
                }
            }

            if (found) {

                JOptionPane.showMessageDialog(this,
                        "Email Found!");

                new BankAppGUI().setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Email not registered!",
                        "Email Not Found",
                        JOptionPane.ERROR_MESSAGE);

                tfEmail.setText("");
                tfEmail.requestFocus();

            }

        });

        btnBack = new JButton("Back to homepage");
        btnBack.setBounds(35, 520, 340, 42);
        btnBack.setBackground(new Color(220, 226, 241));
        btnBack.setForeground(new Color(70, 70, 70));
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bg.add(btnBack);

        btnBack.addActionListener(e -> {

            new frontPage().setVisible(true);
            dispose();

        });

        setVisible(true);
    }
}