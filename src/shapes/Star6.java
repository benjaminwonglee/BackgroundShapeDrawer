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
			//TODO: Fill this out
			int[] xInts = new int[] {};
			int[] yInts = new int[] {};
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 13);
			} else {
				g.drawPolygon(xInts, yInts, 13);
			}
		}
	}

	@Override
	public String name() {
		return "Star6";
	}
}
