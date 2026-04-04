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

public class ViewTransactionsFrame extends JFrame {

    private final User user;
    private final DashboardFrame dashboard;

    public ViewTransactionsFrame(User user, DashboardFrame dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        initWindow();
        createTransactionsList();
        createBackButton();
        setVisible(true);
    } private void initWindow() {
        setTitle("Transaction History");  
        setSize(430, 500);
        setLayout(null);  
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } private void createTransactionsList() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 12));  
        area.setBackground(new Color(248, 250, 252));       
        area.append("=== TRANSACTION HISTORY ===\n\n");
        
        for (String s : user.getHistory()) {
            area.append(s + "\n");
        } if (user.getHistory().isEmpty()) {
            area.append("No transactions yet.\n");
        } JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(20, 60, 380, 320);  
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(scroll);
        
        JLabel title = new JLabel("Recent Transactions");
        title.setBounds(20, 20, 380, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);
    }
    
    private void createBackButton() {
        JButton back = new JButton("Back to Dashboard");
        back.setBounds(150, 410, 120, 40);  
        styleButton(back);
        add(back);

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