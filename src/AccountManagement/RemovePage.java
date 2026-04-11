package project;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class RemovePage extends JFrame implements ActionListener {
    private JTextField txtAccNo;
    private JButton btnRemove, btnBack, btnExit;
    AccountFiles files;

    public RemovePage(AccountFiles files){
        this.files = files;
        setTitle("Remove Account");
        setSize(450, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblTitle = new JLabel("Remove Account");
        lblTitle.setBounds(120, 40, 250, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle);

        JLabel lblAccNo = new JLabel("Account Number:");
        lblAccNo.setBounds(50, 120, 120, 30);
        lblAccNo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblAccNo);

        txtAccNo = new JTextField();
        txtAccNo.setBounds(180, 120, 200, 30);
        add(txtAccNo);

        btnRemove = new JButton("Remove");
        btnRemove.setBounds(90, 200, 100, 40);
        btnRemove.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnRemove);

        btnBack = new JButton("Back");
        btnBack.setBounds(230, 200, 100, 40);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnBack);

        btnExit = new JButton("Exit");
        btnExit.setBounds(160, 260, 100, 35);
        btnExit.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnExit);

        btnRemove.addActionListener(this);
        btnBack.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btnRemove){
            String accNo = txtAccNo.getText().trim();
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                if(files.removeAccount(accNo)){
                    JOptionPane.showMessageDialog(this, "Removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if(e.getSource()==btnBack){
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else if(e.getSource()==btnExit){
            System.exit(0);
        }
    }
}