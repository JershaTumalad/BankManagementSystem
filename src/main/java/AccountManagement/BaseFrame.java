
package AccountManagement;

import java.awt.*;
import javax.swing.*;

// Abstract base class for the search and view page.
//  It defines shared colors and common frame settings used across the application.
public abstract class BaseFrame extends JFrame {

    public final Color NAVY = new Color(25, 48, 90);
    public final Color LIGHT_BLUE = new Color(244, 246, 252);
    public final Color ACCENT = new Color(139, 172, 224);
    public final Color WHITE = Color.WHITE;
    public final Color LIGHT_GRAY = new Color(245, 245, 245);
    public final Color RED_BTN = new Color(200, 70, 70);
    public final Color CARD_BG = new Color(235, 240, 252);
    public final Color PINK_WARN = new Color(255, 214, 214);
    public final Color backgroundColor = new Color(200, 210, 230);

    public BaseFrame(String title) {
        setTitle(title);
        setSize(430, 720);
        setLayout(null);
        getContentPane().setBackground(backgroundColor); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    protected JPanel buildCard(int x, int y, int width, int height) {
        JPanel card = new JPanel();
        card.setBackground(WHITE);
        card.setBounds(x, y, width, height);
        card.setLayout(null);
        return card;
    }

    protected abstract void initComponents();
}
