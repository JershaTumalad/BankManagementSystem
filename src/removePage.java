
package BAMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RemovePage extends JFrame implements ActionListener {
        JTextField txtRemoveField; 
        JButton btnDel, btnBack;

        private AccountFiles accountFiles;

        Color primaryBlue = new Color(25, 45, 85);
        Color backgroundColor = new Color(180, 190, 210);
        Color buttonColor = new Color(130, 160, 210);

        public RemovePage(AccountFiles files){
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
            
            JLabel title = new JLabel("REMOVE ACCOUNT"); 
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
            txtRemoveField = new JTextField(); 
            txtRemoveField.setBounds(30,60,320,45); 
            card.add(txtRemoveField);
            
            btnDel = new JButton("Delete Permanently"); 
            btnDel.setBounds(30,130,320,50); 
            btnDel.setBackground(new Color(200,70,70));
            btnDel.setForeground(Color.WHITE); 
            card.add(btnDel);
            
            btnBack = new JButton("Go Back"); 
            btnBack.setBounds(30,190,320,50); 
            btnBack.setBackground(buttonColor); 
            btnBack.setForeground(Color.WHITE); 
            card.add(btnBack);
            
            btnDel.addActionListener(this); 
            btnBack.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==btnBack){ 
                dispose(); 
                new GUI1Frame(accountFiles).setVisible(true); }
            else {
                String input = txtRemoveField.getText().trim();
                if(!input.matches("\\d+")){ JOptionPane.showMessageDialog(this, "Numbers only!"); 
                return; }
                if(accountFiles.removeAccount(input)) JOptionPane.showMessageDialog(this, "Deleted!");
                else JOptionPane.showMessageDialog(this, "Account Not Found!");
            }
        }
    }
