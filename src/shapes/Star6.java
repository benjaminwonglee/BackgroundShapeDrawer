package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Star6 extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			int x = randomXIntegerInCanvas();
			int y = randomYIntegerInCanvas();
			int[] xInts = new int[] { x, x + getWidth() / 3, x + getWidth() / 4, x + getWidth() / 2,
					x + getWidth() - getWidth() / 4, x + getWidth() - getWidth() / 3, x + getWidth(),
					x + getWidth() - getWidth() / 3, x + getWidth() - getWidth() / 4, x + getWidth() / 2,
					x + getWidth() / 4, x + getWidth() / 3, x };
			int[] yInts = new int[] { y + getHeight() / 2, y + getHeight() / 3, y, y + getHeight() / 4, y,
					y + getHeight() / 3, y + getHeight() / 2, y + getHeight() / 3 * 2, y + getHeight(),
					y + getHeight() / 4 * 3, y + getHeight(), y + getHeight() / 3 * 2, y + getHeight() / 2 };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 13);
			} else {
				g.drawPolygon(xInts, yInts, 13);
			}
		}
	}

	@Override
	public String name() {
		return "6 Point Star";
	}
}
