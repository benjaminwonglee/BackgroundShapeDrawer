package themes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class YellowDiamonds implements Theme {

    @Override
    public void setTheme(Graphics g, JPanel sp) {
        int incr = sp.getBounds().width / 40;
        int add = 1;
        g.setColor(new Color(230, 230, 0));
        g.fillRect(0, 0, sp.getBounds().width, sp.getBounds().height);
        g.setColor(new Color(0, 100, 100));
        for (int row = 0; row < sp.getBounds().width; row += incr) {
            for (int col = 0; col < sp.getBounds().height; col += incr) {
                g.fillOval(row - 2, col - 2, incr + 4, incr + 4);
            }
            if (row < sp.getBounds().width / 2) {
                g.setColor(new Color(g.getColor().getRed() + add, g.getColor().getGreen() + add,
                        g.getColor().getBlue() - add));
            } else {
                g.setColor(new Color(g.getColor().getRed() + add, g.getColor().getGreen() - add,
                        g.getColor().getBlue() + add));
            }
        }
    }

    @Override
    public String getThemeName() {
        return ThemeName.YELLOW_DIAMONDS.getThemeName();
    }
}
