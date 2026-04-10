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

public class DepositFrame extends JFrame {

    private final User user;
    private final DashboardFrame dashboard;
    private JTextField amountField; 

    public DepositFrame(User user, DashboardFrame dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        initWindow();
        createAmountInput();
        createButtons();
        setVisible(true);
    } private void initWindow() {
        setTitle("Deposit");
        setSize(430, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } private void createAmountInput() {
        JLabel label = new JLabel("Enter Amount:");
        label.setBounds(40, 40, 200, 30);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        add(label);

        amountField = new JTextField();  
        amountField.setBounds(40, 70, 330, 40);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        add(amountField);
    }  private void createButtons() {
        JButton confirm = new JButton("Deposit");
        confirm.setBounds(40, 130, 140, 45);
        styleButton(confirm);
        add(confirm);

        JButton back = new JButton("Back");
        back.setBounds(230, 130, 140, 45);
        styleButton(back);
        add(back);

        confirm.addActionListener(e -> 
        { try {
                double amount = Double.parseDouble(amountField.getText());  
                if (user.deposit(amount)) {
                    dashboard.updateBalance(user);
                    JOptionPane.showMessageDialog(this, "Deposit successful!");
                    dispose();
                    dashboard.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid number!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Deposit failed!");
            }
        });

        back.addActionListener(e -> {
            dashboard.setVisible(true);
            dispose();
        });
    }
    
    private void styleButton(JButton btn) {
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