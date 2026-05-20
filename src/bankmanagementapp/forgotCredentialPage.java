
package bankmanagementapp;

import java.awt.*;
import javax.swing.*;

public class forgotCredentialPage extends JFrame {

    JLabel lblMiniTitle, lblTitle, lblDesc;
    JButton btnOpt1, btnOpt2, btnOpt3, btnBack;

    public forgotCredentialPage() {

        setTitle("Forgot Credentials");
        setSize(430, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        JPanel bg = new JPanel();
        bg.setLayout(null);
        bg.setBackground(new Color(220, 226, 241));
        setContentPane(bg);

        JPanel navbar = new JPanel();
        navbar.setBounds(0, 0, 430, 120);
        navbar.setLayout(null);
        navbar.setBackground(new Color(20, 45, 95));
        bg.add(navbar);

        lblMiniTitle = new JLabel("NEED HELP?");
        lblMiniTitle.setForeground(new Color(190, 200, 230));
        lblMiniTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMiniTitle.setBounds(35, 25, 200, 20);
        navbar.add(lblMiniTitle);

        lblTitle = new JLabel("Account Recovery");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setBounds(35, 45, 320, 40);
        navbar.add(lblTitle);

        RoundedPanel card = new RoundedPanel(35);
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(35, 160, 340, 300);
        bg.add(card);

        lblDesc = new JLabel("Select what you need help with");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(100, 100, 100));
        lblDesc.setBounds(30, 25, 250, 20);
        card.add(lblDesc);

        btnOpt1 = new frontPage.RoundedButton("1   I forgot my USER ID");
        btnOpt1.setBounds(25, 70, 290, 50);
        btnOpt1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnOpt1.setBackground(new Color(245, 246, 252));
        btnOpt1.setForeground(new Color(40, 40, 40));
        card.add(btnOpt1);

        btnOpt1.addActionListener(e -> {
            new forgotPage2().setVisible(true);
            dispose();
        });

        btnOpt2 = new frontPage.RoundedButton("2   I forgot my password");
        btnOpt2.setBounds(25, 135, 290, 50);
        btnOpt2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnOpt2.setBackground(new Color(245, 246, 252));
        btnOpt2.setForeground(new Color(40, 40, 40));
        card.add(btnOpt2);

        btnOpt2.addActionListener(e -> {
            new forgotPage1().setVisible(true);
            dispose();
        });

        btnOpt3 = new frontPage.RoundedButton("3   I forgot both");
        btnOpt3.setBounds(25, 200, 290, 50);
        btnOpt3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnOpt3.setBackground(new Color(245, 246, 252));
        btnOpt3.setForeground(new Color(40, 40, 40));
        card.add(btnOpt3);

        btnOpt3.addActionListener(e -> {
            new forgotPage2().setVisible(true);
            dispose();
        });

        btnBack = new frontPage.RoundedButton("Back to homepage");
        btnBack.setBounds(35, 520, 340, 42);
        btnBack.setBackground(new Color(220, 226, 241));
        btnBack.setForeground(new Color(70, 70, 70));
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bg.add(btnBack);

        btnBack.addActionListener(e -> {

            new frontPage().setVisible(true);
            dispose();

        });

        setVisible(true);
    }

    class RoundedPanel extends JPanel {

        private int radius;

        RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                    radius, radius);

            g2.dispose();

            super.paintComponent(g);
        }
    }

    public class theme {

        public static final Color Primary = new Color(25, 42, 86);
        public static final Color Light_blue = new Color(0x8bace0);
        public static final Color bg = new Color(230, 235, 245);
        public static final Color text_d = new Color(40, 40, 40);

    }
}
