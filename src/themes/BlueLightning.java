package themes;

import java.awt.Color;
import java.awt.Graphics;

import main.ShapePanel;
import shapes.Lightning;

public class BlueLightning implements ColorTheme {

	@Override
	public void setTheme(Graphics g, ShapePanel sp) {
		Lightning l = new Lightning();
		g.setColor(new Color(20, 70, 200));
		g.fillRect(0, 0, sp.getBounds().width, sp.getBounds().height);
		int width = 20;
		int height = width * 2;
		g.setColor(new Color(20, 20, 220));
		boolean fill = true;
		int incr = 0;
		for (int row = 0; row < sp.getBounds().width; row += (width * 2)) {
			for (int col = 0; col < sp.getBounds().height; col += height + 5) {
				incr++;
				if (incr % 10 == 0) {
					g.setColor(new Color(g.getColor().getRed(), g.getColor().getGreen(),
							g.getColor().getBlue() - width / 10));
				}

				l.drawFromXY(g, g.getColor(), row, col, width, height, fill);
			}
		}
	}

	@Override
	public String name() {
		return "blue lightning";
	}
}
