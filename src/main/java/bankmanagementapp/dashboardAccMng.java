package bankmanagementapp;

import bankmanagementapp.BankAppGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class dashboardAccMng extends JFrame implements ActionListener {

    private JPanel headerPanel, mainPanel;
    private JLabel lblActions, lblDashboard, lblStateBank;
    private JButton btnAdd, btnRemove, btnSearch, btnView, btnExit;
    private int userId;
    
    
     // Shared color palette used throughout the dashboard
    Color mainColor = new Color(25, 48, 90);
    Color buttonColor = new Color (244, 246, 252);
    Color mainButtonColor = new Color(139, 172, 224);
    Color txtFieldColor = new Color(245, 245, 245);
    Color backgroundColor = new Color(200, 210, 230);

    AccountFiles files;

    
// Builds the dashboard window.
    public dashboardAccMng(AccountFiles files, int userId) {
        this.files = files;
        this.userId = userId;
        
        
         // Basic window configuration
        setTitle("Bank Account Management");
        setSize(430, 720);
        setLayout(null);
        getContentPane().setBackground(backgroundColor); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        
        // Header Panel: displays bank name and page title 
        headerPanel = new JPanel();
        headerPanel.setBackground(mainColor); 
        headerPanel.setBounds(0, 0, 430, 120);
        headerPanel.setLayout(null);
        add(headerPanel);

        lblStateBank = new JLabel("STATE BANK");
        lblStateBank.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblStateBank.setForeground(new Color(170, 190, 220)); 
        lblStateBank.setBounds(30, 20, 350, 25);
        headerPanel.add(lblStateBank);

        lblDashboard = new JLabel("Account Management");
        lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblDashboard.setForeground(Color.WHITE); 
        lblDashboard.setBounds(30, 45, 350, 45);
        headerPanel.add(lblDashboard);

        
        // Main Panel: contains the action buttons 
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(25, 145, 364, 440); 
        mainPanel.setLayout(null);
        add(mainPanel);
        
        lblActions = new JLabel("WHAT CAN I DO FOR YOU TODAY?");
        lblActions.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblActions.setForeground(new Color(70, 80, 95));
        lblActions.setBounds(25, 25, 300, 25);
        mainPanel.add(lblActions);
        
        
        
          // Navigation buttons for each account operation
        btnAdd = new JButton("\u2795   ADD ACCOUNT");
        btnAdd.setBackground(mainButtonColor); 
        btnAdd.setForeground(new Color(40, 45, 55));
        btnAdd.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setBounds(25, 70, 314, 60); 
        mainPanel.add(btnAdd);
        
        btnRemove = new JButton("\u2796   REMOVE ACCOUNT");
        btnRemove.setBackground(mainButtonColor);
        btnRemove.setForeground(new Color(40, 45, 55));
        btnRemove.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnRemove.setFocusPainted(false);
        btnRemove.setBorderPainted(false);
        btnRemove.setBounds(25, 155, 314, 60);
        mainPanel.add(btnRemove);
        
        btnSearch = new JButton("\u2315   SEARCH ACCOUNT");
        btnSearch.setBackground(mainButtonColor);
        btnSearch.setForeground(new Color(40, 45, 55));
        btnSearch.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        btnSearch.setBounds(25, 240, 314, 60);
        mainPanel.add(btnSearch);
        
        btnView = new JButton("\u2630   VIEW ACCOUNTS");
        btnView.setBackground(mainButtonColor);
        btnView.setForeground(new Color(40, 45, 55));
        btnView.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnView.setFocusPainted(false);
        btnView.setBorderPainted(false);
        btnView.setBounds(25, 325, 314, 60);
        mainPanel.add(btnView);
        
        
        // Returns the user to the main bank dashboard
        btnExit = new JButton("Back to Dashboard");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExit.setForeground(new Color(50, 55, 65));
        btnExit.setBackground(mainButtonColor); 
        btnExit.setBounds(25, 605, 364, 50); 
        add(btnExit);

        
        // Register all buttons with the action listener
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnExit.addActionListener(this);
    }

    
    // Handles button clicks.
    @Override
    public void actionPerformed(ActionEvent e){
  if(e.getSource() == btnExit){
    dispose();
    new BankAppGUI(userId).setVisible(true);
}
        else {
            dispose();

            if(e.getSource() == btnAdd) 
                new AddPage(files, userId).setVisible(true);
            if(e.getSource() == btnRemove) 
                new RemovePage(files, userId).setVisible(true);
            if(e.getSource() == btnSearch) 
                new SearchPage(files, userId).setVisible(true);
            if(e.getSource() == btnView) 
                new ViewAccountsPage(files, userId).setVisible(true);
        }
    }
}
