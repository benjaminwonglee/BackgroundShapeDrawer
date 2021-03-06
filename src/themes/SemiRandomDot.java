package themes;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SemiRandomDot implements ITheme {

    @Override
    public void applyTheme(Graphics g, JPanel sp) {
        g.setColor(Color.black);
        g.fillRect(sp.getBounds().x, sp.getBounds().y, sp.getBounds().width, sp.getBounds().height);
        int incr = sp.getBounds().width / 60;
        Random r = new Random(11);
        for (int row = 0; row < sp.getBounds().width; row += incr) {
            for (int col = 0; col < sp.getBounds().height; col += incr) {
                g.setColor(new Color(r.nextInt(200) + 55, r.nextInt(155), r.nextInt(55)));
                g.fillOval(row, col, incr / 2, incr / 2);
            }
            r = new Random(11);
        }
    }

    @Override
    public Theme getThemeEnum() {
        return Theme.SEMI_RANDOM_DOT;
    }
}
