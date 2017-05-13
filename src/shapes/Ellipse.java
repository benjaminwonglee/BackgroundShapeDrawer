package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ellipse extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		int[] xys = new int[2];
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
				g.fillOval(x, y, getWidth(), getHeight());
				gr.fillOval(x, y, getWidth(), getHeight());
			} else {
				g.drawOval(x, y, getWidth(), getHeight());
				gr.drawOval(x, y, getWidth(), getHeight());
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
