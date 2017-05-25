package themes;

import java.awt.Color;
import java.awt.Graphics;

import main.ShapePanel;

public class YellowDiamonds implements ColorTheme {

	@Override
	public void setTheme(Graphics g, ShapePanel sp) {
		int incr = 24;
		int add = 1;
		sp.setBackground(new Color(230, 230, 0));
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
	public String name() {
		return "yellow diamonds";
	}

}
