/*
 * Your original code with minimal Swing-only improvements
 */
package bams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardFrame extends JFrame {

    private JLabel balanceLabel;
    private final User user;

    public DashboardFrame(User user) {
        this.user = user;
        initWindow();
        createHeader();
        createTitle();
        createBalanceBox();
        createButtons();
        addHoverEffects();
        setVisible(true);
    } private void initWindow() {
        setTitle("Bank Ko, Bank Mo, Bank ng Ebriwan!");
        setSize(430, 720);
        setLayout(null);  
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } private void createHeader() {
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(new Color(45, 45, 45));
        header.setBounds(0, 0, 430, 60);

        JLabel title = new JLabel("LOGO & NAME");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(20, 15, 200, 30);
        header.add(title);
        add(header);
    } private void createTitle() {
        JLabel dash = new JLabel("DASHBOARD");
        dash.setFont(new Font("Arial", Font.BOLD, 15));
        dash.setBounds(20, 70, 200, 30);
        add(dash);
    } private void createBalanceBox() {
        JPanel box = new JPanel();
        box.setLayout(null);
        box.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        box.setBounds(20, 110, 380, 110);

        JLabel info = new JLabel("CESS & JERSHA BANK");
        info.setFont(new Font("Arial", Font.BOLD, 12));
        info.setBounds(10, 10, 200, 20);
        box.add(info);

        balanceLabel = new JLabel("Available Balance: PHP " + user.getBalance());
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        balanceLabel.setForeground(new Color(0, 150, 0));
        balanceLabel.setBounds(10, 50, 300, 30);
        box.add(balanceLabel);

        add(box);
    } private void createButtons() {
        
        JButton deposit = new JButton("DEPOSIT MONEY");
        deposit.setBounds(25, 250, 170, 60);  
        styleButton(deposit);
        add(deposit);

        JButton transfer = new JButton("TRANSFER MONEY");
        transfer.setBounds(235, 250, 170, 60);  
        styleButton(transfer);
        add(transfer);

        JButton withdraw = new JButton("WITHDRAW FUNDS");
        withdraw.setBounds(25, 320, 170, 60);  
        styleButton(withdraw);
        add(withdraw);

        JButton pay = new JButton("PAY BILLS");
        pay.setBounds(235, 320, 170, 60);  
        styleButton(pay);
        add(pay);

        JButton load = new JButton("BUY LOAD");
        load.setBounds(25, 390, 170, 60);  
        styleButton(load);
        add(load);

        JButton tap = new JButton("TAP TO PAY");
        tap.setBounds(235, 390, 170, 60);  
        styleButton(tap);
        add(tap);

        JButton view = new JButton("VIEW ACCOUNT TRANSACTIONS");
        view.setBounds(25, 470, 380, 60);  
        styleButton(view);
        add(view);

        deposit.addActionListener(e -> { new DepositFrame(user, this); setVisible(false); });
        withdraw.addActionListener(e -> { new WithdrawFrame(user, this); setVisible(false); });
        transfer.addActionListener(e -> { new TransferFrame(user, this); setVisible(false); });
        load.addActionListener(e -> { new BuyLoadFrame(user, this); setVisible(false); });
        pay.addActionListener(e -> { new PayBillsFrame(user, this); setVisible(false); });
        tap.addActionListener(e -> { new TapToPayFrame(user, this); setVisible(false); });
        view.addActionListener(e -> { new ViewTransactionsFrame(user, this); setVisible(false); });
    }  private void styleButton(JButton btn) {
        btn.setBackground(new Color(80, 80, 80));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setBorderPainted(false);
        btn.setOpaque(true);
    }  private void addHoverEffects() {
        for (Component comp : getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                btn.addMouseListener(new MouseAdapter() 
                { public void mouseEntered(MouseEvent e) {
                        btn.setBackground(new Color(100, 100, 100));  
                    } public void mouseExited(MouseEvent e) {
                        btn.setBackground(new Color(80, 80, 80));  
                    }
                });
            }
        }
    }
    public void updateBalance(User user) {
        balanceLabel.setText("Available Balance: PHP " + user.getBalance());
    }
}