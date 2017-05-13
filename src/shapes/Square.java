package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends ShapeAbstract implements Shape {

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		for (int i = 0; i < getAmount(); i++) {
			g.setColor(c);
			gr.setColor(c);
			int x = xSelection();
			int y = ySelection();
			xys[0] = x;
			xys[1] = y;
			xy.add(xys);
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			if (getFill()) {
				g.fillRect(x, y, getWidth(), getWidth());
				gr.fillRect(x, y, getWidth(), getWidth());
			} else {
				g.drawRect(x, y, getWidth(), getWidth());
				gr.drawRect(x, y, getWidth(), getWidth());
			}
		}
	}

	@Override
	public String name() {
		return "square";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}
}
