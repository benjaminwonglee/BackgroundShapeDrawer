package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Lightning extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			gr.setColor(c);
			int x = xSelection();
			int y = ySelection();
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			int[] xInts = new int[] { x + getWidth() / 5 * 2, x + getWidth(), x + getWidth() / 5 * 3, x + getWidth(),
					x + getWidth() / 5 * 3, x + getWidth(), x + getWidth() / 5, x + getWidth() / 5 * 2, x,
					x + getWidth() / 5 * 2, x, x + getWidth() / 5 * 2 };
			int[] yInts = new int[] { y, y, y + getHeight() / 7 * 2, y + getHeight() / 7 * 2, y + getHeight() / 7 * 4,
					y + getHeight() / 7 * 4, y + getHeight(), y + getHeight() / 7 * 5, y + getHeight() / 7 * 5,
					y + getHeight() / 7 * 3, y + getHeight() / 7 * 3, y };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 11);
				gr.fillPolygon(xInts, yInts, 11);
			} else {
				g.drawPolygon(xInts, yInts, 11);
				gr.drawPolygon(xInts, yInts, 11);
			}
		}
	}

	@Override
	public String name() {
		return "lightning";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}
}
