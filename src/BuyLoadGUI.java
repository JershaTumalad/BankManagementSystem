package BAMS;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class BuyLoadGUI extends JFrame {
    private TransactionManager manager;
    private JRadioButton globe, smart, dito, tm, tnt;
    private JLabel loadLBL, amountLBL, dateLBL, balanceLBL;
    private JTextField amountFLD, dateFLD;
    private JButton subBTN, backBTN;
    ButtonGroup group;

    public BuyLoadGUI(TransactionManager manager, BankAppGUI mainWindow) {
        this.manager = manager;
        setTitle("Buy Load");
        setSize(400, 700);
        setLayout(null);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 0, 390, 130);
        headerPanel.setBackground(new Color(30, 50, 100));
        add(headerPanel);

        backBTN = new JButton("← Back");
        backBTN.setBounds(10, 10, 90, 30);
        backBTN.setBackground(Color.WHITE);
        backBTN.setForeground(new Color(30, 50, 100));
        backBTN.setFont(new Font("Arial", Font.BOLD, 12));
        backBTN.setFocusPainted(false);
        backBTN.setBorder(BorderFactory.createLineBorder(new Color(200, 210, 230)));
        headerPanel.add(backBTN);

        JLabel titleLBL = new JLabel("Buy Load", SwingConstants.CENTER);
        titleLBL.setBounds(0, 45, 390, 35);
        titleLBL.setFont(new Font("Arial", Font.BOLD, 24));
        titleLBL.setForeground(Color.WHITE);
        headerPanel.add(titleLBL);

        JLabel subTitleLBL = new JLabel("Select telco and enter amount", SwingConstants.CENTER);
        subTitleLBL.setBounds(0, 82, 390, 20);
        subTitleLBL.setFont(new Font("Arial", Font.PLAIN, 13));
        subTitleLBL.setForeground(new Color(180, 200, 230));
        headerPanel.add(subTitleLBL);

        loadLBL = new JLabel("Choose Telco:");
        loadLBL.setFont(new Font("Arial", Font.BOLD, 14));
        loadLBL.setForeground(new Color(30, 50, 100));
        loadLBL.setBounds(30, 145, 330, 25);
        add(loadLBL);

        JPanel telcoPanel = new JPanel(null);
        telcoPanel.setBounds(30, 173, 330, 165);
        telcoPanel.setBackground(Color.WHITE);
        telcoPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 50, 100), 1));
        add(telcoPanel);

        globe = makeRadio("Globe",0);
        smart = makeRadio("Smart",1);
        dito = makeRadio("DITO",2);
        tm = makeRadio("TM",3);
        tnt = makeRadio("TNT",4);

        telcoPanel.add(globe);
        telcoPanel.add(smart);
        telcoPanel.add(dito);
        telcoPanel.add(tm);
        telcoPanel.add(tnt);

        group = new ButtonGroup();
        group.add(globe);
        group.add(smart);
        group.add(dito);
        group.add(tm);
        group.add(tnt);

        amountLBL = new JLabel("Amount (PHP):");
        amountLBL.setFont(new Font("Arial", Font.BOLD, 14));
        amountLBL.setForeground(new Color(30, 50, 100));
        amountLBL.setBounds(30, 352, 330, 25);
        add(amountLBL);

        amountFLD = new JTextField();
        amountFLD.setBounds(30, 380, 330, 38);
        amountFLD.setFont(new Font("Arial", Font.PLAIN, 14));
        amountFLD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(30, 50, 100), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(amountFLD);

        dateLBL = new JLabel("Date:");
        dateLBL.setFont(new Font("Arial", Font.BOLD, 14));
        dateLBL.setForeground(new Color(30, 50, 100));
        dateLBL.setBounds(30, 432, 330, 25);
        add(dateLBL);

        String today = LocalDate.now().toString();
        dateFLD = new JTextField(today);
        dateFLD.setBounds(30, 460, 330, 38);
        dateFLD.setFont(new Font("Arial", Font.PLAIN, 14));
        dateFLD.setEditable(false);
        dateFLD.setBackground(new Color(220, 225, 235));
        dateFLD.setForeground(new Color(80, 80, 80));
        dateFLD.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(180, 190, 210), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(dateFLD);

        subBTN = new JButton("Submit");
        subBTN.setBounds(95, 518, 200, 45);
        subBTN.setBackground(new Color(30, 50, 100));
        subBTN.setForeground(Color.WHITE);
        subBTN.setFont(new Font("Arial", Font.BOLD, 15));
        subBTN.setFocusPainted(false);
        subBTN.setBorder(BorderFactory.createEmptyBorder());
        add(subBTN);

        balanceLBL = new JLabel("Balance: PHP " + String.format("%.2f", manager.getBalance()));
        balanceLBL.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLBL.setForeground(new Color(30, 50, 100));
        balanceLBL.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLBL.setBounds(30, 578, 330, 30);
        add(balanceLBL);

        backBTN.addActionListener(e -> {
            mainWindow.setVisible(true);
            dispose();
        });

        subBTN.addActionListener(e -> {
            String selectedTelco = "";
            if      (globe.isSelected()) selectedTelco = "Globe";
            else if (smart.isSelected()) selectedTelco = "Smart";
            else if (dito.isSelected())  selectedTelco = "DITO";
            else if (tm.isSelected())    selectedTelco = "TM";
            else if (tnt.isSelected())   selectedTelco = "TNT";

            if (selectedTelco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a telco.");
                return;
            }

            String date = dateFLD.getText();
            try {
                double amount = Double.parseDouble(amountFLD.getText().trim());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be greater than 0.");
                    return;
                }
                if (amount > manager.getBalance()) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance.");
                    return;
                }
                String result = manager.addTransaction("Buy Load", amount, date, selectedTelco);
                if (result.equals("SUCCESS")) {
                    JOptionPane.showMessageDialog(this, "Load purchased from " + selectedTelco + " successful!");
                    balanceLBL.setText("Balance: PHP " + String.format("%.2f", manager.getBalance()));
                    amountFLD.setText("");
                    group.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(this, result);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Enter a number.");
            }
        });

        setVisible(true);
    }

    private JRadioButton makeRadio(String text, int index) {
        JRadioButton rb = new JRadioButton(text);
        rb.setBounds(15, 10 + (index * 30), 300, 25);
        rb.setFont(new Font("Arial", Font.PLAIN, 14));
        rb.setForeground(new Color(30, 50, 100));
        rb.setBackground(Color.WHITE);
        rb.setFocusPainted(false);
        return rb;
    }
}