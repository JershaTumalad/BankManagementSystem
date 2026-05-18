
package BAMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

public class AddPage extends JFrame implements ActionListener {
        private JTextField txtLast, txtFirst, txtMiddle, txtAccNo, txtIniBal;
        private JComboBox<String> cmbType;
        private JButton btnSave, btnCancel;

        private AccountFiles accountFiles;

        Color primaryBlue = new Color(25, 45, 85);
        Color backgroundColor = new Color(180, 190, 210);
        Color buttonColor = new Color(130, 160, 210);

        public AddPage(AccountFiles files){
            this.accountFiles = files;

            setSize(430, 720); setLayout(null); 
            setLocationRelativeTo(null);
            getContentPane().setBackground(backgroundColor); 
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel header = new JPanel();
            header.setBackground(primaryBlue); 
            header.setBounds(0, 0, 430, 80);
            header.setLayout(null); 
            add(header);

            JLabel lblTitle = new JLabel("New Account");
            lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
            lblTitle.setForeground(Color.WHITE); lblTitle.setBounds(30, 25, 250, 30);
            header.add(lblTitle);

            JPanel formCard = new JPanel();
            formCard.setBackground(Color.WHITE); 
            formCard.setBounds(25, 100, 370, 520);
            formCard.setLayout(null); 
            add(formCard);

            Font labelFont = new Font("Segoe UI", Font.PLAIN, 13);

            JLabel lblLast = new JLabel("Last Name"); 
            lblLast.setBounds(20, 15, 150, 20); 
            lblLast.setFont(labelFont);
            formCard.add(lblLast);
            txtLast = new JTextField(); 
            txtLast.setBounds(20, 35, 330, 30); 
            formCard.add(txtLast);

            JLabel lblFirst = new JLabel("First Name"); 
            lblFirst.setBounds(20, 70, 150, 20); 
            lblFirst.setFont(labelFont); 
            formCard.add(lblFirst);
            txtFirst = new JTextField(); 
            txtFirst.setBounds(20, 90, 330, 30); 
            formCard.add(txtFirst);

            JLabel lblMiddle = new JLabel("Middle Name"); 
            lblMiddle.setBounds(20, 125, 150, 20); 
            lblMiddle.setFont(labelFont); 
            formCard.add(lblMiddle);
            txtMiddle = new JTextField(); 
            txtMiddle.setBounds(20, 145, 330, 30); 
            formCard.add(txtMiddle);

            JLabel lblType = new JLabel("Account Type"); 
            lblType.setBounds(20, 180, 150, 20); 
            lblType.setFont(labelFont); 
            formCard.add(lblType);
            cmbType = new JComboBox<>(new String[]{"Savings", "Current"});
            cmbType.setBounds(20, 200, 330, 30); 
            formCard.add(cmbType);

            JLabel lblAccNo = new JLabel("Account Number"); 
            lblAccNo.setBounds(20, 235, 150, 20); 
            lblAccNo.setFont(labelFont); 
            formCard.add(lblAccNo);
            txtAccNo = new JTextField();
            txtAccNo.setBounds(20, 255, 330, 30); 
            formCard.add(txtAccNo);

            JLabel lblBal = new JLabel("Initial Balance");
            lblBal.setBounds(20, 290, 150, 20); 
            lblBal.setFont(labelFont); 
            formCard.add(lblBal);
            txtIniBal = new JTextField(); 
            txtIniBal.setBounds(20, 310, 330, 30);
            formCard.add(txtIniBal);

            btnSave = new JButton("Save"); 
            btnSave.setBounds(20, 380, 155, 40); 
            btnSave.setBackground(buttonColor); 
            btnSave.setForeground(Color.WHITE); 
            btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
            formCard.add(btnSave);
            btnCancel = new JButton("Cancel");
            btnCancel.setBounds(195, 380, 155, 40);
            btnCancel.setBackground(buttonColor); 
            btnCancel.setForeground(Color.WHITE); 
            btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
            formCard.add(btnCancel);

            btnSave.addActionListener(this); 
            btnCancel.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btnCancel){ 
                dispose(); 
                new GUI1Frame(accountFiles).setVisible(true); }
            else if(e.getSource() == btnSave){
                try {
                    String last = txtLast.getText().trim();
                    String first = txtFirst.getText().trim();
                    String middle = txtMiddle.getText().trim();
                    String accNo = txtAccNo.getText().trim();
                    String balanceStr = txtIniBal.getText().trim();

                    if(last.isEmpty() || first.isEmpty() || middle.isEmpty() || accNo.isEmpty() || balanceStr.isEmpty()){
                        JOptionPane.showMessageDialog(this, "All fields are required!"); 
                        return;
                    }
                    if(!last.matches("[a-zA-Z ]+") || !first.matches("[a-zA-Z ]+") || !middle.matches("[a-zA-Z ]+")){
                        JOptionPane.showMessageDialog(this, "Names must contain letters only!"); 
                        return;
                    }
                    if(!accNo.matches("\\d+")){
                        JOptionPane.showMessageDialog(this, "Account Number must be numbers only!"); 
                        return;
                    }

                    double balanceValue = Double.parseDouble(balanceStr);
                    String fullDisplayName = last + ", " + first + " " + middle;
                    if(accountFiles.addAccount(fullDisplayName, (String) cmbType.getSelectedItem(), accNo, balanceValue)){
                        JOptionPane.showMessageDialog(this, "Account Saved!"); 
                        dispose(); 
                        new GUI1Frame(accountFiles).setVisible(true);
                    } else { JOptionPane.showMessageDialog(this, "Account Number already exists!"); }
                } catch(NumberFormatException ex){ JOptionPane.showMessageDialog(this, "Invalid Balance! Numbers only."); }
            }
        }
    }
    

