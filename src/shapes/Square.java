package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			int x = generateRandomIntegerX();
			int y = generateRandomIntegerY();
			if (getFill()) {
				g.fillRect(x, y, getWidth(), getWidth());
			} else {
				g.drawRect(x, y, getWidth(), getWidth());
			}
		}
	}

	@Override
	public String name() {
		return "Square";
	}
}
