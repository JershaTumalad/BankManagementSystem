package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RemovePage extends JFrame implements ActionListener {

    AccountFiles files;
    JTextField accNo;
    JButton remove, back;

    Color PRIMARY = new Color(25,45,85);
    Color BG = new Color(180,190,210);
    Color BTN = new Color(130,160,210);
    Color RED = new Color(200,70,70);

    public RemovePage(AccountFiles files){
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

        JLabel lbl = new JLabel("Enter Account Number");
        lbl.setBounds(30,30,300,20);
        card.add(lbl);

        accNo = new JTextField();
        accNo.setBounds(30,60,320,45);
        card.add(accNo);

        remove = new JButton("Delete Permanently");
        remove.setBounds(30,130,320,50);
        remove.setBackground(RED);
        remove.setForeground(Color.WHITE);
        remove.setBorder(new LineBorder(Color.GRAY,1,true));
        remove.setFocusPainted(false);

        back = new JButton("Go Back");
        back.setBounds(30,190,320,50);
        back.setBackground(BTN);
        back.setForeground(Color.WHITE);
        back.setBorder(new LineBorder(Color.GRAY,1,true));
        back.setFocusPainted(false);

        card.add(remove);
        card.add(back);

        remove.addActionListener(this);
        back.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==back){
            dispose();
            new GUI1Frame(files).setVisible(true);
        }

        else if(e.getSource()==remove){

            String input = accNo.getText().trim();

            if(input.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter account number!");
                return;
            }

            if(!input.matches("\\d+")){
                JOptionPane.showMessageDialog(this, "Invalid input! Numbers only.");
                return;
            }
<<<<<<< Updated upstream

            if(files.removeAccount(input)){
                JOptionPane.showMessageDialog(this, "Account Deleted!");
                accNo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Account Not Found!");
=======
            int confirm = JOptionPane.showConfirmDialog(this, "Confirm Deletion?", "Warning", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (files.removeAccount(id)) {
                    JOptionPane.showMessageDialog(this, "Account Deleted!");
                    txtDel.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Account Not!");
                }
>>>>>>> Stashed changes
            }
        }
    }
}