package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Star extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			int x = randomXIntegerInCanvas();
			int y = randomYIntegerInCanvas();
			double span = getWidth() / 6;
			int sp = (int) span;
			int[] xInts = new int[] { x, x + getWidth() / 3, x + getWidth() / 2, x + getWidth() - (getWidth() / 3),
					x + getWidth(), x + getWidth() / 4 * 3, x + getWidth() - sp, x + getWidth() / 2, x + sp - 2, x + getWidth() / 4, x };
			int[] yInts = new int[] { y + (getHeight() / 3), y + (getHeight() / 3), y, y + (getHeight() / 3),
					y + (getHeight() / 3), y + (getHeight() / 8) * 5, y + getHeight(), y + (getHeight() / 8 * 7),
					y + getHeight(), y + (getHeight() / 8) * 5, y + (getHeight() / 3) };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 11);
			} else {
				g.drawPolygon(xInts, yInts, 11);
			}
		}
	}

	@Override
	public String name() {
		return "Star";
	}
}
