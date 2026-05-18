package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchPage extends JFrame implements ActionListener {

    private AccountFiles files;
    private JTextField txtSearch;
    private JButton btnGo, btnBack;

    public SearchPage(AccountFiles files) {
        this.files = files;
        setTitle("Search Account");
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

        JLabel lblTitle = new JLabel("SEARCH ACCOUNT");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setBounds(30, 35, 300, 30);
        pnlHeader.add(lblTitle);

        JPanel pnlCard = new JPanel();
        pnlCard.setBounds(27, 140, 360, 280); 
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setLayout(null);
        add(pnlCard);

        JLabel lblHint = new JLabel("Enter Account No. to Search:");
        lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHint.setBounds(30, 30, 300, 20);
        pnlCard.add(lblHint);

        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtSearch.setBounds(30, 60, 300, 45);
        txtSearch.setBorder(null);
        txtSearch.setBackground(new Color(245, 245, 245));
        pnlCard.add(txtSearch);

        btnGo = createRoundedBtn("SEARCH", new Color(0x8bace0), 30, 130, 300);
        btnBack = createRoundedBtn("BACK", new Color(0x8bace0), 30, 195, 300);

        pnlCard.add(btnGo);
        pnlCard.add(btnBack);

        btnGo.addActionListener(this);
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
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else {
            String input = txtSearch.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Search field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!input.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(this, "Invalid input. Numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Account a = files.searchAccount(input);
            if (a != null) {
                JOptionPane.showMessageDialog(this, "Account Found!\nHolder: " + a.getName() + "\nBalance: P" + a.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "No record found.");
            }
        }
    }
}