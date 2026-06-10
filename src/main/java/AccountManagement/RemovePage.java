package AccountManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemovePage extends JFrame implements ActionListener {

    AccountFiles files;
    private JLabel lblDes, lblInfo, lblStateBank;
    private JPanel headerPanel, mainPanel, warningPanel;
    private JTextField accNo;
    private JButton btnRemove, btnBack;
    private int userId;
    
    //Customized Colors
    Color mainColor = new Color(25, 48, 90);
    Color buttonColor = new Color (244, 246, 252);
    Color mainButtonColor = new Color(139, 172, 224);
    Color txtFieldColor = new Color(245, 245, 245);
    Color warnColor = new Color(255, 214, 214);
    Color redWarn= new Color(200, 70, 70);
    Color backgroundColor = new Color(200, 210, 230);

    public RemovePage(AccountFiles files, int userId) {
        this.files = files;
        this.userId = userId;

        setTitle("Bank Account Management");
        setSize(430, 720);
        setLayout(null);
        getContentPane().setBackground(backgroundColor); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        headerPanel = new JPanel();
        headerPanel.setBackground(mainColor); 
        headerPanel.setBounds(0, 0, 430, 120); 
        headerPanel.setLayout(null);
        add(headerPanel);
        
        lblStateBank = new JLabel("STATE Bank");
        lblStateBank.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblStateBank.setForeground(new Color(170, 190, 220)); 
        lblStateBank.setBounds(30, 20, 350, 25);
        headerPanel.add(lblStateBank);
        
        JLabel title = new JLabel("Remove Account");
        title.setForeground(Color.WHITE); 
        title.setFont(new Font("Tahoma", Font.BOLD, 26)); 
        title.setBounds(30, 45, 350, 45);
        headerPanel.add(title);

        warningPanel = new JPanel();
        warningPanel.setBounds(25, 140, 364, 75); 
        warningPanel.setBackground(warnColor); 
        warningPanel.setLayout(null);
        add(warningPanel);
        
        lblDes = new JLabel("This action is permanent and");
        lblDes.setBounds(20, 15, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDes.setForeground(redWarn); 
        warningPanel.add(lblDes);
        
        lblDes = new JLabel("cannot be undone.");
        lblDes.setBounds(20, 38, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDes.setForeground(redWarn);
        warningPanel.add(lblDes);

        mainPanel = new JPanel();
        mainPanel.setBounds(25, 235, 364, 120);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);

        lblDes = new JLabel("ACCOUNT TO REMOVE");
        lblDes.setBounds(20, 10, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDes.setForeground(new Color(70, 80, 95));
        mainPanel.add(lblDes);
        
        lblInfo = new JLabel("ACCOUNT NUMBER");
        lblInfo.setBounds(20, 45, 150, 20);
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblInfo.setForeground(Color.GRAY);
        mainPanel.add(lblInfo);

        accNo = new JTextField();
        accNo.setBackground(txtFieldColor); 
        accNo.setBounds(20, 75, 324, 30); 
        mainPanel.add(accNo);

        btnRemove = new JButton("Delete Permanently");
        btnRemove.setBorderPainted(false);
        btnRemove.setBackground(redWarn); 
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRemove.setFocusPainted(false);
        btnRemove.setBounds(25, 380, 364, 50); 
        add(btnRemove);
        
        btnBack = new JButton("Back");
        btnBack.setBorderPainted(false);
        btnBack.setBackground(mainButtonColor); 
        btnBack.setForeground(mainColor);   
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setFocusPainted(false);
        btnBack.setBounds(25, 445, 364, 50); 
        add(btnBack);

        btnRemove.addActionListener(this);
        btnBack.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnBack){
            dispose();
            new dashboardAccMng(files, userId).setVisible(true);
        }
        else if(e.getSource() == btnRemove){
            String input = accNo.getText().trim();

            if(input.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter an account number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!input.matches("\\d+")){
                JOptionPane.showMessageDialog(this, "Invalid input! Account Number must contain numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(files.removeAccount(input, userId)){
                JOptionPane.showMessageDialog(this, "Account Deleted.");
                accNo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Account Not Found.");
            }
        }
    }
}
