package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class SearchPage extends JFrame implements ActionListener {

    AccountFiles files;
    JTextField accNo;
    JButton search, back;

    Color PRIMARY = new Color(25,45,85);
    Color BG = new Color(180,190,210);
    Color BTN = new Color(130,160,210);

    public SearchPage(AccountFiles files){
        this.files = files;

        setSize(440,720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BG);

        JPanel header = new JPanel();
        header.setBackground(PRIMARY);
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

        JLabel lbl = new JLabel("Enter Account Number");
        lbl.setBounds(30,30,300,20);
        card.add(lbl);

        accNo = new JTextField();
        accNo.setBounds(30,60,320,45);
        card.add(accNo);

        search = new JButton("Search");
        search.setBounds(30,130,320,50);
        search.setBackground(BTN);
        search.setForeground(Color.WHITE);
        search.setBorder(new LineBorder(Color.GRAY,1,true));
        search.setFocusPainted(false);

        back = new JButton("Go Back");
        back.setBounds(30,190,320,50);
        back.setBackground(BTN);
        back.setForeground(Color.WHITE);
        back.setBorder(new LineBorder(Color.GRAY,1,true));
        back.setFocusPainted(false);

        card.add(search);
        card.add(back);

        search.addActionListener(this);
        back.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==back){
            dispose();
            new GUI1Frame(files).setVisible(true);
        }

        else if(e.getSource()==search){

            String input = accNo.getText().trim();

            if(input.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter account number!");
                return;
            }

            if(!input.matches("\\d+")){
                JOptionPane.showMessageDialog(this, "Invalid input! Numbers only.");
                return;
            }

            Account acc = files.searchAccount(input);

            if(acc != null){
                JOptionPane.showMessageDialog(this,
                    "Name: " + acc.getName() +
                    "\nType: " + acc.getAccountType() +
                    "\nBalance: P " + acc.getBalance());
            } else {
<<<<<<< Updated upstream
                JOptionPane.showMessageDialog(this, "Account Not Found!");
=======
                JOptionPane.showMessageDialog(this, "No record.");
>>>>>>> Stashed changes
            }
        }
    }
}