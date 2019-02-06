package borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class OptionBorder implements Border {
    private String label;
    private Color color;

    public OptionBorder(String label, Color c) {
        this.label = label;
        this.color = c;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int wd, int ht) {
        g.setColor(color);
        if (label.length() > 18) {
            g.setFont(new Font("Georgia", Font.ITALIC, 20));
        } else {
            g.setFont(new Font("Georgia", Font.ITALIC, 23));
        }
        int multiplier = 230 / ht;
        for (int i = 0; i < ht; i++) {
            Color curr = g.getColor();
            g.setColor(new Color(curr.getRed() - multiplier, curr.getGreen(), curr.getBlue()));
            g.drawLine(x, y + i, x + wd, y + i);
        }
        g.setColor(new Color(10, 10, 50));
        for (int i = 0; i < 4; i++) {
            g.drawRect(x + i, y + i, wd - 1, ht - 1);
        }
        int rgb = 150;

        // Shadow
        for (int i = 1; i < 2; i++) {
            g.setColor(new Color(rgb - 80, rgb - 80, 0));
            g.drawString(label, wd / 2 - (g.getFontMetrics().stringWidth(label) / 2) - i, ht / 2 + 14 - i);
        }
        g.setColor(new Color(rgb, rgb, 0));
        g.drawString(label, wd / 2 - (g.getFontMetrics().stringWidth(label) / 2), ht / 2 + 14);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public Insets getBorderInsets(Component arg0) {
        return new Insets(0, 0, 0, 0);
    }
}