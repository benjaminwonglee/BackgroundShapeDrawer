package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			int x = randomXIntegerInCanvas();
			int y = randomYIntegerInCanvas();
			if (getFill()) {
				g.fillRect(x, y, getWidth(), getHeight());
			} else {
				g.drawRect(x, y, getWidth(), getHeight());
			}
		}
	}

	@Override
	public String name() {
		return "Rectangle";
	}

}
