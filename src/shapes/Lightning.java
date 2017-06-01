package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import patterns.Pattern;

public class Lightning extends ShapeAbstract implements Shape {
	public static ArrayList<int[]> xy = new ArrayList<int[]>();

	@Override
	public void drawShape(Graphics g, Graphics gr, Color c, boolean fill) {
		g.setColor(c);
		gr.setColor(c);
		Pattern p = selectPattern();
		for (int i = 0; i < getAmount(); i++) {
			int[] xys = setDrawVariables(c, p, fill);
			int x = xys[0];
			int y = xys[1];
			if (x == -1 || y == -1) {
				setDrawnAmount(i);
				setCanvasFilled(true);
				return;
			}
			xy.add(xys);
			int[] xInts = new int[] { x + getWidth() / 5 * 2, x + getWidth(), x + getWidth() / 5 * 3, x + getWidth(),
					x + getWidth() / 5 * 3, x + getWidth(), x + getWidth() / 5, x + getWidth() / 5 * 2, x,
					x + getWidth() / 5 * 2, x, x + getWidth() / 5 * 2 };
			int[] yInts = new int[] { y, y, y + getHeight() / 7 * 2, y + getHeight() / 7 * 2, y + getHeight() / 7 * 4,
					y + getHeight() / 7 * 4, y + getHeight(), y + getHeight() / 7 * 5, y + getHeight() / 7 * 5,
					y + getHeight() / 7 * 3, y + getHeight() / 7 * 3, y + getHeight() / 7 * 3 };
			if (fill) {
				g.fillPolygon(xInts, yInts, 11);
				gr.fillPolygon(xInts, yInts, 11);
			} else {
				g.drawPolygon(xInts, yInts, 11);
				gr.drawPolygon(xInts, yInts, 11);
			}
		}
	}

	@Override
	public String name() {
		return "lightning";
	}

	@Override
	public boolean getCanvasFilled() {
		return canvasFilled;
	}

	@Override
	public void drawFromXY(Graphics g, Color c, int x, int y, int width, int height, boolean fill) {
		g.setColor(c);
		int[] xInts = new int[] { x + getWidth() / 5 * 2, x + getWidth(), x + getWidth() / 5 * 3, x + getWidth(),
				x + getWidth() / 5 * 3, x + getWidth(), x + getWidth() / 5, x + getWidth() / 5 * 2, x,
				x + getWidth() / 5 * 2, x, x + getWidth() / 5 * 2 };
		int[] yInts = new int[] { y, y, y + getHeight() / 7 * 2, y + getHeight() / 7 * 2, y + getHeight() / 7 * 4,
				y + getHeight() / 7 * 4, y + getHeight(), y + getHeight() / 7 * 5, y + getHeight() / 7 * 5,
				y + getHeight() / 7 * 3, y + getHeight() / 7 * 3, y + getHeight() / 7 * 3 };
		if (fill) {
			g.fillPolygon(xInts, yInts, 11);
		} else {
			g.drawPolygon(xInts, yInts, 11);
		}

	}

	@Override
	public ArrayList<int[]> getXY() {
		return xy;
	}

}
