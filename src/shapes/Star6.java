package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Star6 extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		g.setColor(c);
		gr.setColor(c);
		for (int i = 0; i < getAmount(); i++) {
			int[] xys = setDrawVariables();
			xy.add(xys);
			int x = xys[0];
			int y = xys[1];
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			int[] xInts = new int[] { x, x + getWidth() / 3, x + getWidth() / 4, x + getWidth() / 2,
					x + getWidth() - getWidth() / 4, x + getWidth() - getWidth() / 3, x + getWidth(),
					x + getWidth() - getWidth() / 3, x + getWidth() - getWidth() / 4, x + getWidth() / 2,
					x + getWidth() / 4, x + getWidth() / 3, x };
			int[] yInts = new int[] { y + getHeight() / 2, y + getHeight() / 3, y, y + getHeight() / 4, y,
					y + getHeight() / 3, y + getHeight() / 2, y + getHeight() / 3 * 2, y + getHeight(),
					y + getHeight() / 4 * 3, y + getHeight(), y + getHeight() / 3 * 2, y + getHeight() / 2 };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 13);
				gr.fillPolygon(xInts, yInts, 13);
			} else {
				g.drawPolygon(xInts, yInts, 13);
				gr.drawPolygon(xInts, yInts, 13);
			}
		}
	}

	@Override
	public String name() {
		return "6-pointed star";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	@Override
	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height) {

	}
}
