package themes;

import javax.swing.*;
import java.awt.*;

public class GradientBlueRed implements Theme {

    @Override
    public void applyTheme(Graphics g, JPanel sp) {
        Color cOne = new Color(30, 20, 40);
        int incr = sp.getBounds().width / 80;
        g.setColor(cOne);
        for (int row = 0; row < sp.getBounds().width; row += incr) {
            for (int col = 0; col < sp.getBounds().height; col += incr) {
                g.fillRect(row, col, incr, incr);
            }
            g.setColor(new Color(cOne.getRed() + (row / incr) * 2, cOne.getGreen(), cOne.getBlue()));
        }
    }

    @Override
    public ThemeName getThemeName() {
        return ThemeName.GRADIENT_BLUE_RED;
    }
}
