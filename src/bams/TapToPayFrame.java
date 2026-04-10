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

public class TapToPayFrame extends JFrame {

    private final User user;
    private final DashboardFrame dashboard;
    private JTextField merchantField;

    public TapToPayFrame(User user, DashboardFrame dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        initWindow();
        createTapInputs();
        createButtons();
        setVisible(true);
    } private void initWindow() {
        setTitle("Tap to Pay");  
        setSize(430, 350);
        setLayout(null);  
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } private void createTapInputs() {
        JLabel merchantLabel = new JLabel("Store Name:");
        merchantLabel.setBounds(40, 30, 100, 30);
        merchantLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(merchantLabel);
        
        merchantField = new JTextField(" ");
        merchantField.setBounds(40, 55, 330, 40);
        merchantField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(merchantField);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(40, 105, 100, 30);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(40, 130, 330, 40);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        add(amountField);
    } private void createButtons() {
        JButton confirm = new JButton("Tap & Pay");  
        confirm.setBounds(40, 190, 140, 45);
        styleButton(confirm);
        add(confirm);

        JButton back = new JButton("Back");
        back.setBounds(230, 190, 140, 45);
        styleButton(back);
        add(back);
        confirm.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(((JTextField)findAmountField()).getText());
                if (user.withdraw(amount)) {
                    user.addHistory("Tap To Pay: PHP " + amount + " at " + merchantField.getText());
                    dashboard.updateBalance(user);
                    JOptionPane.showMessageDialog(this, "Payment successful!");
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
    } private JTextField findAmountField() {
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