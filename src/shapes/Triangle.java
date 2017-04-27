package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			int x = xSelection();
			int y = ySelection();
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			int[] xInts = new int[] { x, x + getWidth() / 2, x + getWidth(), x };
			int[] yInts = new int[] { y + getHeight(), y, y + getHeight(), y + getHeight() };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 4);
			} else {
				g.drawPolygon(xInts, yInts, 4);
			}
		}
	}

	@Override
	public String name() {
		return "triangle";
	}
	
	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}
}
