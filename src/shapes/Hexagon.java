package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Hexagon extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c) {
		g.setColor(c);
		gr.setColor(c);
		for (int i = 0; i < getAmount(); i++) {
			int[] xys = setDrawVariables(c);
			xy.add(xys);
			int x = xys[0];
			int y = xys[1];
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			int[] xInts = new int[] { x, x + getWidth() / 3, x + getWidth() / 3 * 2, x + getWidth(),
					x + getWidth() / 3 * 2, x + getWidth() / 3, x };
			int[] yInts = new int[] { y + getHeight() / 2, y, y, y + getHeight() / 2, y + getHeight(), y + getHeight(),
					y + getHeight() / 2 };
			if (getFill()) {
				g.fillPolygon(xInts, yInts, 7);
				gr.fillPolygon(xInts, yInts, 7);
			} else {
				g.drawPolygon(xInts, yInts, 7);
				gr.drawPolygon(xInts, yInts, 7);
			}
		}
	}

	@Override
	public String name() {
		return "hexagon";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	@Override
	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height) {
		g.setColor(c);
		int[] xInts = new int[] { x, x + width / 3, x + width / 3 * 2, x + width, x + width / 3 * 2, x + width / 3, x };
		int[] yInts = new int[] { y + getHeight() / 2, y, y, y + getHeight() / 2, y + getHeight(), y + getHeight(),
				y + getHeight() / 2 };
		if (getFill()) {
			g.fillPolygon(xInts, yInts, 7);
		} else {
			g.drawPolygon(xInts, yInts, 7);
		}
	}

	@Override
	public ArrayList<int[]> getXY() {
		return xy;
	}
}
