package bankmanagementapp;

import java.awt.*;
import javax.swing.*;



    
    JPanel  navbar, mainPanel ;
    JPanel  sep ;
    JButton btnOption1;        
    JButton btnOption2;        
    JButton btnOption3;          
    JLabel statement,statemnt1, navTite, appTit, tagLine ,logoLabel;
   
    
      public frontPage(){
          
          
          setSize(430, 720);
          setLocationRelativeTo(null);
          setTitle("STATE BANK APPLICATION");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLayout(null);
          setResizable(false);
          
          
          mainPanel = new JPanel();
          mainPanel.setLayout(null);
          mainPanel.setBackground(BG);
          setContentPane(mainPanel);// ito main Panel ung baby blue na bg

    
          
         navbar = new JPanel(null);
         navbar.setBounds(0, 0, 430, 90);
         navbar.setBackground(PRIMARY); // primary blue -- navbar sa taas na may banklogo etc
         mainPanel.add(navbar);
         
         
        logoLabel = new JLabel();        //logo ng bank app
        logoLabel.setBounds(16, 15, 65, 65);   
        ImageIcon logoIcon = loadImage("/images/bank_logo.png", 70, 70);
        if (logoIcon != null) {
           logoLabel.setIcon(logoIcon);        
        } else {
            
            logoLabel.setText("S");
            logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
            logoLabel.setForeground(LIGHT_BLUE);
        }
           navbar.add(logoLabel);
         
          statement = new JLabel("WELCOME TO STATE-BANK APP ");
          statement.setForeground(LIGHT_BLUE);
          statement.setBounds(90, 18, 300, 16);
          statement.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 15));
          navbar.add(statement);

        navTite = new JLabel("Good Day!");
        navTite.setForeground(Color.WHITE);
        navTite.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
        navTite.setBounds(90, 34, 300, 36);
        navbar.add(navTite);

        heroCard = buildRoundedPanel(20);
        heroCard.setBounds(20, 112, 370, 500);
        heroCard.setLayout(null);
        mainPanel.add(heroCard);

        JPanel logoCircle = new JPanel();
        logoCircle.setBackground(LIGHT_BLUE);
        logoCircle.setBounds(40, 40, 52, 52);
        heroCard.add(logoCircle);
            
        
        appTit = new JLabel("STATE BANK APP");
        appTit.setBounds(105, 40, 280, 28);
        appTit.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 25));
        appTit.setForeground(TEXT_DARK);
        heroCard.add(appTit);
        
        tagLine = new JLabel ("Your trusted digital banking partner");
        tagLine.setBounds(110,70, 280, 18);
        tagLine.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tagLine.setForeground(TEXT_MUTED);
        heroCard.add(tagLine);

        
sep = new JPanel();// ung nag didivide sa white na panel(eme emehan lang)
sep.setBackground(BG);
sep.setBounds(0, 120, 400, 30);
heroCard.add(sep);
        
          statemnt1 = new JLabel("WHAT CAN I DO FOR YOU TODAY? ");
          statemnt1.setBounds(18, 170, 280, 18);
          statemnt1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
          statemnt1.setForeground(TEXT_MUTED);
          heroCard.add(statemnt1);

         




          btnOption1 = new frontPage.RoundedButton("LOG IN AN ACCOUNT");
          btnOption1.setBounds(30, 230, 310, 50);
           btnOption1.setBackground(LIGHT_BLUE);
           btnOption1.setForeground(Color.BLACK);
          btnOption1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
          heroCard.add(btnOption1);
          btnOption1.addActionListener( e -> {
          new logInPage();    
          this.dispose();
          
          });
          
            btnOption2 = new frontPage.RoundedButton("FORGOT USER-ID OR PASSWORD");
          btnOption2.setBounds(30, 300, 310, 50);
            btnOption2.setBackground(LIGHT_BLUE);
            btnOption2.setForeground(Color.BLACK);
           btnOption2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
          heroCard.add(btnOption2);
          btnOption2.addActionListener( e -> {
          new forgotCredentialPage();
          this.dispose();
          
          });
          
          btnOption3 = new frontPage.RoundedButton("CREATE AN ACCOUNT");
          btnOption3.setBounds(30, 370, 310, 50);
            btnOption3.setBackground(LIGHT_BLUE);
        btnOption3.setForeground(Color.BLACK);
        btnOption3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));      
        btnOption3.setFocusPainted(false);

        btnOption3.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
 
          heroCard.add(btnOption3);
          btnOption3.addActionListener(e -> {
          new accounts();
          this.dispose();
          
          });

          setVisible(true);
          
          
          
          
      }
      
      
          
    public static final Color PRIMARY      = new Color(25, 48, 90);   // # dark blue
    public static final Color LIGHT_BLUE   = new Color(139, 172, 224); // #8bace0
    public  static final Color BG           = new Color(200, 210, 230); // baby blue
    public static final Color CARD_BG      = Color.WHITE; // white sa hero card
    public static final Color TEXT_DARK    = new Color(25, 48, 90); // app name
    public static final Color TEXT_MUTED   = new Color(100, 110, 130);// sa tagline & stmnt1
    public static final Color ICON_BG      = new Color(232, 238, 248); // soft blue tint
     
     
static class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.BLACK);
        setBorderPainted(false);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        super.paintComponent(g);
    }
}
    


    
       public ImageIcon loadImage(String path, int width, int height) {
        try {
            
            java.net.URL imgURL = getClass().getResource(path);
 
            if (imgURL == null) {
                
                return null;
            }
            Image img = new ImageIcon(imgURL).getImage()
                            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
 
        } catch (Exception e) {
            return null; 
        }
        
        
        
       }
       
       
}

       
       
      

   
