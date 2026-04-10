/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bams;

/**
 *
 * @author princ
 */
import javax.swing.*;
import java.awt.*;

public class TransferFrame extends JFrame {

    private final User user;
    private final DashboardFrame dashboard;
    private JTextField amountField; 
    private JTextField accountField;  

    public TransferFrame(User user, DashboardFrame dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        initWindow();
        createTransferInput();
        createButtons();
        setVisible(true);
    } private void initWindow() {
        setTitle("Transfer Money");
        setSize(430, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } private void createTransferInput() {
        JLabel label = new JLabel("Enter Amount to Transfer:");
        label.setBounds(40, 20, 320, 30);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        add(label);

        amountField = new JTextField();  
        amountField.setBounds(40, 50, 330, 40);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        add(amountField);
        
        JLabel accountLabel = new JLabel("Recipient Account:");
        accountLabel.setBounds(40, 105, 150, 25);
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(accountLabel);
        
        accountField = new JTextField("****1234");  
        accountField.setBounds(40, 130, 330, 35);
        accountField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(accountField);
    } private void createButtons() {
        JButton confirm = new JButton("Transfer");
        confirm.setBounds(40, 185, 140, 45);
        styleButton(confirm);
        add(confirm);

        JButton back = new JButton("Back");
        back.setBounds(230, 185, 140, 45);
        styleButton(back);
        add(back);

        confirm.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());  
                String recipient = accountField.getText();
                
                if (user.transfer(amount)) {
                    user.addHistory("Transfer PHP " + amount + " to " + recipient);
                    dashboard.updateBalance(user);
                    JOptionPane.showMessageDialog(this, "Transfer successful!");
                    dispose();
                    dashboard.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid number for amount!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error processing transfer!");
            }
        });

        back.addActionListener(e -> {
            dashboard.setVisible(true);
            dispose();
        });
    } private void styleButton(JButton btn) {
        btn.setBackground(new Color(80, 80, 80));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(100, 100, 100));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(80, 80, 80));
            }
        });
    }
}