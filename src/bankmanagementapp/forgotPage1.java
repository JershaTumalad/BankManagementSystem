
package bankmanagementapp;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class forgotPage1 extends JFrame {

    ArrayList<accCreationPage> acc;

    JLabel lblMiniTitle, lblTitle, lblDesc, lblUser;
    JTextField tfUserid;
    JButton btnSubmit, btnBack;

    public forgotPage1() {

        this.acc = accDatabase.acc;

        setTitle("Forgot Password");
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

        lblMiniTitle = new JLabel("PASSWORD RECOVERY");
        lblMiniTitle.setForeground(new Color(190, 200, 230));
        lblMiniTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMiniTitle.setBounds(35, 25, 250, 20);
        navbar.add(lblMiniTitle);

        lblTitle = new JLabel("Forgot Password");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setBounds(35, 45, 320, 40);
        navbar.add(lblTitle);

        RoundedPanel card = new RoundedPanel(35);
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(35, 170, 340, 270);
        bg.add(card);

        lblDesc = new JLabel("Enter your registered USER ID");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(100, 100, 100));
        lblDesc.setBounds(30, 30, 250, 20);
        card.add(lblDesc);

        lblUser = new JLabel("USER ID");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(new Color(110, 110, 110));
        lblUser.setBounds(30, 75, 100, 20);
        card.add(lblUser);

        tfUserid = new JTextField();
        tfUserid.setBounds(30, 100, 280, 42);
        tfUserid.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfUserid.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tfUserid.setBackground(new Color(245, 246, 252));
        card.add(tfUserid);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(30, 180, 280, 42);
        btnSubmit.setBackground(new Color(138, 173, 255));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        card.add(btnSubmit);

        btnSubmit.addActionListener(e -> {

            String inputUserId = tfUserid.getText();
            boolean found = false;

            for (accCreationPage user : acc) {

                if (user.getUserId().equals(inputUserId)) {

                    found = true;
                    break;
                }
            }

            if (found) {

                JOptionPane.showMessageDialog(this,
                        "USER ID Found!");

                new logInPage();
                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "USER ID not registered!");

            }

        });

        btnBack = new JButton("Back to homepage");
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
