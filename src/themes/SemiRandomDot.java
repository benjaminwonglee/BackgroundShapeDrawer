package themes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class SemiRandomDot implements ColorTheme {

	@Override
	public void setTheme(Graphics g, JPanel sp) {
		int incr = 22;
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
	public String name() {
		return "semi random dot";
	}
}
