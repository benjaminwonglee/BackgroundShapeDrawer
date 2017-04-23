package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Hexagon extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			int x = xSelection();
			int y = ySelection();
			if (x == -1 || y == -1) {
				return;
			}
			int[] xInts = new int[] { x, x + getWidth() / 3, x + getWidth() / 3 * 2, x + getWidth(),
					x + getWidth() / 3 * 2, x + getWidth() / 3, x };
			int[] yInts = new int[] { y + getHeight() / 2, y, y, y + getHeight() / 2, y + getHeight(), y + getHeight(),
					y + getHeight() / 2 };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 7);
			} else {
				g.drawPolygon(xInts, yInts, 7);
			}
		}
	}

	@Override
	public String name() {
		return "Hexagon";
	}
}
