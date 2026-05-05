package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddPage extends JFrame implements ActionListener {

    private JTextField txtLast, txtFirst, txtMiddle, txtAccNo, txtBalance;
    private JComboBox<String> cbType;
    private JButton btnSave, btnBack;
    private AccountFiles files;
    private final Color accent = new Color(0x8bace0);

    public AddPage(AccountFiles files) {
        this.files = files;
        setTitle("Add Account");
        setSize(430, 720);
        setLayout(null);
        getContentPane().setBackground(new Color(200, 210, 230));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(25, 42, 86));
        pnlHeader.setBounds(0, 0, 430, 80);
        pnlHeader.setLayout(null);
        add(pnlHeader);

        JLabel lblPage = new JLabel("NEW ACCOUNT");
        lblPage.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPage.setForeground(Color.WHITE);
        lblPage.setBounds(30, 25, 250, 30);
        pnlHeader.add(lblPage);

        JPanel pnlForm = new JPanel();
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBounds(27, 100, 360, 540); 
        pnlForm.setLayout(null);
        add(pnlForm);

        setupField(pnlForm, "Last Name", txtLast = new JTextField(), 20);
        setupField(pnlForm, "First Name", txtFirst = new JTextField(), 85);
        setupField(pnlForm, "Middle Name (Optional)", txtMiddle = new JTextField(), 150);

        JLabel lblType = new JLabel("Account Type");
        lblType.setBounds(20, 215, 150, 20);
        lblType.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pnlForm.add(lblType);

        cbType = new JComboBox<>(new String[]{"Savings Account", "Current Account"});
        cbType.setBounds(20, 240, 320, 35);
        pnlForm.add(cbType);

        setupField(pnlForm, "Account Number", txtAccNo = new JTextField(), 290);
        setupField(pnlForm, "Initial Deposit", txtBalance = new JTextField(), 355);

        btnSave = createRoundedBtn("SAVE", 20, 450, 150);
        btnBack = createRoundedBtn("BACK", 190, 450, 150);

        pnlForm.add(btnSave);
        pnlForm.add(btnBack);

        btnSave.addActionListener(this);
        btnBack.addActionListener(this);
    }

    private void setupField(JPanel p, String label, JTextField tf, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(20, y, 200, 20);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        p.add(lbl);
        tf.setBounds(20, y + 25, 320, 35);
        tf.setBorder(null);
        tf.setBackground(new Color(240, 242, 245));
        p.add(tf);
    }

    private JButton createRoundedBtn(String text, int x, int y, int w) {
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
        btn.setBounds(x, y, w, 45);
        btn.setBackground(accent);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else if (e.getSource() == btnSave) {
            String last = txtLast.getText().trim();
            String first = txtFirst.getText().trim();
            String acc = txtAccNo.getText().trim();
            String bal = txtBalance.getText().trim();

            if (last.isEmpty() || first.isEmpty() || acc.isEmpty() || bal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!last.matches("^[a-zA-Z\\s]+$") || !first.matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(this, "Invalid input. Letters only for names.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!acc.matches("^[0-9]+$") || !bal.matches("^[0-9.]+$")) {
                JOptionPane.showMessageDialog(this, "Invalid input. Numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount = Double.parseDouble(bal);
            String name = last + ", " + first + " " + txtMiddle.getText().trim();
            if (files.addAccount(name, (String) cbType.getSelectedItem(), acc, amount)) {
                JOptionPane.showMessageDialog(this, "Account Created Successfully!");
                dispose();
                new GUI1Frame(files).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Account Number already exists!");
            }
        }
    }
}