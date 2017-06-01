package themes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MetalTheme implements ColorTheme {

	@Override
	public void setTheme(Graphics g, JPanel sp) {
		int incr = sp.getBounds().width/80;
		int add = 3;
		g.setColor(new Color(100, 100, 100));
		for (int row = 0; row < sp.getBounds().width; row += incr) {
			for (int col = 0; col < sp.getBounds().height; col += incr) {
				g.fillRect(row, col, incr, incr);
			}
			if (row < sp.getBounds().width / 2) {
				g.setColor(new Color(g.getColor().getRed() + add, g.getColor().getGreen() + add,
						g.getColor().getBlue() + add));
			} else {
				g.setColor(new Color(g.getColor().getRed() - add, g.getColor().getGreen() - add,
						g.getColor().getBlue() - add));
			}
		}
	}

	@Override
	public String name() {
		return "metal theme";
	}
}
