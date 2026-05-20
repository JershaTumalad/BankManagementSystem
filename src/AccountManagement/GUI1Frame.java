package AccountManagement;

import dashboard_transactions.BankAppGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI1Frame extends JFrame implements ActionListener {

    private JPanel headerPanel, card, addPanel, removePanel, searchPanel, viewPanel;
    private JLabel lblActions, lblDashboard,  lblDesc, lblStateBank, addImage, removeimage, searchimage, viewimage;
    private JButton btnAdd, btnRemove, btnSearch, btnView, btnExit;
    AccountFiles files;

    Color primaryBlue = new Color(25, 48, 90);
    Color bg = new Color(180, 190, 210);
    Color lightBlue = new Color (244, 246, 252);
    Color LIGHTBLUE = new Color(139, 172, 224);

    public GUI1Frame(AccountFiles files){
        this.files = files;

        setTitle("Bank Account Management");
        setSize(440, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(bg);
        
        headerPanel = new JPanel();
        headerPanel.setBackground(primaryBlue);
        headerPanel.setBounds(0, 0, 430, 120);
        headerPanel.setLayout(null);
        add(headerPanel);

        lblStateBank = new JLabel("STATE Bank");
        lblStateBank.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblStateBank.setForeground(Color.WHITE);
        lblStateBank.setBounds(30, 12, 350, 60);
        headerPanel.add(lblStateBank);

        lblDashboard = new JLabel("Account Management");
        lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblDashboard.setForeground(Color.WHITE);
        lblDashboard.setBounds(30, 48, 350, 60);
        headerPanel.add(lblDashboard);

        headerPanel.setComponentZOrder(lblDashboard, 0);

        card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(20, 147, 390, 462); 
        card.setLayout(null);
        add(card);
        
        lblActions = new JLabel("QUICK ACTIONS");
        lblActions.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblActions.setForeground(Color.DARK_GRAY);
        lblActions.setBounds(25, 10, 300, 30);
        card.add(lblActions);

        addPanel = new JPanel();
        addPanel.setBackground(lightBlue);
        addPanel.setBounds(20, 50, 340, 84); 
        addPanel.setLayout(null);
        card.add(addPanel);
        
        removePanel = new JPanel();
        removePanel.setBackground(lightBlue);
        removePanel.setBounds(20, 146, 340, 84); 
        removePanel.setLayout(null);
        card.add(removePanel);
        
        searchPanel = new JPanel();
        searchPanel.setBackground(lightBlue);
        searchPanel.setBounds(20, 242, 340, 84); 
        searchPanel.setLayout(null);
        card.add(searchPanel);
        
        viewPanel = new JPanel();
        viewPanel.setBackground(lightBlue);
        viewPanel.setBounds(20, 338, 340, 84); 
        viewPanel.setLayout(null);
        card.add(viewPanel);
        
        btnAdd = new JButton("Add Account");
        btnAdd.setBorderPainted(false);
        btnAdd.setBackground(lightBlue);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 21));
        btnAdd.setBounds(80,14,180,30);
        addPanel.add(btnAdd);
        
        btnRemove = new JButton("Remove Account");
        btnRemove.setBorderPainted(false);
        btnRemove.setBackground(lightBlue);
        btnRemove.setFont(new Font("Tahoma", Font.BOLD, 21));
        btnRemove.setBounds(80,14,230,30);
        removePanel.add(btnRemove);
        
        btnSearch = new JButton("Search Account");
        btnSearch.setBorderPainted(false);
        btnSearch.setBackground(lightBlue);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 21));
        btnSearch.setBounds(80,14,210,30);
        searchPanel.add(btnSearch);
        
        btnView = new JButton ("View All Records");
        btnView.setBorderPainted(false);
        btnView.setBackground(lightBlue);
        btnView.setFont(new Font("Tahoma", Font.BOLD, 21));
        btnView.setBounds(80,14,230,30);
        viewPanel.add(btnView);
        
        lblDesc = new JLabel("Register a new account");
        lblDesc.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setBounds(100, 45, 200, 20);
        addPanel.add(lblDesc);
        
        lblDesc = new JLabel("Delete an account");
        lblDesc.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setBounds(100, 45, 200, 20);
        removePanel.add(lblDesc);
        
        lblDesc = new JLabel("Look up by account number");
        lblDesc.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setBounds(100, 45, 200, 20);
        searchPanel.add(lblDesc);
        
        lblDesc = new JLabel("View all accounts");
        lblDesc.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setBounds(100, 45, 200, 20);
        viewPanel.add(lblDesc);
        
        ImageIcon addIcon = new ImageIcon("src/AccountManagement/1.png");
        Image scaledaddIcon = addIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(scaledaddIcon);
        addImage = new JLabel(addIcon);
        addImage.setBounds(14, 7, 70, 70);
        addPanel.add(addImage);
        
        ImageIcon removeIcon = new ImageIcon("src/AccountManagement/3.png");
        Image scaledremoveIcon = removeIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        removeIcon = new ImageIcon(scaledremoveIcon);
        removeimage = new JLabel(removeIcon);
        removeimage.setBounds(14, 7, 70, 70);
        removePanel.add(removeimage);
        
        ImageIcon searchIcon = new ImageIcon("src/AccountManagement/2.png");
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledSearchIcon);
        searchimage = new JLabel(searchIcon);
        searchimage.setBounds(14, 7, 70, 70);
        searchPanel.add(searchimage);
        
        ImageIcon viewIcon = new ImageIcon("src/AccountManagement/4.png");
        Image scaledViewIcon = viewIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        viewIcon = new ImageIcon(scaledViewIcon);
        viewimage = new JLabel(viewIcon);
        viewimage.setBounds(14, 7, 70, 70);
        viewPanel.add(viewimage);
        
        btnExit = new JButton("Back to Dashboard");
        btnExit.setBorderPainted(false);
        btnExit.setBackground(LIGHTBLUE);
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExit.setBounds(111, 630, 201, 40);
        add(btnExit);

        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnExit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnExit){
            dispose();
            new BankAppGUI().setVisible(true);
        } else {
            dispose();

            if(e.getSource() == btnAdd) 
                new AddPage(files).setVisible(true);
            if(e.getSource() == btnRemove) 
                new RemovePage(files).setVisible(true);
            if(e.getSource() == btnSearch) 
                new SearchPage(files).setVisible(true);
            if(e.getSource() == btnView) 
                new ViewAccountsPage(files).setVisible(true);
        }
    }
}