package themes;

import java.awt.Color;
import java.awt.Graphics;

import main.ShapePanel;
import shapes.Lightning;

public class BlueLightning implements ColorTheme {

	@Override
	public void setTheme(Graphics g, ShapePanel sp) {
		Lightning l = new Lightning();
		Color blue = new Color(0, 20, 120);
		int width = 40;
		int height = 60;
		g.fillRect(0, 0, sp.getBounds().width, sp.getBounds().height);
		for (int row = 0; row < sp.getBounds().width; row += (width * 2)) {
			for (int col = 0; col < sp.getBounds().height; col += (height * 2)) {
				l.drawFromXY(g, blue, row, col, width, height);
			}
		}
	}

	@Override
	public String name() {
		return "blue lightning";
	}
}
