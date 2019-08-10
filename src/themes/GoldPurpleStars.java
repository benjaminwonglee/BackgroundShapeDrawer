package themes;

import misc.FillStatus;
import shapes.Star5;

import javax.swing.*;
import java.awt.*;

public class GoldPurpleStars implements ITheme {

    @Override
    public void applyTheme(Graphics g, JPanel sp) {
        Star5 s = new Star5();
        g.setColor(new Color(100, 30, 100));
        g.fillRect(0, 0, sp.getBounds().width, sp.getBounds().height);
        int width = sp.getBounds().width / 50;
        int height = width - 3;
        g.setColor(new Color(230, 190, 50));
        for (int row = 0; row < sp.getBounds().width; row += width * 2) {
            FillStatus fill = FillStatus.NONE;
            for (int col = 0; col < sp.getBounds().height; col += height) {
                if (fill == FillStatus.NONE) {
                    fill = FillStatus.FULL;
                } else {
                    fill = FillStatus.NONE;
                }
                s.drawFromXY(g, g.getColor(), row, col, width, height, fill);
            }
            height += 3;
            width += 3;
        }
    }

    @Override
    public Theme getThemeEnum() {
        return Theme.GOLD_PURPLE_STARS;
    }
}
