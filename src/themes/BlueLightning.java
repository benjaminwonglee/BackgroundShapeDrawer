package themes;

import misc.FillStatus;
import shapes.Lightning;

import javax.swing.*;
import java.awt.*;

public class BlueLightning implements Theme {

    @Override
    public void applyTheme(Graphics g, JPanel sp) {
        Lightning l = new Lightning();
        g.setColor(new Color(20, 70, 200));
        g.fillRect(0, 0, sp.getBounds().width, sp.getBounds().height);
        int width = sp.getBounds().width / 80;
        int height = width * 2;
        g.setColor(new Color(20, 20, 220));
        int incr = 0;
        for (int row = 0; row < sp.getBounds().width; row += (width * 2)) {
            for (int col = 0; col < sp.getBounds().height; col += height + 5) {
                incr++;
                if (incr % 10 == 0) {
                    g.setColor(new Color(g.getColor().getRed(), g.getColor().getGreen(),
                            g.getColor().getBlue() - width / 10));
                }

                l.drawFromXY(g, g.getColor(), row, col, width, height, FillStatus.FULL);
            }
        }
    }

    @Override
    public ThemeName getThemeName() {
        return ThemeName.BLUE_LIGHTNING;
    }
}
