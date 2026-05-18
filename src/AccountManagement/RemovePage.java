package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemovePage extends JFrame implements ActionListener {

    private AccountFiles files;
    private JTextField txtDel;
    private JButton btnDel, btnBack;

    public RemovePage(AccountFiles files) {
        this.files = files;
        setTitle("Remove Account");
        setSize(430, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(200, 210, 230));

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(25, 42, 86));
        pnlHeader.setBounds(0, 0, 430, 100);
        pnlHeader.setLayout(null);
        add(pnlHeader);

        JLabel lblTitle = new JLabel("REMOVE ACCOUNT");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setBounds(30, 35, 300, 30);
        pnlHeader.add(lblTitle);

        JPanel pnlCard = new JPanel();
        pnlCard.setBounds(27, 140, 360, 280); 
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setLayout(null);
        add(pnlCard);

        JLabel lblHint = new JLabel("Enter Account No. to Delete:");
        lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHint.setBounds(30, 30, 300, 20);
        pnlCard.add(lblHint);

        txtDel = new JTextField();
        txtDel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtDel.setBounds(30, 60, 300, 45);
        txtDel.setBorder(null);
        txtDel.setBackground(new Color(245, 245, 245));
        pnlCard.add(txtDel);

        btnDel = createRoundedBtn("DELETE PERMANENTLY", new Color(200, 70, 70), 30, 130, 300);
        btnBack = createRoundedBtn("BACK", new Color(0x8bace0), 30, 195, 300);

        pnlCard.add(btnDel);
        pnlCard.add(btnBack);

        btnDel.addActionListener(this);
        btnBack.addActionListener(this);
    }

    private JButton createRoundedBtn(String text, Color bg, int x, int y, int w) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBounds(x, y, w, 50);
        btn.setBackground(bg);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else if (e.getSource() == btnDel) {
            String id = txtDel.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter ID!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!id.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(this, "Invalid input. Numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Confirm Deletion?", "Warning", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (files.removeAccount(id)) {
                    JOptionPane.showMessageDialog(this, "Account Deleted!");
                    txtDel.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Account Not Found!");
                }
            }
        }
    }
}