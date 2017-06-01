package themes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import shapes.Star5;

public class GoldPurpleStars implements ColorTheme {

	@Override
	public void setTheme(Graphics g, JPanel sp) {
		Star5 s = new Star5();
		g.setColor(new Color(140, 30, 140));
		g.fillRect(0, 0, sp.getBounds().width, sp.getBounds().height);
		int width = sp.getBounds().width/50;
		int height = width;
		g.setColor(new Color(250, 250, 70));
		boolean fill = true;
		for (int row = 0; row < sp.getBounds().width; row += width * 2) {
			fill = false;
			for (int col = 0; col < sp.getBounds().height; col += height) {
				fill = !fill;
				s.drawFromXY(g, g.getColor(), row, col, width, height, fill);
			}
			height += 1;
			width += 1;
		}
	}

	@Override
	public String name() {
		return "gold purple stars";
	}
}
