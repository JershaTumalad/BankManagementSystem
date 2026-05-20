package AccountManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchPage extends BaseFrame implements ActionListener {

    private AccountFiles files;
    private JTextField txtAccNo;
    private JButton btnSearch, btnBack;

    private JPanel pnlResult;
    private JLabel lblResultName, lblResultType, lblResultAccNo, lblResultBal, logoImage, lblStateBank;

    public SearchPage(AccountFiles files) {
        super("Bank Account Management");
        this.files = files;
        initComponents();
    }

    @Override
    protected void initComponents() {
        
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(NAVY);
        pnlHeader.setBounds(0, 0, 430, 80);
        pnlHeader.setLayout(null);
        add(pnlHeader);
        
        lblStateBank = new JLabel("STATE Bank");
        lblStateBank.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblStateBank.setForeground(Color.WHITE);
        lblStateBank.setBounds(30, 10, 350, 20);
        pnlHeader.add(lblStateBank);
        
        JLabel lblTitle = new JLabel("Search Account");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblTitle.setForeground(WHITE);
        lblTitle.setBounds(30,36,300,30);
        pnlHeader.add(lblTitle);

        JPanel pnlCard = buildCard(25, 100, 360, 192);
        add(pnlCard);

        JLabel lblSection = new JLabel("FIND AN ACCOUNT");
        lblSection.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSection.setForeground(Color.DARK_GRAY);
        lblSection.setBounds(20, 10, 200, 16);
        pnlCard.add(lblSection);

        JLabel lblAccNo = new JLabel("ACCOUNT NUMBER");
        lblAccNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAccNo.setForeground(Color.GRAY);
        lblAccNo.setBounds(20, 45, 200, 16);
        pnlCard.add(lblAccNo);

        txtAccNo = new JTextField();
        txtAccNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtAccNo.setBounds(20, 75, 318, 38);
        txtAccNo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtAccNo.setBackground(LIGHT_GRAY);
        pnlCard.add(txtAccNo);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(20, 129, 318, 48);
        btnSearch.setBackground(NAVY);
        btnSearch.setForeground(WHITE);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
        pnlCard.add(btnSearch);

        pnlResult = new JPanel();
        pnlResult.setBackground(new Color(235, 250, 240));
        pnlResult.setBounds(27, 315, 360, 155);
        pnlResult.setLayout(null);
        pnlResult.setVisible(false);
        add(pnlResult);

        JLabel lblFound = new JLabel("Account found");
        lblFound.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFound.setForeground(new Color(40, 140, 80));
        lblFound.setBounds(15, 12, 250, 18);
        pnlResult.add(lblFound);

        lblResultName = new JLabel("--");
        lblResultName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblResultName.setForeground(NAVY);
        lblResultName.setBounds(15, 33, 320, 28);
        pnlResult.add(lblResultName);

        lblResultType = new JLabel("--");
        lblResultType.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblResultType.setForeground(Color.DARK_GRAY);
        lblResultType.setBounds(15, 61, 250, 18);
        pnlResult.add(lblResultType);

        JSeparator sep = new JSeparator();
        sep.setBounds(15, 88, 325, 2);
        sep.setForeground(new Color(200, 225, 210));
        pnlResult.add(sep);

        JLabel lblAccLabel = new JLabel("Account no.");
        lblAccLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblAccLabel.setForeground(Color.BLACK);
        lblAccLabel.setBounds(15, 98, 120, 16);
        pnlResult.add(lblAccLabel);

        JLabel lblBalLabel = new JLabel("Balance");
        lblBalLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblBalLabel.setForeground(Color.BLACK);
        lblBalLabel.setBounds(200, 98, 120, 16);
        pnlResult.add(lblBalLabel);

        lblResultAccNo = new JLabel("--");
        lblResultAccNo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblResultAccNo.setForeground(NAVY);
        lblResultAccNo.setBounds(15, 116, 160, 20);
        pnlResult.add(lblResultAccNo);

        lblResultBal = new JLabel("--");
        lblResultBal.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblResultBal.setForeground(NAVY);
        lblResultBal.setBounds(200, 116, 130, 20);
        pnlResult.add(lblResultBal);

        btnBack = new JButton("Back");
        btnBack.setBounds(27, 490, 360, 48);
        btnBack.setBackground(LIGHT_BLUE);
        btnBack.setForeground(NAVY);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(btnBack);

        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            dispose();
            new GUI1Frame(files).setVisible(true);
        } else if (e.getSource() == btnSearch) {
            doSearch();
        }
    }

    private void doSearch() {
        String input = txtAccNo.getText().trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an account number.", "Error", JOptionPane.ERROR_MESSAGE);
            pnlResult.setVisible(false);
            return;
        }

        if (!input.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Invalid input! Account Number must contain numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
            pnlResult.setVisible(false);
            return;
        }

        Account found = files.searchAccount(input);

        if (found != null) {
            lblResultName.setText(found.getName());
            lblResultType.setText(found.getAccountType());
            lblResultAccNo.setText(found.getAccountNo());
            lblResultBal.setText(String.format("P %,.2f", found.getBalance()));
            pnlResult.setVisible(true);
        } else {
            pnlResult.setVisible(false);
            JOptionPane.showMessageDialog(this, "No account found with number: " + input, "Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }
}