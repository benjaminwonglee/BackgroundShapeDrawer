package themes;

import javax.swing.*;
import java.awt.*;

public class RandomDot implements ITheme {

    @Override
    public void applyTheme(Graphics g, JPanel sp) {
        g.setColor(Color.black);
        g.fillRect(sp.getBounds().x, sp.getBounds().y, sp.getBounds().width, sp.getBounds().height);
        int increment = sp.getBounds().width / 50;
        for (int row = 0; row < sp.getBounds().width; row += increment) {
            for (int col = 0; col < sp.getBounds().height; col += increment) {
                g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 155),
                        (int) (Math.random() * 55)));
                g.fillOval(row, col, increment / 2, increment / 2);
            }
        }
    }

    @Override
    public Theme getThemeEnum() {
        return Theme.RANDOM_DOT;
    }
}
