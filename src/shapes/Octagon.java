package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Octagon extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			int x = xSelection();
			int y = ySelection();
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				return;
			}
			int[] xInts = new int[] { x, x + getWidth() / 4, x + getWidth() / 4 * 3, x + getWidth(), x + getWidth(),
					x + getWidth() / 4 * 3, x + getWidth() / 4, x, x };
			int[] yInts = new int[] { y + (getHeight() / 4), y, y, y + (getHeight() / 4), y + getHeight() / 4 * 3,
					y + getHeight(), y + getHeight(), y + (getHeight() / 4) * 3, y + (getHeight() / 4) };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 9);
			} else {
				g.drawPolygon(xInts, yInts, 9);
			}
		}
	}
	
	@Override
	public String name() {
		return "octagon";
	}

}
