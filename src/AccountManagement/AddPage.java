package AccountManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddPage extends JFrame implements ActionListener{

    private JTextField txtLast, txtFirst, txtMiddle, txtAccNo, txtIniBal;
    private JLabel lblLName, lblFName, lblMName, lblAccType, lblAccNo, lblInitial, lblDes, lblInfo, lblSave, lblBack, logoImage, lblStateBank;
    private JPanel headerPanel, card, card2, cardSave, cardBack;
    private JComboBox<String> cmbType;
    private JButton btnAdd, btnBack;
    AccountFiles files;

    Color primaryBlue = new Color(25, 48, 90);
    Color lightBlue = new Color (244, 246, 252);
    Color LIGHTBLUE = new Color(139, 172, 224);
    Color LIGHTGRAY = new Color(245, 245, 245);

    public AddPage(AccountFiles files){
        this.files = files;

        setTitle("Bank Account Management");
        setSize(430, 720);
        setLayout(null);
        getContentPane().setBackground(new Color(180, 190, 210)); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        headerPanel = new JPanel();
        headerPanel.setBackground(primaryBlue);
        headerPanel.setBounds(0, 0, 430, 80); 
        headerPanel.setLayout(null);
        add(headerPanel);
        
        lblStateBank = new JLabel("STATE Bank");
        lblStateBank.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblStateBank.setForeground(Color.WHITE);
        lblStateBank.setBounds(30, 10, 350, 20);
        headerPanel.add(lblStateBank);

        JLabel lblTitle = new JLabel("Add Account");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(30, 36, 250, 30);
        headerPanel.add(lblTitle);

        card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(25, 100, 370, 201);
        card.setLayout(null);
        add(card);
        
        card2 = new JPanel();
        card2.setBackground(Color.WHITE);
        card2.setBounds(25, 320, 370, 282);
        card2.setLayout(null);
        add(card2);

        lblDes = new JLabel ("PERSONAL INFORMATION");
        lblDes.setBounds(20, 10, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDes.setForeground(Color.DARK_GRAY);
        card.add(lblDes);
        
        lblInfo = new JLabel("LAST NAME");
        lblInfo.setBounds(20, 45, 120, 20);
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInfo.setForeground(Color.GRAY);
        card.add(lblInfo);

        txtLast = new JTextField();
        txtLast.setBackground(LIGHTGRAY);
        txtLast.setBounds(20, 75, 160, 30);
        card.add(txtLast);

        lblInfo = new JLabel("FIRST NAME");
        lblInfo.setBounds(190, 45, 120, 20);
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInfo.setForeground(Color.GRAY);
        card.add(lblInfo);

        txtFirst = new JTextField();
        txtFirst.setBackground(LIGHTGRAY);
        txtFirst.setBounds(190, 75, 160, 30);
        card.add(txtFirst);

        lblInfo = new JLabel("MIDDLE NAME (Optional)");
        lblInfo.setBounds(20, 125, 300, 20);
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInfo.setForeground(Color.GRAY);
        card.add(lblInfo);

        txtMiddle = new JTextField();
        txtMiddle.setBackground(LIGHTGRAY);
        txtMiddle.setBounds(20, 155, 330, 30);
        card.add(txtMiddle);

        lblDes = new JLabel ("ACCOUNT DETAILS");
        lblDes.setBounds(20, 10, 300, 20);
        lblDes.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDes.setForeground(Color.DARK_GRAY);
        card2.add(lblDes);
        
        lblInfo = new JLabel("ACCOUNT TYPE");
        lblInfo.setBounds(20, 45, 150, 20);
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInfo.setForeground(Color.GRAY);
        card2.add(lblInfo);

        cmbType = new JComboBox<>(new String[]{"Savings", "Current"});
        cmbType.setBounds(20, 75, 330, 30);
        card2.add(cmbType);

        lblInfo = new JLabel("ACCOUNT NUMBER");
        lblInfo.setBounds(20, 125, 150, 20);
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInfo.setForeground(Color.GRAY);
        card2.add(lblInfo);

        txtAccNo = new JTextField();
        txtAccNo.setBackground(LIGHTGRAY);
        txtAccNo.setBounds(20, 155, 330, 30);
        card2.add(txtAccNo);

        lblInfo = new JLabel("INITIAL BALANCE");
        lblInfo.setBounds(20, 205, 150, 20);
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInfo.setForeground(Color.GRAY);
        card2.add(lblInfo);

        txtIniBal = new JTextField();
        txtIniBal.setBackground(LIGHTGRAY);
        txtIniBal.setBounds(20, 235, 330, 30);
        card2.add(txtIniBal);
        
        btnAdd = new JButton("SAVE ACCOUNT");
        btnAdd.setBorderPainted(false);
        btnAdd.setBackground(primaryBlue);
        btnAdd.setForeground(lightBlue);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAdd.setBounds(192, 615, 192, 45);
        add(btnAdd);
        
        btnBack = new JButton("BACK");
        btnBack.setBorderPainted(false);
        btnBack.setBackground(LIGHTBLUE);
        btnBack.setForeground(primaryBlue);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setBounds(25, 615, 140, 45);
        add(btnBack);
        
        btnAdd.addActionListener(this);
        btnBack.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnBack){
            dispose();
            new GUI1Frame(files).setVisible(true);
        }
        else if(e.getSource() == btnAdd){
            try{
                String last = txtLast.getText().trim().toUpperCase();
                String first = txtFirst.getText().trim().toUpperCase();
                String middle = txtMiddle.getText().trim().toUpperCase();
                String accNo = txtAccNo.getText().trim();
                String balText = txtIniBal.getText().trim();

                if(last.isEmpty() || first.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Last Name and First Name are required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(!last.matches("[a-zA-Z ]+") ||
                   !first.matches("[a-zA-Z ]+") ){
                    JOptionPane.showMessageDialog(this, "Invalid Input! Names must contain letters only.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(accNo.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Account Number is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!accNo.matches("\\d+")){
                    JOptionPane.showMessageDialog(this, "Invalid Input! Account Number must contain numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(balText.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Initial Balance is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double balance = Double.parseDouble(balText);

                if(balance < 0){
                    JOptionPane.showMessageDialog(this, "Invalid Input! Balance cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String fullName = last + ", " + first + " " + middle;

                if(files.addAccount(fullName,
                        (String) cmbType.getSelectedItem(),
                        accNo,
                        balance)){

                    JOptionPane.showMessageDialog(this, "Account Saved.");
                    dispose();
                    new GUI1Frame(files).setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "Account Number already exists.");
                }

            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Invalid balance input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

}