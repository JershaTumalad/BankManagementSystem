package BAMS;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class searchPage extends JFrame implements ActionListener{
    
    private JButton btnSearch, btnBack;
    private JLabel lblTitle, lblName, lblaccType, lblaccNo, lbliniBal, lblResult, lblResultAccNo;
    private JTextField txtaccNo;
    
    accountFiles files;
    
    searchPage(accountFiles files){
        this.files = files;
        
        setSize(360,480);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        lblTitle = new JLabel ("Search Account");
        lblTitle.setBounds(93, 30, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        add(lblTitle);
        
        lblaccNo = new JLabel ("Account Number");
        lblaccNo.setBounds(30, 79, 147, 50);
        lblaccNo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblaccNo);
        
        txtaccNo = new JTextField();
        txtaccNo.setBounds(147, 90, 180, 30);
        add(txtaccNo);
        
        lblResult = new JLabel (" ");
        lblResult.setBounds(30, 224, 300, 50);
        lblResult.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblResult);
        
        lblName = new JLabel (" ");
        lblName.setBounds(30, 244, 300, 50);
        lblName.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblName);
        
        lblaccType = new JLabel (" ");
        lblaccType.setBounds(30, 264, 300, 50);
        lblaccType.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblaccType);
        
        lblResultAccNo = new JLabel (" ");
        lblResultAccNo.setBounds(30, 284, 300, 50);
        lblResultAccNo.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblResultAccNo);
        
        lbliniBal = new JLabel (" ");
        lbliniBal.setBounds(30, 304, 300, 50);
        lbliniBal.setFont(new Font("Arial", Font.BOLD, 12));
        add(lbliniBal);
        
        
        btnSearch = new JButton ("Search");
        btnSearch.setBounds(120, 140, 120, 30);
        add(btnSearch);
        
        btnBack = new JButton ("Back");
        btnBack.setBounds(120, 174, 120, 30);
        add(btnBack);
        
        btnSearch.addActionListener(this); 
        btnBack.addActionListener(this);    

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnSearch){
            
            String accNo = txtaccNo.getText().trim();
             Account acc = files.searchAccount(accNo);

        if (acc != null) {
            lblResult.setText("Account Found: " ); 
            lblName.setText("Name: " + acc.getName()) ;
            lblaccType.setText("Account Type: " + acc.getAccountType());
            lblResultAccNo.setText("Account No.: " + acc.getAccountNo()) ;
            lbliniBal.setText("Balance: " + acc.getBalance());
        } else {
            lblResult.setText( "No account found.");
            lblName.setText(" ") ;
            lblaccType.setText(" ");
            lblResultAccNo.setText(" ") ;
            lbliniBal.setText(" ");
            
        }
        }
        

        if (e.getSource() == btnBack){
            dispose();
            GUI1frame hp = new GUI1frame(files);
            hp.setVisible(true);
            
        }
    }
    
}
