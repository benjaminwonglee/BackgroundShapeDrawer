package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			int x = xSelection();
			int y = ySelection();
			if (x == -1 || y == -1) {
				return;
			}
			if (getFill()) {
				g.fillOval(x, y, getWidth(), getWidth());
			} else {
				g.drawOval(x, y, getWidth(), getWidth());
			}
		}
	}

	@Override
	public String name() {
		return "Circle";
	}

}
