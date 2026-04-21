package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemovePage extends JFrame implements ActionListener {
    private JTextField txtAccNo;
    private JButton btnRemove, btnBack;
    AccountFiles files;

    public RemovePage(AccountFiles files){
        this.files = files;
        setTitle("Remove Account");
        setSize(430, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblTitle = new JLabel("Remove Account");
        lblTitle.setBounds(0, 60, 430, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        JLabel lblPrompt = new JLabel("Enter Account Number to delete:");
        lblPrompt.setBounds(50, 160, 300, 30);
        add(lblPrompt);

        txtAccNo = new JTextField();
        txtAccNo.setBounds(50, 200, 330, 40);
        add(txtAccNo);

        btnRemove = new JButton("Delete");
        btnRemove.setBounds(90, 280, 250, 50);
        add(btnRemove);

        btnBack = new JButton("Back");
        btnBack.setBounds(90, 350, 250, 50);
        add(btnBack);

        btnRemove.addActionListener(this);
        btnBack.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBack) {
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else if(e.getSource() == btnRemove) {
            String accNo = txtAccNo.getText().trim();
            if(accNo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an account number.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, " Do yo want to delete this account?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                if(files.removeAccount(accNo)) {
                    JOptionPane.showMessageDialog(this, "Account removed.");
                    txtAccNo.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Account not found!");
                }
            }
        }
    }
}