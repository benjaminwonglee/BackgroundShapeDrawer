package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends ShapeAbstract implements Shape {

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
			if (getFill()) {
				g.fillOval(x, y, getWidth(), getHeight());
			} else {
				g.drawOval(x, y, getWidth(), getHeight());
			}
		}
	}
	
	@Override
	public String name() {
		return "ellipse";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

}
