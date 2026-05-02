
package BAMS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

public class GUI1Frame extends JFrame implements ActionListener {

    private JPanel headerPanel, cardPanel;
    private JLabel lblWelcome, lblDashboard, lblBalanceBox;
    private JButton btnAdd, btnRemove, btnSearch, btnView, btnExit;
    private AccountFiles accountFiles;

    Color primaryBlue = new Color(25, 45, 85);
    Color backgroundColor = new Color(180, 190, 210);
    Color buttonColor = new Color(130, 160, 210);

    public GUI1Frame(AccountFiles files){
        this.accountFiles = files;

        setTitle("Bank App");
        setSize(440, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(backgroundColor);

        headerPanel = new JPanel();
        headerPanel.setBackground(primaryBlue);
        headerPanel.setBounds(0, 0, 430, 160);
        headerPanel.setLayout(null);
        add(headerPanel);

        lblWelcome = new JLabel("Welcome Back!");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(30, 20, 300, 30);
        headerPanel.add(lblWelcome);

        lblBalanceBox = new JLabel();
        lblBalanceBox.setOpaque(true);
        lblBalanceBox.setBackground(Color.WHITE);
        lblBalanceBox.setBounds(20, 60, 390, 60);
        headerPanel.add(lblBalanceBox);

        lblDashboard = new JLabel("    Account Management");
        lblDashboard.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblDashboard.setForeground(primaryBlue);
        lblDashboard.setBounds(40, 60, 350, 60);
        headerPanel.add(lblDashboard);

        headerPanel.setComponentZOrder(lblDashboard, 0);

        cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBounds(20, 180, 390, 420); 
        cardPanel.setLayout(null);
        add(cardPanel);

        btnAdd = createMenuButton("Add Account", 50);
        btnRemove = createMenuButton("Remove Account", 140);
        btnSearch = createMenuButton("Search Account", 230);
        btnView = createMenuButton("View All Records", 320);

        cardPanel.add(btnAdd); cardPanel.add(btnRemove); 
        cardPanel.add(btnSearch); cardPanel.add(btnView);

        btnExit = new JButton("EXIT");
        btnExit.setBounds(140, 620, 150, 40);
        btnExit.setForeground(Color.RED);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        add(btnExit);

        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnExit.addActionListener(this);
    }

    private JButton createMenuButton(String text, int yPos) {
        JButton btn = new JButton(text);
        btn.setBounds(30, yPos, 330, 55);
        btn.setBackground(buttonColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(new LineBorder(Color.GRAY, 1, true));
        btn.setFocusPainted(false);

        btn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                btn.setLocation(btn.getX(), btn.getY() + 3);
            }
            public void mouseReleased(MouseEvent e) { 
                btn.setLocation(btn.getX(), btn.getY() - 3); 
            }
        });
        return btn;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnExit){
            System.exit(0);
        } else {
            dispose();
            if(e.getSource() == btnAdd) 
                new AddPage(accountFiles).setVisible(true);
            if(e.getSource() == btnRemove) 
                new RemovePage(accountFiles).setVisible(true);
            if(e.getSource() == btnSearch) 
                new SearchPage(accountFiles).setVisible(true);
            if(e.getSource() == btnView)
                new ViewAccountsPage(accountFiles).setVisible(true);
        }
    }
