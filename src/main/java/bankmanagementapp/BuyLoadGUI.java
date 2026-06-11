package bankmanagementapp;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class BuyLoadGUI extends JFrame {

    private TransactionManager manager;
    private BankAppGUI mainWindow;
    private JTextField phoneField;
    private JComboBox<String> telcoCombo;
    private JButton selectedAmountBtn = null;

    // preset load amounts
    private static final int[] AMOUNTS = {20, 50, 100, 150, 200, 300};

    public BuyLoadGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;
        this.mainWindow = mainWindow;

        setTitle("Buy Load");
        setSize(430, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(235, 240, 250));

        //navbar
        JPanel navPanel = new JPanel(null);
        navPanel.setBounds(0, 0, 420, 45);
        navPanel.setBackground(new Color(15, 28, 60));
        add(navPanel);

        JButton backBTN = new JButton("← Back");
        backBTN.setBounds(10, 9, 70, 27);
        backBTN.setFont(new Font("Tahoma", Font.PLAIN, 12));
        backBTN.setForeground(Color.WHITE);
        backBTN.setBackground(new Color(37, 65, 130));
        backBTN.setFocusPainted(false);
        backBTN.setBorder(BorderFactory.createLineBorder(new Color(80, 110, 180), 1, true));
        backBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navPanel.add(backBTN);

        //header
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 45, 420, 120);
        headerPanel.setBackground(new Color(25, 45, 95));
        add(headerPanel);

        JLabel title = new JLabel("Buy Load");
        title.setBounds(0, 30, 420, 35);
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(title);

        JLabel subtitle = new JLabel("Mobile reload");
        subtitle.setBounds(0, 68, 420, 18);
        subtitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
        subtitle.setForeground(new Color(160, 185, 225));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(subtitle);

        //form
        JPanel card = new JPanel(null);
        card.setBounds(24, 186, 372, 390);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
        add(card);

        JLabel telcoLBL = new JLabel("Telco");
        telcoLBL.setBounds(20, 22, 250, 18);
        telcoLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        telcoLBL.setForeground(new Color(60, 80, 120));
        card.add(telcoLBL);

        telcoCombo = new JComboBox<>(new String[]{"Globe", "Smart", "TNT", "Sun", "DITO"});
        telcoCombo.setBounds(20, 46, 330, 38);
        telcoCombo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        telcoCombo.setBackground(Color.WHITE);
        card.add(telcoCombo);

        JLabel phoneLBL = new JLabel("Phone Number");
        phoneLBL.setBounds(20, 100, 250, 18);
        phoneLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        phoneLBL.setForeground(new Color(60, 80, 120));
        card.add(phoneLBL);

        phoneField = new JTextField("0917XXXXXXX");
        phoneField.setBounds(20, 124, 330, 42);
        phoneField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        phoneField.setHorizontalAlignment(JTextField.CENTER);
        phoneField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        card.add(phoneField);

        JLabel amountLBL = new JLabel("Select Amount (PHP)");
        amountLBL.setBounds(20, 182, 250, 18);
        amountLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        amountLBL.setForeground(new Color(60, 80, 120));
        card.add(amountLBL);

        //amount
        JButton[] amountBtns = new JButton[AMOUNTS.length];
        int btnW = 97, btnH = 40, startX = 20, startY = 208, gapX = 10, gapY = 10;

        for (int i = 0; i < AMOUNTS.length; i++) {
            final int idx = i;
            JButton btn = new JButton("₱" + AMOUNTS[i]);
            btn.setFont(new Font("Tahoma", Font.PLAIN, 13));
            btn.setBackground(new Color(235, 240, 250));
            btn.setForeground(new Color(25, 45, 95));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));

            int col = i % 3;
            int row = i / 3;
            btn.setBounds(startX + col * (btnW + gapX), startY + row * (btnH + gapY), btnW, btnH);

            btn.addActionListener(e -> {
                if (selectedAmountBtn != null) {
                    selectedAmountBtn.setBackground(new Color(235, 240, 250));
                    selectedAmountBtn.setForeground(new Color(25, 45, 95));
                    selectedAmountBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true));
                }
                btn.setBackground(new Color(25, 45, 95));
                btn.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createLineBorder(new Color(25, 45, 95), 1, true));
                selectedAmountBtn = btn;
            });

            amountBtns[i] = btn;
            card.add(btn);
        }

        JLabel dateLBL = new JLabel("Date");
        dateLBL.setBounds(20, 314, 250, 18);
        dateLBL.setFont(new Font("Tahoma", Font.BOLD, 12));
        dateLBL.setForeground(new Color(60, 80, 120));
        card.add(dateLBL);

        JTextField dateField = new JTextField(LocalDate.now().toString());
        dateField.setBounds(20, 338, 330, 42);
        dateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateField.setHorizontalAlignment(JTextField.CENTER);
        dateField.setEditable(false);
        dateField.setBackground(new Color(235, 240, 250));
        dateField.setForeground(new Color(100, 120, 160));
        dateField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 215, 235), 1, true),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        card.add(dateField);

        //submit
        JButton submitBTN = new JButton("Buy Load");
        submitBTN.setBounds(24, 600, 372, 48);
        submitBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
        submitBTN.setBackground(new Color(25, 45, 95));
        submitBTN.setForeground(Color.WHITE);
        submitBTN.setFocusPainted(false);
        submitBTN.setBorderPainted(false);
        submitBTN.setOpaque(true);
        submitBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        submitBTN.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { submitBTN.setBackground(new Color(37, 65, 130)); }
            public void mouseExited (MouseEvent e) { submitBTN.setBackground(new Color(25, 45, 95)); }
        });
        add(submitBTN);

        //action listeners
        backBTN.addActionListener(e -> { mainWindow.setVisible(true); dispose(); });

        submitBTN.addActionListener(e -> {
            if (selectedAmountBtn == null) {
                JOptionPane.showMessageDialog(this, "Please select a load amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String phone = phoneField.getText().trim();
        
        if(phone.isEmpty() || phone.equals("0917XXXXXXXX")){
            JOptionPane.showMessageDialog(this, "Please enter a phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!phone.matches("^09[0-9]{9}$")){
            JOptionPane.showMessageDialog(this, "Invalid phone number.\nMust be 11 digits and start with 09(e.g. 09171234567", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
            //get amount
            double amount = Double.parseDouble(selectedAmountBtn.getText().replace("₱", ""));
            String telco = telcoCombo.getSelectedItem() + " (" + phone + ")";
            String result = manager.addTransaction("Buy Load", amount, dateField.getText(), telco);
            if (result.equals("SUCCESS")) {
                mainWindow.updateBalance("Debit");
                JOptionPane.showMessageDialog(this,
                    "Load sent successfully!\nNew Balance: PHP " + String.format("%.2f", manager.getBalance()));
                mainWindow.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}