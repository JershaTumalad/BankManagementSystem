package AccountManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemovePage extends JFrame implements ActionListener{

    AccountFiles files;
    JLabel lblDes, lblInfo, logoImage, lblStateBank;
    JPanel headerPanel, card, card2, delPanel, backPanel;
    JTextField accNo;
    JButton btnRemove, btnBack;

    Color BG = new Color(180,190,210);
    Color RED = new Color(200,70,70);
    Color pink = new Color(255,214,214);
    Color primaryBlue = new Color(25, 48, 90);
    Color LIGHTBLUE = new Color(139, 172, 224);
    Color LIGHTGRAY = new Color(245, 245, 245);

    public RemovePage(AccountFiles files){
        this.files = files;

        setTitle("Bank Account Management");
        setSize(440,720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BG);

        headerPanel = new JPanel();
        headerPanel.setBackground(primaryBlue);
        headerPanel.setBounds(0,0,430,80);
        headerPanel.setLayout(null);
        add(headerPanel);
        
        lblStateBank = new JLabel("STATE Bank");
        lblStateBank.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblStateBank.setForeground(Color.WHITE);
        lblStateBank.setBounds(30, 10, 350, 20);
        headerPanel.add(lblStateBank);
        
        JLabel title = new JLabel("Remove Account");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Tahoma",Font.BOLD,32));
        title.setBounds(30,36,300,30);
        headerPanel.add(title);

        card2 = new JPanel();
        card2.setBounds(20,120,390,75);
        card2.setBackground(pink);
        card2.setLayout(null);
        add(card2);
        
        card = new JPanel();
        card.setBounds(20,232,390,120);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        add(card);

        lblDes = new JLabel ("This action is permanent and");
        lblDes.setBounds(20, 10, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDes.setForeground(RED);
        card2.add(lblDes);
        
        lblDes = new JLabel ("cannot be undone.");
        lblDes.setBounds(20, 39, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDes.setForeground(RED);
        card2.add(lblDes);
        
        lblDes = new JLabel ("ACCOUNT TO REMOVE");
        lblDes.setBounds(20, 10, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDes.setForeground(Color.DARK_GRAY);
        card.add(lblDes);
        
        lblInfo = new JLabel("ACCOUNT NUMBER");
        lblInfo.setBounds(20, 45, 150, 20);
        lblInfo.setFont (new Font("Tahoma", Font.BOLD, 14));
        lblInfo.setForeground(Color.GRAY);
        card.add(lblInfo);

        accNo = new JTextField();
        accNo.setBackground(LIGHTGRAY);
        accNo.setBounds(20, 75, 330, 30);
        card.add(accNo);

        btnRemove = new JButton("Delete Permanently");
        btnRemove.setBorderPainted(false);
        btnRemove.setBackground(RED);
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRemove.setBounds(20,380,390,50);
        add(btnRemove);
        
        btnBack = new JButton("Back");
        btnBack.setBorderPainted(false);
        btnBack.setBackground(LIGHTBLUE);
        btnBack.setForeground(primaryBlue);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setBounds(20,450,390,50);
        add(btnBack);

        btnRemove.addActionListener(this);
        btnBack.addActionListener(this);
    }

    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btnBack){
            dispose();
            new GUI1Frame(files).setVisible(true);
        }

        else if(e.getSource()==btnRemove){

            String input = accNo.getText().trim();

            if(input.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter an account number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!input.matches("\\d+")){
                JOptionPane.showMessageDialog(this, "Invalid input! Account Number must contain numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(files.removeAccount(input)){
                JOptionPane.showMessageDialog(this, "Account Deleted.");
                accNo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Account Not Found.");
            }
        }
    }
}