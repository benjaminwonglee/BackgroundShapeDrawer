package themes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import shapes.Star5;

public class GoldPurpleStars implements Theme {

    @Override
    public void setTheme(Graphics g, JPanel sp) {
        Star5 s = new Star5();
        g.setColor(new Color(100, 30, 100));
        g.fillRect(0, 0, sp.getBounds().width, sp.getBounds().height);
        int width = sp.getBounds().width / 50;
        int height = width - 3;
        g.setColor(new Color(230, 190, 50));
        for (int row = 0; row < sp.getBounds().width; row += width * 2) {
            boolean fill = false;
            for (int col = 0; col < sp.getBounds().height; col += height) {
                fill = !fill;
                s.drawFromXY(g, g.getColor(), row, col, width, height, fill);
            }
            height += 3;
            width += 3;
        }
    }

    @Override
    public String name() {
        return "gold purple stars";
    }
}
