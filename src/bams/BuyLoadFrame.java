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

public class BuyLoadFrame extends JFrame {

    private final User user;
    private final DashboardFrame dashboard;
    private JTextField phoneField; 

    public BuyLoadFrame(User user, DashboardFrame dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        initWindow();
        createLoadInputs();
        createButtons();
        setVisible(true);
    } private void initWindow() {
        setTitle("Buy Load");  
        setSize(430, 350);
        setLayout(null);  
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } private void createLoadInputs() {
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(40, 30, 120, 30);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(phoneLabel);
        
        phoneField = new JTextField("0917XXXXXXX");  
        phoneField.setBounds(40, 55, 330, 40);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(phoneField);
        
        JLabel amountLabel = new JLabel("Load Amount:");
        amountLabel.setBounds(40, 105, 120, 30);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(40, 130, 330, 40);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        add(amountField);
    } private void createButtons() {
        JButton confirm = new JButton("Buy Load");  
        confirm.setBounds(40, 190, 140, 45);
        styleButton(confirm);
        add(confirm);

        JButton back = new JButton("Back");
        back.setBounds(230, 190, 140, 45);
        styleButton(back);
        add(back);
        confirm.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(((JTextField)findComponent("amount")).getText());
                if (user.withdraw(amount)) {
                    user.addHistory("Bought Load: PHP " + amount + " to " + phoneField.getText());
                    dashboard.updateBalance(user);
                    JOptionPane.showMessageDialog(this, "Load sent successfully!");
                    dispose();
                    dashboard.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid number!");
            }
        });

        back.addActionListener(e -> {
            dashboard.setVisible(true);
            dispose();
        });
    }
    private JTextField findComponent(String name) {
        for (Component c : getContentPane().getComponents()) {
            if (c instanceof JTextField && c.getBounds().y == 130) {  
                return (JTextField) c;
            }
        } return null;
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