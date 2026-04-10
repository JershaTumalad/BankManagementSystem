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

public class PayBillsFrame extends JFrame {

    private final User user;
    private final DashboardFrame dashboard;
    private JComboBox<String> billTypeCombo;
    private JTextField accountField;

    public PayBillsFrame(User user, DashboardFrame dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        initWindow();
        createBillInputs();
        createButtons();
        setVisible(true);
    } private void initWindow() {
        setTitle("Pay Bills");  
        setSize(430, 350);
        setLayout(null);  
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } private void createBillInputs() {
        JLabel typeLabel = new JLabel("Bill Type:");
        typeLabel.setBounds(40, 25, 100, 30);
        typeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(typeLabel);
        
        billTypeCombo = new JComboBox<>(new String[]{
            "Meralco", "Laguna Water", "PLDT", "Bisyo", "Credit Card"
        });
        billTypeCombo.setBounds(40, 50, 330, 40);
        billTypeCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(billTypeCombo);
        
        JLabel accountLabel = new JLabel("Account No.:");
        accountLabel.setBounds(40, 100, 120, 30);
        accountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(accountLabel);
        
        accountField = new JTextField("ACC-123456");
        accountField.setBounds(40, 125, 330, 40);
        accountField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(accountField);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(40, 175, 100, 30);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(40, 200, 330, 40);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        add(amountField);
    } private void createButtons() {
        JButton confirm = new JButton("Pay Bill"); 
        confirm.setBounds(40, 260, 140, 45);
        styleButton(confirm);
        add(confirm);

        JButton back = new JButton("Back");
        back.setBounds(230, 260, 140, 45);
        styleButton(back);
        add(back);
        confirm.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(((JTextField)findAmountField()).getText());
                if (user.withdraw(amount)) {
                    String billType = (String) billTypeCombo.getSelectedItem();
                    user.addHistory("Paid " + billType + ": PHP " + amount + " (ACC: " + accountField.getText() + ")");
                    dashboard.updateBalance(user);
                    JOptionPane.showMessageDialog(this, "Bill paid successfully!");
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
            if (c instanceof JTextField && c.getBounds().y == 200) {
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