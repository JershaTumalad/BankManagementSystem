package BAMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchPage extends JFrame implements ActionListener {

        JTextField txtSearchField; 
        JButton btnFind, btnReturn;

        private AccountFiles accountFiles;

        Color primaryBlue = new Color(25, 45, 85);
        Color backgroundColor = new Color(180, 190, 210);
        Color buttonColor = new Color(130, 160, 210);

        public SearchPage(AccountFiles files){

            this.accountFiles = files;

            setSize(440,720);
            setLayout(null); 
            setLocationRelativeTo(null); 
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(backgroundColor);
            JPanel header = new JPanel(); 
            header.setBackground(primaryBlue); 
            header.setBounds(0,0,430,100); 
            header.setLayout(null); 
            add(header);
            
            JLabel title = new JLabel("SEARCH ACCOUNT"); 
            title.setForeground(Color.WHITE); 
            title.setFont(new Font("Segoe UI",Font.BOLD,18));
            title.setBounds(30,35,300,30);
            header.add(title);
            JPanel card = new JPanel(); 
            card.setBounds(20,140,390,300); 
            card.setBackground(Color.WHITE); 
            card.setLayout(null); 
            add(card);
            
            JLabel lblInstruction = new JLabel("Enter Account Number"); 
            lblInstruction.setBounds(30,30,300,20); 
            card.add(lblInstruction);
            txtSearchField = new JTextField(); 
            txtSearchField.setBounds(30,60,320,45); 
            card.add(txtSearchField);
            
            btnFind = new JButton("Search"); 
            btnFind.setBounds(30,130,320,50);
            btnFind.setBackground(buttonColor); 
            btnFind.setForeground(Color.WHITE); 
            card.add(btnFind);
            
            btnReturn = new JButton("Go Back"); 
            btnReturn.setBounds(30,190,320,50); 
            btnReturn.setBackground(buttonColor); 
            btnReturn.setForeground(Color.WHITE); 
            card.add(btnReturn);
            
            btnFind.addActionListener(this);
            btnReturn.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==btnReturn){ 
                dispose(); 
                new GUI1Frame(accountFiles).setVisible(true); }
            else {
                String input = txtSearchField.getText().trim();
                if(!input.matches("\\d+")){ JOptionPane.showMessageDialog(this, "Numbers only!"); 
                return; }
                Account acc = accountFiles.searchAccount(input);
                if(acc != null) JOptionPane.showMessageDialog(this, "Name: " + acc.getName() + "\nBalance: P " + acc.getBalance());
                else JOptionPane.showMessageDialog(this, "Account Not Found!");
            }
        }
    }
