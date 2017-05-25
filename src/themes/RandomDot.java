package themes;

import java.awt.Color;
import java.awt.Graphics;

import main.ShapePanel;

public class RandomDot implements ColorTheme {

	@Override
	public void setTheme(Graphics g, ShapePanel sp) {
		int incr = 32;
		for (int row = 0; row < sp.getBounds().width; row += incr) {
			for (int col = 0; col < sp.getBounds().height; col += incr) {
				g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 155),
						(int) (Math.random() * 55)));
				g.fillOval(row, col, incr / 2, incr / 2);
			}
		}
	}

	@Override
	public String name() {
		return "random dot";
	}
}
