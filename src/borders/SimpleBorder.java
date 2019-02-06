package borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class SimpleBorder implements Border {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int wd, int ht) {
        g.setColor(new Color(10, 10, 10));
        g.drawRect(x, y, wd - 1, ht - 1);
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